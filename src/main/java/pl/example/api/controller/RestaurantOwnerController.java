package pl.example.api.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.example.api.dto.*;
import pl.example.api.dto.mapper.*;
import pl.example.business.*;
import pl.example.domain.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantOwner")

public class RestaurantOwnerController {
    private final RestaurantService restaurantService;
    private final MealMenuService mealMenuService;
    private final StreetService streetService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    private final RestaurantOwnerService restaurantOwnerService;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final RestaurantMapper restaurantMapper;
    @Autowired
    private final MealMapper mealMapper;
    @Autowired
    private final StreetMapper streetMapper;
    @Autowired
    private final RestaurantOwnerMapper restaurantOwnerMapper;

    @Autowired
    private final ClientMapper clientMapper;

    @GetMapping("/{restaurantOwnerId}")
    public String restaurantList(
            @PathVariable(value = "restaurantOwnerId", required = false) String restaurantOwnerId,
            Model model) {
        var restaurants = restaurantService.findByRestaurantOwnerId(Integer.valueOf(restaurantOwnerId)).stream()
                .map(restaurantMapper::map)
                .collect(Collectors.toList());

        Map<Integer, List<MealDTO>> mealsMap = new HashMap<>();
        Map<Integer, List<StreetDTO>> streetsMap = new HashMap<>();

        for (RestaurantDTO restaurant : restaurants) {
            Integer restaurantId = restaurant.getRestaurantId();
            var meals = mealMenuService.findAllBySelectedRestaurant(restaurantId).stream()
                    .map(mealMapper::map)
                    .collect(Collectors.toList());
            mealsMap.put(restaurantId, meals);

            var streets = streetService.findAllByRestaurantId(restaurantId).stream()
                    .map(streetMapper::map)
                    .collect(Collectors.toList());
            streetsMap.put(restaurantId, streets);
        }

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("mealsMap", mealsMap);
        model.addAttribute("streetsMap", streetsMap);

        return "restaurant";
    }

    @GetMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/addMeal")
    public String addMealForm(@PathVariable Integer restaurantId,
                              @PathVariable Integer restaurantOwnerId,
                              @ModelAttribute MealDTO mealDTO,
                              Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("meal", mealDTO);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("restaurantOwnerId", restaurantOwnerId);
        return "meal_new";
    }


    @PostMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/addMeal")
    public String addMealToMenu(
            @PathVariable Integer restaurantId,
            @PathVariable Integer restaurantOwnerId,
            @Valid @ModelAttribute("mealDTO") MealDTO mealDTO,
            ModelMap model
    ) {
        CategoryDTO categoryDTO = mealDTO.getCategory();
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);

        MealDTO dto = MealDTO.builder()
                .name(mealDTO.getName())
                .description(mealDTO.getDescription())
                .price(mealDTO.getPrice())
                .category(categoryDTO)
                .restaurant(restaurantDTO)
                .build();
        Meal mealToEntity = mealMapper.map(dto);

        mealMenuService.makeMealForRestaurant(mealToEntity);
        model.addAttribute("name", mealDTO.getName());
        model.addAttribute("category", categoryDTO.getCategoryId());
        model.addAttribute("description", mealDTO.getDescription());
        model.addAttribute("price", mealDTO.getPrice());

        return "redirect:/restaurantOwner/{restaurantOwnerId}";
    }

    @GetMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/deleteMeal")
    @Transactional
    public ModelAndView deleteMealByRestaurant(
            @PathVariable String restaurantId,
            @PathVariable Integer restaurantOwnerId
    ) {
        List<Meal> meals = mealMenuService.findAllBySelectedRestaurant(Integer.valueOf(restaurantId));
        List<MealDTO> mealDTOs = meals.stream().map(mealMapper::map).toList();

        Map<String, ?> preparedInfo = Map.of(
                "restaurantId", restaurantId,
                "restaurantOwnerId", restaurantOwnerId,
                "mealDTOs", mealDTOs
        );

        return new ModelAndView("meal_delete_restaurant", preparedInfo);


    }

    @DeleteMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/deleteMeal/{mealId}")
    @Transactional
    public String deleteMealByRestaurant(
            @PathVariable String restaurantId,
            @PathVariable Integer restaurantOwnerId,
            @PathVariable String mealId
    ) {

        mealMenuService.delete(Integer.valueOf(mealId));
        return String.format("redirect:/restaurantOwner/%s", restaurantOwnerId);
    }

    @GetMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/addStreet")
    public String addStreet(@PathVariable Integer restaurantId,
                            @PathVariable Integer restaurantOwnerId,
                            @ModelAttribute StreetDTO streetDTO,
                            Model model) {
        model.addAttribute("street", streetDTO);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("restaurantOwnerId", restaurantOwnerId);
        return "street_new";
    }

    @PostMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/addStreet")
    public String addStreet(
            @PathVariable Integer restaurantId,
            @PathVariable String restaurantOwnerId,
            @Valid @ModelAttribute("streetDTO") StreetDTO streetDTO,
            ModelMap model
    ) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);
        StreetDTO dto = StreetDTO.builder()
                .name(streetDTO.getName())
                .restaurant(restaurantDTO)
                .build();

        Street street = streetMapper.mapFromDTO(dto);

        streetService.save(street);

        model.addAttribute("name", streetDTO.getName());
        model.addAttribute("restaurantOwnerId", restaurantOwnerId);

        return "redirect:/restaurantOwner/%s".formatted(restaurantOwnerId);

    }


    @GetMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/deleteStreet")
    @Transactional
    public ModelAndView deleteStreetByRestaurant(
            @PathVariable String restaurantId,
            @PathVariable String restaurantOwnerId
    ) {
        List<Street> streets = streetService.findAllByRestaurantId(Integer.valueOf(restaurantId));
        List<StreetDTO> streetDTOs = streets.stream().map(streetMapper::map).toList();

        Map<String, ?> preparedInfo = Map.of(
                "restaurantId", restaurantId,
                "restaurantOwnerId", restaurantOwnerId,
                "streetDTOs", streetDTOs
        );
        return new ModelAndView("street_delete_restaurant", preparedInfo);

    }

    @DeleteMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/deleteStreet/{streetId}")
    @Transactional
    public String deleteStreetByRestaurant(
            @PathVariable String restaurantId,
            @PathVariable String restaurantOwnerId,
            @PathVariable String streetId
    ) {

        streetService.deleteById(Integer.valueOf(streetId));
        return String.format("redirect:/restaurantOwner/%s", restaurantOwnerId);
    }


    @GetMapping("/{restaurantOwnerId}/addRestaurant")
    public String addRestaurantForm(@PathVariable Integer restaurantOwnerId,
                                    @ModelAttribute RestaurantDTO restaurantDTO,
                                    Model model) {
        model.addAttribute("restaurant", restaurantDTO);

        return "restaurant_new";
    }

    @PostMapping("/{restaurantOwnerId}/addRestaurant")
    public String makeRestaurant(
            @PathVariable String restaurantOwnerId,
            @Valid @ModelAttribute("restaurantDTO") RestaurantDTO restaurantDTO,
            ModelMap model
    ) {
        RestaurantOwner restaurantOwner = restaurantOwnerService.findById(Integer.valueOf(restaurantOwnerId));
        RestaurantOwnerDTO restaurantOwnerDTO = restaurantOwnerMapper.mapToDTO(restaurantOwner);
        RestaurantDTO dto = RestaurantDTO.builder()
                .name(restaurantDTO.getName())
                .restaurantOwner(restaurantOwnerDTO)
                .build();

        Restaurant restaurant = restaurantMapper.mapFromDto(dto);
        restaurantService.saveRestaurant(restaurant);
        model.addAttribute("name", restaurantDTO.getName());
        model.addAttribute("restaurantOwner", restaurantOwnerDTO.getName());
        return "redirect:/restaurantOwner/%s".formatted(restaurantOwnerId);
    }

    @GetMapping("/{restaurantOwnerId}/showOrders/{restaurantId}")
    public ModelAndView showOrdersForRestaurant(
            @PathVariable(value = "restaurantId") String restaurantId,
            @PathVariable(value = "restaurantOwnerId") String restaurantOwnerId
    ) {

        Map<String, ?> model = preparedInputData(Integer.valueOf(restaurantId));

        return new ModelAndView("restaurant_orders", model);

    }


    private Map<String, ?> preparedInputData(Integer id) {
        var orders = orderService.findByRestaurantId(id).stream().map(orderMapper::mapToDTO)
                .toList();

        return Map.of("ordersDTOs", orders, "restaurantId", id);
    }

    @GetMapping("/{restaurantOwnerId}/order/{orderNumber}/orderDetails")
    public String showOrderDetails(
            @PathVariable("restaurantOwnerId") String restaurantOwnerId,
            @PathVariable("orderNumber") String orderNumber,
            @ModelAttribute("orderDTO") OrderDTO orderDTO,
            Model model) {

        orderNumber = orderDTO.getOrderNumber();
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        OrderDTO dto = orderMapper.mapToDTO(order);
        model.addAttribute("order", dto);

        return "order_details_for_rest";
    }
}
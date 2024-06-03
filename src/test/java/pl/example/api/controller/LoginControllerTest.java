package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.example.api.dto.UserDTO;
import pl.example.api.dto.mapper.UserMapper;
import pl.example.business.UserService;
import pl.example.domain.User;

import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = LoginController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class LoginControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private Authentication authentication;
    private MockMvc mockMvc;

    @Test
    void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testHomePage() {

    }

    @Test
    void testShowLoginPageWithRegistration() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

    }

    @Test
    void testRegisterUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Roman");
        userDTO.setRole("RESTAURANT_OWNER");
        userDTO.setEmail("roman@gmail.com");
        userDTO.setPhone("+48 000 111 222");
        User user = userMapper.map(userDTO);
        Mockito.when(userService.registerNewUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .flashAttr("userDTO", userDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));

    }

    @Test
    void testLogout() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);


        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));

        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);

    }


    @Test
    void testHomeClientRole() throws Exception {
        GrantedAuthority authority = new SimpleGrantedAuthority("CLIENT");
        Collection<? extends GrantedAuthority> authorities = List.of(authority);

        Mockito.doReturn(authorities).when(authentication).getAuthorities();

        mockMvc.perform(get("/home").principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("client_homepage"));
    }

    @Test
    void testHomeRestaurantOwnerRole() throws Exception {
        GrantedAuthority authority = new SimpleGrantedAuthority("RESTAURANT_OWNER");
        Collection<? extends GrantedAuthority> authorities = List.of(authority);

        Mockito.doReturn(authorities).when(authentication).getAuthorities();

        mockMvc.perform(get("/home").principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurantOwner_homepage"));
    }
}
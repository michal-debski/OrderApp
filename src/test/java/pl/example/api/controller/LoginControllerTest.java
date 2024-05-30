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
import org.springframework.security.core.Authentication;
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

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = LoginController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class LoginControllerTest {
    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;
    private MockMvc mockMvc;

    @Test
    void testShowLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    void testHomePage() {

    }

    @Test
    void testShowLoginPageWithRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));

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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/login"));

    }

    @Test
    void testLogout() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);


        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));

        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);

    }

}
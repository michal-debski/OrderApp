package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.UserDAO;
import pl.example.domain.User;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserDAO userDAO;

    @Transactional
    public User registerNewUser(User user) {
        log.info("Trying to create new user");
        User created = userDAO.registerNewUser(user);
        log.info("User created: {}", created);
        return created;
    }

}
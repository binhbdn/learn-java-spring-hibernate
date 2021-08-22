package vn.hvcg.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.hvcg.security.domain.User;
import vn.hvcg.security.domain.UserRepository;

import java.util.Random;

@Controller
public class LoginController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login-error")
    @ResponseBody
    public String loginError() {
        return "Login failed";
    }

    @GetMapping("/forbidden")
    @ResponseBody
    public String perm() {
        return "Permission denied";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseBody
    public String register(User userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return "Not registered, username is taken";
        }
        int idx = (int)(System.currentTimeMillis() / 10000) + new Random().nextInt(1000);
        userDTO.setId(idx);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRoles("ROLE_User");
        userRepository.save(userDTO);
        return "checked !";
    }
}

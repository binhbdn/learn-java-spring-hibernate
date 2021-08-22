package vn.hvcg;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class TestClass {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }
}

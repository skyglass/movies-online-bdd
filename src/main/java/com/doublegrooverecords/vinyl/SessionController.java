package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {
    @PostMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "ignored";
    }
}

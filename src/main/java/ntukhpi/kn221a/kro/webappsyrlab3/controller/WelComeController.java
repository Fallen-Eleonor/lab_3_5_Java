package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelComeController {
    @GetMapping("/")
    public String greeting() {
        return "startpage";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
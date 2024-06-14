package neotech.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomePageController {
    
    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }
    
}

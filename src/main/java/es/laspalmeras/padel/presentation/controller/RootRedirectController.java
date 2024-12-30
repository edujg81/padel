package es.laspalmeras.padel.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
public class RootRedirectController {

    @GetMapping
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}

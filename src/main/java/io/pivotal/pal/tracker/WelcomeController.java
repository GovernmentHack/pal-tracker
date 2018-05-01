package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private String message;

    public WelcomeController(@Value("${WELCOME_MESSAGE}") String message) {
        this.message = message;
    }

    @GetMapping("/")
    public String sayHello() {
        return message;
    }

    @GetMapping("/parrot/{something}")
    public String say(@PathVariable("something") String something) {
        return something;
    }

}

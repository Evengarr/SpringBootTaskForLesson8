package ru.geekbrains.springboottaskforlesson8.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.springboottaskforlesson8.models.entities.User;
import ru.geekbrains.springboottaskforlesson8.services.UserService;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DaoController {
    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }


}

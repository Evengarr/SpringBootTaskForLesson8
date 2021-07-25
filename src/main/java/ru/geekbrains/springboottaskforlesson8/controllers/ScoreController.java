package ru.geekbrains.springboottaskforlesson8.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.springboottaskforlesson8.models.entities.User;
import ru.geekbrains.springboottaskforlesson8.services.UserService;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("score")
public class ScoreController {
    private final UserService userService;

    @GetMapping("/inc")
    public String scoreInc(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
        Long originalValue = user.getScore();
        Long inc = user.getScore();
        inc++;
        user.setScore(inc);
        userService.saveOrUpdate(user);
        return "начальный счет " + originalValue + ", теперь счет равен " + inc;
    }

    @GetMapping("/dec")
    public String scoreDec(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
        Long originalValue = user.getScore();
        Long dec = user.getScore();
        dec--;
        user.setScore(dec);
        userService.saveOrUpdate(user);
        return "начальный счет " + originalValue + ", теперь счет равен " + dec;
    }

    @GetMapping("/current")
    public String currentScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
        return "Ваш текущий счет: " + user.getScore();
    }

    @GetMapping("/get/{id}")
    public String scoreById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("unable to find user by username: " + id));
        return "Счет пользователя с Id " + id + ": " + user.getScore();
    }
}

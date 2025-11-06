package ru.MarkMoskvitin.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import ru.MarkMoskvitin.NauJava.entity.User;
import ru.MarkMoskvitin.NauJava.repo.UserRepository;
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/register")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/register")
        public String adduser(User user, Model model){
            try
            {
                userRepository.save(user);
                return "redirect:/login";
            }
        catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}

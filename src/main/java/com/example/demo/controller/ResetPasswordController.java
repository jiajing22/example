package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/eDonor")
public class ResetPasswordController {

    @Autowired
    private DonorService donorService;

    @GetMapping("/reset-password")
    public String showChangePasswordPage(@RequestParam(value="token") String token, Model model) throws Exception {
        String checkToken = donorService.matchToken(token);
        if (checkToken == null) {
            model.addAttribute("errorMessage", "Invalid Token");
            return null;
        }
        model.addAttribute("token", token);
        return "reset-password";
    }

//    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
//    public String resetPassword(@RequestParam(value="token") String token, Model model) throws Exception {
//        String checkToken = donorService.matchToken(token);
//        System.out.println(checkToken);
//        if (checkToken == null) {
//            return "invalid-token";
//        }
//        model.addAttribute("token", token);
//        return "reset-password";
//    }
}
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

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
            model.addAttribute("invalid", "true");
        } else{
            model.addAttribute("token", token);
            model.addAttribute("invalid", "false");
            model.addAttribute("email", checkToken);
        }
        return "reset-password";
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token, Model model) throws ExecutionException, InterruptedException {
        // Validate the token and update user's verification status
        boolean isValidToken = donorService.validateToken(token);
        if (isValidToken) {
            boolean isUpdated = donorService.updateVerificationStatus(token);
            if (isUpdated) {
                model.addAttribute("status", "success");
            } else {
                model.addAttribute("status", "error");
            }
        } else {
            model.addAttribute("status", "invalid");
        }

        return "verification";
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

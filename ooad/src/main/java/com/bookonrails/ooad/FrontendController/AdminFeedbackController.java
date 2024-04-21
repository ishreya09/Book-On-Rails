package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookonrails.ooad.Service.FeedbackService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/admin/feedback")
    public String feedback(Model model, HttpServletRequest request) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        return "admin/feedback_view";
    }
}
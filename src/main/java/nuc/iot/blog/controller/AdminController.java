package nuc.iot.blog.controller;

import nuc.iot.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {


    @GetMapping("/admin/{action}")
    public String admin(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model
                        ) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDTO);
        } else if ("replies".equals(action)) {
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}

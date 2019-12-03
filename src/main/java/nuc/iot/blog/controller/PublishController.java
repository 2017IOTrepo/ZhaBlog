package nuc.iot.blog.controller;

import nuc.iot.blog.mapper.BlogMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private BlogMapper blogMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);

        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空!");
            return "publish";
        }

        if (content == null || content.equals("")) {
            model.addAttribute("error", "内容不能为空!");
            return "publish";
        }

        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空!");
            return "publish";
        }

        String value = CookieUtils.getValue(request.getCookies(), "user_id");
        if (value != null) {
            Long time = System.currentTimeMillis();
            Blog blog = new Blog(
                    title,
                    content,
                    time,
                    time,
                    Integer.valueOf(value),
                    tag
            );
            blogMapper.create(blog);
            model.addAttribute("error", null);
        } else {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }


        return "redirect:/";
    }
}

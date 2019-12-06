package nuc.iot.blog.controller;

import nuc.iot.blog.mapper.BlogMapper;
import nuc.iot.blog.model.Blog;
import nuc.iot.blog.model.User;
import nuc.iot.blog.service.BlogService;
import nuc.iot.blog.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogService blogService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

//    @GetMapping("/publish/{id}")
//    public String edit(@PathVariable(name = "id") Long id,
//                       Model model) {
//        QuestionDTO question = questionService.getById(id);
//        model.addAttribute("title", question.getTitle());
//        model.addAttribute("description", question.getDescription());
//        model.addAttribute("tag", question.getTag());
//        model.addAttribute("id", question.getId());
//        model.addAttribute("tags", TagCache.get());
//        return "publish";
//    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", content);
        model.addAttribute("tag", tag);

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(content)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Long now = System.currentTimeMillis();
        Blog blog = new Blog(
                title,
                content,
                now,
                now,
                user.getId(),
                tag
        );
        blog.setId(id);

        blogService.createOrUpdate(blog);
        return "redirect:/";
    }
}

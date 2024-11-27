package smweb.chillana.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import smweb.chillana.Service.PostInteractionService;


@Controller
public class PostsController {
    private PostInteractionService postInteractionService;
    @Autowired
    public PostsController(PostInteractionService postInteractionService) {
        this.postInteractionService = postInteractionService;
    }

    @PostMapping("/{postId}/like")
    public String like(@PathVariable int postId, HttpSession session) {
     String username = (String) session.getAttribute("username");
     postInteractionService.LikePost(postId, username);
        System.out.println("like have been saved");
        return "redirect:/homeFeed/"+username;
    }

    @PostMapping("/{postId}/comment")

    public String comment(@PathVariable int postId, HttpSession session, @RequestParam String comment) {
        String username = (String) session.getAttribute("username");
        postInteractionService.CommentPost(postId, username, comment);
        System.out.println("comment have been saved");
        return "redirect:/homeFeed/"+username;
    }
}

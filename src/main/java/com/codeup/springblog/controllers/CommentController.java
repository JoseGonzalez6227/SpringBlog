package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Comment;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.AdRepository;
import com.codeup.springblog.repos.CommentRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    private final AdRepository adDao;
    private final UserRepository userDao;
    private final CommentRepository commentDao;

    public CommentController(AdRepository adRepo, UserRepository userRepo, CommentRepository commentRepo){
        this.userDao = userRepo;
        this.adDao = adRepo;
        this.commentDao = commentRepo;

    }

    @GetMapping("/comments/create")
    public String addComment(Model model) {
        model.addAttribute("comment", new Comment());
        return "ads/";
    }

    @PostMapping("/comments/create")
    public String createComment(
            @ModelAttribute Comment commentText
    ) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User usersDB = userDao.findOne(userSession.getId());
        commentText.setUsers(usersDB);
        Comment savedComment = commentDao.save(commentText);
        return "redirect:/ads/" + savedComment.getId();
    }

    @GetMapping("/comments/show")
    public String show(@RequestParam(name = "ads-id") long term, Model viewModel) {
        List<Comment> comments = commentDao.findCommentsByAd(term);
        viewModel.addAttribute("comments", comments);
        return "ads/index";
    }
}
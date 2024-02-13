package com.example.demo.controller;

import com.example.demo.model.entity.Review;
import com.example.demo.model.response.ReviewResDto;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//@Controller
//@RequestMapping("/reviews")
//public class ReviewController {
//
//    private ReviewService reviewService;
//
//    public ReviewController(ReviewService reviewService){
//        this.reviewService = reviewService;
//    }
//    @PostMapping("/delete/{review_id}")
////    @GetMapping("/{review_id}/delete")
//    public String deleteReview(@PathVariable UUID reviewId) {
//        try {
//            System.out.println("Received Review ID: " + reviewId);
//            reviewService.deleteReview(reviewId);
//            return "redirect:/reviews";
//        } catch (EntityNotFoundException e) {
//            return "redirect:/error";
//        }
//    }
//
//    @GetMapping("/new")
//    public ModelAndView showReviewForm() {
//        ModelAndView modelAndView = new ModelAndView("/review");
//        modelAndView.addObject("review", new Review());
//        return modelAndView;
//    }
//
//    @PostMapping("/save")
//    public String saveReview(@ModelAttribute Review review) {
//        reviewService.saveReview(review);
//        return "redirect:/reviews";
//    }
//
//
//
//    @GetMapping
//    public ModelAndView listReviews() {
//        List<Review> reviews = reviewService.getAllReviews();
//
//        ModelAndView modelAndView = new ModelAndView("/reviews");
//        modelAndView.addObject("reviews", reviews);
//        return modelAndView;
//    }
////
////    @GetMapping("/{id}")
////    public String viewReview(@PathVariable UUID id, Model model) {
////        Review review = reviewService.getReviewById(id);
////        model.addAttribute("review", review);
////        return "reviews/view";
////    }
////
////    @GetMapping("/{id}/edit")
////    public String editReview(@PathVariable UUID id, Model model) {
////        Review review = reviewService.getReviewById(id);
////        model.addAttribute("review", review);
////        return "reviews/form";
////    }
////
//
//}


@Controller
//@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews")
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "reviews";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

//    @PostMapping("/review/save")
//        public String saveReview(@ModelAttribute ReviewReqDto review) {
//            reviewService.save(review);
//        // return "home";
//        return "redirect:/home";
//    }
@PostMapping("review/save")
//    public String saveReview(@ModelAttribute ReviewReqDto review) {
//        reviewService.save(review);
//        return "redirect:/reviews";
//    }
public String saveReview(@ModelAttribute Review review) {
    reviewService.save(review);
    return "redirect:/review";
}


@GetMapping("/review")
public String reviewPage(Model model) {
    ReviewResDto review = new ReviewResDto();
    model.addAttribute("review", review);
    return "review";
}
//    @GetMapping("/{id}")
//    public String getReviewById(Model model, @PathVariable UUID id) {
//        model.addAttribute("review", reviewService.findById(id));
//        return "review";
//    }

//    @PutMapping("/{id}")
//    public String updateReview(@Valid @PathVariable UUID id, @RequestBody ReviewReqDto review){
//        reviewService.update(id, review);
//        return "review";
//    }

//    @DeleteMapping("/{id}")
//    public String deleteReview(@PathVariable UUID id){
//        reviewService.delete(id);
//        return "review";
//    }


}

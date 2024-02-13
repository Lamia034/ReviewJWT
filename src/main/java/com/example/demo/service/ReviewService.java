package com.example.demo.service;

import com.example.demo.model.entity.Review;
import com.example.demo.model.response.ReviewResDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
//    List<Review> getAllReviews();
List<ReviewResDto> findAll();
//    Review saveReview(Review review);
// ReviewResDto save(ReviewReqDto reviewReqDto);
    void delete(UUID id);
    Review save(Review review);
}

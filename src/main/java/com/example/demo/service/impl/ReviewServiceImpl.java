package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.Review;
import com.example.demo.model.response.ReviewResDto;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ReviewRepository reviewRepository;
    @Override
//    public ReviewResDto save(ReviewReqDto reviewReqDto) {
//        Review reviewEntity = modelMapper.map(reviewReqDto, Review.class);
//        reviewEntity.setLastModifiedDate(LocalDateTime.now());
//
//        Review savedReviewEntity = reviewRepository.save(reviewEntity);
//        return modelMapper.map(savedReviewEntity, ReviewResDto.class);
//    }
    public Review save(Review review) {

        review.setLastModifiedDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }
    @Override
    public List<ReviewResDto> findAll() {
        return reviewRepository.findAll().stream().map(review -> modelMapper.map(review, ReviewResDto.class)).collect(Collectors.toList());
    }

//    @Override
//    public ReviewResDto findById(UUID id) {
//        return reviewRepository.findById(id)
//                .map(review -> modelMapper.map(review, ReviewResDto.class)).orElseThrow(() -> new ResourceNotFoundException("Review Not found with this: " + id));
//    }
//
//    @Override
//    public ReviewResDto update(UUID id, ReviewReqDto reviewReqDto) {
//        Review existingReview = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review Not found with this: " + id));
//        BeanUtils.copyProperties(reviewReqDto, existingReview);
//        existingReview.setId(id);
//        return modelMapper.map(reviewRepository.save(existingReview), ReviewResDto.class);
//    }

    @Override
    public void delete(UUID id) {
        reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review Not found with this: " + id));
        reviewRepository.deleteById(id);
    }
}

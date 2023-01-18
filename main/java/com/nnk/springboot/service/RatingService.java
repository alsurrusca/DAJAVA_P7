package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {return ratingRepository.findAll();}

    public Rating save(Rating ratingList){
        return ratingRepository.save(ratingList);
    }

    public Optional<Rating> findById(Integer id){return ratingRepository.findById(id);}

    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }
}

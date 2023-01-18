package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RatingController {
    // TODO: Inject Rating service OK

    @Autowired
    private RatingService ratingService;

    Logger log = LoggerFactory.getLogger(RatingController.class);


    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model OK
        List<Rating> findAllRating = ratingService.findAll();
        log.info("Find All rating SUCCESS");
        model.addAttribute("ratingList",findAllRating);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {

        log.info("Add Rating SUCCESS");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list OK
        if(!result.hasErrors()){
            ratingService.save(rating);
            model.addAttribute("ratingList", ratingService.findAll());
            log.info("Save rating in DB SUCCESS");
            return "redirect:/rating/list";
        }
        log.error("Validate rating in DB FAILED");
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form OK
        Rating rating = ratingService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid rating id: " + id));
        model.addAttribute("rating",rating );
        log.info("Find Rating by ID SUCCESS");
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list OK
        if(result.hasErrors()){
            log.error("Update Rating FAILES");
            return "redirect:/rating/update";
        }
        rating.setId(id);
        ratingService.save(rating);
        model.addAttribute("ratingList", ratingService.findAll());
        log.info("Update Rating SUCCESS");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        Rating rating = ratingService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ratind id: " + id));
        ratingService.delete(rating);
        model.addAttribute("ratingList", rating);
        log.info("Delete rating SUCCESS");
        return "redirect:/rating/list";
    }
}

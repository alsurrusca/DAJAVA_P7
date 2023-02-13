package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RatingControllerTest {


    @Autowired
    private RatingController ratingController;

    @MockBean
    private RatingService ratingService;

    private final Rating rating = new Rating();
    private final List<Rating> ratingList = Arrays.asList(rating);
    private final Model model = new ExtendedModelMap();
    private final Principal user = new Principal() {

        @Override
        public String getName() {
            return "testUser";
        }
    };

    @Before
    public void setUp() {
        when(ratingService.findAll()).thenReturn(ratingList);
        when(ratingService.findById(1)).thenReturn(Optional.of(rating));
    }

    @Test
    public void addRatingForm() {
        String view = ratingController.addRatingForm(rating);
        assertEquals("rating/add", view);
    }

    @Test
    public void validateRating() {
        BindingResult result = new BeanPropertyBindingResult(rating, "rating");
        String view = ratingController.validate(rating, result, model);
        assertEquals("redirect:/rating/list", view);
        verify(ratingService, times(1)).save(rating);
        assertEquals(ratingList, model.asMap().get("ratingList"));
    }

    @Test
    public void showUpdateForm() {
        String view = ratingController.showUpdateForm(1, model);
        assertEquals("rating/update", view);
        assertEquals(rating, model.asMap().get("rating"));
    }

    @Test
    public void updateRating() {
        BindingResult result = new BeanPropertyBindingResult(rating, "rating");
        String view = ratingController.updateRating(1, rating, result, model);
        assertEquals("redirect:/rating/list", view);
        verify(ratingService, times(1)).save(rating);
        assertEquals(ratingList, model.asMap().get("ratingList"));
    }

    @Test
    public void deleteRating() {
        String view = ratingController.deleteRating(1, model);
        assertEquals("redirect:/rating/list", view);
        verify(ratingService, times(1)).delete(rating);
    }
}

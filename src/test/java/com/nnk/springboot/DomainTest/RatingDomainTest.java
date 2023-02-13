package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.Rating;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class RatingDomainTest {

    @Test
    public void RatinTest(){
        Rating rating = new Rating();

        rating.setId(1);
        rating.setMoodysRating("moodysRating");
        rating.setFitchRating("FitchRating");
        rating.setOrderNumber(2);
        rating.setSandPRating("SandPRating");

        assertEquals(java.util.Optional.of(1), java.util.Optional.of(rating.getId()));
        assertEquals("moodysRating", rating.getMoodysRating());
        assertEquals("FitchRating", rating.getFitchRating());
        assertEquals("SandPRating", rating.getSandPRating());
        assertEquals(java.util.Optional.of(2),java.util.Optional.of( rating.getOrderNumber()));


    }
}

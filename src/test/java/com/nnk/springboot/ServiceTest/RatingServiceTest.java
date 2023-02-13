package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class RatingServiceTest {


    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;

    @Before
    public void setUp() {
        ratingRepository.deleteAll();
    }

    @After
    public void tearDown() {
        ratingRepository.deleteAll();
    }

    @Test
    public void findAllTest(){

        List<Rating> ratings = ratingService.findAll();
        assertNotNull(ratings);

    }

    @Test
    public void saveTest(){
        Rating rating = new Rating();
        rating.setFitchRating("test rating list");
        rating.setMoodysRating("type");
        rating = ratingService.save(rating);
        assertNotNull(rating.getId());
    }


    @Test
    public void getByIdTest(){

        Rating rating = new Rating();
        rating.setFitchRating("test rating list");
        rating.setMoodysRating("type");
        rating = ratingService.save(rating);

        // When
        Optional<Rating> ratingServiceById = ratingService.findById(rating.getId());

        // Then
        assertTrue(ratingServiceById.isPresent());
        assertEquals(rating.getFitchRating(), ratingServiceById.get().getFitchRating());

    }

    @Test
    public void testDelete() {
        // Given
        Rating rating = new Rating();
        rating.setFitchRating("test rating list");
        rating.setMoodysRating("type");
        rating = ratingService.save(rating);
        // When
        ratingService.delete(rating);

        // Then
        Optional<Rating> ratingServiceById = ratingService.findById(rating.getId());
        assertFalse(ratingServiceById.isPresent());
    }
}


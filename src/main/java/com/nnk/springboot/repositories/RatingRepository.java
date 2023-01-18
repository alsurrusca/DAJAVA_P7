package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< Updated upstream

=======
import org.springframework.stereotype.Repository;

@Repository
>>>>>>> Stashed changes
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}

package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
<<<<<<< Updated upstream
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======

import java.util.Optional;
>>>>>>> Stashed changes


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

<<<<<<< Updated upstream
=======
    Optional<User> findByUsername(String username);

>>>>>>> Stashed changes
}

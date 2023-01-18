package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< Updated upstream

import java.util.List;
import java.util.Optional;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

   Optional<BidList> findById(Integer integer);
=======
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

   Optional<BidList> findById(Integer id);
>>>>>>> Stashed changes

}

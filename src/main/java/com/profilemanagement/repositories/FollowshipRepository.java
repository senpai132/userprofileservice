package com.profilemanagement.repositories;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profilemanagement.model.Followship;

public interface FollowshipRepository extends JpaRepository<Followship, Integer>{
    List<Followship> findByInitiator(String initiator);
    List<Followship> findByTarget(String target);
    //Optional<Followship> findById(Integer id);
    Followship findByInitiatorAndTarget(String initiator, String target);
}

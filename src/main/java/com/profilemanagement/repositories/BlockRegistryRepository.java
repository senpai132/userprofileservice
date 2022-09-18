package com.profilemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profilemanagement.model.BlockRegistry;

public interface BlockRegistryRepository extends JpaRepository<BlockRegistry, Integer>{
    List<BlockRegistry> findByInitiator(String initiator);
    List<BlockRegistry> findByTarget(String target);
    //Optional<Followship> findById(Integer id);
    BlockRegistry findByInitiatorAndTarget(String initiator, String target);
}

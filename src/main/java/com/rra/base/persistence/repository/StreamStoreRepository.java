package com.rra.base.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rra.base.persistence.StreamStore;

@Repository
public interface StreamStoreRepository extends JpaRepository<StreamStore, Long>{

}

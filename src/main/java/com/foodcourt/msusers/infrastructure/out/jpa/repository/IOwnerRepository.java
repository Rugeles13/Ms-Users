package com.foodcourt.msusers.infrastructure.out.jpa.repository;

import com.foodcourt.msusers.infrastructure.out.jpa.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOwnerRepository extends JpaRepository<OwnerEntity, Long> {
    Optional<OwnerEntity> findByEmail (String email);
}

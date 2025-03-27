package swe304.swe304_1.repository;

import swe304.swe304_1.entity.Patent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentRepository extends JpaRepository<Patent, Integer> {}

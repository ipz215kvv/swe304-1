package swe304.swe304_1.repository;

import swe304.swe304_1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {}

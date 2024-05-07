package com.example.mysqljpa;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}

package com.apaslabs.store.serviceproduct.repository;

import com.apaslabs.store.serviceproduct.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

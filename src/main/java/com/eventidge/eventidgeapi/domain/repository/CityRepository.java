package com.eventidge.eventidgeapi.domain.repository;


import com.eventidge.eventidgeapi.domain.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

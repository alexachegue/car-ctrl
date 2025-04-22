package com.csc340group6.carctrl.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT * FROM car c WHERE c.user_id = :userId", nativeQuery = true)
    List<Car> findCarsByUser(@Param("userId") int userId);

}

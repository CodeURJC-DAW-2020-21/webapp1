package es.codeurjc.friends_padel_tour.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.Double;

import java.util.Optional;

public interface DoubleRepository extends JpaRepository<Double,Long>{
    
        Optional<Double> findById(Long id);
    
        Optional<Double> findByUser(Double user);
    
        Optional<Double> findByUserName(Double name);
    }
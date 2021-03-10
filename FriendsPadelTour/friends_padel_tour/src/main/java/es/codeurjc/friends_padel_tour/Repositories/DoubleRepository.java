package es.codeurjc.friends_padel_tour.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;

import java.util.Optional;

public interface DoubleRepository extends JpaRepository<DoubleOfPlayers,Long>{
    
        Optional<DoubleOfPlayers> findById(Long id);
    
    }
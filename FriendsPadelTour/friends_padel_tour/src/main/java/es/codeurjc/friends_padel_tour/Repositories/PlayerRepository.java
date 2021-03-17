package es.codeurjc.friends_padel_tour.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.Player;

public interface PlayerRepository extends JpaRepository<Player,Long>{

    Optional<Player> findByEmail(String email);

    Optional<Player> findByUsername(String username);
    
}

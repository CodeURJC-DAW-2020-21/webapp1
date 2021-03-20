package es.codeurjc.friends_padel_tour.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.friends_padel_tour.Entities.Player;

public interface PlayerRepository extends JpaRepository<Player,Long>{

    Optional<Player> findByEmail(String email);

    Optional<Player> findByUsername(String username);

    @Query("select p from Player p where p.division = ?1 order by score desc")
        Optional<List<Player>> findTop10(int divisionOfPlayer);
    
}

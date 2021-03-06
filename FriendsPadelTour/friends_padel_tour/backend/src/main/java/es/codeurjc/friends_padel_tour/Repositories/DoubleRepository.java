package es.codeurjc.friends_padel_tour.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;

import java.util.List;
import java.util.Optional;

public interface DoubleRepository extends JpaRepository<DoubleOfPlayers,Long>{
    
        Optional<DoubleOfPlayers> findById(Long id);

        //Select a player who belongs to different doubles
        @Query("select d from DoubleOfPlayers d where d.player1.username = ?1 or d.player2.username = ?1")
        Optional<List<DoubleOfPlayers>> findDoublesOf(String name);

        @Query("select d from DoubleOfPlayers d where (d.player1.username = ?1 and d.player2.username = ?2)or(d.player2.username = ?1 and d.player1.username = ?2)")
        Optional<DoubleOfPlayers> findDouble(String player1, String player2);
    
    }
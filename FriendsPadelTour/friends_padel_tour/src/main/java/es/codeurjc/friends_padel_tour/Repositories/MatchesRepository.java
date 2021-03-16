package es.codeurjc.friends_padel_tour.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.PadelMatch;

public interface MatchesRepository extends JpaRepository<PadelMatch,Long> {

    Optional<List<PadelMatch>> findByDivision(int num);
    
}

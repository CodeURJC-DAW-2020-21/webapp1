package es.codeurjc.friends_padel_tour.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



import es.codeurjc.friends_padel_tour.Entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    
    @Modifying
    @Query("update tournament t set t.firstWinnngCouple = ?1 where t.id = id")
    int  setFirstWinningCouple(String couple);

    
    @Query("update tournament t set t.SecondWinningCouple = ?1 where t.id = id")
    int setSecondWinningCouple(String couple);

    Optional<List<Tournament>> findByIdAndAccepted(Long id, boolean accepted);

}

package es.codeurjc.friends_padel_tour.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



import es.codeurjc.friends_padel_tour.Entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    
    @Modifying
    @Query("update Tournament t set t.firstWinnngCouple = ?1 where t.id = ?2")
    int  setFirstWinningCouple(String couple, Long id);

    @Modifying
    @Query("update Tournament t set t.secondWinningCouple = ?1 where t.id = ?2")
    int setSecondWinningCouple(String couple, Long id);

    Optional<List<Tournament>> findByIdAndAccepted(Long id, boolean accepted);

}

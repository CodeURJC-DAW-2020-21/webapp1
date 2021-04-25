package es.codeurjc.friends_padel_tour.Repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    
    //Query that permits to update the first place of the Tournament
    @Modifying
    @Query("update Tournament t set t.firstWinnngCouple = ?1 where t.id = ?2")
    int  setFirstWinningCouple(DoubleOfPlayers couple, Long id);

    //Query that permits to update the first place of the Tournament
    @Modifying
    @Query("update Tournament t set t.secondWinningCouple = ?1 where t.id = ?2")
    int setSecondWinningCouple(DoubleOfPlayers couple, Long id);


    Optional<Tournament> findById(Long id);

    Optional<Page<Tournament>> findByIdAndAccepted(Long id, boolean accepted, Pageable pageable);
    Optional<List<Tournament>> findByAccepted(boolean accepted);

    
    Page<Tournament> findAllByAccepted(boolean b, Pageable p);

}

package es.codeurjc.friends_padel_tour.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    
    @Modifying
    @Query("update tournament t set t.first_winning_couple = ? where t.id = id")
    void  setFirstWinnngCouple(DoubleOfPlayers couple);

    @Modifying
    @Query("update tournament t set t.second_winning_couple = ? where t.id = id")
    void setSecondWinnngCouple(DoubleOfPlayers couple);

    @Query("select t from tournament t where t.bussiness_id = ? and accepted = true")
    Optional<List<Tournament>> getAccepted(Long id);

    @Query("select t from tournament t where t.bussiness_id = ? and accepted = false")
    Optional<List<Tournament>> getNotAccepted(Long id);

}

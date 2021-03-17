package es.codeurjc.friends_padel_tour.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    
}

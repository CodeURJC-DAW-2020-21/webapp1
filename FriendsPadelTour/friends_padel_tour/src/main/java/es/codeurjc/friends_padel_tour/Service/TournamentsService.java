package es.codeurjc.friends_padel_tour.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Repositories.TournamentRepository;

@Service
public class TournamentsService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament findById(long id) {
        Optional<Tournament> tournamentInDB = tournamentRepository.findById(id);
        if(tournamentInDB.isPresent()) return tournamentInDB.get();
        else return null;
    }
    
}

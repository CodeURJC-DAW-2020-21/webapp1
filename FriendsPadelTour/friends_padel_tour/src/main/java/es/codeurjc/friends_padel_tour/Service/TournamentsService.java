package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
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

    public Tournament deleteById(long id){
        Optional<Tournament> tournamentInDB = tournamentRepository.findById(id);
        if(tournamentInDB.isPresent()){
            tournamentRepository.deleteById(id);
            return tournamentInDB.get();
        } 
        else return null;
    }
    
    public void save(Tournament tournament){
        tournamentRepository.save(tournament);    
    }

    public Page<Tournament> getAccepted(Bussiness bussiness, Pageable pageable){
        Optional<Page<Tournament>> tournamentsInDB = tournamentRepository.findByIdAndAccepted(bussiness.getId(),true, pageable);

        if(tournamentsInDB.isPresent()){
            return tournamentsInDB.get();
        } 
        else return null;
    }

    public Page<Tournament> getNotAccepted(Bussiness bussiness, Pageable pageable){
        Optional<Page<Tournament>> tournamentsInDB = tournamentRepository.findByIdAndAccepted(bussiness.getId(),false, pageable);
        if(tournamentsInDB.isPresent()){
            return tournamentsInDB.get();
        } 
        else return null;
    }

    public void setFirstWinnngCouple(DoubleOfPlayers couple, Long id){
        tournamentRepository.setFirstWinningCouple(couple, id);
    }

    public void setSecondWinnngCouple(DoubleOfPlayers couple, Long id){
        tournamentRepository.setSecondWinningCouple(couple, id);
    }
}

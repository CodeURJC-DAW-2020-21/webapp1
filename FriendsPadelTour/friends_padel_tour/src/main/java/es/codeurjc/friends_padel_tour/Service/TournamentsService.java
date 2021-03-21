package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Tournament> getAccepted(Bussiness bussiness){
        Optional<List<Tournament>> tournamentsInDB = tournamentRepository.findByIdAndAccepted(bussiness.getId(),true);
        if(tournamentsInDB.isPresent()){
            return tournamentsInDB.get();
        } 
        else return null;
    }

    public List<Tournament> getNotAccepted(Bussiness bussiness){
        Optional<List<Tournament>> tournamentsInDB = tournamentRepository.findByIdAndAccepted(bussiness.getId(),false);
        if(tournamentsInDB.isPresent()){
            return tournamentsInDB.get();
        } 
        else return null;
    }

    public void setFirstWinnngCouple(DoubleOfPlayers couple){
        tournamentRepository.setFirstWinningCouple(couple.getPlayer1().getName() +" " +couple.getPlayer2().getName());
    }

    public void setSecondWinnngCouple(DoubleOfPlayers couple){
        tournamentRepository.setSecondWinningCouple(couple.getPlayer1().getName() +" " + couple.getPlayer2().getName());
    }
}

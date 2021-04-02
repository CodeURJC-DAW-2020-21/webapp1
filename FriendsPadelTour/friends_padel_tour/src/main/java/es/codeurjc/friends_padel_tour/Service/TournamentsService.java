package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Tournament;

import es.codeurjc.friends_padel_tour.Repositories.TournamentRepository;

@Service
public class TournamentsService {

    //Autowired section
    @Autowired
    private TournamentRepository tournamentRepository;

    //Find a tournament
    public Tournament findById(long id) {
        Optional<Tournament> tournamentInDB = tournamentRepository.findById(id);
        if(tournamentInDB.isPresent()) return tournamentInDB.get();
        else return null;
    }

    //Delete a tournament
    public Tournament deleteById(long id){
        Optional<Tournament> tournamentInDB = tournamentRepository.findById(id);
        if(tournamentInDB.isPresent()){
            tournamentRepository.deleteById(id);
            return tournamentInDB.get();
        } 
        else return null;
    }

    //Method that takes the pending tournament
    public List<Tournament> getPending(){
        Optional<List<Tournament>> tournamentsInDB = tournamentRepository.findByAccepted(false);
        if(tournamentsInDB.isPresent()){
            return tournamentsInDB.get();
        } 
        else return null;
    }
    
    //Save a tournament
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

    public List<Tournament> getAllAccepted(){
        Optional<List<Tournament>> tournamentsInDB = tournamentRepository.findByAccepted(true);
        if(tournamentsInDB.isPresent()){
            return tournamentsInDB.get();
        } 
        else return null;
    }

    //Set First winning couple 
    public void setFirstWinnngCouple(DoubleOfPlayers couple, Long id){
        tournamentRepository.setFirstWinningCouple(couple, id);
    }

    //Set second winning couple
    public void setSecondWinnngCouple(DoubleOfPlayers couple, Long id){
        tournamentRepository.setSecondWinningCouple(couple, id);
    }

    public void uptdate(Tournament tournamentToUpdate) {
        tournamentRepository.save(tournamentToUpdate);
    }
}

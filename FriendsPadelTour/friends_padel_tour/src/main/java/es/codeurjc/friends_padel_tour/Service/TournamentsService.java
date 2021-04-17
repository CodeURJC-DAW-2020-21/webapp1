package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Repositories.TournamentRepository;

@Service
public class TournamentsService {

    //Autowired section
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private DoubleService doubleService;

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

    public Page<Tournament> getPageTournaments(int pageNumber, int pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
		return tournamentRepository.findAllByAccepted(true,p);
	}

    public boolean joinTournament(long tournamentId, String doubleSelect, String userName){
        DoubleOfPlayers doubleWhoJoin = doubleService.findDouble(userName,doubleSelect);
        Tournament tournamentToJoin = this.findById(tournamentId);

        if (doubleWhoJoin != null && tournamentToJoin != null){
            if (!tournamentToJoin.isFinished()){
                doubleWhoJoin.getTournaments().add(tournamentToJoin);
                tournamentToJoin.getPlayers().add(doubleWhoJoin);
                tournamentToJoin.setRegisteredCouples(tournamentToJoin.getRegisteredCouples()+1);
                if(tournamentToJoin.getRegisteredCouples()==tournamentToJoin.getMaxCouples()){
                    tournamentToJoin.setFull(true);
                }
                doubleService.update(doubleWhoJoin);
                this.uptdate(tournamentToJoin);
                return true;
            }
            return false;
        }else{
            return false;
        }

    }

    public void setTournamentWinners(long tournamentId, int doubleSelect){
        Tournament tournament = this.findById(tournamentId);
        DoubleOfPlayers winners = tournament.getPlayers().get(doubleSelect-1);
        if (tournament != null){
            tournament.setFirstWinnngCouple(winners);
            tournament.setFinished(true);
            this.uptdate(tournament);
        }
    }

}

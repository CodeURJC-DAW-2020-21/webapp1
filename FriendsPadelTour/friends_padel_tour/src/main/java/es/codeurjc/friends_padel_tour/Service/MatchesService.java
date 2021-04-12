package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Repositories.MatchesRepository;

@Service
public class MatchesService {

    //Autowired section
    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private PlayersService playersService;
    @Autowired
    private DoubleService doubleService;

    //Find Matches by Division
    public List<PadelMatch> findByDivision(int num) {
        Optional<List<PadelMatch>> matches = matchesRepository.findByDivision(num);
        if(matches.isPresent()) return matches.get();
        else return null;
    }

    //Save a Match
    public void save(PadelMatch newMatch) {
        matchesRepository.save(newMatch);
    }

    //Find a padel macth by its id
    public PadelMatch findById(long id) {
        Optional<PadelMatch> matchInDB = matchesRepository.findById(id);
        if(matchInDB.isPresent()) return matchInDB.get();
        return null;
    }

    public void deleteMatch(long id) {
        matchesRepository.deleteById(id);     
    }

    public void joinLonely(PadelMatch matchToJoin, Player loggedPlayer, int slot) {
        if(slot==1||slot==2){
            if(matchToJoin.getDouble1()==null){
                DoubleOfPlayers newDoubleOfPlayers1 = new DoubleOfPlayers();
                doubleService.saveDouble(newDoubleOfPlayers1);
                matchToJoin.setDouble1(newDoubleOfPlayers1);
            }
        }
        if(slot==3||slot==4){
            if(matchToJoin.getDouble2()==null){
                DoubleOfPlayers newDoubleOfPlayers2 = new DoubleOfPlayers();
                doubleService.saveDouble(newDoubleOfPlayers2);
                matchToJoin.setDouble2(newDoubleOfPlayers2);
            }
        }
        if(slot==1){
            matchToJoin.getDouble1().setPlayer1(loggedPlayer);
            loggedPlayer.getDoubles1().add(matchToJoin.getDouble1());
            doubleService.saveDouble(matchToJoin.getDouble1());
        }
        if(slot==2){
            matchToJoin.getDouble1().setPlayer2(loggedPlayer);
            loggedPlayer.getDoubles2().add(matchToJoin.getDouble1());
            doubleService.saveDouble(matchToJoin.getDouble1());
        }
        if(slot==3){
            matchToJoin.getDouble2().setPlayer1(loggedPlayer);
            loggedPlayer.getDoubles1().add(matchToJoin.getDouble2());
            doubleService.saveDouble(matchToJoin.getDouble2());
        }
        if(slot==4){
            matchToJoin.getDouble2().setPlayer2(loggedPlayer);
            loggedPlayer.getDoubles2().add(matchToJoin.getDouble2());
            doubleService.saveDouble(matchToJoin.getDouble2());
        }
        matchToJoin.setnPlayers(matchToJoin.getnPlayers()+1);

        if(!matchToJoin.getCreator().getUsername().equals(loggedPlayer.getUsername())){
            loggedPlayer.getPendingMatches().add(matchToJoin);
        }

        playersService.updatePlayer(loggedPlayer);
        this.save(matchToJoin);
    }


    public void joinDouble(PadelMatch matchToJoin, Player loggedPlayer, Player playerDouble, int slot) {
        DoubleOfPlayers doubleWhoJoins = doubleService.findDouble(playerDouble.getUsername(), loggedPlayer.getUsername());
        if(slot ==1){
            matchToJoin.setDouble1(doubleWhoJoins);
        }else{
            matchToJoin.setDouble2(doubleWhoJoins);
        }
        matchToJoin.setnPlayers(matchToJoin.getnPlayers()+2);

        doubleService.saveDouble(doubleWhoJoins);

        if(!matchToJoin.getCreator().getUsername().equals(loggedPlayer.getUsername())){
            loggedPlayer.getPendingMatches().add(matchToJoin);
        }
        if(!matchToJoin.getCreator().getUsername().equals(playerDouble.getUsername())){
            playerDouble.getPendingMatches().add(matchToJoin);
        }

        playersService.updatePlayer(playerDouble);
        playersService.updatePlayer(loggedPlayer);
        
        this.save(matchToJoin);
    }

    public void createFriendlyMatch(PadelMatch newMatch, Player creator) {
        creator.getCreatedMatches().add(newMatch);
        this.save(newMatch);
        playersService.updatePlayer(creator);
    }
    
}

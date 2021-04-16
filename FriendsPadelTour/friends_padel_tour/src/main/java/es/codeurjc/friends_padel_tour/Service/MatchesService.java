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
        PadelMatch matchToDelete = this.findById(id);
        Player p1,p2,p3,p4;
        p1 = p2 = p3 = p4 = null;
        if(matchToDelete!=null){
            DoubleOfPlayers d1= matchToDelete.getDouble1();
            if(d1!=null){
                p1 = d1.getPlayer1();
                p2 = d1.getPlayer2();
                p1.getCreatedMatches().remove(matchToDelete);
                p1.getPendingMatches().remove(matchToDelete);
                p2.getCreatedMatches().remove(matchToDelete);
                p2.getPendingMatches().remove(matchToDelete);
            }
            DoubleOfPlayers d2= matchToDelete.getDouble1();
            if(d2!=null){
                p3 = d2.getPlayer1();
                p4 = d2.getPlayer2();
                p3.getCreatedMatches().remove(matchToDelete);
                p3.getPendingMatches().remove(matchToDelete);
                p4.getCreatedMatches().remove(matchToDelete);
                p4.getPendingMatches().remove(matchToDelete);
            }
        }
        if(p1!=null) playersService.updatePlayer(p1);
        if(p2!=null) playersService.updatePlayer(p2);
        if(p3!=null) playersService.updatePlayer(p3);
        if(p4!=null) playersService.updatePlayer(p4);
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

    public void selectMatchWinner(PadelMatch match, int doubleWinnerSlot, Player creator) {
        DoubleOfPlayers doubleWinner;
        DoubleOfPlayers doubleLoss;
        if(doubleWinnerSlot==1){
            doubleWinner = match.getDouble1();
            doubleLoss = match.getDouble2();
        }else{
            doubleWinner = match.getDouble2();
            doubleLoss = match.getDouble1();
        }
        match.setDoubleWinner(doubleWinner);
        match.setHasWinner(true);
        creator.getCreatedMatches().remove(match);
        
        Player winner1 = doubleWinner.getPlayer1();
        Player winner2 = doubleWinner.getPlayer2();
        Player loser1 = doubleLoss.getPlayer1();
        Player loser2 = doubleLoss.getPlayer2();
        
        winner1.getPlayedMatches().add(match);
        winner2.getPlayedMatches().add(match);
        loser1.getPlayedMatches().add(match);
        loser2.getPlayedMatches().add(match);
        winner1.getPendingMatches().remove(match);
        winner2.getPendingMatches().remove(match);
        loser1.getPendingMatches().remove(match);
        loser2.getPendingMatches().remove(match);
        creator.getCreatedMatches().remove(match);

        winner1.setScore(winner1.getScore()+3);
        winner2.setScore(winner2.getScore()+3);
        loser1.setScore(loser1.getScore()-3);
        loser2.setScore(loser2.getScore()-3);

        winner1.setMathcesWon(winner1.getMathcesWon()+1);
        winner2.setMathcesWon(winner2.getMathcesWon()+1);
        loser1.setMatchesLost(loser1.getMatchesLost()+1);
        loser2.setMatchesLost(loser2.getMatchesLost()+1);

        winner1.setMathesPlayed(winner1.getMathesPlayed()+1);
        winner2.setMathesPlayed(winner2.getMathesPlayed()+1);
        loser1.setMathesPlayed(loser1.getMathesPlayed()+1);
        loser2.setMathesPlayed(loser2.getMathesPlayed()+1);

        playersService.updatePlayer(winner1);
        playersService.updatePlayer(winner2);
        playersService.updatePlayer(loser1);
        playersService.updatePlayer(loser2);
        playersService.updatePlayer(creator);
        this.save(match);
        doubleService.saveDouble(doubleWinner);
        doubleService.saveDouble(doubleLoss);
    }
    
}

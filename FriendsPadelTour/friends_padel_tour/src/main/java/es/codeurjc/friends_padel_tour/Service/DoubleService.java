package es.codeurjc.friends_padel_tour.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Repositories.DoubleRepository;

@Service
public class DoubleService {

    //Autowired section
    @Autowired
    private DoubleRepository doubleRepository;

    //Save a double players
    public void saveDouble(DoubleOfPlayers newDouble){
        doubleRepository.save(newDouble);  
    }

    //Find Doubles
    public List<Player> findDoublesOf(String name) {
        Optional<List<DoubleOfPlayers>> doublesInDB = doubleRepository.findDoublesOf(name);
        if(doublesInDB.isPresent()){
            ArrayList<Player> doubles = new ArrayList<>();
            for (DoubleOfPlayers d : doublesInDB.get()) {
                if(d.getPlayer1().getUsername().equals(name)){
                    doubles.add(d.getPlayer2());
                }else{
                    doubles.add(d.getPlayer1());
                }
            }
            return doubles;
        }
        else return null;
    }

    public DoubleOfPlayers findDouble(String player1, String player2) {
        Optional<DoubleOfPlayers> doubleInDB = doubleRepository.findDouble(player1,player2);
        if(doubleInDB.isPresent()){
            return doubleInDB.get();
        }
        return null;
    }
    
}

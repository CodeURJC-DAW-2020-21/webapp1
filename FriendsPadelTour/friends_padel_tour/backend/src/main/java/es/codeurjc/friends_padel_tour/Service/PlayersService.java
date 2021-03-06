package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Repositories.PlayerRepository;

@Service
public class PlayersService {
    
    @Autowired
    private PlayerRepository playerRepository;
    //Save a player having in mind his role
    public boolean savePlayer(Player newPlayer){
        playerRepository.save(newPlayer);
        return true;
    }


    //Find a player by his Username
    public Player findByUsername(String attribute) {
        Optional<Player> playerInDB = playerRepository.findByUsername(attribute);
        if(playerInDB.isPresent()) return playerInDB.get();
        return null;
    }

    //Find a player by his id
    public Player findById(long id) {
        Optional<Player> playerInDB = playerRepository.findById(id);
        if(playerInDB.isPresent()) return playerInDB.get();
        return null;
    }

    //Update a player
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    //Find the top 10 players in the repository
    public List<Player> findTOP10(int division){
        Optional<List<Player>> top10players = playerRepository.findTop10(division);
        if(top10players.isPresent()) return top10players.get();
        return null;  
    }

    //Find all players
    public List<Player> findAll() {
        return playerRepository.findAll();
    }
        
}

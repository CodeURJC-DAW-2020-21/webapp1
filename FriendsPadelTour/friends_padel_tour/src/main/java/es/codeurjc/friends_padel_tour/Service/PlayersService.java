package es.codeurjc.friends_padel_tour.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;
import es.codeurjc.friends_padel_tour.Repositories.PlayerRepository;

@Service
public class PlayersService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BussinessRepository bussinessRepository;

    public boolean savePlayer(Player newPlayer){
        Optional<Player> playerInDB = playerRepository.findByEmail(newPlayer.getEmail());
        Optional<Bussiness> bussinessInDB = bussinessRepository.findByEmail(newPlayer.getEmail());
        if(playerInDB.isPresent()) 
            return false;
        if(bussinessInDB.isPresent())
            return false;
        playerInDB = playerRepository.findByUsername(newPlayer.getName());
        if(playerInDB.isPresent()) 
            return false;
        playerRepository.save(newPlayer);
        return true;
    }

    public boolean uploadImage(Player player,MultipartFile imageFile){
        return false;
    }

    public Player getPlayer(String email) {
        Optional<Player> playerInDB = playerRepository.findByEmail(email);
        if(playerInDB.isPresent())
            return playerInDB.get();
        return null;
    }

    public Player findByUsername(String attribute) {
        Optional<Player> playerInDB = playerRepository.findByUsername(attribute);
        if(playerInDB.isPresent()) return playerInDB.get();
        return null;
    }

    public Player findById(long id) {
        Optional<Player> playerInDB = playerRepository.findById(id);
        if(playerInDB.isPresent()) return playerInDB.get();
        return null;
    }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }


        
}

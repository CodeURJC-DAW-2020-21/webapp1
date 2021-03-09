package es.codeurjc.friends_padel_tour.Service;

import java.util.Optional;

import org.aspectj.weaver.NewConstructorTypeMunger;
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

    public boolean savePlayer(String name, String surname, String email, String password, String location){
        Optional<Player> playerInDB = playerRepository.findByEmail(email);
        Optional<Bussiness> bussinessInDB = bussinessRepository.findByEmail(email);
        if(playerInDB.isPresent()) 
            return false;
        if(bussinessInDB.isPresent())
            return false;
        playerInDB = playerRepository.findByName(name);
        if(playerInDB.isPresent()) 
            return false;
        Player newPlayer = new Player(name,surname,email,password,location);
        playerRepository.save(newPlayer);
        return true;
    }

    public boolean uploadImage(Player player,MultipartFile imageFile){
        return false;
    }
        
}

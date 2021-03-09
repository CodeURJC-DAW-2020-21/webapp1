package es.codeurjc.friends_padel_tour.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Repositories.PlayerRepository;

@Service
public class PlayersService {

    @Autowired
    private PlayerRepository playerRepository;

    public boolean savePlayer(String name, String surname, String email, String password, String location){
        //TO-DO
        return false;
    }

    public boolean uploadImage(Player player,MultipartFile imageFile){
        return false;
    }
        
}

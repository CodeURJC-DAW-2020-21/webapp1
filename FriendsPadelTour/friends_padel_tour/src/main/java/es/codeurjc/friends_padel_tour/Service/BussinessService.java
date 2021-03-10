package es.codeurjc.friends_padel_tour.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;
import es.codeurjc.friends_padel_tour.Repositories.PlayerRepository;

@Service
public class BussinessService {

    @Autowired
    private BussinessRepository bussinessRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public boolean saveBussiness(String bussinessName, String userName, String userSurname, String email, String password, String location, String adress){
        Optional<Player> playerInDB = playerRepository.findByEmail(email);
        Optional<Bussiness> bussinessInDB = bussinessRepository.findByEmail(email);
        if(playerInDB.isPresent()) 
            return false;
        if(bussinessInDB.isPresent())
            return false;
        bussinessInDB = bussinessRepository.findByBussinessName(bussinessName);
        if(bussinessInDB.isPresent())
            return false;
        Bussiness newBussiness = new Bussiness(bussinessName,userName,userSurname,location,email,adress);
        bussinessRepository.save(newBussiness);
        return true;
    }
    
}

package es.codeurjc.friends_padel_tour.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;
import es.codeurjc.friends_padel_tour.Repositories.PlayerRepository;


@Service
public class BussinessService {

    //Autowired Section
    @Autowired
    private UserService userService;

    @Autowired
    private BussinessRepository bussinessRepository;

    @Autowired
    private PlayerRepository playerRepository;

    //Update bussiness
    public void updateBussiness(Bussiness bussiness) {
        bussinessRepository.save(bussiness);
    }

    //Save bussiness
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
        User newUser = userService.saveUser(userName, password,"BUSSINESS");
        Bussiness newBussiness = new Bussiness(bussinessName,userName,userSurname,location,email,adress,newUser);
        bussinessRepository.save(newBussiness);
        return true;
    }

    //Find bussiness by id
    public Bussiness findById(long id) {
        Optional<Bussiness> BussinessInDB = bussinessRepository.findById(id);
        if(BussinessInDB.isPresent()) return BussinessInDB.get();
        else return null;
    }

    //Get bussiness
    public Bussiness getBussiness(String email) {
        Optional<Bussiness> bussinessInDB = bussinessRepository.findByEmail(email);
        if(bussinessInDB.isPresent()) return bussinessInDB.get();
        return null;
    }

    //Find a bussiness by username
    public Bussiness findByUsername(String name) {
        Optional<Bussiness> bussinesInDB = bussinessRepository.findByUsername(name);
        if(bussinesInDB.isPresent()) return bussinesInDB.get();
        return null;
    }
    
}

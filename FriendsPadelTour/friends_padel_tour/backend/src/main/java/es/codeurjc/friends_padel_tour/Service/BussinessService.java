package es.codeurjc.friends_padel_tour.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;


@Service
public class BussinessService {

    //Autowired Section
    @Autowired
    private UserService userService;

    @Autowired
    private BussinessRepository bussinessRepository;

    //Update bussiness
    public void updateBussiness(Bussiness bussiness) {
        bussinessRepository.save(bussiness);
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

    public void saveBussiness(Bussiness loggedBussiness) {
        User businessUser =userService.saveUser(loggedBussiness.getUsername(), loggedBussiness.getPassword(), "BUSSINESS");
        loggedBussiness.setUser(businessUser);
        bussinessRepository.save(loggedBussiness);
    }
    
}

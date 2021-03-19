package es.codeurjc.friends_padel_tour.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Repositories.DoubleRepository;

@Service
public class DoubleService {
    @Autowired
    private DoubleRepository doubleRepository;

    public void saveDouble(DoubleOfPlayers newDouble){
        doubleRepository.save(newDouble);  
    }

    public List<DoubleOfPlayers> findDoublesOf(String name) {
        Optional<List<DoubleOfPlayers>> doublesInDB = doubleRepository.findDoublesOf(name);
        if(doublesInDB.isPresent()) return doublesInDB.get();
        else return null;
    }
    
}

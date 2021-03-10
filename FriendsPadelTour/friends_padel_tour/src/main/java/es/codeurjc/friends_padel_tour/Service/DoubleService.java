package es.codeurjc.friends_padel_tour.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Repositories.DoubleRepository;

@Service
public class DoubleService {
    @Autowired
    private DoubleRepository doubleRepository;

    public boolean saveDouble(){
        doubleRepository.save(new DoubleOfPlayers());
        return false;   
    }
    
}

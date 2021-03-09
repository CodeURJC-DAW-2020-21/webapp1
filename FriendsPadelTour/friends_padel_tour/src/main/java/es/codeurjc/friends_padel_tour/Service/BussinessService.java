package es.codeurjc.friends_padel_tour.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;

@Service
public class BussinessService {
    @Autowired
    private BussinessRepository bussinessRepository;

    public boolean saveBussiness(){
        return false;
    }
    
}

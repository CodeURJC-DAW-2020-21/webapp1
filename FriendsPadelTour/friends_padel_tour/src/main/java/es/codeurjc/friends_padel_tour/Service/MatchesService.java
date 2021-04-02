package es.codeurjc.friends_padel_tour.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Repositories.MatchesRepository;

@Service
public class MatchesService {

    //Autowired section
    @Autowired
    private MatchesRepository matchesRepository;

    //Find Matches by Division
    public List<PadelMatch> findByDivision(int num) {
        Optional<List<PadelMatch>> matches = matchesRepository.findByDivision(num);
        if(matches.isPresent()) return matches.get();
        else return null;
    }

    //Save a Match
    public void save(PadelMatch newMatch) {
        matchesRepository.save(newMatch);
    }

    //Find a padel macth by its id
    public PadelMatch findById(long id) {
        Optional<PadelMatch> matchInDB = matchesRepository.findById(id);
        if(matchInDB.isPresent()) return matchInDB.get();
        return null;
    }

    public void deleteMatch(long id) {
        matchesRepository.deleteById(id);     
    }
    
}

package es.codeurjc.friends_padel_tour.Controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/matches")
public class ApiMatchController {
    @Autowired
    private MatchesService matchesService;
    @Autowired
    private PlayersService playersService;

    @GetMapping(value="/team/{num}")
    public ResponseEntity<List<PadelMatch>> getfriendlyMatchesByDivision(@PathVariable int num) {
        List<PadelMatch> mathces = matchesService.findByDivision(num);
        return ResponseEntity.ok(mathces);
    }

    @GetMapping(value="/team/{num}/ranking")
    public ResponseEntity<List<Player>> getRankingOfDivision(@PathVariable int num) {
        if(num<1 || num>6){
            return ResponseEntity.notFound().build();
        }
        List<Player> topPlayers = playersService.findTOP10(num);
        return ResponseEntity.ok(topPlayers);
    }
    
    
    
}

package es.codeurjc.friends_padel_tour.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;

import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;



@RestController
@RequestMapping("/api/matches")
public class ApiMatchController {

    @Autowired
    private MatchesService matchesService;
    @Autowired
    private PlayersService playersService;

    @GetMapping(value="/{num}")
    public ResponseEntity<List<PadelMatch>> getfriendlyMatchesByDivision(@PathVariable int num) {
        List<PadelMatch> mathces = matchesService.findByDivision(num);
        return ResponseEntity.ok(mathces);
    }

    @GetMapping(value="/{num}/rankings")
    public ResponseEntity<List<Player>> getRankingOfDivision(@PathVariable int num) {
        if(num<1 || num>6){
            return ResponseEntity.notFound().build();
        }
        List<Player> topPlayers = playersService.findTOP10(num);
        return ResponseEntity.ok(topPlayers);
    }

    @PostMapping(value="/")
    public ResponseEntity<PadelMatch> createFriendlyMatch(@RequestBody PadelMatch newMatch,@RequestBody Player creator) {
        matchesService.createFriendlyMatch(newMatch, creator);;

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newMatch.getId()).toUri();

        return ResponseEntity.created(location).body(newMatch);
    }

    @PutMapping(value="/{id}/{slot}")
    public ResponseEntity<PadelMatch> joinMatchInDouble(@PathVariable long id, @RequestBody Player player1, @RequestBody Player player2, @PathVariable int slot) {
        PadelMatch matchToJoin = matchesService.findById(id);
        if(matchToJoin == null){
            return ResponseEntity.notFound().build();
        }
        matchesService.joinDouble(matchToJoin, player1, player2, slot);
        return ResponseEntity.ok(matchToJoin);
    }

    @PutMapping(value="/{id}/{slot}")
    public ResponseEntity<PadelMatch> joinMatchLonely(@PathVariable long id, @RequestBody Player playerWhoJoin, @PathVariable int slot) {
        PadelMatch matchToJoin = matchesService.findById(id);
        if(matchToJoin == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(500).build();
    }
    
}
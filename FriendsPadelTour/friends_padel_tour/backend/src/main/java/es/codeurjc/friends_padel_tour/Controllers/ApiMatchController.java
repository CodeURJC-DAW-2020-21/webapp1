package es.codeurjc.friends_padel_tour.Controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.MatchesService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;




@RestController
@RequestMapping("/api/matches")
public class ApiMatchController {

    @Autowired
    private MatchesService matchesService;
    @Autowired
    private PlayersService playersService;

    @GetMapping(value="/division/{num}")
    public ResponseEntity<List<PadelMatch>> getfriendlyMatchesByDivision(@PathVariable int num) {
        if(num<1 || num>6){
            return ResponseEntity.notFound().build();
        }
        List<PadelMatch> mathces = matchesService.findByDivision(num);
        return ResponseEntity.ok(mathces);
    }

    @GetMapping(value="/division/{num}/ranking")
    public ResponseEntity<List<Player>> getRankingOfDivision(@PathVariable int num) {
        if(num<1 || num>6){
            return ResponseEntity.notFound().build();
        }
        List<Player> topPlayers = playersService.findTOP10(num);
        return ResponseEntity.ok(topPlayers);
    }

    @PostMapping(value="/")
    public ResponseEntity<PadelMatch> createFriendlyMatch(@RequestBody PadelMatch newMatch,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Player creator = playersService.findByUsername(principal.getName());
        
        matchesService.createFriendlyMatch(newMatch, creator);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newMatch.getId()).toUri();

        return ResponseEntity.created(location).body(newMatch);
    }

    @PostMapping(value="/{id}/double/{slot}")
    public ResponseEntity<PadelMatch> joinMatchInDouble(@PathVariable long id,HttpServletRequest request, @RequestBody Player player2, @PathVariable int slot) {
        Principal principal = request.getUserPrincipal();
        Player player1 = playersService.findByUsername(principal.getName());
        PadelMatch matchToJoin = matchesService.findById(id);
        if(matchToJoin == null){
            return ResponseEntity.notFound().build();
        }
        matchesService.joinDouble(matchToJoin, player1, player2, slot);
        return ResponseEntity.ok(matchToJoin);
    }

    @PostMapping(value="/{id}/player/{slot}")
    public ResponseEntity<PadelMatch> joinMatchLonely(@PathVariable long id,HttpServletRequest request,  @PathVariable int slot) {
        PadelMatch matchToJoin = matchesService.findById(id);
        Principal principal = request.getUserPrincipal();
        Player playerWhoJoin = playersService.findByUsername(principal.getName());
        if(matchToJoin == null){
            return ResponseEntity.notFound().build();
        }
        matchesService.joinLonely(matchToJoin, playerWhoJoin, slot);
        return ResponseEntity.ok(matchToJoin);
    }

    @GetMapping(value="/{id}/player/{slot}")
    public ResponseEntity<Player> getMatchPlayer(@PathVariable long id, @PathVariable int slot) {
        PadelMatch matchToJoin = matchesService.findById(id);
        if(matchToJoin == null){
            return ResponseEntity.notFound().build();
        }
        Player player;
        if(slot < 1 || slot >4){
            return ResponseEntity.badRequest().build();
        }
        if(slot ==1 || slot == 2){
            if(matchToJoin.getDouble1()==null){
                return ResponseEntity.notFound().build();
            }
        }
        if(slot == 3 || slot == 4){
            if(matchToJoin.getDouble2()==null){
                return ResponseEntity.notFound().build();
            }
        }
        if(slot == 1){
            player = matchToJoin.getDouble1().getPlayer1();
        }else
            if(slot == 2){
                player = matchToJoin.getDouble1().getPlayer2();
            }else
            if(slot == 3){
                player = matchToJoin.getDouble2().getPlayer1();
            }else{
                player = matchToJoin.getDouble2().getPlayer2();
            }
        if(player == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    @GetMapping(value="/{id}/double/{slot}")
    public ResponseEntity<DoubleOfPlayers> getMatchDouble(@PathVariable long id, @PathVariable int slot) {
        PadelMatch matchToJoin = matchesService.findById(id);
        if(matchToJoin == null){
            return ResponseEntity.notFound().build();
        }
        DoubleOfPlayers d;
        if(slot != 1 && slot != 2){
            return ResponseEntity.badRequest().build();
        }
        if(slot == 1){
            d = matchToJoin.getDouble1();
        }else
            d = matchToJoin.getDouble2();
        if(d == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PadelMatch> deleteAFriendlyMatch(@PathVariable long id){
        PadelMatch matchToDelete = matchesService.findById(id);
        if(matchToDelete==null){
            return ResponseEntity.notFound().build();
        }
        matchesService.deleteMatch(id);
        return ResponseEntity.ok(matchToDelete);
    }

    @PutMapping(value="/{id}/{winnerSlot}")
    public ResponseEntity<PadelMatch> winAMatch(@PathVariable long id,@PathVariable int winnerSlot) {
        PadelMatch match = matchesService.findById(id);
        if(match==null){
            return ResponseEntity.notFound().build();
        }
        if(winnerSlot != 1 && winnerSlot != 2){
            return ResponseEntity.badRequest().build();
        }
        matchesService.selectMatchWinner(match, winnerSlot, match.getPlayerCreator());
        return ResponseEntity.ok(match);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<PadelMatch> getMatch(@PathVariable long id) {
        PadelMatch match = matchesService.findById(id);
        if(match==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(match);
    }
    
    @GetMapping(value="/createdBy/{username}")
    public ResponseEntity<List<PadelMatch>> getMatchesCreatedBy(@PathVariable String username) {
        Player p = playersService.findByUsername(username);
        if(p==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p.getCreatedMatches());
    }

    @GetMapping(value="/pendingMatchesOf/{username}")
    public ResponseEntity<List<PadelMatch>> getPendingMatchesOf(@PathVariable String username) {
        Player p = playersService.findByUsername(username);
        if(p==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p.getPendingMatches());
    }

    @GetMapping(value="/playedMatchesOf/{username}")
    public ResponseEntity<List<PadelMatch>> getplayedMatchesOf(@PathVariable String username) {
        Player p = playersService.findByUsername(username);
        if(p==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p.getPlayedMatches());
    }
    
}

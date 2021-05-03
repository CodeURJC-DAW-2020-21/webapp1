package es.codeurjc.friends_padel_tour.Controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;

import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/doubles")
public class ApiDoublesController {
    
    @Autowired
    private DoubleService service;
    @Autowired
    private PlayersService playerService;

    @GetMapping(value="/of/{username}")
    public ResponseEntity<List<Player>> getDoublesOf(@PathVariable String username) {
        List<Player> doubles = service.findDoublesOf(username);
        if(doubles==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doubles);
    }
    

    @GetMapping(value="/{username1}/{username2}")
    public ResponseEntity<DoubleOfPlayers> getDouble(@PathVariable String username1,@PathVariable String username2){
        DoubleOfPlayers d = service.findDouble(username1, username2);
        if(d==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d);
    }

    @PostMapping(value="/")
    public ResponseEntity<DoubleOfPlayers> makeDoubleWith(@RequestBody String doubleName,HttpServletRequest request) {
        String creator = request.getUserPrincipal().getName();
        Player userNewDouble1 = playerService.findByUsername(creator);
        Player userNewDouble2 = playerService.findByUsername(doubleName);
        
        DoubleOfPlayers newDouble = new DoubleOfPlayers();
        newDouble.setPlayer1(userNewDouble1);
        newDouble.setPlayer2(userNewDouble2);
        userNewDouble1.getDoubles1().add(newDouble);
        userNewDouble2.getDoubles2().add(newDouble);
        playerService.updatePlayer(userNewDouble1);
        playerService.updatePlayer(userNewDouble2);
        service.saveDouble(newDouble);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newDouble.getId()).toUri();
        return ResponseEntity.created(location).body(newDouble);
            
    }
    
}

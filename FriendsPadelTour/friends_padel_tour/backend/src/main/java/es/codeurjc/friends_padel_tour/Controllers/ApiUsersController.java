package es.codeurjc.friends_padel_tour.Controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.BussinessRequest;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.PlayerEditRequest;
import es.codeurjc.friends_padel_tour.Entities.PlayerRequest;
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.ImageService;
import es.codeurjc.friends_padel_tour.Service.PlayersService;
import es.codeurjc.friends_padel_tour.Service.UserService;




@RestController
@RequestMapping("/api/users")
public class ApiUsersController {
    
    @Autowired
    private PlayersService playerService;
    @Autowired
    private BussinessService bussinessService;
    @Autowired
	private ImageService imgService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/bussiness/")
    public ResponseEntity<Bussiness> signUpBussiness(@RequestBody BussinessRequest bussinessRequest){
        User newUser = bussinessRequest.getUser();
        userService.saveUser(newUser);
        Bussiness newBussiness = bussinessRequest.getBussiness();
        newBussiness.setUser(newUser);
        bussinessService.saveBussiness(newBussiness);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newBussiness.getId()).toUri();
        return ResponseEntity.created(location).body(newBussiness);
    }

    @PostMapping(value="/player/")
    public ResponseEntity<Player> signUpUser(@RequestBody PlayerRequest playerRequest){
        User newUser = playerRequest.getUser();
        userService.saveUser(newUser);
        Player newPlayer = playerRequest.getPlayer();
        newPlayer.setUser(newUser);
        playerService.savePlayer(newPlayer);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newPlayer.getId()).toUri();
        return ResponseEntity.created(location).body(newPlayer);
    }

    @GetMapping(value = "/player/{username}")
    public ResponseEntity<Player> goToOtherPlayerProfile(@PathVariable String username) {
        Player player = playerService.findByUsername(username);
        if (player != null) {
            return ResponseEntity.ok(player);
            } else {
            return ResponseEntity.notFound().build();
            }
    }

    @GetMapping(value="/bussiness/{username}")
    public ResponseEntity<Bussiness> bussinessProfile(@PathVariable String username) {
        Bussiness bussiness = bussinessService.findByUsername(username);
        if (bussiness != null) {
            return ResponseEntity.ok(bussiness);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value="/player/{id}")
    public ResponseEntity<Player> editProfile(@PathVariable long id, @RequestBody PlayerEditRequest pRequest) {
        String password = pRequest.password;
        int division = pRequest.division;
        Player newPlayer = playerService.findById(id);
        if(newPlayer == null){
            return ResponseEntity.notFound().build();
        }
        if (!password.isBlank()) {
            newPlayer.getUser().setEncodedPassword(password);
            userService.updatePasswordOf(newPlayer.getUser(), password);
            return ResponseEntity.ok(newPlayer);
        } 
        if (division != 0) {
            newPlayer.setDivision(division);
            return ResponseEntity.ok(newPlayer);
        }
        return ResponseEntity.status(500).build();       
    }

    @PutMapping(value="/bussiness/{id}")
    public ResponseEntity<Bussiness> editBussinessProfile(@PathVariable long id, @RequestBody String password) {
        Bussiness newBussiness = bussinessService.findById(id);
        if(newBussiness == null){
            return ResponseEntity.notFound().build();
        }
        if (!password.isBlank()) {
            newBussiness.setPassword(password);
            userService.updatePasswordOf(newBussiness.getUser(), password);
            return ResponseEntity.ok(newBussiness);
        } 
        return ResponseEntity.status(500).build();
    }

    @PostMapping(value="/player/{id}/image")
    public ResponseEntity<Object> updateImage(@PathVariable long id, @RequestParam MultipartFile profilePicture) throws IOException {
        Player player = playerService.findById(id);
        if (player != null) {
            URI location = fromCurrentRequest().build().toUri();
            player.setRutaImagen(location.toString());
            playerService.savePlayer(player);
            imgService.saveImage("/images", player.getId(), profilePicture);
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/user/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws  MalformedURLException{
        return this.imgService.createResponseFromImage("/images", id);
    }
    
    @PostMapping(value="/bussiness/{id}/image")
    public ResponseEntity<Object> updateBussinessImage(@PathVariable long id, @RequestParam MultipartFile profilePicture) throws IOException {
        Bussiness bussinessUser = bussinessService.findById(id);
        if(bussinessUser!=null){
            URI location = fromCurrentRequest().build().toUri();
            bussinessUser.setPathImage(location.toString());
            bussinessService.saveBussiness(bussinessUser);
            imgService.saveImage("/images", bussinessUser.getId(), profilePicture);
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value="/player/{username}/stats")
    public ResponseEntity<int[]> getPlayerStats(@PathVariable String username) {
        Player player = playerService.findByUsername(username);
        if(player==null){
            return ResponseEntity.notFound().build();
        }
        int[] stats = new int[3];
        stats[0] = player.getMathesPlayed();
        stats[1] = player.getMathcesWon();
        stats[2] = player.getMatchesLost();
        return ResponseEntity.ok().body(stats);
    }

    @GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userService.findByUsername(principal.getName()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
    
}
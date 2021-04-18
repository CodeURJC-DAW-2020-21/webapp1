package es.codeurjc.friends_padel_tour.Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.MalformedURLException;



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
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.DoubleService;
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
    private DoubleService doubleService;
    @Autowired
	private ImageService imgService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/bussiness/")
    public ResponseEntity<Bussiness> signUpBussiness(@RequestBody Bussiness newBussiness){
        bussinessService.saveBussiness(newBussiness);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newBussiness.getId()).toUri();
        return ResponseEntity.created(location).body(newBussiness);
    }

    @PostMapping(value="/player/")
    public ResponseEntity<Player> signUpUser(@RequestBody Player newPlayer){
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
    public ResponseEntity<Player> editProfile(@PathVariable long id, @RequestBody String password, @RequestBody int division) {
        Player newPlayer = playerService.findById(id);
        if(newPlayer == null){
            return ResponseEntity.notFound().build();
        }
        if (password.isBlank()) {
            newPlayer.setPassword(password);
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
    public ResponseEntity<Bussiness> editBussinessProfile(@PathVariable long id, @RequestBody String password, @RequestBody String s1_1, @RequestBody String s1_2, @RequestBody String s1_3, @RequestBody String s1_4, @RequestBody String s1_5,@RequestBody String s1_6, @RequestBody String s1_7,@RequestBody String s2_1,@RequestBody String s2_2,@RequestBody String s2_3,@RequestBody String s2_4,@RequestBody String s2_5, @RequestBody String s2_6, @RequestBody String s2_7) {
        Bussiness newBussiness = bussinessService.findById(id);
        if(newBussiness == null){
            return ResponseEntity.notFound().build();
        }else{
            String[][] schedule = {{"L", "M", "X", "J", "V", "S", "D"},{s1_1, s1_2, s1_3, s1_4, s1_5, s1_6, s1_7},{s2_1, s2_2, s2_3, s2_4, s2_5, s2_6, s2_7}};
            newBussiness.setSchedule(schedule);
        }
        if (password.isBlank()) {
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

    @PostMapping(value="/DoubleWith/")
    public ResponseEntity<DoubleOfPlayers> makeDoubleWith(@RequestBody String doubleName, @RequestBody String creator) {
        
        Player userNewDouble1 = playerService.findByUsername(creator);
        Player userNewDouble2 = playerService.findByUsername(doubleName);
        
        DoubleOfPlayers newDouble = new DoubleOfPlayers();
        newDouble.setPlayer1(userNewDouble1);
        newDouble.setPlayer2(userNewDouble2);
        userNewDouble1.getDoubles1().add(newDouble);
        userNewDouble2.getDoubles2().add(newDouble);
        playerService.updatePlayer(userNewDouble1);
        playerService.updatePlayer(userNewDouble2);
        doubleService.saveDouble(newDouble);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newDouble.getId()).toUri();
        return ResponseEntity.created(location).body(newDouble);
            
    }
    
    
    
}
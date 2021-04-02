package es.codeurjc.friends_padel_tour.Security;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;
import es.codeurjc.friends_padel_tour.Repositories.DoubleRepository;
import es.codeurjc.friends_padel_tour.Repositories.PlayerRepository;
import es.codeurjc.friends_padel_tour.Repositories.TournamentRepository;
import es.codeurjc.friends_padel_tour.Repositories.UserRepository;


@Component
public class DatabaseUsersLoader {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BussinessRepository bussinessRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoubleRepository doubleRepository;
    @Autowired
	private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase() {
        userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "ADMIN"));
        User user1 = new User("username", passwordEncoder.encode("contraseña"), "USER");
        User user2 = new User("username2", passwordEncoder.encode("contraseña2"), "USER");
        User user3 = new User("username3", passwordEncoder.encode("contraseña3"), "USER");
        User user4 = new User("username4", passwordEncoder.encode("contraseña4"), "USER");
        User userBussiness = new User("bussinessUsername", passwordEncoder.encode("contraseña"), "BUSSINESS");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(userBussiness);

    	Player newPlayer1 = new Player("username","yo","apellido","email","contraseña","ciudad",1);
        newPlayer1.setUser(user1);
        newPlayer1.setMathesPlayed(2);
        newPlayer1.setMathcesWon(1);
        newPlayer1.setMatchesLost(1);
        newPlayer1.setScore(200);

        Player newPlayer2 = new Player("username2","yo2","apellido2","email2","contraseña2","ciudad2",1);
        newPlayer2.setUser(user2);
        newPlayer2.setScore(500);

        Player newPlayer3 = new Player("username3","yo3","apellido3","email3","contraseña3","ciudad3",1);
        newPlayer3.setUser(user3);

        Player newPlayer4 = new Player("username4","yo4","apellido4","email4","contraseña4","ciudad4",1);
        newPlayer4.setUser(user4);


        DoubleOfPlayers newDouble1 = new DoubleOfPlayers();
        DoubleOfPlayers newDouble2 = new DoubleOfPlayers();

        newDouble1.setPlayer1(newPlayer1);
        newDouble1.setPlayer2(newPlayer2);
        newDouble2.setPlayer1(newPlayer4);
        newDouble2.setPlayer2(newPlayer3);

        newPlayer1.getDoubles1().add(newDouble1);
        newPlayer2.getDoubles2().add(newDouble1);
        newPlayer3.getDoubles2().add(newDouble2);
        newPlayer4.getDoubles1().add(newDouble2);

        playerRepository.save(newPlayer1);
        playerRepository.save(newPlayer2);
        playerRepository.save(newPlayer3);
        playerRepository.save(newPlayer4);

        doubleRepository.save(newDouble1);
        doubleRepository.save(newDouble2);

        
        Bussiness newBussiness = new Bussiness("Padel Arroyo Molinos", "bussinessUsername", "Madrid", "email@gmail.com", "password", userBussiness);
        bussinessRepository.save(newBussiness);

        Tournament tournament = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        tournamentRepository.save(tournament);
                
    }
}

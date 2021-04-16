package es.codeurjc.friends_padel_tour.Security;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.PadelMatch;
import es.codeurjc.friends_padel_tour.Entities.Player;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Repositories.BussinessRepository;
import es.codeurjc.friends_padel_tour.Repositories.DoubleRepository;
import es.codeurjc.friends_padel_tour.Repositories.MatchesRepository;
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
    private MatchesRepository matchesRepository;
    @Autowired
	private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase() {
        userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "ADMIN"));
        User user1 = new User("username", passwordEncoder.encode("contraseña"), "USER");
        User user2 = new User("username2", passwordEncoder.encode("contraseña2"), "USER");
        User user3 = new User("username3", passwordEncoder.encode("contraseña3"), "USER");
        User user4 = new User("username4", passwordEncoder.encode("contraseña4"), "USER");
        User user5 = new User("username5", passwordEncoder.encode("contraseña5"), "USER");
        User userBussiness = new User("bussinessUsername", passwordEncoder.encode("contraseña"), "BUSSINESS");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
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

        Player newPlayer5 = new Player("username5","yo4","apellido4","email4","contraseña5","ciudad4",1);
        newPlayer5.setUser(user5);


        DoubleOfPlayers newDouble1 = new DoubleOfPlayers();
        DoubleOfPlayers newDouble2 = new DoubleOfPlayers();
        DoubleOfPlayers newDouble3 = new DoubleOfPlayers();
        DoubleOfPlayers newDouble4 = new DoubleOfPlayers();

        newDouble1.setPlayer1(newPlayer1);
        newDouble1.setPlayer2(newPlayer2);
        newDouble2.setPlayer1(newPlayer4);
        newDouble2.setPlayer2(newPlayer3);
        newDouble3.setPlayer1(newPlayer1);
        newDouble3.setPlayer2(newPlayer4);
        newDouble4.setPlayer2(newPlayer1);
        newDouble4.setPlayer1(newPlayer5);

        newPlayer1.getDoubles1().add(newDouble1);
        newPlayer2.getDoubles2().add(newDouble1);
        newPlayer4.getDoubles1().add(newDouble2);
        newPlayer3.getDoubles2().add(newDouble2);
        newPlayer1.getDoubles1().add(newDouble3);
        newPlayer4.getDoubles2().add(newDouble3);
        newPlayer5.getDoubles1().add(newDouble4);
        newPlayer1.getDoubles2().add(newDouble4);

        playerRepository.save(newPlayer1);
        playerRepository.save(newPlayer2);
        playerRepository.save(newPlayer3);
        playerRepository.save(newPlayer4);
        playerRepository.save(newPlayer5);

        doubleRepository.save(newDouble1);
        doubleRepository.save(newDouble2);
        doubleRepository.save(newDouble3);
        doubleRepository.save(newDouble4);
        
        Bussiness newBussiness = new Bussiness("Padel Arroyo Molinos", "bussinessUsername", "Madrid", "email@gmail.com", "password", userBussiness);
        newBussiness.setAdress("Calle de ejemplo");
        bussinessRepository.save(newBussiness);

        PadelMatch match1 = new PadelMatch("Madrid", "Mostoles", "Campus de la URJC", LocalDate.of(2021, 5, 30).toString(), LocalTime.of(16, 30).toString(), 1, newPlayer1);
        PadelMatch match2 = new PadelMatch("Madrid", "Mostoles", "Campus de la URJC", LocalDate.of(2021, 5, 29).toString(), LocalTime.of(16, 30).toString(), 1, newPlayer2);
        PadelMatch match3 = new PadelMatch("Madrid", "Mostoles", "Campus de la URJC", LocalDate.of(2021, 5, 28).toString(), LocalTime.of(16, 30).toString(), 1, newPlayer3);
        PadelMatch match4 = new PadelMatch("Madrid", "Mostoles", "Campus de la URJC", LocalDate.of(2021, 5, 27).toString(), LocalTime.of(16, 30).toString(), 1, newPlayer4);
        PadelMatch match5 = new PadelMatch("Madrid", "Mostoles", "Campus de la URJC", LocalDate.of(2021, 5, 26).toString(), LocalTime.of(16, 30).toString(), 1, newPlayer5);

        matchesRepository.save(match1);
        matchesRepository.save(match2);
        matchesRepository.save(match3);
        matchesRepository.save(match4);
        matchesRepository.save(match5);

        Tournament tournament = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        tournament.setAccepted(true);
        Tournament tournament2 = new Tournament(newBussiness, "Torneo 2", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament3 = new Tournament(newBussiness, "Torneo 3", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament4 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament5 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament6 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament7 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament8 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament9 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament10 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        Tournament tournament11 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");
        
        Tournament tournament12 = new Tournament(newBussiness, "Torneo 1", "Un torneo para novatos", "11/8/21", "12/8/21", "20/7/21", "25/7/21", 1, 5, 1, 500, 200, "Madrid");


        tournament2.setAccepted(true);
        tournament3.setAccepted(true);
        tournament4.setAccepted(true);
        tournament5.setAccepted(true);
        tournament6.setAccepted(true);
        tournament7.setAccepted(true);
        tournament8.setAccepted(true);
        tournament9.setAccepted(true);

        
        tournamentRepository.save(tournament);  
        tournamentRepository.save(tournament2);  
        tournamentRepository.save(tournament3);  
        tournamentRepository.save(tournament4);  
        tournamentRepository.save(tournament5);  
        tournamentRepository.save(tournament6);  
        tournamentRepository.save(tournament7);  
        tournamentRepository.save(tournament8);  
        tournamentRepository.save(tournament9);  
        tournamentRepository.save(tournament10);  
        tournamentRepository.save(tournament11);  
        tournamentRepository.save(tournament12);  
          

    }
}

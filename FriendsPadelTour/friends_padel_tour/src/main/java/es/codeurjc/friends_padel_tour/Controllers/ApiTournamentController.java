package es.codeurjc.friends_padel_tour.Controllers;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.codeurjc.friends_padel_tour.Entities.DoubleOfPlayers;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;

@RestController
@RequestMapping("/api/tournaments")
public class ApiTournamentController {
    

    @Autowired
    TournamentsService tournamentsService;

    @PostMapping(value="/{id}")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament){
        

    }

    @PutMapping(value="/TournamentAccepted/{id}")
    public ResponseEntity<Tournament> acceptTournament(@PathVariable long id){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        tournament.setAccepted(true);
        tournamentsService.uptdate(tournament);
        return ResponseEntity.ok(tournament);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Tournament> deleteTournament(@PathVariable long id){
        Tournament tournamentToDelete = tournamentsService.findById(id);
        if(tournamentToDelete==null){
            return ResponseEntity.notFound().build();
        }
        tournamentsService.deleteById(id);
        return ResponseEntity.ok(tournamentToDelete);
    }

    @GetMapping(value = "/info/{id}")
    public ResponseEntity<Tournament> tournamentInfo(@PathVariable long id){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournament);
    }

    @PutMapping(value = "/winner/{id}")
    public ResponseEntity<Tournament> tournamentWinner(@PathVariable long id, @RequestBody int doublee){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        tournamentsService.setTournamentWinners(id, doublee);
        return ResponseEntity.ok(tournament);
    }


}

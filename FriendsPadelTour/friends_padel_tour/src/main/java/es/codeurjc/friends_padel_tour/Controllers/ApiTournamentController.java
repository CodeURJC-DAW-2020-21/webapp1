package es.codeurjc.friends_padel_tour.Controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;

@RestController
@RequestMapping("/api/tournaments")
public class ApiTournamentController {
    

    @Autowired
    TournamentsService tournamentsService;

    @GetMapping(value = "/acceptedTournaments")
    public ResponseEntity<Page<Tournament>> getTournaments(@RequestParam int pageNumber){
        Page<Tournament> tournaments = tournamentsService.getPageAcceptedTournaments(pageNumber, 3);
        if(tournaments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping(value = "/nonAceceptedTournaments")
    public ResponseEntity<Page<Tournament>> getNonAcceptedTournaments(@RequestParam int pageNumber){
        Page<Tournament> tournaments = tournamentsService.getPageNoAcceptedTournaments(pageNumber, 3);
        if(tournaments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournaments);
    }

    @PostMapping(value="/{id}")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament){
        tournamentsService.save(tournament);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(tournament.getId()).toUri();

        return ResponseEntity.created(location).body(tournament);
        
    }

    @PutMapping(value="/acceptedTournament/{id}")
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tournament> tournamentInfo(@PathVariable long id){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournament);
    }

    @PutMapping(value = "/acceptedTournament/winner/{id}")
    public ResponseEntity<Tournament> tournamentWinner(@PathVariable long id, @RequestBody int doublee){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        tournamentsService.setTournamentWinners(id, doublee);
        return ResponseEntity.ok(tournament);
    }


}

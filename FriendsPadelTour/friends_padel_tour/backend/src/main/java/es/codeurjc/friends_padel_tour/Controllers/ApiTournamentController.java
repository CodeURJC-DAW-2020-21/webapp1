package es.codeurjc.friends_padel_tour.Controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

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

import es.codeurjc.friends_padel_tour.Entities.Bussiness;
import es.codeurjc.friends_padel_tour.Entities.Tournament;
import es.codeurjc.friends_padel_tour.Service.BussinessService;
import es.codeurjc.friends_padel_tour.Service.TournamentsService;

@RestController
@RequestMapping("/api/tournaments")
public class ApiTournamentController {
    

    @Autowired
    TournamentsService tournamentsService;
    @Autowired
    private BussinessService bussinesService;

    @GetMapping(value = "/acceptedTournaments")
    public ResponseEntity<Page<Tournament>> getTournaments(@RequestParam int pageNumber){
        Page<Tournament> tournaments = tournamentsService.getPageAcceptedTournaments(pageNumber, 3);
        if(tournaments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping(value = "/nonAcceptedTournaments")
    public ResponseEntity<Page<Tournament>> getNonAcceptedTournaments(@RequestParam int pageNumber){
        Page<Tournament> tournaments = tournamentsService.getPageNoAcceptedTournaments(pageNumber, 3);
        if(tournaments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournaments);
    }

    @PostMapping(value="/")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Bussiness bussiness = bussinesService.findByUsername(principal.getName());

        tournament.setBussinnes(bussiness);
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

    @PutMapping(value = "/acceptedTournament/{id}/winner")
    public ResponseEntity<Tournament> tournamentWinner(@PathVariable long id, @RequestParam int winner){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        if(winner > tournament.getRegisteredCouples()){
            return ResponseEntity.badRequest().build();
        }
        tournamentsService.setTournamentWinners(id, winner);
        return ResponseEntity.ok(tournament);
    }


    @PostMapping(value="/acceptedTournament/{id}/double/")
    public ResponseEntity<Tournament> joinATournament(@PathVariable long id, @RequestBody String doubleSelected,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        tournamentsService.joinTournament(id, doubleSelected, principal.getName());
        URI location = fromCurrentRequest().path("/{nDouble}").buildAndExpand(tournament.getRegisteredCouples()).toUri();

        return ResponseEntity.created(location).body(tournament);
    }

}

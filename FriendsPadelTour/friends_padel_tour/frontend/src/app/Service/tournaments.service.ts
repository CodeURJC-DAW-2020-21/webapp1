import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

const BASE_URL = '/tournaments'

@Injectable({
  providedIn: 'root'
})
export class TournamentsService {

  constructor(private http: HttpClient) { }
/*
  
    public ResponseEntity<Page<Tournament>> getTournaments(@RequestParam int pageNumber){
        Page<Tournament> tournaments = tournamentsService.getPageAcceptedTournaments(pageNumber, 3);
        if(tournaments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournaments);
    }

    getNonAcceptedTournaments(@RequestParam int pageNumber){
        Page<Tournament> tournaments = tournamentsService.getPageNoAcceptedTournaments(pageNumber, 3);
        if(tournaments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournaments);
    }

    createTournament(@RequestBody Tournament tournament,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Bussiness bussiness = bussinesService.findByUsername(principal.getName());

        tournament.setBussinnes(bussiness);
        tournamentsService.save(tournament);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(tournament.getId()).toUri();

        return ResponseEntity.created(location).body(tournament);
        
    }

    acceptTournament(@PathVariable long id){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        tournament.setAccepted(true);
        tournamentsService.uptdate(tournament);
        return ResponseEntity.ok(tournament);
    }


   deleteTournament(@PathVariable long id){
        Tournament tournamentToDelete = tournamentsService.findById(id);
        if(tournamentToDelete==null){
            return ResponseEntity.notFound().build();
        }
        tournamentsService.deleteById(id);
        return ResponseEntity.ok(tournamentToDelete);
    }

    tournamentInfo(@PathVariable long id){
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournament);
    }

    tournamentWinner(@PathVariable long id, @RequestParam int winner){
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


  joinATournament(@PathVariable long id, @RequestBody String doubleSelected,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Tournament tournament = tournamentsService.findById(id);
        if (tournament==null){
            return ResponseEntity.notFound().build();
        }
        tournamentsService.joinTournament(id, doubleSelected, principal.getName());
        URI location = fromCurrentRequest().path("/{nDouble}").buildAndExpand(tournament.getRegisteredCouples()).toUri();

        return ResponseEntity.created(location).body(tournament);
    }
*/
  handleError(err: Error){
    console.error(err.message);
    return throwError('Server error');
  }
}

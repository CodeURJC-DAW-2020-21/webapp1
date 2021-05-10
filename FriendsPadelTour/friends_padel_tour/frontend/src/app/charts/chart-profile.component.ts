import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "src/app/Service/users.service";



@Component({
    selector: 'app-chart',
    templateUrl: './chart-profile.component.html'
  })

export class chartProfileComponent implements OnInit {

    data: any;
    myplayerstats: number[];
    
    myplayer: string = "";

    

  
    view: [number,number] = [700, 400];
    


    constructor(private usersService : UserService, private router: Router, activatedRoute: ActivatedRoute){
        this.myplayerstats = []
        const playerUserName = activatedRoute.snapshot.params['userName'];
        usersService.getPlayer(playerUserName).subscribe(
            user => {
                this.myplayer = user.username
                this.usersService.getPlayer(this.myplayer).subscribe(
                  myplayerstats => {
                      this.myplayerstats?.push(myplayerstats.mathesPlayed,myplayerstats.mathcesWon,myplayerstats.matchesLost)
                      let data = [
                        {
                          "name": "Partidos jugados",
                          "value": this.myplayerstats[0]
                        },
                        {
                          "name": "Partidos ganados",
                          "value": this.myplayerstats[1]
                        },
                        {
                          "name": "Partidos perdidos",
                          "value": this.myplayerstats[2]
                        }
                      ];
                      Object.assign(this, {data})
                    }
                  
              )
            }
        )

    }


    
  
    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'PARTIDOS DISPUTADOS';
    showYAxisLabel = true;
    yAxisLabel = 'CANTIDAD';
  
    colorScheme = {
      domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
    };



    


    ngOnInit(): void {
    
      }

    }

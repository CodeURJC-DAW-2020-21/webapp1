import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Chart } from "chart.js";
import { UserService } from "src/app/Service/users.service";



@Component({
    selector: 'app-chart',
    templateUrl: './chart-profile.component.html'
  })

export class chartProfileComponent implements OnInit {

    data: any;
    myplayerstats: number[];
    chart: any;
    myplayer: string = "";


    


    constructor(private usersService : UserService, private router: Router, activatedRoute: ActivatedRoute){
        this.myplayerstats = []

        const playerUserName = activatedRoute.snapshot.params['userName'];
        usersService.getPlayer(playerUserName).subscribe(
            user => {
                this.myplayer = user.username
                this.createChart(this.myplayer)
            }
        )

    }
    


    ngOnInit(): void {
    
      }

      createChart(player: string){
        this.usersService.getPlayer(player).subscribe(
            myplayerstats => {
                this.myplayerstats?.push(myplayerstats.mathesPlayed,myplayerstats.mathcesWon,myplayerstats.matchesLost)
                this.generateChart(this.myplayerstats)
            }
            
        )

      }
      //type: 'bar',
      generateChart(myplayerstats: number[]){
        const data1 = {
            labels: ['Red', 'Orange', 'Yellow', 'Green', 'Blue'],
            datasets: [
              {
                label: 'GRAFICA PARTIDOS',
                data: [myplayerstats[0],myplayerstats[1],myplayerstats[2]],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                ],
                borderWidth: 1
              }
            ]
          };
        this.chart = new Chart('chart-container', {
            
            type: 'bar',
            data: data1,
            options: {
                scales: {
                    //yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    //}]
                }
            }
        })
      }


     /* animationEnabled: true,
                exportEnabled: true,
                title: {
                  text: "Usuarios de Porthub por mes"
                },
                data: [{
                  type: "column",
                  dataPoints: [
                    { y: myplayerstats[0], label: "Enero" },
                    { y: myplayerstats[1], label: "Febrero" },
                    { y: myplayerstats[2], label: "Marzo" },
                  ]
                }]
              })
            this.chart.render()*/

}
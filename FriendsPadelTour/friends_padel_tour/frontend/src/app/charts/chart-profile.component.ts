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
        const DATA_COUNT = 7;
        const NUMBER_CFG = {count: DATA_COUNT, min: -100, max: 100};
        
        const labels = [1,2,3,4,5];
        const data = {
          labels: labels,
          datasets: [
            {
              label: 'Dataset 1',
              data: [1,2,3],
              borderColor: "blue",
              backgroundColor: "red",
            },
            {
              label: 'Dataset 2',
              data:[1,2,3],
              borderColor: "blue",
              backgroundColor: "red",
            }
          ]
        };
        const config = {
          type: 'bar',
          data: data,
          options: {
            indexAxis: 'y',
            // Elements options apply to all of the options unless overridden in a dataset
            // In this case, we are setting the border of each horizontal bar to be 2px wide
            elements: {
              bar: {
                borderWidth: 2,
              }
            },
            responsive: true,
            plugins: {
              legend: {
                position: 'right',
              },
              title: {
                display: true,
                text: 'Chart.js Horizontal Bar Chart'
              }
            }
          },
        };
      }
    }

        /*const data1 = {
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
        this.chart = new Chart('canvas', {
            
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
      }*/


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


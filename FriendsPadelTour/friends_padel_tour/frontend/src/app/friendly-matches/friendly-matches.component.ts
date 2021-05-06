import { MatchesService } from './../Service/matches.service';
import { PdfGeneratorService } from './../Service/pdf-generator.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-friendly-matches',
  templateUrl: './friendly-matches.component.html',
})
export class FriendlyMatchesComponent implements OnInit {


  constructor(public pdfgenerator: PdfGeneratorService, public matchesService: MatchesService) { }

  ngOnInit(): void {
  }

  downloadInstructions(){
    return this.pdfgenerator.getPdf().subscribe(
      response => {
        return response;
      },
      error => {
        alert('Error downloadig pdf');
      }
    );
  }

}

import { PdfGeneratorService } from './../Service/pdf-generator.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-friendly-matches',
  templateUrl: './friendly-matches.component.html',
})
export class FriendlyMatchesComponent implements OnInit {


  constructor(public pdfgenerator: PdfGeneratorService) { }

  ngOnInit(): void {
  }

  downloadInstructions(){
    this.pdfgenerator.download();
  }

}

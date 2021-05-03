import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class PdfGeneratorService {

  download() {
    this.http.get('download-pdf').subscribe(
      response =>{

      },
      console=>{
        console.error('Bad request.');

      }
    );
  }

  constructor(private http: HttpClient) { }
}

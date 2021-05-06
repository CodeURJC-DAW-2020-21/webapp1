import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASE_URL = '/api/download-pdf';

@Injectable({
  providedIn: 'root'
})
export class PdfGeneratorService {

  constructor(private http: HttpClient) { }

  getPdf(){
    return this.http.get(BASE_URL).pipe();
  }
}

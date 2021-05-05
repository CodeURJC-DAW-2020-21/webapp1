import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bussiness-sign-up-form',
  templateUrl: './bussiness-sign-up-form.component.html',
})
export class BussinessSignUpFormComponent implements OnInit {


  listProvinces:string[] = ["Álava","Albacete","Alicante","Almería","Ávila","Asturias"];
  listType:string[] = ["Escuela de Padel","Centro Deportivo","Tienda de material deportivo","Otro"];
  constructor() { }

  ngOnInit(): void {
  }

  signUp(){


  }

}

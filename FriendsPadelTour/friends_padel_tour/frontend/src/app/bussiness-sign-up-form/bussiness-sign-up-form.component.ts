import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bussiness } from '../model/bussiness.model';
import { bussinessRequest } from '../model/bussinessRequest.model';
import { playerRequest } from '../model/playerRequest.model';
import { User } from '../model/user.model';
import { SignUpService } from '../Service/signup.service';

@Component({
  selector: 'app-bussiness-sign-up-form',
  templateUrl: './bussiness-sign-up-form.component.html',
})
export class BussinessSignUpFormComponent implements OnInit {


  listProvinces:string[] = ["Álava","Albacete","Alicante","Almería","Ávila","Asturias"];
  listType:string[] = ["Escuela de Padel","Centro Deportivo","Tienda de material deportivo","Otro"];


  bussiness: Bussiness | undefined;
  user: User;
  bussinessRequest: bussinessRequest | undefined;

  constructor(private SignUpService: SignUpService, private router: Router) {
    this.user = {username: '', encodedPassword:'', roles:['']}
  }

  ngOnInit(): void {
  }



  signUpBussiness(password: string,username: string, bussinessName:string,ownerName:string,ownerSurname:string,location:string,city:string,email:string,adress:string,bussinessType:string){
    this.user = {encodedPassword: password, username:username, roles: ['BUSSINESS']}

    this.bussiness = {username: username, bussinessName: bussinessName, ownerName:ownerName,ownerSurname:ownerSurname,
      city:city,adress:adress,location:location,email:email,bussinessType:bussinessType,hasImage:false,tournaments:[],createdTournaments:0,imagePath:'',user:this.user};
      this.bussinessRequest = {user: this.user, bussiness: this.bussiness}
    this.SignUpService.signUpBussiness(this.bussinessRequest).subscribe(
      data => {console.log("Usuario creado correctarmente")},
      error => console.log("Error al crear el usuario")
    //this.router.navigate(['succeesPage']);

    )
  }



}

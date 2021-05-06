import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bussiness } from '../model/bussiness.model';
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

  constructor(private SignUpService: SignUpService, private router: Router) {
    this.user = {userName: '', password:'', roles:['']}
  }

  ngOnInit(): void {
  }



  signUpBussiness(password: string,username: string, bussinessName:string,ownerName:string,ownerSurname:string,location:string,city:string,email:string,adress:string,bussinessType:string){
    this.user = {password: password, userName:username, roles: ['BUSSINESS']}

    // tslint:disable-next-line: max-line-length
    this.bussiness = {username: this.user.userName, bussinessName: bussinessName, ownerName:ownerName,ownerSurname:ownerSurname,city:city,adress:adress,location:location,email:email,bussinessType:bussinessType,hasImage:false,tournaments:[],createdTournaments:0,imagePath:'', user: this.user};
    this.SignUpService.signUpBussiness(this.bussiness).subscribe(
      data => {console.log("Usuario creado correctarmente")},
      error => console.log("Error al crear el usuario")
    //this.router.navigate(['succeesPage']);

    )
  }



}

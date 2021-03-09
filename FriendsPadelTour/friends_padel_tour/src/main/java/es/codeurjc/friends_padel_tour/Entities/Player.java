package es.codeurjc.friends_padel_tour.Entities;

import java.sql.Blob;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private String location;
    private String email;
    private ArrayList<Double> doubles;

    @Lob
    @JsonIgnore
    private Blob image;
    
    public Player(){}

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getDoubles() {
        return doubles;
    }

    public void setDoubles(ArrayList<Double> doubles) {
        this.doubles = doubles;
    }

    
    
}

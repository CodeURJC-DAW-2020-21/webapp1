package es.codeurjc.friends_padel_tour.Entities;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bussiness{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String bussinessName;
    private String ownerName;
    private String ownerSurname;
    private String location;
    private String city;
    private String email;
    private String adress;
    private String bussinessType;
    private int createdTournaments = 0;
    private String pathImage;

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussiness")
    @JsonIgnore
    private List<Tournament> tournaments = new ArrayList<>();
    private boolean hasImage = false;
    @Lob
    @JsonIgnore
    private Blob image;


    @OneToOne
    private User user;

    public Bussiness(){
        
    }

    public User getUser() {
        return user;
    }

    public String getPassword(){
        User user = this.user;
        return user.getEncodedPassword();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    //public String getPassword() {
      //  return password;
    //}

    public void setPassword(String password) {
        this.user.setEncodedPassword(password);
    }

    public int getCreatedTournaments() {
        return createdTournaments;
    }

    public void setCreatedTournaments(int createdTournaments) {
        this.createdTournaments = createdTournaments;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public Bussiness(String bussinessName, String ownerName, String ownerSurname, String email, String city,
            String adress, String location, String bussinessType, User user) {
        this.bussinessName = bussinessName;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.location = location;
        this.city = city;
        this.email = email;
        this.adress = adress;
        this.bussinessType = bussinessType;
        this.username = user.getUsername();
        this.user = user;
    }

    public String getAdress() {
        return adress;
    }
    public String getBussinessName() {
        return bussinessName;
    }
    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }
    public String getOwnerSurname() {
        return ownerSurname;
    }
    public void setOwnerSurname(String surname) {
        this.ownerSurname = surname;
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
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public long getId() {
        return id;
    }
    public Blob getImage() {
        return image;
    }
    public List<Tournament> getTournaments() {
        return tournaments;
    }
    public void setImage(Blob image) {
        this.image = image;
    }
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Bussiness(long id, String userName, String bussinessName, String userSurname, String location, String email,
            String adress, Blob image) {
        this.id = id;
        this.bussinessName = bussinessName;
        this.location = location;
        this.email = email;
        this.adress = adress;
        this.image = image;
    }

}

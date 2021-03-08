package es.codeurjc.friends_padel_tour.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User {
    @Id
    private long id;
    private String name;
    private String surname;

    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
}

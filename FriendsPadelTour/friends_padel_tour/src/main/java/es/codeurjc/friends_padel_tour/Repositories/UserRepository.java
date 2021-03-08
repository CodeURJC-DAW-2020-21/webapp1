package es.codeurjc.friends_padel_tour.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}

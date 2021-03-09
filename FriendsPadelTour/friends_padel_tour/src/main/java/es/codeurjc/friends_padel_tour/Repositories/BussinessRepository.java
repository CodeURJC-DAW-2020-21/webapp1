package es.codeurjc.friends_padel_tour.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;

import java.util.Optional;

public interface BussinessRepository extends JpaRepository<Bussiness,Long>{
    
        Optional<Bussiness> findById(Long id);
    
        Optional<Bussiness> findByUser(Bussiness user);
    
        Optional<Bussiness> findByUserName(Bussiness name);
    }


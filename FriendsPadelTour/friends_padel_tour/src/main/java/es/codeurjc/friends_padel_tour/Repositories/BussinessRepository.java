package es.codeurjc.friends_padel_tour.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.friends_padel_tour.Entities.Bussiness;

public interface BussinessRepository extends JpaRepository<Bussiness,Long>{

    Optional<Bussiness> findByEmail(String email);

    Optional<Bussiness> findById(Long id);

    Optional<Bussiness> findByBussinessName(String bussinessName);
}


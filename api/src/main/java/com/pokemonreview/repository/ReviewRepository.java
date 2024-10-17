package com.pokemonreview.repository;

import com.pokemonreview.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//Uso de querys
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByPokemonId(int pokemonId);


}

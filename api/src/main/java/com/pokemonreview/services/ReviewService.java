package com.pokemonreview.services;

import com.pokemonreview.dto.PokemonDto;
import com.pokemonreview.dto.PokemonResponse;
import com.pokemonreview.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto pokemonDto, int pokemonID);

    List<ReviewDto> getReviewsByPokemonId(int pokemonID);

    List<ReviewDto> getAllReview();

    ReviewDto getReviewById(int id, int pokemonID);

    ReviewDto updateReview(ReviewDto reviewDto, int id, int pokemonID);

    void deleteReview(int id);


}

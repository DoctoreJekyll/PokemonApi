package com.pokemonreview.services;

import com.pokemonreview.dto.PokemonDto;
import com.pokemonreview.dto.ReviewDto;
import com.pokemonreview.exceptions.PokemonNotFoundException;
import com.pokemonreview.models.Pokemon;
import com.pokemonreview.models.Review;
import com.pokemonreview.repository.PokemonRepository;
import com.pokemonreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, int pokemonId) {
        Review review = mapToEntity(reviewDto);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int pokemonID) {
        List<Review> reviews = reviewRepository.findByPokemonId(pokemonID);

        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllReview() {
        return null;
    }

    @Override
    public ReviewDto getReviewById(int id, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));

        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));

        if (review.getPokemon().getId() != pokemon.getId()) {//Mejor un .equal pero encesitamos un integer y no un int
            throw new RuntimeException("Pokemon id mismatch");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, int id, int pokemonId) {
        return null;
    }

    @Override
    public void deleteReview(int id) {

    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setContent(reviewDto.getContent());
        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());

        return review;
    }
}

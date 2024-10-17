package com.pokemonreview.dto;

import com.pokemonreview.models.Pokemon;
import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {

    private List<PokemonDto> pokemons;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}

package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourRepository {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    void deleteFavNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    Neighbour getNeighbourByID(long id);

    void toggleNeighbourFavorite(long id);
}

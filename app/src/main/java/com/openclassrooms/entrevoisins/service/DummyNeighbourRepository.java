package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourRepository implements NeighbourRepository {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final List<Neighbour> favNeighbours = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavNeighbour(Neighbour neighbour) {
        favNeighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbourByID(long id) {
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getId() == id) {
                return neighbour;
            }
        }
        return null;
    }

    @Override
    public void toggleNeighbourFavorite(long id) {
        Neighbour favNeighbour = getNeighbourByID(id);
        if (!isNeighbourFavorite(id)) {
            favNeighbours.add(favNeighbour);
        } else {
            favNeighbours.remove(favNeighbour);
        }
    }

    @Override
    public boolean isNeighbourFavorite(long id) {
        Neighbour neighbour = getNeighbourByID(id);
        if (favNeighbours.contains(neighbour)) {
            return true;
        }
        return false;
    }
}

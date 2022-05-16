package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourRepository implements NeighbourRepository {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final List<Neighbour> favNeightbours = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favNeightbours;
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
        favNeightbours.remove(neighbour);
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
        // TODO Thomas
        if (!isNeighbourFavorite(id)) {
            favNeightbours.add(favNeighbour);
        }
    }

    @Override
    public boolean isNeighbourFavorite(long id) {
        for (Neighbour favNeighbour : favNeightbours) {
            if (favNeighbour.getId() == id) {
                return true;
            }
        }
        return false;
    }


}

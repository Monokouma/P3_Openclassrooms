package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourRepository service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourToFavoriteWithSuccess() {
        Neighbour neighbourToAddToFavorite = service.getNeighbours().get(0);
        service.toggleNeighbourFavorite(neighbourToAddToFavorite.getId());
        assertTrue(service.getFavoriteNeighbours().contains(neighbourToAddToFavorite));
    }

    @Test
    public void deleteNeighbourFromFavorite() {
        Neighbour neighbourToRemoveFromFavorite = service.getNeighbours().get(0);
        service.toggleNeighbourFavorite(neighbourToRemoveFromFavorite.getId());
        service.deleteFavNeighbour(neighbourToRemoveFromFavorite);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourToRemoveFromFavorite));
    }

    @Test
    public void removeNeighbourFromFavoriteOnly() {
        Neighbour neighbourToCompare = service.getNeighbours().get(0);
        service.toggleNeighbourFavorite(neighbourToCompare.getId());
        service.deleteFavNeighbour(neighbourToCompare);
        assertTrue(service.getNeighbours().contains(neighbourToCompare));
    }

    @Test
    public void deleteFavNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.toggleNeighbourFavorite(neighbourToDelete.getId());
        service.deleteFavNeighbour(neighbourToDelete);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourToDelete));
    }

}

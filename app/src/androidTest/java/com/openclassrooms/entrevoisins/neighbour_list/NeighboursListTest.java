
package com.openclassrooms.entrevoisins.neighbour_list;

import com.openclassrooms.entrevoisins.R;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onIdle;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;





/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
        new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
            .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myNeighboursList_clickAction_showNeighbourInfo() {
        onView(
            allOf(
                withId(R.id.list_neighbours),
                isDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.activity_neighbour_info_text_view_neighbour_name_in_cardview)).check(matches(withText("Jack")));
    }

    @Test
    public void myNeighboursList_clickAction_addToFavorite() throws InterruptedException {
        myNeighboursList_clickAction_clickOnNeighbour_then_addToFavorite();
        pressBack();
        myNeighboursList_swipeAction_swipeLeftToFavList();
        //Thread.sleep(500);
        wait(500);
        myNeighboursList_checkAction_isNeihbourNameMatching();
        wait(1000);
        //Thread.sleep(1000);
    }

    private void myNeighboursList_checkAction_isNeihbourNameMatching() {
        onView(
            allOf(
                withId(R.id.item_list_name),
                isDisplayed()
            )
        ).check(matches(withText("Jack")));
    }

    private void myNeighboursList_swipeAction_swipeLeftToFavList() {
        onView(
            allOf(
                withId(R.id.list_neighbours),
                isDisplayed()
            )
        ).perform(swipeLeft());
    }

    private void myNeighboursList_clickAction_clickOnNeighbour_then_addToFavorite() {
        onView(
            allOf(
                withId(R.id.list_neighbours),
                isDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.activity_neighbour_info_addToFavoriteButton)).perform(click());
    }
}
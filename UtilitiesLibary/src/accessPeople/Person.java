/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract class Person, used as Base class for all card holders. instantiates
 * Property Swipecard.
 */
public abstract class Person implements Serializable {

    private SwipeCard MySwipeCard;

    /**
     *  Method for producing a new person with swipecard.
     * @param id Integer - Used for Person ID
     * @param name String - Used for Person Name
     * @param role List - Contains user Roles
     */
    public Person(Integer id, String name, List<String> role) {

        this.MySwipeCard = new SwipeCard(id, name, role);

    }

    /**
     * Returns the persons swipecard object.
     *
     * @return SwipeCard Object.
     */
    public SwipeCard getMySwipeCard() {
        return this.MySwipeCard;
    }

    /**
     * Overwrites current swipecard object with new swipecard object.
     *
     * @param MySwipeCard SwipeCard - New Swipecard object
     */
    public void setMySwipeCard(SwipeCard MySwipeCard) {
        this.MySwipeCard = MySwipeCard;
    }

}

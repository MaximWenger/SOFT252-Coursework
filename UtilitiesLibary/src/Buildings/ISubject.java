/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

/**
 * Produces interface ISubject/ Ensures registerObservers and notifyObservers
 * method is available .
 */
public interface ISubject {

    /**
     * Used to register observers.
     *
     * @param observer IObservers - new observer
     */
    public void registerObservers(IObservers observer);

    /**
     * Used to notifyObservers of new mode
     * @param mode String - new mode.
     */
    public void notifyObservers(String mode);

}

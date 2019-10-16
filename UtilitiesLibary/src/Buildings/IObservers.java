/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

/**
 * Produces IObservers interface.
 * Ensures update method with String mode parameter is available 
 * 
 */
public interface IObservers {
    /**
     * Used to update IObservers with new mode.
     * @param mode String - new Mode
     */
    public void update(String mode);
    
}

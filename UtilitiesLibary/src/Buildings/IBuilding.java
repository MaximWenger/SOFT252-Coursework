/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

/**
 *Produces interface IBuilding
 * Ensures getName and setName methods is available .
 * 
 */
public interface IBuilding {
     /**
      * Used to return name for all building types.
      * @return String - Building type name.
      */
    public String getName();
    /**
     * Used to set name for all building types.
     * @param name String - building type name.
     */
    public void setName(String name);
    
}

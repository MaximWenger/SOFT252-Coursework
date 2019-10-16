/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 * Creates Class Person and calls Super Class Constructor with given parameters.
 * 
 */
public class Staff extends Person implements Serializable {
    /**
     * Used to populate new Staff object
     * @param id Integer - Staff ID number
     * @param name String - Staff Name
     * @param role List - Staff Role(s)
     */
    public Staff(Integer id, String name, List<String> role) {
        super(id, name, role);
    }
}

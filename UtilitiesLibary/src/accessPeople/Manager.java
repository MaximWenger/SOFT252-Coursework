/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 *Creates Class Manager and calls Super Class Constructor with given parameters.
 * 
 */
public class Manager extends Person implements Serializable {
    /**
     * Used to populate new Manager object
     * @param id Integer - Manager ID Number
     * @param name String - user Name
     * @param role List - user Role(s)
     */
    public Manager(Integer id, String name, List<String> role) {
        super(id, name, role);
    }

}

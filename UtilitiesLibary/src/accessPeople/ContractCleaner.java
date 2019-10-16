/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 * Creates Class ContractCleaner and calls Super Class Constructor with given parameters.
 * 
 */
public class ContractCleaner extends Person implements Serializable {
    /**
     * Used to populate new ContractCleaner object
     * @param id Integer - user ID
     * @param name String - User Name
     * @param role List - user role(S)
     */
    public ContractCleaner(Integer id, String name, List<String> role) {
        super(id, name, role);
    }
    
}

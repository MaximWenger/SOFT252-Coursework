/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 * People Factory Class follows Factory Pattern to create new people.
 *
 */
public class PeopleFactory  implements Serializable{ //factory class

    /**
     *
     */

    public PeopleFactory() {

    }
    /**
     * CreatePerson uses given parameters to create a new object depending on given role, which is then returned within a Person object.
     * (Allows multiple roles, as the Role List is passed into the new role)
     * @param id Integer - User ID
     * @param name String - User name
     * @param role List - user Role(s)
     * @return Person - new person object.
     */
    public Person createPerson(Integer id, String name, List<String> role) {
        Person person = null;
        String currentRole = role.get(0);//Primary Role is always passed first.

        switch (currentRole) {
            case "Staff":
                person = new Staff(id, name, role); 
                break;
            case "Student":
                person = new Student(id, name, role);
                break;
            case "EmergencyResponder":
                person = new EmergencyResponder(id, name, role);
                break;
            case "Visitor":
                person = new Visitor(id, name, role);
                break;
            case "Manager":
                person = new Manager(id, name, role);
                break;
            case "Security":
                person = new Security(id, name, role);
                break;
            case "ContractCleaner":
                person = new ContractCleaner(id, name, role);
                break;
        }
        return person;
    }

}

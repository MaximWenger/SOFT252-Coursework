/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.io.Serializable;
import java.util.List;

/**
 *Creates Class Student and calls Super Class Constructor with given parameters..
 * 
 */
public class Student extends Person implements Serializable{
    /**
     * Used to populate new Student object.
     * @param id Integer - Student ID number
     * @param name String - Student name
     * @param role List - Student Role(s)
     */
    public Student(Integer id, String name, List<String> role) {
        super(id, name, role);
    }

}

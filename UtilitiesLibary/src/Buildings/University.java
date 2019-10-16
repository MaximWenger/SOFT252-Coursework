/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import java.util.List;

/**
 * Creates class University.
 * (instantiating Properties List String and Integer)
 * 
 */
public class University implements IBuilding {

    private List<Campus> CampusList;
    private String UniversityName;
    private Integer UniversityId;

    /**
     * Populate University Name and University ID.
     *
     * @param UName String - University name
     * @param UId Integer - University ID
     */
    public University(String UName, Integer UId) {

        this.UniversityName = UName;
        this.UniversityId = UId;

    }

    /**
     * Make new campus, adding name to campus List
     *
     * @param name String - new campus name
     */
    public void makeCampus(String name) {
        this.CampusList.add(new Campus(name));
    }

    /**
     * Remove campus from campus list
     *
     * @param index Integer - which campus to remove, index provided by dropdown on GUI
     */
    public void removeCampus(Integer index) {
        this.CampusList.remove(index);
    }

    /**
     * Returns Current University Object Name
     *
     * @return String - University Name
     */
    @Override
    public String getName() {
        return this.UniversityName;
    }

    /**
     * Sets current University Object Name to given parameter.
     *
     * @param name String - used to change University name
     */
    @Override
    public void setName(String name) {
        this.UniversityName = name;
    }

}

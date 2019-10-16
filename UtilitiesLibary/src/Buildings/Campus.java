/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates Campus Class and Initiates Required Properties.
 *
 */
public class Campus implements IBuilding, ISubject, IObservers {

    private List<Building> BuildingList;
    private String CampusName;
    private String CampusMode;
    private ArrayList<IObservers> Observers = new ArrayList<>();

    /**
     * Campus Constructor Class populates Campus parameters with given parameter
     * name, new Array list for building and sets default campus mode.
     *
     * @param name String - Campus name
     */
    public Campus(String name) {
        this.CampusName = name;
        this.BuildingList = new ArrayList<>();
        this.CampusMode = "Normal";
    }

    /**
     * Returns list of all building within current campus object.
     *
     * @return List - current object building list.
     */
    public List<Building> getBuildingList() {
        return this.BuildingList;
    }

    /**
     * Add Building objects to current Campus
     * @param buildings List - new building to add to campus
     */
    public void addBuildingList(List<Building> buildings) {
        this.BuildingList = buildings;
    }

    /**
     * Adds new building object, with building Name and campus object and adds
     * that to Building list.
     *
     * @param Name String - Building Name
     * @param campus Campus - Campus object.
     */
    public void makeBuilding(String Name, Campus campus) {
        this.BuildingList.add(new Building(Name, campus));
    }

    /**
     * Removes building object from building list, depending on index given from
     * interface.
     *
     * @param index Integer - Remove building from buildingList with value from GUI
     */
    public void removeBuilding(Integer index) {
        this.BuildingList.remove(index);
    }

    /**
     * Returns current Campus object Name
     *
     * @return String - Campus name
     */
    @Override
    public String getName() {
        return this.CampusName;
    }

    /**
     * Sets current campus object name to given parameter
     *
     * @param name String - Campus name
     */
    @Override
    public void setName(String name) {
        this.CampusName = name;
    }

    /**
     * Updates all campus' modes to given parameter, iterating though the entire
     * list.
     *
     * @param mode String - New mode
     */
    @Override
    public void notifyObservers(String mode) {
        this.CampusMode = mode;

        Observers.stream().forEach((o) -> {
            o.update(mode);
        });
    }
    /**
     * Sets Campus mode for current Campus object.
     * @param CampusMode String - used to Set Campus mode for current Campus object.
     */
        public void setCampusMode(String CampusMode) {
        this.CampusMode = CampusMode;
    }
    /**
     * Adds observer object to observer list.
     *
     * @param observer IObservers - current observers
     */
    @Override
    public void registerObservers(IObservers observer) {
        this.Observers.add(observer);
    }

    @Override
    public void update(String mode) {
        setCampusMode(mode);
        notifyObservers(mode);
    }

}

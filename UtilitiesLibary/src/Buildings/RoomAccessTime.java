/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import java.time.LocalTime;

/**
 *Creates new Class RoomAccessTime which Checks and compares times.
 * instantiating Property LocalTime.
 */
public class RoomAccessTime {

    private LocalTime localtime;
    
    /**
     * Populates localtime variable with the local time.
     */
    public RoomAccessTime() {
        this.localtime = LocalTime.now();
    }

    /**
     * Compares given time paramters with LocalTime, returning Boolean.
     * @param startTime LocalTime - accesstime start.
     * @param EndTime LocalTime - accesstime End.
     * @return Boolean -  if user can enter.
     */
    public Boolean CheckTimeBetweenRange(LocalTime startTime, LocalTime EndTime) {

        if (localtime.isAfter(EndTime) || localtime.isBefore(startTime)) { //checks to see if timerange is outside specified times.
            return false;
        }
        if (localtime.isAfter(startTime) && localtime.isBefore(EndTime)) { //Checks to see if timerange is inside specificed times.
            return true;
        }
        return false;
    }

}

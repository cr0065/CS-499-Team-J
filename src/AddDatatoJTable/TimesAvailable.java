/*
 * Name: TimesAbailable
 * Authors: Cameron Ramos & Ben Davis
 * Date: 12/7/2021
 * Purpose: Getters and Setters for TimesAvailable
 */
package AddDatatoJTable;

public class TimesAvailable {

    //declaring variables
    private final int timeslotId;
    private final String timeslot;

    //initialising variables
    public TimesAvailable(int timeslotId, String timeslot){
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
    }

    public int getTimeslotId(){
        return this.timeslotId;
    }

    public String getTimeslot(){
        return this.timeslot;
    }
}

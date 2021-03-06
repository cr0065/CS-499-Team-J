/*
 * Name: Studentgroup
 * Authors: Cameron Ramos & Ben Davis
 * Date: 12/7/2021
 * Purpose: Getters and Setters for Student Group
 */
package AddDatatoJTable;

public class Studentgroup {



    //declaring variables

    private final int groupId;
    private final int groupSize;
    private final int courseIds[];


    //initializing variables

    public Studentgroup(int groupId, int groupSize, int courseIds[]){
        this.groupId = groupId;
        this.groupSize = groupSize;
        this.courseIds = courseIds;
    }

    public int getGroupId(){
        return this.groupId;
    }

    public int getGroupSize(){
        return this.groupSize;
    }

    public int[] getCourseIds(){
        return this.courseIds;
    }


}

package AddDatatoJTable;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser extends JFrame {
    
    
    public static Schedule ParseInput(File filepath){ //BufferedReader infile
        if(filepath == null){
            return null;
        }
        int[] To_Teach = { };
        int[] small = {};
        int[] med = {};
        int[] large = {};
        int[] xlarge = {};
        int amount = 0;
        int amount2 = 0;
        String line = "";  
        String splitBy = ",";
        Schedule schedule = new Schedule();
        try
        {
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader file = new BufferedReader(new FileReader(filepath.getAbsolutePath()));
            while ((line = file.readLine()) != null)   //returns a Boolean value
            {

                String[] ScheduleLine = line.split(splitBy);    // use comma as separator

                schedule.addTimeslot(1, "MW 8:00 - 9:20");
                schedule.addTimeslot(2, "MW 9:40 - 11:00");
                schedule.addTimeslot(3, "MW 11:20 - 12:40");
                schedule.addTimeslot(4, "MW 1:00 - 2:20");
                schedule.addTimeslot(5, "MW 2:40 - 4:00");
                schedule.addTimeslot(6, "MW 4:20 - 5:40");
                schedule.addTimeslot(7, "TR 8:00 - 9:20");
                schedule.addTimeslot(8, "TR 9:40 - 11:00");
                schedule.addTimeslot(9, "TR 11:20 - 12:40");
                schedule.addTimeslot(10, "TR 1:00 - 2:20");
                schedule.addTimeslot(11, "TR 2:40 - 4:00");
                schedule.addTimeslot(12, "TR 4:20 - 5:40");

                if(ScheduleLine[0].equals("Room")) {
                    System.out.println("Room ID: " + ScheduleLine[1] + " Room Name: "
                            + ScheduleLine[2] + " Cap: " + ScheduleLine[3]);
                    schedule.addRoom(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2], Integer.parseInt(ScheduleLine[3]));
                    System.out.println(schedule.getRoom(Integer.parseInt(ScheduleLine[1])));
                }
                else if (ScheduleLine[0].equals("Instructor")) {
                    System.out.println("Instructor ID: " + ScheduleLine[1] + " Instructor Name: "
                            + ScheduleLine[2] + " Preferred Classroom: "
                            + ScheduleLine[3] + " Preferred Time: " + ScheduleLine[4]);
                    if(ScheduleLine[4].equals("null")) {
                        schedule.addProfessor(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2],
                                Integer.parseInt(ScheduleLine[3]));
                    }
                    else {
                        schedule.addProfessor(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2],
                                Integer.parseInt(ScheduleLine[3]), Integer.parseInt(ScheduleLine[4]));
                    }
                    To_Teach[amount] = Integer.parseInt(ScheduleLine[1]);
                    amount++;
                }
                else if (ScheduleLine[0].equals("Course")) {
                    System.out.println("Course ID: " + ScheduleLine[1] + " Course Name: "
                            + ScheduleLine[2] + " Course Full Name: "
                            + ScheduleLine[3] + " Enrolled: " + ScheduleLine[4]);
                    schedule.addCourse(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2], ScheduleLine[3], To_Teach);
                    if(Integer.parseInt(ScheduleLine[4]) <= 20) {
                        small[amount2] = Integer.parseInt(ScheduleLine[1]);
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 30) {
                        med[amount2] = Integer.parseInt(ScheduleLine[1]);
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 30) {
                        large[amount2] = Integer.parseInt(ScheduleLine[1]);
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 30) {
                        xlarge[amount2] = Integer.parseInt(ScheduleLine[1]);
                    }
                    amount2++;
                }
                else {
                    file.close();
                }
                schedule.addGroup(1, 20, small);
                schedule.addGroup(2, 30, med);
                schedule.addGroup(3, 40, large);
                schedule.addGroup(4, 50, xlarge);
            }
        }
        catch (IOException e)
        {

        }
        System.out.println(schedule);
        return schedule;
    }
}

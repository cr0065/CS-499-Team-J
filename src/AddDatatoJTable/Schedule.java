/*
 * Name: Schedule
 * Authors: Cameron Ramos & Ben Davis
 * Date: 12/7/2021
 * Purpose: This class is responsible for knowing all the data for the scheduling algorithm it also reads evaluate fitness
 */

package AddDatatoJTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Schedule {
    private final HashMap<Integer, Classroom> rooms;
    private final HashMap<Integer, Professor> professors;
    private final HashMap<Integer, Course> courses;
    private final HashMap<Integer, Studentgroup> groups;
    private final HashMap<Integer, TimesAvailable> timeslots;
    private final HashMap<Integer, List<Class>> roomMap;
    private final HashMap<Integer, List<Class>> profMap;
    private final HashMap<Integer, List<Class>> courseMap;
    private final HashMap<Integer, List<Class>> groupMap;
    private Class classes[];

    public HashMap<Integer, List<Class>> getRoomMap() {
        return roomMap;
    }

    public HashMap<Integer, List<Class>> getProfMap() {
        return profMap;
    }

    public HashMap<Integer, List<Class>> getCourseMap() {
        return courseMap;
    }

    public HashMap<Integer, List<Class>> getGroupMap() {
        return groupMap;
    }

    private int numClasses = 0;


    public Schedule() {
        this.rooms = new HashMap<Integer, Classroom>();
        this.professors = new HashMap<Integer, Professor>();
        this.courses = new HashMap<Integer, Course>();
        this.groups = new HashMap<Integer, Studentgroup>();
        this.timeslots = new HashMap<Integer, TimesAvailable>();

        this.roomMap = new HashMap<>();
        this.profMap = new HashMap<>();
        this.courseMap = new HashMap<>();
        this.groupMap = new HashMap<>();
    }

    public Schedule(Schedule cloneable) {
        this.rooms = cloneable.getRooms();
        this.professors = cloneable.getProfessors();
        this.courses = cloneable.getCourses();
        this.groups = cloneable.getGroups();
        this.timeslots = cloneable.getTimeslots();

        this.roomMap = new HashMap<>();
        this.profMap = new HashMap<>();
        this.courseMap = new HashMap<>();
        this.groupMap = new HashMap<>();
    }

    private HashMap<Integer, Studentgroup> getGroups() {
        return this.groups;
    }

    private HashMap<Integer, TimesAvailable> getTimeslots() {
        return this.timeslots;
    }

    private HashMap<Integer, Course> getCourses() {
        return this.courses;
    }

    private HashMap<Integer, Professor> getProfessors() {
        return this.professors;
    }



    public void addRoom(int roomId, String roomName, int capacity) {
        this.rooms.put(roomId, new Classroom(roomId, roomName, capacity));
    }



    public void addProfessor(int professorId, String professorName) {
        this.professors.put(professorId, new Professor(professorId, professorName));
    }



    public void addProfessor(int professorId, String professorName, int preferred_room){
        this.professors.put(professorId, new Professor(professorId, professorName, preferred_room));
    }



    public void addProfessor(int professorId, String professorName, int preferred_room, int preferred_time){
        this.professors.put(professorId, new Professor(professorId, professorName, preferred_room, preferred_time));
    }


    public void addCourse(int courseId, String courseCode, String course, int professorIds[]) {
        this.courses.put(courseId, new Course(courseId, courseCode, course, professorIds));
    }



    public void addGroup(int groupId, int groupSize, int courseIds[]) {
        this.groups.put(groupId, new Studentgroup(groupId, groupSize, courseIds));
        this.numClasses = 0;
    }



    public void addTimeslot(int timeslotId, String timeslot) {
        this.timeslots.put(timeslotId, new TimesAvailable(timeslotId, timeslot));
    }


    // accepts individual, checks course and student groups and creates an object out of them.
    // it then assigns the rest of the information to them
    public void createClasses(Individual individual) {

        Class classes[] = new Class[this.getNumClasses()];

        int chromosome[] = individual.getChromosome();
        int chromosomePos = 0;
        int classIndex = 0;

        for (Studentgroup group : this.getGroupsAsArray()) {
            int courseIds[] = group.getCourseIds();
            for (int courseId : courseIds) {

                Class newClass = new Class(classIndex, group.getGroupId(), courseId);
                newClass.setTimeslot(chromosome[chromosomePos]);
                chromosomePos++;
                newClass.setRoomId(chromosome[chromosomePos]);
                chromosomePos++;
                newClass.setProfessor(chromosome[chromosomePos]);
                chromosomePos++;
                this.roomMap.putIfAbsent(newClass.getRoomId(), new ArrayList<>());
                this.groupMap.putIfAbsent(newClass.getGroupId(), new ArrayList<>());
                this.courseMap.putIfAbsent(newClass.getCourseId(), new ArrayList<>());
                this.profMap.putIfAbsent(newClass.getProfessorId(), new ArrayList<>());
                this.roomMap.get(newClass.getRoomId()).add(newClass);
                this.groupMap.get(newClass.getGroupId()).add(newClass);
                this.courseMap.get(newClass.getCourseId()).add(newClass);
                this.profMap.get(newClass.getProfessorId()).add(newClass);
                classes[classIndex] = newClass;
                classIndex++;
            }
        }
        this.classes = classes;
    }

    public Classroom getRoom(int roomId) {
        if (!this.rooms.containsKey(roomId)) {
            System.out.println("Rooms doesn't contain key " + roomId);
        }
        return (Classroom) this.rooms.get(roomId);
    }

    public HashMap<Integer, Classroom> getRooms() {
        return this.rooms;
    }

    public Classroom getRandomRoom() {
        Object[] rooms = this.rooms.values().toArray();
        Classroom room = (Classroom) rooms[(int) (rooms.length * Math.random())];
        return room;
    }

    public Professor getProfessor(int professorId) {
        return (Professor) this.professors.get(professorId);
    }

    public Course getCourse(int courseId) {
        return (Course) this.courses.get(courseId);
    }

    public int[] getGroupCourses(int groupId) {
        Studentgroup group = (Studentgroup) this.groups.get(groupId);
        return group.getCourseIds();
    }

    public Studentgroup getGroup(int groupId) {
        return (Studentgroup) this.groups.get(groupId);
    }


    public Studentgroup[] getGroupsAsArray() {
        return (Studentgroup[]) this.groups.values().toArray(new Studentgroup[this.groups.size()]);
    }

    public TimesAvailable getTimeslot(int timeslotId) {
        return (TimesAvailable) this.timeslots.get(timeslotId);
    }

    public TimesAvailable getRandomTimeslot() {
        Object[] timeslotArray = this.timeslots.values().toArray();
        TimesAvailable timeslot = (TimesAvailable) timeslotArray[(int)(timeslotArray.length * Math.random())];
        return timeslot;

    }

    public Class[] getClasses() {
        return this.classes;
    }

    public int getNumClasses() {
        if (this.numClasses > 0) {
            return this.numClasses;
        }
        int numClasses = 0;
        Studentgroup groups[] = (Studentgroup[]) this.groups.values().toArray(new Studentgroup[this.groups.size()]);
        for (Studentgroup group : groups) {
            numClasses += group.getCourseIds().length;
        }
        this.numClasses = numClasses;

        return this.numClasses;
    }

    // checks to see if any constraints are violated and keeps track of it
    public int calcClashes(int size) {

        int clashes = 100;
        for (Class classA : this.classes) {

            //  room capacity
            int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
            int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
            if (roomCapacity < groupSize) {
                clashes = clashes-33*size;
            }
            //room occupied
            for (Class classB : this.classes) {
                if (classA.getRoomId() == classB.getRoomId()&& classA.getTimeslotId() == classB.getTimeslotId()&& classA.getClassId() != classB.getClassId()) {
                    clashes = clashes-33*size;
                    break;
                }
            }
            //professor available
            for (Class classB : this.classes) {
                if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId() && classA.getClassId() != classB.getClassId()) {
                    clashes = clashes-33*size;
                    break;
                }
            }
            //professor preferred room check
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getProfessorId();
                int tmp_Room=classB.getRoomId();
                if (this.getProfessor(tmp_Prof).getPreferedroom()== tmp_Room){
                    clashes++;
                }
            }
            // professor preferred time
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getProfessorId();
                int tmp_Time=classB.getTimeslotId();
                if (this.getProfessor(tmp_Prof).getPreferedtime()== tmp_Time){
                    clashes=clashes+2;
                }
            }
        }
        return clashes;
    }

    public String PrintClashes() {
        String clashes = "";
        for (Class classA : this.classes) {
            //  room capacity
            int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
            int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
            if (roomCapacity < groupSize) {
                clashes = "There is no class big enough for the amount of students \n" +
                        "please find a larger room available.";
                break;
            }

            //room occupied
            for (Class classB : this.classes) {
                if (classA.getRoomId() == classB.getRoomId()&& classA.getTimeslotId() == classB.getTimeslotId()&& classA.getClassId() != classB.getClassId()) {
                    clashes = "Room is already occupied: " + this.getRoom(classA.getRoomId()).getRoomNumber() + "\n" +
                            "Please try to get more available rooms to teach in.";
                    break;
                }
            }

            //professor available
            for (Class classB : this.classes) {
                if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId() && classA.getClassId() != classB.getClassId()) {
                    clashes = this.getProfessor(classA.getProfessorId()).getProfessorName() + " is not available to teach \n" +
                            "add more professors or lighten the load to teach.";
                    break;
                }
            }
            //professor preferred room check
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getProfessorId();
                int tmp_Room=classB.getRoomId();
                if (this.getProfessor(tmp_Prof).getPreferedroom()== tmp_Room){
                    clashes = this.getProfessor(classB.getProfessorId()).getProfessorName()
                            + " did not receive their preferred room.";
                    break;
                }
            }
            // professor preferred time
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getProfessorId();
                int tmp_Time=classB.getTimeslotId();
                if (this.getProfessor(tmp_Prof).getPreferedtime()== tmp_Time){
                    clashes =  this.getProfessor(classB.getProfessorId()).getProfessorName()
                            + " did not receive their preferred time.";
                    break;
                }
            }
        }
        return clashes;
    }


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddDatatoJTable;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aceth
 */
public class AddDataToJTable extends javax.swing.JFrame {

    /**
     * Creates new form AddDataToJTable
     */
    private Scanner input;
    private Schedule ParsedSchedule = null;
    private File file = null;
    public AddDataToJTable() {
        initComponents();
    }

    public Schedule ParseInput(File filepath){ //BufferedReader infile
        if(filepath == null){
            return null;
        }
        int[] To_Teach = {0};
        int[] small = {};
        int[] med = {};
        int[] large = {};
        int[] xlarge = {};
        int amount = 0;
        int amount2 = 0;
        String line = "";
        String splitBy = ",";
        Schedule schedule = new Schedule();

        // First try loop is to get the information of our data
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader file = new BufferedReader(new FileReader(filepath.getAbsolutePath()));
            while ((line = file.readLine()) != null)   //returns a Boolean value
            {

                String[] ScheduleLine = line.split(splitBy);    // use comma as separator

                if(ScheduleLine[0].equals("Room")) {
                    jTextArea1.append("Room ID: " + ScheduleLine[1] + " Room Name: "
                            + ScheduleLine[2] + " Cap: " + ScheduleLine[3] + "\n");
                }
                else if (ScheduleLine[0].equals("Instructor")) {
                    jTextArea1.append("Instructor ID: " + ScheduleLine[1] + " Instructor Name: "
                            + ScheduleLine[2] + " Preferred Classroom: "
                            + ScheduleLine[3] + " Preferred Time: " + ScheduleLine[4] + "\n");
                    amount++;
                    System.out.println(amount);
                }
                else if (ScheduleLine[0].equals("Course")) {
                    jTextArea1.append("Course ID: " + ScheduleLine[1] + " Course Name: "
                            + ScheduleLine[2] + " Course Full Name: "
                            + ScheduleLine[3] + " Enrolled: " + ScheduleLine[4] + "\n");
                    amount2++;
                    System.out.println(amount2);
                }
                else {
                    file.close();
                }
            }
        }
        catch (IOException e)
        {

        }
        /*
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader file = new BufferedReader(new FileReader(filepath.getAbsolutePath()));
            while ((line = file.readLine()) != null)   //returns a Boolean value
            {

                String[] ScheduleLine = line.split(splitBy);    // use comma as separator

                if(ScheduleLine[0].equals("Room")) {
                    jTextArea1.append("Room ID: " + ScheduleLine[1] + " Room Name: "
                            + ScheduleLine[2] + " Cap: " + ScheduleLine[3] + "\n");
                }
                else if (ScheduleLine[0].equals("Instructor")) {
                    jTextArea1.append("Instructor ID: " + ScheduleLine[1] + " Instructor Name: "
                            + ScheduleLine[2] + " Preferred Classroom: "
                            + ScheduleLine[3] + " Preferred Time: " + ScheduleLine[4] + "\n");
                    amount++;
                    System.out.println(amount);
                }
                else if (ScheduleLine[0].equals("Course")) {
                    jTextArea1.append("Course ID: " + ScheduleLine[1] + " Course Name: "
                            + ScheduleLine[2] + " Course Full Name: "
                            + ScheduleLine[3] + " Enrolled: " + ScheduleLine[4] + "\n");
                    amount2++;
                    System.out.println(amount2);
                }
                else {
                    file.close();
                }
            }
        }
        catch (IOException e)
        {

        }

        */
        return schedule;
    }

   public static Schedule initializeSchedule() {

        Schedule schedule = new Schedule();

        // Assigns information for the room along with ID's
        schedule.addRoom(1, "N327", 50);
        schedule.addRoom(2, "N105", 50);
        schedule.addRoom(3, "N218", 50);
        schedule.addRoom(4, "N315", 55);
        schedule.addRoom(5, "N308", 50);
        schedule.addRoom(6,"N328", 50);
        schedule.addRoom(7, "N324", 50);

        // Assigns information for the time slots
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

        // Assigns information for the professors along with preferred room and preferred time
        schedule.addProfessor(1, "Hardin Danny", 2,8);
        schedule.addProfessor(2, "Williamson James",2);
        schedule.addProfessor(3, "Rochowiak Daniel",3);
        schedule.addProfessor(4, "Poole Jeffrey",1);
        schedule.addProfessor(5, "Allen Mary", 5, 6);
        schedule.addProfessor(6, "Rochowiak Daniel", 1, 10);
        schedule.addProfessor(7, "Newman Timothy",4);

        // Assigns information to the course the third field is assigning which professors can teach those classes
        schedule.addCourse(1, "CS326", "Algorithm", new int[] { 1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(2, "CS421", "Database", new int[] { 1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(3, "CS400", "Cloud Computing", new int[] { 1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(4, "CS218", "Web Development", new int[] { 1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(5, "CS310", "Application Engineering", new int[] { 1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(6, "CS421", "Data Science", new int[] { 1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(7, "CS303", "Business Intelligence", new int[]{1, 2, 3 ,4, 5, 6, 7});
        schedule.addCourse(8, "CS121", "Intro to JAVA", new int[] {1, 2, 3 ,4, 5, 6, 7});

        // Assigns student group information
        schedule.addGroup(1, 25, new int[] { 1, 3, 4, 8});
        schedule.addGroup(2, 30, new int[] { 2, 3, 5, 6, 8 });
        schedule.addGroup(3, 18, new int[] { 3, 4, 5 });
        schedule.addGroup(4, 25, new int[] { 1, 4 ,7});

        // Returns the created schedule
        return schedule;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Browse //");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Import (Excel)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Export (Excel)");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Run");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Generate Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(102, 102, 102))))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (evt.getSource() == jButton1) {
            final JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV Files", "csv");
            fc.setFileFilter(csvFilter);
            //In response to a button click:
            int returnVal = fc.showOpenDialog(AddDataToJTable.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println("Opening: " + file.getName());
                System.out.println("Opening: " + file.getAbsolutePath());
                //Scanner input;
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //BufferedReader infile = new BufferedReader(new FileReader());

        if(file==null){
            showMessageDialog(null, "No File Selected, Aborting Import", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else{
            ParsedSchedule = new Schedule();
            ParsedSchedule = ParseInput(file);
            System.out.println(file.getName()+" Imported");
        }

    }
    //Export button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        File outFile;
        if (evt.getSource() == jButton6) {
            final JFileChooser fc = new JFileChooser(new File("c:\\"));
            fc.setFileFilter(new FileNameExtensionFilter(".csv", "Excel Document"));
            //In response to a button click:
            int returnVal = fc.showSaveDialog(null); //AddDataToJTable.this
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String content  =  jTextArea1.getText();
                outFile = fc.getSelectedFile();
                try {
                    FileWriter fw = new FileWriter(fc.getSelectedFile());
                    fw.write(content);
                    fw.flush();
                    fw.close();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
                System.out.println("Save as file: " + outFile.getAbsolutePath());
                System.out.println("File Exported");

            }
        }


    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jTextArea1.setText("");
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        showMessageDialog(null, "Program is running give \n approx. 60 - 180s for run time",
                "Notification", JOptionPane.INFORMATION_MESSAGE);
        if(ParsedSchedule==null){
            showMessageDialog(null, "No File imported, aborting schdule creation", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println(ParsedSchedule.getProfessor(1));

        GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);
        Population population = ga.initializingPopulation(ParsedSchedule);
        ga.calcPopulation(population, ParsedSchedule);
        int generation = 1;

        while (ga.isTerminating(generation, 100) == false && ga.isTerminating(population) == false) {
            System.out.println("Generation No." + generation);
            population = ga.crossoverPopulation(population);
            population = ga.mutatingPopulation(population, ParsedSchedule);
            ga.calcPopulation(population, ParsedSchedule);

            generation++;
        }

        // Print fitness
        ParsedSchedule.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Clashes: " + ParsedSchedule.calcClashes());

        if(population.getFittest(0).getFitness() <= 0){
            showMessageDialog(null, ParsedSchedule.PrintClashes(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        else{
            showMessageDialog(null, "Schedule Generation Successful", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        Class classes[] = ParsedSchedule.getClasses();
        jTextArea1.append("CLASS: " + "COURSE: " + "ROOM: " + "PROFESSOR: " + "TIMESLOT: " +"\n");
        int classIndex = 1;
        for (Class bestClass : classes) {
            jTextArea1.append(classIndex + ": ");
            jTextArea1.append(ParsedSchedule.getCourse(bestClass.getCourseId()).getCourseName() + ", ");
            jTextArea1.append(ParsedSchedule.getRoom(bestClass.getRoomId()).getRoomNumber() + ", ");
            jTextArea1.append(ParsedSchedule.getProfessor(bestClass.getProfessorId()).getProfessorName() + ", ");
            jTextArea1.append(ParsedSchedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot() + "\n");
            classIndex++;
        }
        //PrintClassAll(schedule);
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        File outFile;
        if (evt.getSource() == jButton6) {
            final JFileChooser fc = new JFileChooser(new File("c:\\"));
            fc.setFileFilter(new FileNameExtensionFilter(".docx", "Word Document"));
            //In response to a button click:
            int returnVal = fc.showSaveDialog(null); //AddDataToJTable.this
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String content  =  jTextArea1.getText();
                outFile = fc.getSelectedFile();
                try {
                    FileWriter fw = new FileWriter(fc.getSelectedFile());
                    fw.write(content);
                    fw.flush();
                    fw.close();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
                 System.out.println("Save as file: " + outFile.getAbsolutePath());
                 System.out.println("File Exported");

            }
        }


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddDataToJTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDataToJTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDataToJTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDataToJTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDataToJTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration
    int counter = 0;
    boolean RunClicked = false;
}

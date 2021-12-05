/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddDatatoJTable;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
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
    private Schedule ParsedSchedule = new Schedule();
    private File file = null;

    public AddDataToJTable() {
        initComponents();
    }

    public Schedule ParseInput(File filepath){ //BufferedReader infile
        if(filepath == null){
            return null;
        }
        int amount = 0;
        int amount_small = 0;
        int amount_med = 0;
        int amount_large = 0;
        int amount_xlarge = 0;
        int amount_xxlarge = 0;
        int amount_massive = 0;
        String line = "";
        String splitBy = ",";

        ParsedSchedule.addTimeslot(1, "MW 8:00 - 9:20");
        ParsedSchedule.addTimeslot(2, "MW 9:40 - 11:00");
        ParsedSchedule.addTimeslot(3, "MW 11:20 - 12:40");
        ParsedSchedule.addTimeslot(4, "MW 1:00 - 2:20");
        ParsedSchedule.addTimeslot(5, "MW 2:40 - 4:00");
        ParsedSchedule.addTimeslot(6, "MW 4:20 - 5:40");
        ParsedSchedule.addTimeslot(7, "TR 8:00 - 9:20");
        ParsedSchedule.addTimeslot(8, "TR 9:40 - 11:00");
        ParsedSchedule.addTimeslot(9, "TR 11:20 - 12:40");
        ParsedSchedule.addTimeslot(10, "TR 1:00 - 2:20");
        ParsedSchedule.addTimeslot(11, "TR 2:40 - 4:00");
        ParsedSchedule.addTimeslot(12, "TR 4:20 - 5:40");

        // First try loop is to get the information of our data
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader file = new BufferedReader(new FileReader(filepath.getAbsolutePath()));
            while ((line = file.readLine()) != null)   //returns a Boolean value
            {

                String[] ScheduleLine = line.split(splitBy);    // use comma as separator

                if(ScheduleLine[0].equals("Room")) {
                    jTextArea1.append("Room," + ScheduleLine[1] + ","
                            + ScheduleLine[2] + "," + ScheduleLine[3] + "\n");
                }
                else if (ScheduleLine[0].equals("Instructor")) {
                    jTextArea1.append("Instructor," + ScheduleLine[1] + ","
                            + ScheduleLine[2] + ","
                            + ScheduleLine[3] + "," + ScheduleLine[4] + "\n");
                    amount++;
                }
                else if (ScheduleLine[0].equals("Course")) {
                    jTextArea1.append("Course," + ScheduleLine[1] + ","
                            + ScheduleLine[2] + ","
                            + ScheduleLine[3] + "," + ScheduleLine[4] + "\n");
                    if(Integer.parseInt(ScheduleLine[4]) <= 20) {
                        amount_small++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 30) {
                        amount_med++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 40) {
                        amount_large++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 50) {
                        amount_xlarge++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 60) {
                        amount_xxlarge++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) >= 120) {
                        amount_massive++;
                    }
                }
                else {
                    file.close();
                }
            }
        }
        catch (IOException e)
        {

        }

        int[] To_Teach = new int[amount];
        int[] small = new int[amount_small];
        int[] med = new int[amount_med];
        int[] large = new int[amount_large];
        int[] xlarge = new int[amount_xlarge];
        int[] xxlarge = new int[amount_xxlarge];
        int[] massive = new int[amount_massive];
        int current = 0;
        int current_small = 0;
        int current_med = 0;
        int current_large = 0;
        int current_xlarge = 0;
        int current_xxlarge = 0;
        int current_massive = 0;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader file = new BufferedReader(new FileReader(filepath.getAbsolutePath()));
            while ((line = file.readLine()) != null)   //returns a Boolean value
            {

                String[] ScheduleLine = line.split(splitBy);    // use comma as separator

                if(ScheduleLine[0].equals("Room")) {
                    ParsedSchedule.addRoom(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2], Integer.parseInt(ScheduleLine[3]));
                }
                else if (ScheduleLine[0].equals("Instructor")) {
                    if(ScheduleLine[3].equals("null")) {
                        ParsedSchedule.addProfessor(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2]);
                    }
                    else if (ScheduleLine[4].equals("null")) {
                        ParsedSchedule.addProfessor(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2],
                                Integer.parseInt(ScheduleLine[3]));
                    }
                    else {
                        ParsedSchedule.addProfessor(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2],
                                Integer.parseInt(ScheduleLine[3]), Integer.parseInt(ScheduleLine[4]));
                    }
                    To_Teach[current] = Integer.parseInt(ScheduleLine[1]);
                    current++;
                }
                else if (ScheduleLine[0].equals("Course")) {
                    ParsedSchedule.addCourse(Integer.parseInt(ScheduleLine[1]), ScheduleLine[2], ScheduleLine[3], To_Teach);
                    if(Integer.parseInt(ScheduleLine[4]) <= 20) {
                        small[current_small] = Integer.parseInt(ScheduleLine[1]);
                        current_small++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 30) {
                        med[current_med] = Integer.parseInt(ScheduleLine[1]);
                        current_med++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 40) {
                        large[current_large] = Integer.parseInt(ScheduleLine[1]);
                        current_large++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 50) {
                        xlarge[current_xlarge] = Integer.parseInt(ScheduleLine[1]);
                        current_xlarge++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) <= 60) {
                        xxlarge[current_xxlarge] = Integer.parseInt(ScheduleLine[1]);
                        current_xxlarge++;
                    }
                    else if (Integer.parseInt(ScheduleLine[4]) >= 120) {
                        massive[current_massive] = Integer.parseInt(ScheduleLine[1]);
                        current_massive++;
                    }
                }
                else {
                    file.close();
                }

                if (small.length != 0)
                    ParsedSchedule.addGroup(1, 20, small);
                if (med.length != 0)
                    ParsedSchedule.addGroup(2, 30, med);
                if (large.length != 0)
                    ParsedSchedule.addGroup(3, 40, large);
                if (xlarge.length != 0)
                    ParsedSchedule.addGroup(4, 50, xlarge);
                if (xxlarge.length != 0)
                    ParsedSchedule.addGroup(5, 60, xxlarge);
                if (massive.length != 0)
                    ParsedSchedule.addGroup(5, 120, massive);
            }
        }
        catch (IOException e)
        {

        }

        return ParsedSchedule;
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
            ParsedSchedule = ParseInput(file);
            System.out.println(file.getName()+" Imported");
        }

    }
    //Export button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            String s = jTextArea1.getText();
            if (s.length() > 0) {
                FileDialog fd = new FileDialog(this, "Save File As", FileDialog.SAVE);
                fd.setFile(".csv");
                fd.setDirectory("c:\\windows\\temp");
                fd.setVisible(true);
                String path = fd.getDirectory() + fd.getFile();

                FileOutputStream fos = new FileOutputStream(path);
                byte[] b = s.getBytes();
                fos.write(b);
                fos.close();
            }
        }
        catch (Exception e){

        }


    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jTextArea1.setText("");
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jTextArea1.setText("");
        showMessageDialog(null, "Program is running give \n approx. 60 - 180s for run time",
                "Notification", JOptionPane.INFORMATION_MESSAGE);
        if(ParsedSchedule==null){
            showMessageDialog(null, "No File imported, aborting schdule creation", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println(ParsedSchedule.getCourse(1));
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
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Clashes: " + ParsedSchedule.calcClashes(100));

        if(population.getFittest(0).getFitness() <= 0){
            showMessageDialog(null, ParsedSchedule.PrintClashes(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        else{
            showMessageDialog(null, "Schedule Generation Successful", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        Class classes[] = ParsedSchedule.getClasses();
        jTextArea1.append("CLASS:"
                + "COURSE:"
                + "ROOM:"
                + "PROFESSOR:"
                + "DATE/TIME:" +"\n");
        for (Class bestClass : classes) {
            jTextArea1.append(ParsedSchedule.getCourse(bestClass.getCourseId()).getCourseName() + ",");
            jTextArea1.append(ParsedSchedule.getRoom(bestClass.getRoomId()).getRoomNumber() + ",");
            jTextArea1.append(ParsedSchedule.getProfessor(bestClass.getProfessorId()).getProfessorName() + ",");
            jTextArea1.append(ParsedSchedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot() + "\n");
        }
        //PrintClassAll(schedule);
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            String s = jTextArea1.getText();
            if (s.length() > 0) {
                FileDialog fd = new FileDialog(this, "Save File As", FileDialog.SAVE);
                fd.setFile(".docx");
                fd.setDirectory("c:\\windows\\temp");
                fd.setVisible(true);
                String path = fd.getDirectory() + fd.getFile();

                FileOutputStream fos = new FileOutputStream(path);
                byte[] b = s.getBytes();
                fos.write(b);
                fos.close();
            }
        }
        catch (Exception e){

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

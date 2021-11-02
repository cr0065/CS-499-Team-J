/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddDatatoJTable;

import java.util.List;

/**
 *
 * @author aceth
 */
public class AddDataToJTable extends javax.swing.JFrame {

    /**
     * Creates new form AddDataToJTable
     */
    public AddDataToJTable() {
        initComponents();
    }

    public static Schedule initializeSchedule() {

        Schedule schedule = new Schedule();

        // Assigns information for the room along with ID's
        schedule.addRoom(1, "A", 15);
        schedule.addRoom(2, "B", 30);
        schedule.addRoom(4, "C", 20);
        schedule.addRoom(5, "D", 25);
        schedule.addRoom(3, "E", 20);

        // Assigns information for the time slots *needs to remove the friday times and add proper times in place*
        schedule.addTimeslot(1, "Monday 9:00 - 11:00");
        schedule.addTimeslot(2, "Monday 11:00 - 13:00");
        schedule.addTimeslot(3, "Monday 13:00 - 15:00");
        schedule.addTimeslot(4, "Tuesday 9:00 - 11:00");
        schedule.addTimeslot(5, "Tuesday 11:00 - 13:00");
        schedule.addTimeslot(6, "Tuesday 13:00 - 15:00");
        schedule.addTimeslot(7, "Wednesday 9:00 - 11:00");
        schedule.addTimeslot(8, "Wednesday 11:00 - 13:00");
        schedule.addTimeslot(9, "Wednesday 13:00 - 15:00");
        schedule.addTimeslot(10, "Thursday 9:00 - 11:00");
        schedule.addTimeslot(11, "Thursday 11:00 - 13:00");
        schedule.addTimeslot(12, "Thursday 13:00 - 15:00");
        schedule.addTimeslot(13, "Friday 9:00 - 11:00");
        schedule.addTimeslot(14, "Friday 11:00 - 13:00");
        schedule.addTimeslot(15, "Friday 13:00 - 15:00");

        // Assigns information for the professors along with preferred room and preferred time
        schedule.addProfessor(1, "Kal Bugrara", 2,8);
        schedule.addProfessor(2, "Robin Hilyard",2);
        schedule.addProfessor(3, "Yusuf Ozbek",3);
        schedule.addProfessor(4, "Vishal Chawla",1);
        schedule.addProfessor(5, "Amuthan", 5, 6);
        schedule.addProfessor(6, "Tejas Parikh", 1, 10);

        // Assigns information to the course the third field is assigning which professors can teach those classes
        schedule.addCourse(1, "AA", "Algorithm", new int[] { 1, 2 ,4});
        schedule.addCourse(2, "DD", "Database", new int[] { 1, 3 ,5});
        schedule.addCourse(3, "CC", "Cloud Computing", new int[] { 1, 2 ,5});
        schedule.addCourse(4, "WD", "Web Development", new int[] { 3, 4 ,6});
        schedule.addCourse(5, "AE", "Application Engineering", new int[] { 4 });
        schedule.addCourse(6, "DS", "Data Science", new int[] { 1, 4,6 });
        schedule.addCourse(7, "BI", "Business Intelligence", new int[]{2,5,6});

        // Assigns student group information, not really needed for us we could probably take this part out
        schedule.addGroup(1, 10, new int[] { 1, 3, 4 });
        schedule.addGroup(2, 30, new int[] { 2, 3, 5, 6 });
        schedule.addGroup(3, 18, new int[] { 3, 4, 5 });
        schedule.addGroup(4, 25, new int[] { 1, 4 ,7});
        schedule.addGroup(5, 20, new int[] { 2, 3, 5 });
        schedule.addGroup(6, 22, new int[] { 1, 4, 5 });
        schedule.addGroup(7, 16, new int[] { 1, 3 });
        schedule.addGroup(8, 18, new int[] { 2, 6 ,7});
        schedule.addGroup(9, 24, new int[] { 1, 6 });
        schedule.addGroup(10, 25, new int[] { 3, 4 ,7});

        // Returns the created schedule
        return schedule;
    }


    public static void PrintClassAll(Schedule schedule){
        Class classes[] = schedule.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            System.out.println("CLASS " + classIndex + ":");
            System.out.println("COURSE: " + schedule.getCourse(bestClass.getCourseId()).getCourseName());
            System.out.println("STUDENT GROUP: " + schedule.getGroup(bestClass.getGroupId()).getGroupId());
            System.out.println("ROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
            System.out.println("PROFESSOR: " + schedule.getProfessor(bestClass.getProfessorId()).getProfessorName());
            System.out.println("TIMESLOT: " + schedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            System.out.println("*****************************************************************");
            classIndex++;
        }
    }



    public static List<Class> getClasses(Schedule schedule, String type, int id) {
        switch (type) {
            case "ROOM":
                return schedule.getRoomMap().get(id);
            case "PROF":
                return schedule.getProfMap().get(id);
            case "MODULE":
                return schedule.getCourseMap().get(id);
            case "GROUP":
                return schedule.getGroupMap().get(id);
            default:
                return null;
        }
    }



    public static void PrintClasses(Schedule schedule, String type, int id){
        List<Class> classes = getClasses(schedule, type, id);
        for (Class bestClass: classes){
            printClass(schedule, bestClass);
        }
    }


    public static void printClass(Schedule schedule, Class bestClass) {
        System.out.println("COURSE: " + schedule.getCourse(bestClass.getCourseId()).getCourseName());
        System.out.println("STUDENT GROUP: " + schedule.getGroup(bestClass.getGroupId()).getGroupId());
        System.out.println("CLASSROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
        System.out.println("PROFESSOR: " + schedule.getProfessor(bestClass.getProfessorId()).getProfessorName());
        System.out.println("TIMESLOT: " + schedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
        System.out.println("*********************************************************************");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Browse //");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println("Hello World"); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.out.println("Hello World");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.out.println("Hello World");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.out.println("Hello World");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Schedule schedule = initializeSchedule();


        GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);


        Population population = ga.initializingPopulation(schedule);


        ga.calcPopulation(population, schedule);


        int generation = 1;



        while (ga.isTerminating(generation, 100) == false && ga.isTerminating(population) == false) {

            System.out.println("Generation No." + generation + " Best fitness: " + population.getFittest(0).getFitness());


            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }

        // Print fitness
        schedule.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + schedule.calcClashes(100));

        PrintClassAll(schedule);
    }//GEN-LAST:event_jButton5ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

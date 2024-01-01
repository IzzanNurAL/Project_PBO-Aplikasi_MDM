package com.crud.mahasiswa;

import javax.swing.*;

public class Show {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Show::createGUI);
    }

    public static void createGUI(){
        DataInterface UI = new DataInterface();
        JPanel root = UI.getMainPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

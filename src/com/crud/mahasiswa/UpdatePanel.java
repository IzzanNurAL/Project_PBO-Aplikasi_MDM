package com.crud.mahasiswa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UpdatePanel {
    public UpdatePanel() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userNim, userName, userClass, userIpk;
                userNim = jStatNIM.getText();
                userName = jNama.getText();
                userClass = jKelas.getText();
                userIpk = jIpk.getText();
                if(!Objects.equals(userNim, "") && !Objects.equals(userName, "") && !Objects.equals(userClass, "") && !Objects.equals(userIpk, "")){
                    try{
                        preparedStatement = Connector.connectDB().prepareStatement("UPDATE mahasiswa SET nama=?, kelas=?, ipk=? WHERE nim=?;");
                        preparedStatement.setString(1,userName);
                        preparedStatement.setString(2,userClass);
                        preparedStatement.setString(3,userIpk);
                        preparedStatement.setString(4,userNim);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Update Data Telah Berhasil");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Isi Semua Data");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getUpdatePanel(){
        return mainUpdatePanel;
    }

    private PreparedStatement preparedStatement;
    private JPanel mainUpdatePanel;
    private JLabel jTitleUpdatePanel;
    private JTextField jStatNIM;
    private JTextField jNama;
    private JTextField jKelas;
    private JTextField jIpk;
    private JButton cancelButton;
    private JButton updateButton;
    private JLabel jPanelNimLabel;
    private JLabel jNimLabel;
    private JLabel jNamaLabel;
    private JLabel jKelasLabel;
    private JLabel jIpkLabel;
}

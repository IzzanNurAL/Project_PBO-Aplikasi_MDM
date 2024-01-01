package com.crud.mahasiswa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeletePanel {
    public DeletePanel() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userNim;
                userNim = jTextFieldNim.getText();
                if (!Objects.equals(userNim, "")){
                    try{
                        preparedStatement = Connector.connectDB().prepareStatement("DELETE FROM mahasiswa WHERE nim=?;");
                        preparedStatement.setString(1,userNim);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    }catch (SQLException err){
                        err.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Masukkan NIM Yang Akan Dihapus");
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

    public JPanel getDeletePanel(){
        return deletePanel;
    }
    public PreparedStatement preparedStatement;
    private JPanel deletePanel;
    private JLabel jTitleDeletePanel;
    private JPanel jPanelNIM;
    private JPanel jPanelButton;
    private JTextField jTextFieldNim;
    private JButton cancelButton;
    private JButton deleteButton;
    private JLabel jNimDeleteLabel;
    private JLabel jNimLabel;
}

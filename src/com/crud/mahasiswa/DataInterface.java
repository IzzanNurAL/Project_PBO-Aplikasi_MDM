package com.crud.mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataInterface {
    public DataInterface() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String statNim, statNama, statKelas, statIpk;
                statNim = jTextFieldNim.getText();
                statNama = jTextFieldName.getText();
                statKelas = jTextFieldClass.getText();
                statIpk = jTextFieldIpk.getText();

                try{
                    preparedStatement = Connector.connectDB().prepareStatement("INSERT INTO mahasiswa(nim,nama,kelas,ipk) values(?,?,?,?);");
                    preparedStatement.setString(1,statNim);
                    preparedStatement.setString(2,statNama);
                    preparedStatement.setString(3,statKelas);
                    preparedStatement.setString(4,statIpk);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Ditambahkan");
                }catch (SQLException err){
                    Logger.getLogger(DataInterface.class.getName()).log(Level.SEVERE, null, err);
                }
                jTextFieldNim.setText("");
                jTextFieldName.setText("");
                jTextFieldClass.setText("");
                jTextFieldIpk.setText("");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(DataInterface::createUpdateGUI);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(DataInterface::createDeleteGUI);
            }
        });
    }

    public JPanel getMainPanel(){
        showData();
        return mainPanel;
    }
    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData(){
        try{
            Object[] columnTitle = {"nim","nama","kelas","ipk"};
            tableModel = new DefaultTableModel(null,columnTitle);
            jTable.setModel(tableModel);

            //retrieve connection from DB
            Connection connection = Connector.connectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            //initiate result with SQL Query
            resultSet = statement.executeQuery("SELECT * FROM mahasiswa");
            while(resultSet.next()){
                Object[] data = {
                        resultSet.getString("nim"),
                        resultSet.getString("nama"),
                        resultSet.getString("kelas"),
                        resultSet.getString("ipk")
                };
                tableModel.addRow(data);
            }
        }catch (SQLException err){
            throw new RuntimeException(err);
        }
    }

    private static void createUpdateGUI(){
        UpdatePanel updateUI = new UpdatePanel();
        JPanel updateRoot = updateUI.getUpdatePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(updateRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private static void createDeleteGUI(){
        DeletePanel deleteUI = new DeletePanel();
        JPanel deleteRoot = deleteUI.getDeletePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(deleteRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    private JPanel mainPanel;
    private JLabel jTitlePanel;
    private JTable jTable;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel jFirstPanel;
    private JPanel jSecondPanel;
    private JPanel jThirdPanel;
    private JLabel jLabelName;
    private JTextField jTextFieldName;
    private JTextField jTextFieldNim;
    private JTextField jTextFieldClass;
    private JTextField jTextFieldIpk;
    private JLabel jLabelNim;
    private JLabel jLabelClass;
    private JLabel jLabelIpk;
    private JScrollPane jScrolls;
}

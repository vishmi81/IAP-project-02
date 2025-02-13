package com.example.demofx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteController {

    @FXML
    private TextField vehicleid;

    @FXML
    void cancelbtn(ActionEvent event) {
        System.exit(0);
    }



    @FXML
    void deletebtn(ActionEvent event) {

        int id = Integer.parseInt(vehicleid.getText());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iap_2","root","1234");

            //dynamic query
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicles WHERE id = ?;");
            preparedStatement.setObject(1,id);


            int i = preparedStatement.executeUpdate();

            if(i > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("delete Successful!");
                alert.showAndWait();
            } else{
                System.out.println("Failed !");
            }



        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



    }



}

package com.example.demofx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class UpdateController {

    @FXML
    private TextField txtbrand;

    @FXML
    private TextField txtgear;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtmodel;

    @FXML
    private TextField txtprice;

    @FXML
    void cancelbtn(ActionEvent event) {

        System.out.println("Exit");

    }

    @FXML
    void searchbtn(ActionEvent event) {

        int id = Integer.parseInt(txtid.getText());

        //load the installed driver to this class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iap_2","root","1234");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE id = ? ");

            preparedStatement.setObject(1,id);

            //execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                 txtbrand.setText(resultSet.getString(2));
                 txtmodel.setText(resultSet.getString(3));
                 txtgear.setText(String.valueOf(resultSet.getInt(4)));
                 txtprice.setText(String.valueOf(resultSet.getDouble(5)));


            }



        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void updatebtn(ActionEvent event) {

        String brand = txtbrand.getText();
        String model = txtmodel.getText();
        int gears = Integer.parseInt(txtgear.getText());
        double price = Double.parseDouble((txtprice.getText()));
        int id = Integer.parseInt(txtid.getText());

        //load the installed driver to this class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iap_2","root","1234");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE vehicles SET brand = ?, model = ? ,no_of_gerars =?, price = ? WHERE id = ?  ");
            preparedStatement.setObject(1,brand);
            preparedStatement.setObject(2,model);
            preparedStatement.setObject(3,gears);
            preparedStatement.setObject(4,price);
            preparedStatement.setObject(5,id);

            int i = preparedStatement.executeUpdate();

            if(i > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Update Successful!");
                alert.showAndWait();
            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("failed!");
                alert.showAndWait();
            }



        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }



}

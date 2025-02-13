package com.example.demofx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleController {

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtGears;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    void cancelbtn(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void savebtn(ActionEvent event) {

        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int gears = Integer.parseInt(txtGears.getText());
        double price = Double.parseDouble((txtPrice.getText()));

        //load the installed driver to this class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

           //create a connection with database server and database
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iap_2","root","1234");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicles(brand , model, no_of_gerars, price) values(?,?,?,?) ");
            preparedStatement.setObject(1,brand);
            preparedStatement.setObject(2,model);
            preparedStatement.setObject(3,gears);
            preparedStatement.setObject(4,price);

            int i = preparedStatement.executeUpdate();

            if(i > 0){
                System.out.println("Data Added Successfully !");
            } else{
                System.out.println("Failed !");
            }



        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }




}

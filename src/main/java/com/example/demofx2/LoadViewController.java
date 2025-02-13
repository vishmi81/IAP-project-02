package com.example.demofx2;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadViewController implements Initializable {

    @FXML
    private TableView<Vehicle> tblVehicle;



    @FXML
    void loadData(ActionEvent event) {
        //fx table config
        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfGears"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        ArrayList<Vehicle> vehicles = loadDataFromDB();
        tblVehicle.setItems(FXCollections.observableArrayList(vehicles));


    }

    public ArrayList<Vehicle> loadDataFromDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iap_2","root","1234");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles ");


            //execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Vehicle> list = new ArrayList();

            while(resultSet.next()){
                Vehicle vehicle = new Vehicle();

                vehicle.setId(resultSet.getInt(1));
                vehicle.setBrand(resultSet.getString(2));
                vehicle.setModel(resultSet.getString(3));
                vehicle.setNoOfGears(resultSet.getInt(4));
                vehicle.setPrice(resultSet.getDouble(5));

                list.add(vehicle);
            }
            return list;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfGears"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        ArrayList<Vehicle> vehicles = loadDataFromDB();
        tblVehicle.setItems(FXCollections.observableArrayList(vehicles));

    }
}

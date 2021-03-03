/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naturemorning.parallax.controllers;

import com.naturemorning.parallax.config.StageManager;
import com.naturemorning.parallax.models.Cashier;
import com.naturemorning.parallax.services.impl.CashierService;
import com.naturemorning.parallax.views.FxmlView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class ViewTableController implements Initializable {

    @FXML
    private TableView<Cashier> paymentTable;
    @FXML
    private TableColumn<Cashier, Long> colID;
    @FXML
    private TableColumn<Cashier, String> colName;
    @FXML
    private TableColumn<Cashier, String> colAge;
    @FXML
    private TableColumn<Cashier, String> colAmount;
    @FXML
    private TableColumn<Cashier, String> colPayment;
    @FXML
    private Button back;
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private CashierService cashierService;
    private ObservableList<Cashier> cashierList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paymentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        loadUserDetails();
    }

    @FXML
    private void back(ActionEvent event) {
        stageManager.switchScene(FxmlView.CASHIER);
    }

    private void setColumnProperties() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cashierName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("cashierAge"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

    }

    private void loadUserDetails() {
        cashierList.clear();
        cashierList.addAll(cashierService.findAll());
        paymentTable.setItems(cashierList);
    }
}

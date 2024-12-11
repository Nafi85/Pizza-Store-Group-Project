/**
 CSC 210 0900
 GROUP PROJECT: Pizza Store
 Group Members: Nahidul Islam Nafi, Bilal ALi, Aaron Davis
 Due: 12/04/2024
 **/
package com.example.pizzastorev2;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class LoginScene {
    // Memory
    PizzaStore myPizzaStore;

    // Fields
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Label resultLabel;
    public String title = "Employee Login";
    public Button loginButton;

    public Scene getScene(
        PizzaStore pizzaStoreData,
        EventHandler<ActionEvent> loginButtonHandler
    ) {

        // Use the current state of pizzaStore
        myPizzaStore = pizzaStoreData;

        // Create a GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Username input
        Label usernameLabel = new Label("Username:");
        usernameTextField = new TextField();

        // Password input
        Label passwordLabel = new Label("Password:");
        passwordTextField = new TextField();

        // Create a Button to login.
        loginButton = new Button("Login");

        // Create an empty Label to display the result.
        resultLabel = new Label();

        // Add components to the GridPane
        gridPane.add(usernameLabel, 0, 0); // Column 0, Row 0
        gridPane.add(usernameTextField, 1, 0); // Column 1, Row 0
        gridPane.add(passwordLabel, 0, 1); // Column 0, Row 1
        gridPane.add(passwordTextField, 1, 1); // Column 1, Row 1
        gridPane.add(loginButton, 1, 2); // Column 1, Row 2
        gridPane.add(resultLabel, 1, 3); // Column 1, Row 3

        // Register the event handler, using Lamda expression.
        loginButton.setOnAction(loginButtonHandler);

        // Create a Scene.
        return new Scene(gridPane,300,200);
    }

    // Internal function to get status of login
    public boolean onLogin(){
        // Hardcoded credentials for demonstration purposes
        String validUsername = myPizzaStore.getUsername();
        String validPassword = myPizzaStore.getPassword();

        // Compare the login details
        String enteredUsername = usernameTextField.getText();
        String enteredPassword = passwordTextField.getText();

        // handle login action
        if (enteredUsername.equals(validUsername) && enteredPassword.equals(validPassword)){
            resultLabel.setText("Login successful! Welcome.");
            resultLabel.setStyle("-fx-text-fill: green;"); // Green text for success
            return true;
        }else{
            resultLabel.setText("Invalid username or password. Try again.");
            resultLabel.setStyle("-fx-text-fill: red;"); // Red text for failure
            return false;
        }
    }
}

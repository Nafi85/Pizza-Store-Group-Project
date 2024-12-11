/**
 CSC 210 0900
 GROUP PROJECT: Pizza Store
 Group Members: Nahidul Islam Nafi, Bilal ALi, Aaron Davis
 Due: 12/04/2024
 **/
package com.example.pizzastorev2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * Pizza Store Login Scene
 */
public class PizzaStoreApp extends Application
{
    // State
    PizzaStore myPizzaStore = new PizzaStore();
    private Stage stageController;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void switchToGallery(Stage primaryStage){
        GalleryScene galleryScene = new GalleryScene();

        // Add the Scene to the Stage.
        primaryStage.setScene(galleryScene.getScene(myPizzaStore));

        // Set the stage title.
        primaryStage.setTitle(galleryScene.title);

        // Show the window.
        primaryStage.show();
    }

    public void switchToLogin(Stage primaryStage)
    {
        // login scene
        LoginScene loginScene = new LoginScene();

        // create button event
        EventHandler<ActionEvent> loginButtonHandler = event -> {
            boolean valid = loginScene.onLogin();
            if(valid){
                primaryStage.close();
                switchToGallery(primaryStage);
            }

            System.out.println("login attempt");
        };

        // Add the Scene to the Stage.
        primaryStage.setScene(loginScene.getScene(myPizzaStore, loginButtonHandler));

        // Set the stage title.
        primaryStage.setTitle(loginScene.title);

        // Show the window.
        primaryStage.show();
    }



    @Override
    public void start(Stage primaryStage) {
        // start the login
        switchToLogin(primaryStage);
        //
    }

}

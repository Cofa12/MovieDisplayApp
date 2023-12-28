/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class popularMovies extends Application {

    @Override
    public void start(Stage popular) {
        GridPane pane = new GridPane(1, 1);
        int currentRow = 1, currentColumn = 1;
        Scene scene = new Scene(pane, 300, 300);
        popular.setTitle("Popular Movies");
        popular.setScene(scene);
        popular.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

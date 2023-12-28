/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javafxproject;

import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 *
 * @author Dell
 */

/*
    What I did ? 
    - Clear button to clear all data in fields
    - When mouse enter the field the content will disappear 
    - When mouse enter the field the total will disappear
    - can't enter unnumeric values 
    - When press clear the total will disappear
 */
public class Javafxproject extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        VBox fxmlLoader = (VBox) FXMLLoader.load(getClass().getResource("categories.fxml"));
        Scene scene = new Scene(fxmlLoader, 300, 300);
        stage.setTitle("Categories");
        stage.setScene(scene);
        stage.show();
        
    }

}

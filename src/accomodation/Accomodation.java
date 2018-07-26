/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accomodation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Crazydude
 */
public class Accomodation extends Application {
    
    
    //project start method
    @Override
    public void start(Stage stage) throws Exception {
        
        // Login.fxml is the page that loads first soon after you run the program
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); 
   
        stage.initStyle(StageStyle.UNDECORATED);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    //main method
    public static void main(String[] args) {
        launch(args);
    }
    
}

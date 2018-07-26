/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accomodation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


//Login class
public class LoginController implements Initializable {

    //Declaration of  all elements used in the scene builder such as buttons, text fields and pane declaration
    @FXML
    private AnchorPane root;

    @FXML
    private Label close;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    //declaring database connection variables
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        conn = DbConnection.getConnection();
    }
    
    //close button  in the login interface(top right corner)
    @FXML
    void closeTab(MouseEvent event) {

        System.exit(0);
    }
    
    //method to be executed if the login button clicked
    @FXML
    void login_btnPress(ActionEvent event) throws SQLException, IOException {
        
        //declaring and assigning uuser inputs to variables
        String UserName = username.getText().trim();
        String Password = password.getText().trim();
        
        //username and password validation
        String sql = "SELECT * FROM login WHERE username = ? OR email = ? AND password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, UserName);
            ps.setString(3, Password);

            rs = ps.executeQuery();
            
            //condition to identify manager or wardens login
            if (rs.next()) {
                int id = rs.getInt("id");
                if (id == 1) {
                    Parent root = FXMLLoader.load(getClass().getResource("UserSpace.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Manager");
                    stage.show();
                } else if (id == 2) {
                    Parent root = FXMLLoader.load(getClass().getResource("WardenSpace.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Warden");
                    stage.show();

                }
            } else {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("The Username/Password is Incorrect!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //clears the text fields 
        username.clear();
        password.clear();
    }
    
    //method to be executed if the enter button was hit in the password text field
    @FXML
    private void on_EnterPress(ActionEvent event) {

        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    
                    //declaring and assigning uuser inputs to variables
                    String UserName = username.getText().trim();
                    String Password = password.getText().trim();
                    
                    //username and password validation
                    String sql = "SELECT * FROM login WHERE username = ? OR email = ? AND password = ?";
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, UserName);
                        ps.setString(2, UserName);
                        ps.setString(3, Password);
                        rs = ps.executeQuery();
                        
                        //condition to identify manager or wardens login
                        if (rs.next()) {
                            int id = rs.getInt("id");
                            if (id == 1) {
                                Parent root1 = FXMLLoader.load(LoginController.this.getClass().getResource("UserSpace.fxml"));
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                stage.setTitle("Manager");
                                stage.show();
                            } else if (id == 2) {
                                Parent root2 = FXMLLoader.load(LoginController.this.getClass().getResource("WardenSpace.fxml"));
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root2));
                                stage.setTitle("Warden");
                                stage.show();
                            }
                        } else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("The Username/Password is Incorrect!");
                            alert.showAndWait();
                        }
                    } catch (IOException | SQLException e) {
                    }
                    //clears the text fields 
                    username.clear();
                    password.clear();
                }
            }
        });

    }

}

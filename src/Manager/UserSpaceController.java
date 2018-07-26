/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

//all imports
import accomodation.DbConnection;
import accomodation.ManagerPortal;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class UserSpaceController implements Initializable {
    
    //Declaration of  all elements used in the scene builder such as buttons, text fields and pane declaration
    @FXML
    private TableColumn<ManagerPortal, String> col_id;
    @FXML
    private TableColumn<ManagerPortal, String> col_leaseNumber;
    @FXML
    private TableColumn<ManagerPortal, String> col_hallName;
    @FXML
    private TableColumn<ManagerPortal, String> col_hallNo;
    @FXML
    private TableColumn<ManagerPortal, String> col_roomNo;
    @FXML
    private TableColumn<ManagerPortal, String> col_studentName;
    @FXML
    private TableColumn<ManagerPortal, String> col_studentId;
    @FXML
    private TableColumn<ManagerPortal, String> col_occupancyStatus;
    @FXML
    private TableColumn<ManagerPortal, String> col_cleaningStatus;
    @FXML
    private JFXButton hall1_btn;
    @FXML
    private TableView<ManagerPortal> tableUser;   
    @FXML
    private VBox logout;
    @FXML
    private JFXButton hall2_btn;
    @FXML
    private JFXButton hall3_btn;
    @FXML
    private JFXButton logout_btn;
    @FXML
    private TextField hallName;
    @FXML
    private TextField roomNumber;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentId;
    @FXML
    private TextField leaseNumber;
    @FXML
    private ComboBox<String> combo_box;
    @FXML
    private GridPane gridPane;
    @FXML
    private JFXButton update_btn;
    @FXML
    private JFXButton delete_btn;
    @FXML
    private TextField id;
    @FXML
    private TextField cleaningStatus;
    @FXML
    private JFXButton allHall_btnpress;
    
    //declaring database connection variables
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    //data observableArrayList used to retreive infromation from table and displayed in the GUI
    ObservableList<ManagerPortal> data = FXCollections.observableArrayList();
    //data2 observableArrayList used to store strings into the combo box
    ObservableList<String> data2 = FXCollections.observableArrayList("Unoccupied");
    
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //insertng the Strings from the array list to the combo_box
        combo_box.setValue("");
        combo_box.setItems(data2);
        
        //calling method
         setCellValueFromTableToTextField();
         
        //Establishing database connection and executing query
        try {
            conn = DbConnection.getConnection();
            
            //query to display first 20 rows only
            rs = conn.createStatement().executeQuery("SELECT * FROM maindb LIMIT 20");
            
            //retriving strings from the database
            while (rs.next()) {
                data.add(new ManagerPortal(rs.getString("id"), rs.getString("leaseNumber"), rs.getString("hallName"),
                        rs.getString("hallNumber"), rs.getString("roomNumber"), rs.getString("studentName"),
                        rs.getString("studentId"), rs.getString("occupancyStatus"), rs.getString("cleaningStatus")));
            }
        } catch (SQLException ex) {
        }
        //setting the retreived strings from database to the table columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_leaseNumber.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        col_hallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        col_hallNo.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        col_roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_occupancyStatus.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));
        col_cleaningStatus.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        
        //displaying the string in the gui table
        tableUser.setItems(null);
        tableUser.setItems(data);
    }
    
    //Hall 1 Button
    @FXML
    private void hall1_btnPress(ActionEvent event) {
        //clear table 
        data.clear();
        
        //Establishing database connection and executing query
        try {
            conn = DbConnection.getConnection();
            
            //query to display first 20 rows only
            rs = conn.createStatement().executeQuery("SELECT * FROM maindb LIMIT 20");
            
            //retriving strings from the database
            while (rs.next()) {
               data.add(new ManagerPortal(rs.getString("id"), rs.getString("leaseNumber"), rs.getString("hallName"),
                       rs.getString("hallNumber"), rs.getString("roomNumber"), rs.getString("studentName"),
                       rs.getString("studentId"), rs.getString("occupancyStatus"), rs.getString("cleaningStatus")));
                     }
        } catch (SQLException ex) {     
        }
        //setting the retreived strings from database to the table columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_leaseNumber.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        col_hallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        col_hallNo.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        col_roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_occupancyStatus.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));
        col_cleaningStatus.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        
        //displaying the string in the gui table
        tableUser.setItems(null);
        tableUser.setItems(data);

    }
    
    //Hall 2 Button
    @FXML
    private void hall2_btnPress(ActionEvent event) {
        //clear table 
        data.clear();
        
        //Establishing database connection and executing query
        try {
            conn = DbConnection.getConnection();
            
            //query to display 20 rows skipping the first 20 rows
            rs = conn.createStatement().executeQuery("SELECT * FROM maindb LIMIT 20 OFFSET 20");
            
            //retriving strings from the database
            while (rs.next()) {
                data.add(new ManagerPortal(rs.getString("id"), rs.getString("leaseNumber"), rs.getString("hallName"),
                        rs.getString("hallNumber"), rs.getString("roomNumber"), rs.getString("studentName"),
                        rs.getString("studentId"), rs.getString("occupancyStatus"), rs.getString("cleaningStatus")));
                 }
        } catch (SQLException ex) {    
        }
        //setting the retreived strings from database to the table columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_leaseNumber.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        col_hallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        col_hallNo.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        col_roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_occupancyStatus.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));
        col_cleaningStatus.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        
        //displaying the string in the gui table
        tableUser.setItems(null);
        tableUser.setItems(data);
    }
    
    //Hall 3 Button
    @FXML
    private void hall3_btnPress(ActionEvent event) {
        //clear table 
        data.clear();
        
        //Establishing database connection and executing query
        try {
            conn = DbConnection.getConnection();
            
            //query to display 20 rows skipping the  first 40 rows
            rs = conn.createStatement().executeQuery("SELECT * FROM maindb LIMIT 20 OFFSET 40");
            
            //retriving strings from the database
            while (rs.next()) {
               data.add(new ManagerPortal(rs.getString("id"), rs.getString("leaseNumber"), rs.getString("hallName"),
                        rs.getString("hallNumber"), rs.getString("roomNumber"), rs.getString("studentName"),
                        rs.getString("studentId"), rs.getString("occupancyStatus"), rs.getString("cleaningStatus")));
            }
        } catch (SQLException ex) {       
        }
        //setting the retreived strings from database to the table columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_leaseNumber.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        col_hallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        col_hallNo.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        col_roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_occupancyStatus.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));
        col_cleaningStatus.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        
        //displaying the string in the gui table
        tableUser.setItems(null);
        tableUser.setItems(data);
    }
    
    //All Hall Button
    @FXML
    private void allHall_btnPress(ActionEvent event) {
        //clear table 
        data.clear();
        
        //Establishing database connection and executing query
        try {
            conn = DbConnection.getConnection();
            //quuery to display the entire table
            rs = conn.createStatement().executeQuery("SELECT * FROM maindb");
            
            //retriving strings from the database
            while (rs.next()) {
                data.add(new ManagerPortal(rs.getString("id"), rs.getString("leaseNumber"), rs.getString("hallName"),
                                           rs.getString("hallNumber"), rs.getString("roomNumber"), rs.getString("studentName"),
                                           rs.getString("studentId"), rs.getString("occupancyStatus"), rs.getString("cleaningStatus")));
                }

        } catch (SQLException ex) {  
        }
        //setting the retreived strings from database to the table columns //quuery to display the entire table
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_leaseNumber.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        col_hallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        col_hallNo.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        col_roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_occupancyStatus.setCellValueFactory(new PropertyValueFactory<>("occupancyStatus"));
        col_cleaningStatus.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        
        //displaying the string in the gui table
        tableUser.setItems(null);
        tableUser.setItems(data);
    }
    
    //Loggout Button
    @FXML
    private void logout_btnPress(ActionEvent event) {
        
        ///logs the user out by closing the window
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
    }
    
    //Method to retrieve data from row when selected in the GUI
    private void setCellValueFromTableToTextField() {

        tableUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                //retrieves data from table and assigns to the variables
                ManagerPortal p1 = tableUser.getItems().get(tableUser.getSelectionModel().getSelectedIndex());
                id.setText(p1.getId());
                hallName.setText(p1.getHallName());
                roomNumber.setText(p1.getRoomNumber());
                leaseNumber.setText(p1.getLeaseNumber());
                studentName.setText(p1.getStudentName());
                studentId.setText(p1.getStudentId());
                cleaningStatus.setText(p1.getCleaningStatus());
                combo_box.setValue(p1.getOccupancyStatus());
            }
        });
    }
    
    //Method to execute the update and delete query
    public void theQuery(String query) {

        try {
            conn = DbConnection.getConnection();
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {          
        }
    }
    
    //method to refresh the table when ever table is updated/deleted
    public void refreshTable() {
        //clear table
        data.clear();
        
        //Establishing database connection and executing query
        try {
            conn = DbConnection.getConnection();
            
            //query to display the entire table
            rs = conn.createStatement().executeQuery("SELECT * FROM maindb ");
            
            //retriving strings from the database
            while (rs.next()) {
                data.add(new ManagerPortal(rs.getString("id"), rs.getString("leaseNumber"), rs.getString("hallName"),
                        rs.getString("hallNumber"), rs.getString("roomNumber"), rs.getString("studentName"),
                        rs.getString("studentId"), rs.getString("occupancyStatus"), rs.getString("cleaningStatus")));
                 }
        } catch (SQLException ex) {
        }
        //displaying the string in the gui table
        tableUser.setItems(null);
        tableUser.setItems(data);
    }
    
    //Update Button
    @FXML
    private void update_btnPress(ActionEvent event) {
        
        //condition to verify if a lease can be created or no
        if ((!combo_box.getValue().equals("Occupied")) && (cleaningStatus.getText().equals("Clean") || cleaningStatus.getText().equals("Dirty"))) {
            
            theQuery("update maindb set leaseNumber = '" + leaseNumber.getText() + "',studentName = '" + studentName.getText() + "', studentId = '"+ studentId.getText() + "', occupancyStatus = 'Occupied' where id = " + id.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Update Successful");
            alert.showAndWait();
        
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Cannot create Lease! room either occupied or offline.");
            alert.showAndWait(); 
        }
        //Refresh table and clear all text fields
        refreshTable();
        leaseNumber.clear();
        hallName.clear();
        roomNumber.clear();
        studentName.clear();
        studentId.clear();
        combo_box.setValue("");
    }
    
    //Delete Button
    @FXML
    private void delete_btnPress(ActionEvent event) {
        
        
            //confirmation message prompted to execute the deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this Lease?");
            Optional<ButtonType> result = alert.showAndWait();
            
            //query to display null values to the selected rows if the confirmation was positive
            if(result.get() == ButtonType.OK){
            theQuery("update maindb set leaseNumber = '',studentName = '', studentId = '', occupancyStatus = 'Unoccupied' where id = " + id.getText());
            
            //message to inform the deletion was successful
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete Successful");
            alert.showAndWait();}
            
            //if the confimation was negative, a message is displayed.
            else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Operation Cancelled");
            alert.showAndWait();
            }
       
        //Refresh table
        refreshTable();
    }
   
}

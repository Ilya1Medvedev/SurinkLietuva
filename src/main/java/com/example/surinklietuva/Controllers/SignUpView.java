package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.AllertBox;
import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignUpView {
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField loginField;
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public Button backButton;
    @FXML
    public Button signUpButton;

    private List<User> listOfUsers;
    private BigDataManager bigDataManager = new BigDataManager();

    public void setData(List<User> listOfUsers){
        this.listOfUsers = listOfUsers;
    }
    public boolean checkPasswordLength(String passValue){
        if(passValue.length()>15 || passValue.length()<4){
            return false;
        }
        return true;
    }
    public void returnToPrevious(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        LoginView loginView = fxmlLoader.getController();
        loginView.setData(listOfUsers);

        Scene scene = new Scene(root);
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setTitle("Prisijungimas");
        stage.setScene(scene);
        stage.show();
    }

    public void createUser(ActionEvent actionEvent) throws IOException {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String login = loginField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        if(!checkPasswordLength(password)){
            AllertBox.display("Klaida","Slaptažodis turi būti ne ilgesnis 15 simbolių ir trumpesnis 4 simbolių");
        }
        // Check if fields not null, password field and confirm password field equals
        else if(!name.equals("") && !surname.equals("") && !login.equals("") && !email.equals("") && !password.equals("") && password.equals(confirmPasswordField.getText())) {
            // Check if login value is distinct
            if(listOfUsers.stream().noneMatch(u-> u.getUsername().equals(login))) {
                List<Magnet> emptyMagnetList = new ArrayList<>();
                listOfUsers.add(new User(name, surname, login, email, password, emptyMagnetList));
                bigDataManager.writeAllUsersToDB(listOfUsers);
                returnToPrevious(actionEvent);
            }
            else
            {
                AllertBox.display("Klaida", "Vartotojas su tokiu prisijungimu jau egzistuoja");
            }
        }
        else
        {
            AllertBox.display("Klaida", "Visi laukai turi būti užpildyti");
        }
    }
}

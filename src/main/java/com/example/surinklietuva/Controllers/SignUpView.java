package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.AlertBox;
import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.StartProgram;
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

import java.util.regex.Pattern;

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
    private final BigDataManager bigDataManager = new BigDataManager();
    private static final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,15}$");
    private static final int PASSWORD_MIN_SIZE = 4;
    private static final int PASSWORD_MAX_SIZE = 15;

    public void setData(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public static boolean checkPasswordLength(String passValue) {
        return !(passValue.length() > PASSWORD_MAX_SIZE || passValue.length() < PASSWORD_MIN_SIZE);
    }

    public static boolean checkEmailValidation(String emailValue) {
        return emailPattern.matcher(emailValue).matches();
    }

    public static boolean checkPasswordValidation(String passwordValueOfSimbols) {
        return passwordPattern
                .matcher(passwordValueOfSimbols)
                .matches();
    }

    public void returnToPrevious(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("login-view.fxml"));
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
        if (!checkEmailValidation(email)) {
            AlertBox.display("Klaida", "Neteisingas emailo formatas");
        } else if (!checkPasswordLength(password)) {
            AlertBox.display("Klaida", "Slaptažodis turi būti ne ilgesnis 15 simbolių ir trumpesnis 4 simbolių");
        } else if (!checkPasswordValidation(password)) {
            AlertBox.display("Klaida", "Slaptažodis turi turėti savyyje 1 simbolį, viena didžiąją raidę,nors viena skaičių");
        }
        // Check if fields not null, password field and confirm password field equals
        else if (!"".equals(name) && !"".equals(surname) && !"".equals(login) && !"".equals(email) && !"".equals(password) && confirmPasswordField.getText().equals(password)) {
            // Check if login value is distinct
            if (listOfUsers.stream().noneMatch(u -> u.getUsername().equals(login))) {
                List<Magnet> emptyMagnetList = new ArrayList<>();
                listOfUsers.add(new User(name, surname, login, email, password, emptyMagnetList));
                bigDataManager.writeAllUsersToDB(listOfUsers);
                returnToPrevious(actionEvent);
            } else {
                AlertBox.display("Klaida", "Vartotojas su tokiu prisijungimu jau egzistuoja");
            }
        } else {
            AlertBox.display("Klaida", "Visi laukai turi būti užpildyti");
        }
    }
}

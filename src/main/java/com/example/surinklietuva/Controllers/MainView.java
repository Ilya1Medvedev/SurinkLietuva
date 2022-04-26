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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MainView {
    @FXML
    public Text nameSurnameField;
    @FXML
    public Text emailField;
    @FXML
    public Text magnetukuSkaicius;
    @FXML
    public Text friendNameTxt;
    @FXML
    public Text friendSurname;
    @FXML
    public Text friendMail;
    @FXML
    public Text MagnetsNumber;
    @FXML
    public Button logoutButton;
    @FXML
    public TextField friendName;
    @FXML
    public Button addButton;
    @FXML
    public Text magnetsNum;
    @FXML
    public Button removeButton;
    @FXML
    public ListView MagnetsListOfView;
    @FXML
    public ListView myFriends;
    @FXML
    public ListView friends;
    @FXML
    public ListView missingMagnetsListOfView;
    @FXML
    public ChoiceBox regionChoiceBox;
    @FXML
    public ListView regionsListOfView;
    @FXML
    public ListView regionMagnets;
    @FXML
    public Button showButton;

    private User user;
    private List<User> listOfUsers;
    private List<Magnet> missingMagnets;
    private BigDataManager bigDataManager = new BigDataManager();
    private List<String> allAreasList = Arrays.asList("Vilniaus apskritis", "Kauno apskritis", "Klaipėdos apskritis", "Šiaulių apskritis", "Telšių apskritis", "Marijampolės apskritis", "Tauragės apskritis", "Utenos apskritis", "Panevėžio apskritis", "Alytaus apskritis");



    public void setData(List<User> listOfUsers, User user) throws FileNotFoundException {
        this.listOfUsers = listOfUsers;
        this.user = user;
        getMissingMagnets();
        fillTables();
        regionChoiceBox.setOnAction(actionEvent -> {
            try {
                checkboxFunc();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void getMissingMagnets() throws FileNotFoundException {
        missingMagnets = bigDataManager.getAllMagnetsListFromDataBase();
        for (int i = missingMagnets.size() - 1; i >= 0; i--) {
            for (int j = user.getMagnetList().size() - 1; j >= 0; j--) {
                if (missingMagnets.get(i).getName().equals(user.getMagnetList().get(j).getName())) {
                    missingMagnets.remove(i);
                    break;
                }
            }
        }

    }

    public void addMagnet(ActionEvent actionEvent) throws IOException {
        String magnetName = missingMagnetsListOfView.getSelectionModel().getSelectedItem().toString();
        if (magnetName != null) {
            Magnet currentMagnet = null;
            for (Magnet m : missingMagnets) {
                if (m.getName().equals(magnetName)) {
                    currentMagnet = m;
                }
            }
            missingMagnets.remove(currentMagnet);
            user.getMagnetList().add(currentMagnet);
            fillTables();
            bigDataManager.updateUserToDataBase(listOfUsers, user);
            regionChoiceBox.setValue("Visi duomenis");
        }
    }

    public void remove(ActionEvent actionEvent) throws IOException {
        String magnetName = MagnetsListOfView.getSelectionModel().getSelectedItem().toString();
        if (magnetName != null) {
            Magnet currentMagnet = null;
            for (Magnet m : user.getMagnetList()) {
                if (m.getName().equals(magnetName)) {
                    currentMagnet = m;
                }
            }
            user.getMagnetList().remove(currentMagnet);
            missingMagnets.add(currentMagnet);
            fillTables();
            bigDataManager.updateUserToDataBase(listOfUsers, user);
            regionChoiceBox.setValue("Visi duomenis");
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        LoginView loginView = fxmlLoader.getController();
        loginView.setData(listOfUsers);

        Scene scene = new Scene(root);
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setTitle("Prisijungimas");
        stage.setScene(scene);
        stage.show();
    }

    public void fillTables() throws FileNotFoundException {

        nameSurnameField.setText(user.getName() + " " + user.getSurname());
        emailField.setText(user.getMail());
        MagnetsNumber.setText(user.getMagnetList().size() + " Magnetukų iš 60");


        missingMagnetsListOfView.getItems().clear();
        List<String> magnetsNames = new ArrayList<>();
        for (Magnet m : missingMagnets) {
            magnetsNames.add(m.getName());
        }
        Collections.sort(magnetsNames);
        for (String s : magnetsNames) {
            missingMagnetsListOfView.getItems().add(s);
        }

        MagnetsListOfView.getItems().clear();
        for (Magnet m : user.getMagnetList()) {
            MagnetsListOfView.getItems().add(m.getName());
        }


        magnetsNum.setText(user.getMagnetList().size() + " / 60");

        regionsListOfView.getItems().clear();
        for (String s : allAreasList) {
            regionsListOfView.getItems().add(s);
        }

    }

    public void showRegionMagnets(MouseEvent mouseEvent) {
        String regionName = regionsListOfView.getSelectionModel().getSelectedItem().toString();
        if (regionName != null) {
            regionMagnets.getItems().clear();
            List<Magnet> sortedMagnets = bigDataManager.getListOfMagnetsByRegion(missingMagnets, regionName);
            for (Magnet m : sortedMagnets) {
                regionMagnets.getItems().add(m.getName());
            }
        }
    }

    public void goToMagnetInfo(ActionEvent actionEvent) throws IOException {
        String magnetName = regionMagnets.getSelectionModel().getSelectedItem().toString();
        Magnet currentMagnet = bigDataManager.getMagnetByName(magnetName);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("magnet-info-view.fxml"));
        Parent root = fxmlLoader.load();
        MagnetInfoView magnetInfoView = fxmlLoader.getController();
        magnetInfoView.setData(listOfUsers, user, currentMagnet);

        Scene scene = new Scene(root);
        Stage stage = (Stage) removeButton.getScene().getWindow();
        stage.setTitle("Magnetuko informacija");
        stage.setScene(scene);
        stage.show();
    }

    public void checkboxFunc() throws FileNotFoundException {
        String regionName = regionChoiceBox.getValue().toString();
        if (regionName.equals("Visi duomenis")) {
            fillTables();
        } else if (regionName != null) {
            missingMagnetsListOfView.getItems().clear();
            List<Magnet> sortedMagnets = bigDataManager.getListOfMagnetsByRegion(missingMagnets, regionName);
            for (Magnet m : sortedMagnets) {
                missingMagnetsListOfView.getItems().add(m.getName());
            }
        }
    }

    public void addFriend(ActionEvent actionEvent) {

        String futureFriends = friends.getSelectionModel().getSelectedItem().toString();

            System.out.print(futureFriends);

            myFriends.getItems().add(futureFriends);

    }

    public Button getAddButton() {
        return addButton;
    }

    public void search(ActionEvent actionEvent) throws IOException {

        friends.getItems().clear();

        for(User u: listOfUsers){

            if(friendName.getText().isEmpty()){
                AllertBox.display("Klaida","Prašome įvesti vartotojo vardą");
                break;
            }

            else if(u.getName().equals(friendName.getText())){
                System.out.print(u);
                friends.getItems().add(u.getName() + " " + u.getSurname());
            }
        }
    }

    public User getUserData(String name, String surname) throws FileNotFoundException {

        listOfUsers = bigDataManager.getAllUserListFromDataBase();

        for(User u: listOfUsers){
            if(u.getName().equals(name) && u.getSurname().equals(surname)){

                return u;

            }
        }

        return null;
    }

    public void showFriend(ActionEvent actionEvent) throws FileNotFoundException {

        User userInfo = new User();

        String friend = myFriends.getSelectionModel().getSelectedItem().toString();

        String[] parts = friend.split(" ");

        String part1 = parts[0];
        String part2 = parts[1];

        userInfo = getUserData(part1, part2);

        friendNameTxt.setText(userInfo.getName());
        friendSurname.setText(userInfo.getSurname());
        friendMail.setText(userInfo.getMail());

        int magnetukai = userInfo.getMagnetList().size();
        magnetukuSkaicius.setText(String.valueOf(magnetukai));

    }
}


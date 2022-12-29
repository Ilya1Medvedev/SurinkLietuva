package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.StartProgram;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class MagnetInfoView {

    public Text magnetInfo;
    public Button backButton;
    public ImageView regionImage;
    public Text magnetShops;
    private Magnet magnet;
    private User user;
    private List<User> listOfUsers;

    public void setData(List<User> listOfUsers, User user, Magnet magnet) throws FileNotFoundException {
        this.listOfUsers = listOfUsers;
        this.user = user;
        this.magnet = magnet;
        fillTables();
    }

    private void fillTables() throws FileNotFoundException {
        String imgFolderPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\surinklietuva\\Images";
        regionImage.setImage(null);
        Image img = new Image(new FileInputStream(imgFolderPath + "\\"+magnet.getArea()+".png"));
        regionImage.setImage(img);
        magnetInfo.setText(magnet.getName() + " " + magnet.getArea());
        String shops = "";
        for (String s : magnet.getListOfShops()) {
            shops += s + "\n";
        }
        magnetShops.setText(shops);
    }

    public void returnToPrevious(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainView mainView = fxmlLoader.getController();
        mainView.setData(listOfUsers, user);

        Scene scene = new Scene(root);
        Stage stage = (Stage) regionImage.getScene().getWindow();
        stage.setTitle("Pagrindinis");
        stage.setScene(scene);
        stage.show();
    }
}

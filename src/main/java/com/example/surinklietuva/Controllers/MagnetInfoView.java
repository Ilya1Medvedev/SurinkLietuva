package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MagnetInfoView {

    public Text magnetInfo;
    public Button backButton;
    public ImageView regionImage;
    public Text magnetShops;
    private Magnet magnet;
    private User user;
    private List<User> listOfUsers;
    private List<Magnet> missingMagnets;
    private BigDataManager bigDataManager = new BigDataManager();

    public void setData(List<User> listOfUsers, User user, Magnet magnet) throws FileNotFoundException {
        this.listOfUsers = listOfUsers;
        this.user = user;
        this.magnet = magnet;
        missingMagnets = bigDataManager.getAllMagnetsListFromDataBase();
        fillTables();
    }

    private void fillTables() {
        regionImage.setImage(null);
        Image img = null;
        switch (magnet.getArea()) {
            case "Vilniaus apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Vilnius.png");
                break;
            case "Kauno apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Kaunas.png");
                break;
            case "Klaipėdos apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Klaipeda.png");
                break;
            case "Šiaulių apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Siauliai.png");
                break;
            case "Telšių apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Telsiai.png");
                break;
            case "Marijampolės apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Marijampole.png");
                break;
            case "Tauragės apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Taurage.png");
                break;
            case "Utenos apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Utena.png");
                break;
            case "Panevėžio apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Panevezys.png");
                break;
            case "Alytaus apskritis":
                img = new Image("C:\\Users\\Vladislav\\Desktop\\VGTU\\Projektu valdymas\\Projektas\\src\\main\\java\\com\\example\\surinklietuva\\Images\\Alytus.png");
                break;
            default:
                int i = 0;
        }
        regionImage.setImage(img);
        magnetInfo.setText(magnet.getName() + " " + magnet.getArea());
        String shops = "";
        for (String s : magnet.getListOfShops()) {
            shops += s + "\n";
        }
        magnetShops.setText(shops);
    }

    public void returnToPrevious(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
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

package Test;

import com.example.surinklietuva.DataBaseControllers.MagnetDataController;
import com.example.surinklietuva.DataStructures.Magnet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataBase {

    @Test
    public void testSelectMagnet() throws SQLException {
        MagnetDataController magnetDataController = new MagnetDataController();
        Magnet magnet = magnetDataController.selectMagnet("SELECT * FROM `magnet` WHERE `name` = 'VILNIUS'").get(0);

        Assert.assertEquals(magnet.getName(), "VILNIUS");

        List<String> expectedMagnetsNames = Arrays.asList("ELEKTRĖNAI", "ŠALČININKAI", "ŠIRVINTOS", "ŠVENČIONYS", "TRAKAI", "UKMERGĖ", "VILNIAUS RAJONAS", "VILNIUS");

        List<Magnet> listOfMagnets = magnetDataController.selectMagnet("SELECT * FROM `magnet` WHERE `area` = 'Vilniaus apskritis'");
        List<String> actualMagnetsNames = listOfMagnets.stream().map(Magnet::getName).collect(Collectors.toList());

        Assert.assertEquals(actualMagnetsNames, expectedMagnetsNames);
    }

    @Test
    public void testAddMagnetToUser() throws SQLException {
        MagnetDataController magnetDataController = new MagnetDataController();
        int userID = 1;
        String magnetName = "ALYTAUS MIESTAS";
        magnetDataController.addMagnetToUser(magnetName, userID);

        List<Magnet> listOfMagnets = magnetDataController.selectMagnet("SELECT magnet.name, magnet.area, magnet.shops\n" +
                "FROM user_magnet\n" +
                "INNER JOIN magnet\n" +
                "ON user_magnet.magnetName = magnet.name\n" +
                "WHERE userID = '1'");

        List<String> expectedMagnetsNames = Arrays.asList("JONAVA", "KAUNAS", "ALYTAUS MIESTAS");
        List<String> actualMagnetsNames = listOfMagnets.stream().map(Magnet::getName).collect(Collectors.toList());

        Assert.assertEquals(actualMagnetsNames, expectedMagnetsNames);
    }

    @After public void deleteMagnetToUser() throws SQLException {
        int userID = 1;
        String magnetName = "ALYTAUS MIESTAS";
        MagnetDataController magnetDataController = new MagnetDataController();
        magnetDataController.removeMagnetToUser(magnetName, userID);
    }
}

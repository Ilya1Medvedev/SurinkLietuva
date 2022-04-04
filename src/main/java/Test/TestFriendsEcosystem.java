package Test;

import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.Controllers.MainView;
import com.example.surinklietuva.DataStructures.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class TestFriendsEcosystem {
    @Test
    public void TestUserInfoNull() throws FileNotFoundException {
        MainView mainView = new MainView();

        Assert.assertEquals(mainView.getUserData(null, null), null);

    }

    @Test
    public void TestUserInfo() throws FileNotFoundException {
        MainView mainView = new MainView();

        Assert.assertNotNull(mainView.getUserData("Ilya", "Medvedev"));

    }
}

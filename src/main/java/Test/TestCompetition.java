package Test;

import com.example.surinklietuva.DataStructures.GameParticipant;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCompetition {
    @Test
    public void TestGameParticipantConstructors()
    {
        List<Magnet> magnetList = new ArrayList<>();
        User user = new User("TestName","TestSurname", "TestUsername","TestMail","TestPassword",magnetList);
        GameParticipant gameParticipant1 = new GameParticipant(user);
        GameParticipant gameParticipant2 = new GameParticipant(user,4);
        Assert.assertEquals(gameParticipant1.getName(),"TestName");
        Assert.assertEquals(gameParticipant2.getName(),"TestName");
        Assert.assertEquals(gameParticipant1.getScore(),0);
        Assert.assertEquals(gameParticipant2.getScore(),4);
    }
}
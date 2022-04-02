package Test;

import com.example.surinklietuva.DataStructures.Competition;
import com.example.surinklietuva.DataStructures.GameParticipant;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Test
    public void TestCompetitionConstructor()
    {
        List<Magnet> magnetList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2000-12-11",formatter);
        User user1 = new User("TestName1","TestSurname1", "TestUsername1","TestMail1","TestPassword1",magnetList);
        User user2 = new User("TestName2","TestSurname2", "TestUsername2","TestMail2","TestPassword2",magnetList);
        GameParticipant gameParticipant1 = new GameParticipant(user1);
        GameParticipant gameParticipant2 = new GameParticipant(user2);
        List<GameParticipant> gameParticipants = new ArrayList<>();
        gameParticipants.add(gameParticipant1);
        gameParticipants.add(gameParticipant2);
        Competition competition = new Competition(4, date, date.plusDays(1), gameParticipants);
        Assert.assertEquals(competition.getStartDate(),LocalDate.parse("2000-12-11",formatter));
        Assert.assertEquals(competition.getEndDate(),LocalDate.parse("2000-12-12",formatter));
        Assert.assertEquals(competition.getGameParticipants().get(0).getName(),"TestName1");
    }
}
package Test;

import com.example.surinklietuva.DataStructures.Competition;
import com.example.surinklietuva.DataStructures.GameParticipant;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestCompetition {
    @Test
    public void TestGameParticipantConstructors()
    {
        List<Magnet> listOfMagnets = new ArrayList<>();
        User user = new User("TestName","TestSurname", "TestUsername","TestMail","TestPassword",listOfMagnets);
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
        List<Magnet> ListOfMagnets = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2000-12-11",formatter);
        User user1 = new User("TestName1","TestSurname1", "TestUsername1","TestMail1","TestPassword1",ListOfMagnets);
        User user2 = new User("TestName2","TestSurname2", "TestUsername2","TestMail2","TestPassword2",ListOfMagnets);

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
    @Test
    public void TestCompetitionUpdateScores() //TDD 1
    {
        List<Magnet> listOfMagnets = new ArrayList<>();
        List<Magnet> listOfMagnets1 = new ArrayList<>();                                                                                                   //1.1
        Magnet magnet1 = new Magnet("testArea1","testName1",new ArrayList<>());                                                                 //1.1
        Magnet magnet2 = new Magnet("testArea2","testName2",new ArrayList<>());                                                                 //1.1

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");                                                                           //1.1
        LocalDate date = LocalDate.parse("2000-12-11",formatter);                                                                                     //1.1
        //User user1 = new User("TestName1","TestSurname1", "TestUsername1","TestMail1","TestPassword1",listOfMagnets);  //1.1
        //User user2 = new User("TestName2","TestSurname2", "TestUsername2","TestMail2","TestPassword2",listOfMagnets);  //1.1
        User user1 = new User("TestName1","TestSurname1", "TestUsername1","TestMail1","TestPassword1",new ArrayList<>());  //1.1
        User user2 = new User("TestName2","TestSurname2", "TestUsername2","TestMail2","TestPassword2",new ArrayList<>());  //1.1

        List<User> userList = new ArrayList<>();                                                                                                           //1.1
        userList.add(user1);                                                                                                                               //1.1
        userList.add(user2);                                                                                                                               //1.1

        GameParticipant gameParticipant1 = new GameParticipant(new User("TestName1", "TestSurname1", "TestUsername1", "TestMail1","TestPassword1", listOfMagnets1));//1.1
        GameParticipant gameParticipant2 = new GameParticipant(new User("TestName2", "TestSurname2", "TestUsername2", "TestMail2","TestPassword2", listOfMagnets1));//1.1

        List<GameParticipant> gameParticipants = new ArrayList<>();                                                                                     //1.1
        gameParticipants.add(gameParticipant1);                                                                                                         //1.1
        gameParticipants.add(gameParticipant2);

        Competition competition = new Competition(1, date, date.plusDays(1), gameParticipants);                                                      //1.1

        Assert.assertEquals(0,competition.getGameParticipants().get(0).getScore());                                                             //1.1

        //userList.get(0).getMagnetList().add(magnet1);                                                                                                   //1.1
        userList.get(0).getMagnetList().add(magnet1);
        competition.updateScores(userList);                                                                                                             //1.1
        Assert.assertEquals(1,competition.getGameParticipants().get(0).getScore());                                                             //1.1 Testas praeina igivendinus 1.2 kodo eilutes

        userList.get(0).getMagnetList().add(magnet2);                                                                                                   //1.3
        Assert.assertEquals(1,competition.getGameParticipants().get(0).getScore());                                                             //1.3

        competition.updateScores(userList);                                                                                                             //1.3
        Assert.assertEquals(2,competition.getGameParticipants().get(0).getScore());                                                             //1.3 Testas praeina igivendinus 1.4 kodo eilutes, o 1.4 kodo eilutes vykdomos kai praeina visi 2 numerio testai


        userList.get(1).getMagnetList().add(magnet1);                                                                                                   //1.5
        Assert.assertEquals(0,competition.getGameParticipants().get(1).getScore());                                                             //1.5

        competition.updateScores(userList);                                                                                                             //1.5
        Assert.assertEquals(1,competition.getGameParticipants().get(1).getScore());                                                             //1.5

        userList.get(1).getMagnetList().add(magnet2);                                                                                                   //1.5
        competition.updateScores(userList);                                                                                                             //1.5
        Assert.assertEquals(2,competition.getGameParticipants().get(1).getScore());                                                             //1.5 Galutine patikra, visi testai praeina.
    }
    @Test
    public void TestReturnMagnetsFromUser() //TDD 2
    {
        List<Magnet> magnetArray= new ArrayList<>();                                                                                                      //2.1
        User user1 = new User("TestName1","TestSurname1", "TestUsername1","TestMail1","TestPassword1",new ArrayList<>());  //2.1
        Assert.assertEquals(magnetArray,user1.getMagnetList());                                                                                           //2.1 Testas praeina igivendinus 2.2

        Magnet magnet1 = new Magnet("testArea1","testName1",new ArrayList<>());                                                                 //2.3
        user1.getMagnetList().add(magnet1);                                                                                                                //2.3
        magnetArray.add(magnet1);                                                                                                                          //2.3
        Assert.assertEquals(magnetArray,user1.getMagnetList());                                                                                            //2.3 Testas praeina ivikdzius 2.4 kodo eilutes

        user1.getMagnetList().add(magnet1);                                                                                                                //2.5
        magnetArray.add(magnet1);                                                                                                                          //2.5
        Assert.assertEquals(magnetArray,user1.getMagnetList());                                                                                            //2.5 Galutine patikra, nauju klaidu nerasta.
    }
}
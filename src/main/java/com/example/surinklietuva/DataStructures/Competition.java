package com.example.surinklietuva.DataStructures;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Competition {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<GameParticipant> gameParticipants;

    public Competition()
    {

    }

    public Competition(int id, LocalDate startDate, LocalDate endDate, List<GameParticipant> gameParticipants) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gameParticipants = gameParticipants;
    }

    public void updateScores(List<User> users)                                                                  //1.1
    {                                                                                                           //1.1
        for(int i=0;i<gameParticipants.size();i++)                                                              //1.2
        {                                                                                                       //1.2
            for(int j=0; j<users.size(); j++)                                                                   //1.2
            {                                                                                                   //1.2
                //if(Objects.equals(gameParticipants.get(i).getUsername(), users.get(j).getUsername()))           //1.2
                if(gameParticipants.get(i).getUsername().equals(users.get(j).getUsername())&&gameParticipants.get(i).getMagnetList().size()!=users.get(j).getMagnetList().size())
                {                                                                                               //1.2
                    int tempScore = gameParticipants.get(i).getScore();                                         //1.2
                    int tempParticipantMagnetCount = gameParticipants.get(i).getMagnetList().size();            //1.2
                    int tempUserMagnetCount = users.get(j).getMagnetList().size();                              //1.2
                    System.out.println(tempScore + "+" + tempUserMagnetCount + "-" + tempParticipantMagnetCount);//1.2
                    this.gameParticipants.get(i).setScore(tempScore + tempUserMagnetCount - tempParticipantMagnetCount);    //1.2
                    this.gameParticipants.get(i).setMagnetList(returnMagnetsFromUser(users.get(j)));                //1.4
                    break;
                }                                                                                               //1.2
            }                                                                                                   //1.2
        }                                                                                                       //1.2
    }                                                                                                           //1.1
    public List<Magnet> returnMagnetsFromUser(User user)
    {
        List<Magnet> magnets= new ArrayList<>();                //2.2
        for(int i=0;i<user.getMagnetList().size();i++)          //2.4
        {                                                       //2.4
            magnets.add(user.getMagnetList().get(i));           //2.4
        }                                                       //2.4
        return magnets;                                         //2.2
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<GameParticipant> getGameParticipants() {
        return gameParticipants;
    }

    public void setGameParticipants(List<GameParticipant> gameParticipants) {
        this.gameParticipants = gameParticipants;
    }
}

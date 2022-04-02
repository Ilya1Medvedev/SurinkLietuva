package com.example.surinklietuva.DataStructures;

import java.time.LocalDate;
import java.util.List;

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

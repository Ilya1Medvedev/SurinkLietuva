package com.example.surinklietuva.DataStructures;

import java.util.List;

public class GameParticipant extends User {
    private int score;

    public GameParticipant()
    {

    }

    public GameParticipant(User user, int score)
    {
        this.setName(user.getName());
        this.setSurname(user.getSurname());
        this.setUsername(user.getUsername());
        this.setMail(user.getMail());
        this.setPassword(user.getPassword());
        this.setMagnetList(user.getMagnetList());
        this.setScore(score);
    }

    public GameParticipant(User user)
    {
        this.setName(user.getName());
        this.setSurname(user.getSurname());
        this.setUsername(user.getUsername());
        this.setMail(user.getMail());
        this.setPassword(user.getPassword());
        this.setMagnetList(user.getMagnetList());
        this.setScore(0);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

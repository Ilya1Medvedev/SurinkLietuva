package com.example.surinklietuva.DataStructures;

import java.util.List;

public class User {
    private final String name;
    private final String surname;
    private final String username;
    private final String mail;
    private final String password;
    private List<Magnet> magnetList;


    public User(String name, String surname, String username, String mail, String password, List<Magnet> magnetList) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.magnetList = magnetList;
    }

    public String getUserInfoForDataBase() {
        String userInfo = "";
        userInfo += this.getName() + "||";
        userInfo += this.getSurname() + "||";
        userInfo += this.getUsername() + "||";
        userInfo += this.getMail() + "||";
        userInfo += this.getPassword() + "||";
        if (!getMagnetList().isEmpty()) {
            for (int i = 0; i < this.getMagnetList().size(); i++) {
                userInfo += this.getMagnetList().get(i).getName() + "||";
            }
        }
        return userInfo;

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public List<Magnet> getMagnetList() {
        return magnetList;
    }

    public void setMagnetList(List<Magnet> magnetList) {
        this.magnetList = magnetList;
    }
}


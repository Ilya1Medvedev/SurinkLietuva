package com.example.surinklietuva;

import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BigDataManager {

    private final List<Magnet> MAGNETS = new ArrayList<>();
    private File file;
    private static final int DATA_SIZE = 5;

    public List<Magnet> getAllMagnetsListFromDataBase() throws FileNotFoundException {

        file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\surinklietuva\\ProgramMemory\\MagnetsDataBase");
        Scanner scanner = new Scanner(file);
        String line;
        String permArea = "Vilniaus apskritis";
        String permCity = "UKMERGĖ";
        List<String> permShops = new ArrayList<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.charAt(0) == 'A' && line.charAt(1) == 'A' && line.charAt(2) == 'A') {
                MAGNETS.add(new Magnet(permArea, permCity, permShops));
                permShops = new ArrayList<>();
                permArea = makeAreaOrCityFromLine(line);
                line = scanner.nextLine();
                permCity = makeAreaOrCityFromLine(line);
            } else if (line.charAt(0) == 'M' && line.charAt(1) == 'M' && line.charAt(2) == 'M') {

                MAGNETS.add(new Magnet(permArea, permCity, permShops));
                permShops = new ArrayList<>();
                permCity = makeAreaOrCityFromLine(line);
            } else {
                permShops.add(line);
            }
        }
        MAGNETS.add(new Magnet(permArea, permCity, permShops));
        return MAGNETS;
    }

    private String makeAreaOrCityFromLine(String line) {

        String area = "";
        for (int i = 4; i < line.length(); i++) {
            area += line.charAt(i);
        }
        return area;
    }

    public List<User> getAllUserListFromDataBase() throws FileNotFoundException {
        file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\surinklietuva\\ProgramMemory\\UsersDataBase");
        List<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        List<String> tempStrings;
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            tempStrings = returnStringsListFromLine(line);
            List<Magnet> tempMagnets = new ArrayList<>();
            if (tempStrings.size() > DATA_SIZE) {
                for (int i = 5; i < tempStrings.size(); i++) {
                    tempMagnets.add(getMagnetByName(tempStrings.get(i)));
                }
            }
            if (tempStrings.size() > DATA_SIZE) {
                users.add(new User(tempStrings.get(0), tempStrings.get(1), tempStrings.get(2), tempStrings.get(3), tempStrings.get(4), tempMagnets));
            } else {
                tempMagnets = new ArrayList<>();
                users.add(new User(tempStrings.get(0), tempStrings.get(1), tempStrings.get(2), tempStrings.get(3), tempStrings.get(4), tempMagnets));
            }
        }
        scanner.close();
        return users;
    }

    public Magnet getMagnetByName(String name) throws FileNotFoundException {
        List<Magnet> allMagnets = getAllMagnetsListFromDataBase();
        return allMagnets.stream().filter(m -> m.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public void writeAllUsersToDB(List<User> usersToWrite) throws IOException {
        file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\surinklietuva\\ProgramMemory\\UsersDataBase");
        FileWriter writer = null;
        writer = new FileWriter(file);
        for (User user : usersToWrite) {

            writer.write(user.getUserInfoForDataBase() + "\n");
        }
        writer.close();
    }

    private static List<String> returnStringsListFromLine(String line) {
        List<String> x;
        x = List.of(line.split("\\|\\|"));
        return x;

    }

    public void updateUserToDataBase(List<User> userList, User userToUpdate) throws IOException {
        for (User user : userList) {
            if (user.getUsername().equals(userToUpdate.getUsername())) {
                user.setMagnetList(userToUpdate.getMagnetList());
            }
        }
        writeAllUsersToDB(userList);
    }

    public List<Magnet> getListOfMagnetsByRegion(List<Magnet> magnets, String regionName) {
        return magnets.stream().filter(m -> m.getArea().equals(regionName)).collect(Collectors.toList());
    }
}

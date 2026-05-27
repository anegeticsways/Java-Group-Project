/*
Contributed by Andrean (103325)
Description:
1. User.java is responsible for managing user data
2. Mainly loading and saving user information to a file and provides methods to access & modify user attributes
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class User {

    private final String name; //User name cannot be changed
    private int score;
    //PLEASE MODIFY BASED ON OWN FILE DIRECTORY
    private static final String FILE_PATH = "C:\\Users\\andre\\OneDrive - UNIMAS\\Year 2 Sem 2\\TMF2954 Java Programming\\Assessment\\Group Project (5 June)\\userdata.txt";

    //Constructors
    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public static boolean isExistingUser(String name) {
        ArrayList<User> users = loadUsers();
            for (User u : users) {
                if (u.getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        return false;
    }
    
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Skip empty lines in file
                }

                String[] data = line.split(",");
                if (data.length == 2) {
                    String name = data[0];
                    int score = Integer.parseInt(data[1]);
                    users.add(new User(name, score));
                }
            }
            br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return users;
        }

    //Setters
    public void setScore(int score) {
        this.score = score;
    }

    public static void saveAllUsers(ArrayList<User> users) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH); // overwrite existing file
            for (User u : users) {
                fw.write(u.getName() + "," + u.getScore() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

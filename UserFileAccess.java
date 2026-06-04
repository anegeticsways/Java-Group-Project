/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Manages user data by reading from and writing to a file (userdata.txt).
2. Implements the UserAccess interface to provide methods for retrieving all users, saving users, and updating user scores.
3. Ensures that user data is persistent across sessions by storing it in a text file.
*/

import java.io.*;
import java.util.ArrayList;

public class UserFileAccess implements UserAccess {

    private static final String FILE_PATH = "userdata.txt";

    @Override
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length == 2) {
                    String name = data[0];
                    int score = Integer.parseInt(data[1]);
                    users.add(new User(name, score));
                }
            }

            br.close();

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading user data.");
        }

        return users;
    }

    @Override
    public void saveAllUsers(ArrayList<User> users) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);

            for (User u : users) {
                fw.write(u.getName() + "," + u.getScore() + "\n");
            }

            fw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user data.");
        }
    }

    @Override
    public void updateUserScore(String name, int score) {
        ArrayList<User> users = loadUsers();

        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                u.setScore(score);
                break;
            }
        }

        saveAllUsers(users);
    }
}

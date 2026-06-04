/*
Created by: Andrean (103325)
Role: Member 1 - Data and Storage Lead

Tested by: Ainin (102810)

Description:
1. Displays all users' scores.
2. Sorts users from highest score to lowest score.
3. Shows badge/title earned using GamificationEngine.
*/

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Achievement {

    public void displayAchievements() {
        UserAccess userAccess = new UserFileAccess(); // Instance of UserFileAccess to access user data
        ArrayList<User> users = userAccess.getAllUsers();
        GamificationEngine engine = new GamificationEngine();

        users.sort((u1, u2) -> u2.getScore() - u1.getScore());

        String scoreboard = "Score Board\n\n";

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            String badge = engine.determineBadge(u.getScore());

            scoreboard += (i + 1) + ". " +
                          u.getName() + " - " +
                          u.getScore() + " Points | " +
                          badge + "\n";
        }

        JOptionPane.showMessageDialog(
            null,
            scoreboard,
            "Score Board",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

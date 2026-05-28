/*
Contributed by Andrean (103325)
Description:
1. Contribute basic structure of Achievement.java only - call displayAchievements() method to display all users score in console
2. User.java allows user to view scores for all users
3. [NOT YET] It includes elements like badges/title earned
*/

/*
Thoughts and Comments:
1. Can assign badges or title for user with top 10 highest marks
2. Sort user data based on the highest marks
*/


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Achievement {

    //UNSORTED - Display all users score in Console
    public void displayAchievements() {
        //Call all users method to load user data
        ArrayList<User> users = User.loadUsers();
       
        // Contributed by Afiq (103041)
        GamificationEngine engine = new GamificationEngine();

        for (User u : users) {
            // Get the badge for each user's score
            String badge = engine.determineBadge(u.getScore()); 
            
            // Print the name, score, AND badge to the console
            System.out.println(u.getName() + " - " + u.getScore() + " Points | Rank: " + badge);
        }
    }    
}

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
       
        for (User u : users) {
            System.out.println(u.getName() + " - " + u.getScore());
        }
    }    
}

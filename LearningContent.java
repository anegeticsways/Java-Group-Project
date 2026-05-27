/*
Contributed by Andrean (103325)
Description:
1. To display learning content about eWaste to users 
2. No data is stored in this class, it is only for viewing learning content
3. Only contribute structure of LearningContent.java
*/

/*
Thoughts & Comments;
1. I think for this part/code, we can just display learning contents about eWaste then have a button for user to return to the main menu.
2. Do ensure learning content is informative to generate 20+ questions for quiz

Better suggestion (Option): 
1. If do-able, we can do like embedded YouTube link so that no need to manually enter all the information
Good YouTube video about eWaste: https://youtu.be/MQLadfsvfLo?si=S1k9BvATWnqvYj8t
*/

import javax.swing.JOptionPane;

public class LearningContent {

    public void displayContent() {
        // Method to display learning content about eWaste
        // This is a placeholder implementation and should be replaced with actual content
        String content = "Let's learn about eWaste more!\n\n" +
                         "eWaste / electronic waste, refers to discarded electronic devices such as computers, smartphones, and televisions. \n" +
                         "Improper disposal of eWaste can lead to environmental pollution and health hazards due to the presence of toxic substances.\n" +
                         "It is important to recycle eWaste properly to reduce its impact on the environment and human health.\n\n" +
                         "Here are some tips for managing eWaste:\n" +
                         "1. Donate or sell working electronics instead of throwing them away.\n" +
                         "2. Use certified eWaste recycling facilities for disposal.\n" +
                         "3. Reduce electronic consumption by repairing devices instead of replacing them.\n\n" +
                         "Thank you for learning about eWaste! Let's work together to create a more sustainable future.";
        
        JOptionPane.showMessageDialog(null, content, "Learning Content", JOptionPane.INFORMATION_MESSAGE);
    }
}
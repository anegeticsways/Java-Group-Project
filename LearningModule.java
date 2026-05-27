// Contributed by (Ailin Najwa)(102390)
// Role: Member 2 - Content Specialist
// Description:
// 1. Manages 10+ pages of SDG/e-waste educational content.
// 2. Displays both text and images.
// 3. Implements Displayable interface for page rendering and navigation.

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LearningModule implements Displayable {

    private String[] pageTitles;
    private String[] pageTexts;
    private String[] imagePaths;
    private int currentPage;

    public LearningModule() {
        currentPage = 0;

        pageTitles = new String[] {
            "What is E-Waste?",
            "Causes of E-Waste",
            "Examples of E-Waste",
            "Toxic Materials",
            "Environmental Impact",
            "Health Impact",
            "Importance of Recycling",
            "Reduce E-Waste",
            "Reuse Electronics",
            "Responsible Disposal",
            "SDG Connection",
            "What You Can Do"
        };

        pageTexts = new String[] {
            "E-waste means discarded electronic devices such as phones, laptops, computers, televisions and chargers.",

            "E-waste is caused by rapid technology development, frequent device upgrades, damaged electronics and short product lifespans.",

            "Examples of e-waste include smartphones, laptops, printers, batteries, cables, televisions, tablets and computer parts.",

            "E-waste may contain harmful substances such as lead, mercury, cadmium and other toxic chemicals.",

            "Improper disposal of e-waste can pollute soil, water and air. This can damage ecosystems and harm animals.",

            "People exposed to toxic e-waste materials may face health problems such as breathing issues, skin irritation and long-term illness.",

            "Recycling e-waste helps recover useful materials, reduces pollution and prevents harmful chemicals from entering the environment.",

            "We can reduce e-waste by repairing devices, using electronics longer and avoiding unnecessary upgrades.",

            "Working devices can be donated, sold or reused instead of being thrown away.",

            "Broken electronics should be sent to certified e-waste recycling centres instead of being placed in normal rubbish bins.",

            "E-waste management supports Sustainable Development Goal 12: Responsible Consumption and Production.",

            "You can help by buying only what you need, taking care of your devices and recycling electronics properly."
        };

imagePaths = new String[] {
    "ewaste1.jpg",
    "ewaste2.jpg",
    "ewaste3.jpg",
    "ewaste4.jpg",
    "ewaste5.jpg",
    "ewaste6.jpg",
    "ewaste7.jpg",
    "ewaste8.jpg",
    "ewaste9.jpg",
    "ewaste10.jpg",
    "sdg12.jpg",
    "recycle.jpg"
};
    }

  @Override
public void displayPage() {
    ImageIcon originalIcon = new ImageIcon(imagePaths[currentPage]);
    ImageIcon resizedIcon = new ImageIcon(
        originalIcon.getImage().getScaledInstance(320, 220, java.awt.Image.SCALE_SMOOTH)
    );

    javax.swing.JLabel titleLabel = new javax.swing.JLabel(pageTitles[currentPage]);
    titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 22));
    titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    javax.swing.JLabel imageLabel = new javax.swing.JLabel(resizedIcon);
    imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    javax.swing.JLabel textLabel = new javax.swing.JLabel(
        "<html><div style='width:350px; text-align:center; font-size:12px;'>"
        + pageTexts[currentPage]
        + "</div></html>"
    );
    textLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    javax.swing.JLabel pageLabel = new javax.swing.JLabel(
        "Page " + (currentPage + 1) + " of " + pageTexts.length
    );
    pageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    javax.swing.JPanel panel = new javax.swing.JPanel();
    panel.setLayout(new java.awt.GridLayout(4, 1, 5, 10));
    panel.add(titleLabel);
    panel.add(imageLabel);
    panel.add(textLabel);
    panel.add(pageLabel);

    JOptionPane.showMessageDialog(
        null,
        panel,
        "Learning Module",
        JOptionPane.PLAIN_MESSAGE
    );
}

    @Override
    public void nextPage() {
        if (currentPage < pageTexts.length - 1) {
            currentPage++;
        } else {
            JOptionPane.showMessageDialog(null, "You have reached the last page.");
        }
    }

    @Override
    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
        } else {
            JOptionPane.showMessageDialog(null, "You are already on the first page.");
        }
    }

    public void startLearning() {
        int choice;

        do {
            displayPage();
            String[] options = {"Previous", "Next", "Exit"};

            choice = JOptionPane.showOptionDialog(
                null,
                "Choose an action:",
                "Learning Page Navigation",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
            );

            if (choice == 0) {
                previousPage();
            } else if (choice == 1) {
                nextPage();
            }

        } while (choice != 2);
    }
}
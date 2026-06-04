/*
Contributed by Ailin Najwa (102390)
Role: Member 2 - Content Specialist
Tester: Ailin Najwa (102390)

Description:
1. Manages 10+ pages of SDG/e-waste educational content.
2. Displays both text and images.
3. Implements Displayable interface for page rendering and navigation.
*/

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

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

    if (originalIcon.getIconWidth() == -1) {
        JOptionPane.showMessageDialog(null, "Cannot load image: " + imagePaths[currentPage]);
        return;
    }

    Image scaledImage = originalIcon.getImage().getScaledInstance(AppConfig.PHONE_WIDTH - 170, 130, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(scaledImage);

    JLabel logoLabel = new JLabel("EcoLearn");
    logoLabel.setFont(AppConfig.TITLE_FONT);
    logoLabel.setForeground(AppConfig.PRIMARY);
    logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel titleLabel = new JLabel(pageTitles[currentPage]);
    titleLabel.setFont(AppConfig.TITLE_FONT);
    titleLabel.setForeground(AppConfig.TEXT);
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel imageLabel = new JLabel(resizedIcon);
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel textLabel = new JLabel(
        "<html><div style='width:220px; text-align:center; padding:5px;'>"
        + pageTexts[currentPage]
        + "</div></html>"
    );
    textLabel.setFont(AppConfig.NORMAL_FONT);
    textLabel.setForeground(AppConfig.TEXT);
    textLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel pageLabel = new JLabel("Page " + (currentPage + 1) + " of " + pageTexts.length);
    pageLabel.setFont(AppConfig.NORMAL_FONT.deriveFont(Font.ITALIC, 11f));
    pageLabel.setForeground(AppConfig.TEXT);
    pageLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(AppConfig.BG);
    panel.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(180, 220, 190), 2, true),
    BorderFactory.createEmptyBorder(15, 20, 15, 20)
    ));
    panel.setPreferredSize(new java.awt.Dimension(AppConfig.PHONE_WIDTH - 70, AppConfig.PHONE_HEIGHT - 250));

    logoLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    titleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    imageLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    textLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    pageLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

    panel.add(Box.createVerticalGlue());
    panel.add(logoLabel);
    panel.add(Box.createVerticalStrut(8));
    panel.add(titleLabel);
    panel.add(Box.createVerticalStrut(6));
    panel.add(imageLabel);
    panel.add(Box.createVerticalStrut(6));
    panel.add(textLabel);
    panel.add(Box.createVerticalStrut(8));
    panel.add(pageLabel);
    panel.add(Box.createVerticalGlue());

    JOptionPane.showMessageDialog(
        null,
        panel,
        "EcoLearn - E-Waste Awareness",
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


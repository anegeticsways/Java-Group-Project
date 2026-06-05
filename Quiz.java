/*
Contributed by Ainin (102810)
Role: Member 4 - Assessment Lead

Tested by : Ailin Najwa (102390)

Description:
1. Quiz.java manages the quiz assessment part of the program.
2. Displays 20+ e-waste quiz questions to users.
3. Calculates the final score based on correct answers.
4. Gives score-based motivational messages.
5. Works together with Assessment.java to store question structure.
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class Quiz extends JFrame implements ModuleAction {

    private String userName;

    private ArrayList<Assessment> questions = new ArrayList<>();
    private UserFileAccess fileAccess = new UserFileAccess();

    private int oldScore;
    private int currentQuestion = 0;
    private int correctCount = 0;

    private JLabel questionLabel;
    private JPanel optionsPanel;

    public Quiz(String name, int score) {
        super("Assessment Quiz");
        this.userName = name;
        this.oldScore = score;

        setSize(AppConfig.PHONE_WIDTH, AppConfig.PHONE_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        loadQuestions();
        openModule();
    }

    @Override
    public void openModule() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Warning: Your score will reset if you retake the quiz.\nPlease answer carefully.",
                "Quiz Warning",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.OK_OPTION) {
            dispose();
            new UserOptions(userName, oldScore);
            return;
        }

        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        JLabel title = new JLabel("Quiz Assessment");
        title.setFont(AppConfig.TITLE_FONT);
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionLabel = new JLabel();
        questionLabel.setFont(AppConfig.NORMAL_FONT);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        optionsPanel = new JPanel();
        optionsPanel.setBackground(AppConfig.BG);
        optionsPanel.setLayout(new GridLayout(4, 1, 8, 8));

        JButton cancelBtn = new JButton("Cancel Quiz");
        cancelBtn.setFont(AppConfig.BUTTON_FONT);
        cancelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelBtn.addActionListener(e -> cancelQuiz());

        panel.add(title);
        panel.add(Box.createVerticalStrut(40));
        panel.add(questionLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(optionsPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(cancelBtn);

        add(panel);
        displayQuestion();
        setVisible(true);
    }

    private void displayQuestion() {
        optionsPanel.removeAll();

        Assessment q = questions.get(currentQuestion);

questionLabel.setHorizontalAlignment(SwingConstants.CENTER);  // ensures JLabel centers all text
questionLabel.setText(
    "<html>"
    + "<b>Question " + (currentQuestion + 1) + " of " + questions.size() + "</b>"
    + "<br><br>"
    + "<div style='text-align:center;'>[" + q.getQuestionType() + "]</div>"
    + "<br>"
    + "<div style='text-align:center;'>" + q.getQuestion() + "</div>"
    + "</html>"
);

        String[] options = q.getOptions();

        for (int i = 0; i < options.length; i++) {
                JButton optionBtn = new JButton("<html><center>" + options[i] + "</center></html>");
                optionBtn.setFont(AppConfig.BUTTON_FONT);
                optionBtn.setFocusPainted(false);
                optionBtn.setHorizontalAlignment(SwingConstants.CENTER);

                int selectedAnswer = i;
                optionBtn.addActionListener(e -> checkAnswer(selectedAnswer));

                optionsPanel.add(optionBtn);
        }
        
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private void checkAnswer(int selectedAnswer) {
        Assessment q = questions.get(currentQuestion);

        if (selectedAnswer == q.getCorrectAnswer()) {
            correctCount++;
            JOptionPane.showMessageDialog(this, "Correct! You earned points.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Incorrect.\nCorrect answer: " + q.getOptions()[q.getCorrectAnswer()]);
        }

        currentQuestion++;

        if (currentQuestion < questions.size()) {
            displayQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {
        int totalScore = (correctCount * 100) / questions.size();
        double percentage = ((double) correctCount / questions.size()) * 100;

        GamificationEngine engine = new GamificationEngine();

        fileAccess.updateUserScore(userName, totalScore);

        String result =
                "Quiz Completed!\n\n" +
                "Name: " + userName + "\n" +
                "Correct Answers: " + correctCount + "/" + questions.size() + "\n" +
                "Percentage: " + String.format("%.2f", percentage) + "%\n" +
                "Final Score: " + totalScore + "/100\n\n" +
                "Performance Evaluation:\n" +
                engine.getRubricMessage(totalScore) + "\n\n" +
                "Badge Earned:\n" +
                engine.determineBadge(totalScore) + "\n\n" +
                engine.getMotivationalMessage(totalScore) + "\n\n" +
                "Your score has been saved.";

        JOptionPane.showMessageDialog(this, result, "Quiz Result", JOptionPane.INFORMATION_MESSAGE);

        dispose();
        new UserOptions(userName, totalScore);
    }

    private void cancelQuiz() {
        JOptionPane.showMessageDialog(this,
                "Quiz cancelled. Your score will not be updated.",
                "Quiz Cancelled",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
        new UserOptions(userName, oldScore);
    }

    private void loadQuestions() {
        questions.add(new Assessment("What is e-waste?",
                new String[]{"Food waste", "Electronic waste", "Plastic waste", "Paper waste"}, 1, "MCQ"));

        questions.add(new Assessment("Which item is an example of e-waste?",
                new String[]{"Banana peel", "Glass bottle", "Newspaper", "Old smartphone"}, 3, "MCQ"));

        questions.add(new Assessment("Which SDG is related to responsible consumption and production?",
                new String[]{"SDG 3", "SDG 6", "SDG 12", "SDG 15"}, 2, "MCQ"));

        questions.add(new Assessment("Which material can be found in e-waste?",
                new String[]{"Sugar", "Cotton", "Lead", "Wood"}, 2, "MCQ"));

        questions.add(new Assessment("True or False: E-waste may contain toxic materials.",
                new String[]{"False", "True"}, 1, "True/False"));

        questions.add(new Assessment("Improper e-waste disposal can pollute:",
                new String[]{"Only books", "Only clothes", "Soil and water", "Nothing"}, 2, "MCQ"));

        questions.add(new Assessment("Which is a responsible way to dispose of e-waste?",
                new String[]{"Throw into river", "Burn it", "Use certified recycling centres", "Mix with food waste"}, 2, "MCQ"));

        questions.add(new Assessment("True or False: Recycling e-waste helps reduce pollution.",
                new String[]{"True", "False"}, 0, "True/False"));

        questions.add(new Assessment("Which device is considered e-waste when discarded?",
                new String[]{"Plastic spoon", "Paper bag", "Food wrapper", "Laptop"}, 3, "MCQ"));

        questions.add(new Assessment("Why should we repair electronics?",
                new String[]{"To waste money", "To reduce e-waste", "To increase pollution", "To damage devices"}, 1, "MCQ"));

        questions.add(new Assessment("True or False: Batteries can be part of e-waste.",
                new String[]{"True", "False"}, 0, "True/False"));

        questions.add(new Assessment("What is the main purpose of e-waste recycling centres?",
                new String[]{"Sell unhealthy food", "Burn electronics openly", "Safely process discarded electronics", "Store plastic bottles only"}, 2, "MCQ"));

        questions.add(new Assessment("What can toxic chemicals from e-waste affect?",
                new String[]{"Only furniture", "Only books", "Human health", "Nothing"}, 2, "MCQ"));

        questions.add(new Assessment("Which is better before buying a new device?",
                new String[]{"Throw old device immediately", "Check if old device can be repaired", "Burn old device", "Hide old device"}, 1, "MCQ"));

        questions.add(new Assessment("True or False: Donating working electronics can reduce e-waste.",
                new String[]{"False", "True"}, 1, "True/False"));

        questions.add(new Assessment("Which of these is NOT e-waste?",
                new String[]{"Printer", "Tablet", "Apple peel", "Charger"}, 2, "MCQ"));

        questions.add(new Assessment("Why is informal burning of e-waste dangerous?",
                new String[]{"It creates clean energy", "It improves air quality", "It releases toxic gases", "It reduces pollution"}, 2, "MCQ"));

        questions.add(new Assessment("Which valuable material can be recovered from recycled electronic devices?",
                new String[]{"Plastic straws", "Gold", "Rubber bands", "Paper clips"}, 1, "MCQ"));

        questions.add(new Assessment("True or False: Throwing electronics into regular rubbish bins is recommended.",
                new String[]{"True", "False"}, 1, "True/False"));

        questions.add(new Assessment("True or False: E-waste recycling is unnecessary because electronics naturally disappear over time.",
                new String[]{"True", "False"}, 1, "True/False"));
    }
}

/*
Contributed by Ainin (102810)
Role: Member 4 - Assessment Lead

Description:
1. Defines the structure for quiz questions and answers.
2. Supports different question types such as MCQ and True/False.
3. Stores question text, answer options, and correct answers.
4. Used by Quiz.java to manage 20+ assessment questions.
*/

public class Assessment {

    // Fields to store the question text, answer options, correct answer index, and question type (e.g., MCQ, True/False)
    private String question;
    private String[] options;
    private int correctAnswer;
    private String questionType;

    // Constructor to initialize the assessment question with its details
    public Assessment(String question, String[] options, int correctAnswer, String questionType) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    // Getter methods to access the question details
    public String getQuestion() {
        return question;
    }

    // Returns the array of answer options for the question
    public String[] getOptions() {
        return options;
    }

    // Returns the index of the correct answer in the options array
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    // Returns the type of the question (e.g., "MCQ", "True/False")
    public String getQuestionType() {
        return questionType;
    }
}

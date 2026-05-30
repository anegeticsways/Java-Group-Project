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

    private String question;
    private String[] options;
    private int correctAnswer;
    private String questionType;

    public Assessment(String question,
                      String[] options,
                      int correctAnswer,
                      String questionType) {

        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestionType() {
        return questionType;
    }
}

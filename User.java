/*
Contributed by Andrean (103325)

Description: 
1. Represents a user in the eWaste education application
*/

public class User {
    private final String name;
    private int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}

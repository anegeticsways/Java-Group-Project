//Contributed by Ainin (102810)

import javax.swing.*;

public abstract class ModuleBase extends JFrame {
    protected String name;
    protected int score;

    public ModuleBase(String title, String name, int score) {
        this.name = name;
        this.score = score;

        setTitle(title);
        setSize(AppConfig.PHONE_WIDTH, AppConfig.PHONE_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public abstract void openModule();
}

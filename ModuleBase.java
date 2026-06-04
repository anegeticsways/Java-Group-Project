//Contributed by Ainin (102810)

public abstract class ModuleBase implements ModuleAction {
    protected String name;
    protected int score;

    public ModuleBase(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

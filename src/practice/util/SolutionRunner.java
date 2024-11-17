package practice.util;

public class SolutionRunner {
    private final Solution solution;

    public SolutionRunner(Solution solution) {
        this.solution = solution;
    }

    public void run(){
        solution.run();
    }
}

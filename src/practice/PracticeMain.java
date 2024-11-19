package practice;

import practice.programmers.graph.example.NetworkConnection;
import practice.util.Solution;
import practice.util.SolutionRunner;

public class PracticeMain {
    public static void main(String[] args) {
        Solution solution = new NetworkConnection();
        SolutionRunner runner = new SolutionRunner(solution);
        runner.run();
    }
}



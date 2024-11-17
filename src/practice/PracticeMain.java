package practice;

import practice.programmers.search.graph.GraphSearch;
import practice.programmers.search.graph.example.NetworkConnection;
import practice.programmers.search.graph.example.TargetNumber;
import practice.util.Solution;
import practice.util.SolutionRunner;

public class PracticeMain {
    public static void main(String[] args) {
        Solution solution = new NetworkConnection();
        SolutionRunner runner = new SolutionRunner(solution);
        runner.run();
    }
}



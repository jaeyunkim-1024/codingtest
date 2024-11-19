package practice.programmers.exhaustiveSearch.example;

import practice.util.Solution;


// 완전 탐색 - 전력망
public class ElectricTop implements Solution {

    @Override
    public void run() {
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        int result = solution(9,wires);
    }

    public int solution(int n, int[][] wires) {

        return -1;
    }

}

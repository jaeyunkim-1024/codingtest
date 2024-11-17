package practice.programmers.search.exhaustiveSearch.example;

import practice.util.CustomLog;
import practice.util.Solution;

// 최소 직사각형
public class Oblong implements Solution {
    @Override
    public void run() {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int result = solution(sizes);
        CustomLog.info(result);
    }

    public int solution(int[][] sizes) {
        int answer = 0;
        answer = sort(sizes);
        return answer;
    }

    public int sort(int[][] sizes){
        int c = sizes.length;
        int r = sizes[0].length;
        int[][] result = new int[c][r];
        int maxA = 0, maxB = 0;
        for(int i = 0; i<c; i+=1){
            int a = sizes[i][0];
            int b = sizes[i][1];
            int tempA = Math.max(a,b);
            int tempB = Math.min(a,b);
            maxA = Math.max(maxA,tempA);
            maxB = Math.max(maxB,tempB);
        }
        return maxA * maxB;
    }
}

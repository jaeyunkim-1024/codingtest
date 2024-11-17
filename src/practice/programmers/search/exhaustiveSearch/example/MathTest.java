package practice.programmers.search.exhaustiveSearch.example;

import practice.util.CustomLog;
import practice.util.Solution;

import java.util.*;

public class MathTest implements Solution {
    @Override
    public void run() {
        int size = 10000000;
        int[] answers = new int[size];
        Random random = new Random();
        for(int i =0; i<size; i+=1){
            answers[i] = random.nextInt(5) + 1;
        }
        long startTime = System.currentTimeMillis();
        int[] winner = solution(answers);
        long endTime = System.currentTimeMillis();
        CustomLog.e(endTime - startTime);
        CustomLog.arr(winner);
    }

    /*
    1번 수포자가 찍는 방식: 1, 2, 3, 4, 5
    2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5
    3번 수포자가 찍는 방식: 3, 3,  1, 1,  2, 2,  4, 4,  5, 5
     */
    public int[] solution(int[] answers) {
        int size = answers.length;

        int a = 0, b = 0, c = 0;

        int[] bAnswer = getBAnswer();
        int[] cAnswer = getCAnswer();

        for(int i = 0; i<size; i+=1){
            int answer = answers[i];
            if(typeA(i,answer)){
                a += 1;
            }
            if(typeBC(i,answer, bAnswer)){
                b += 1;
            }
            if(typeBC(i,answer, cAnswer)){
                c += 1;
            }
        }

        int highScore = getMaxScore(a,b,c);

        Map<Integer,List<Integer>> scoreMap = new HashMap<>();
        scoreMap = putScore(scoreMap,1,a);
        scoreMap = putScore(scoreMap,2,b);
        scoreMap = putScore(scoreMap,3,c);

        List<Integer> winner = scoreMap.get(highScore);
        winner.sort(Comparator.comparing(Integer::valueOf));

//        Map<Integer,Integer> scoreMap = new HashMap<>();
//        scoreMap.put(1,a);
//        scoreMap.put(2,b);
//        scoreMap.put(3,c);
//
//        List<Integer> winner = new ArrayList<>();
//        for(int who =1; who<4; who+=1){
//            int score = scoreMap.get(who);
//            if(highScore == score){
//                winner.add(who);
//            }
//        }
        int[] answer = winner.stream().mapToInt(i->i).toArray();
        return answer;
    }

    public int getMaxScore(int a, int b ,int c){
        int max = 0;
        int[] score = new int[]{a,b,c};
        for(int i =0; i<3; i+=1){
            max = Math.max(max,score[i]);
        }
        return max;
    }

    public Map<Integer,List<Integer>> putScore(Map<Integer,List<Integer>> scoreMap, Integer key, int score){
        if(score != 0){
            List<Integer> who = Optional.ofNullable(scoreMap.get(score)).orElse(new ArrayList<>());
            who.add(key);
            scoreMap.put(score,who);
        }
        return scoreMap;
    }

    public boolean typeA(int index, int answer){
        return ((index % 5) + 1) == answer;
    }

    public boolean typeBC(int index, int answer, int[] myAnswers){
        int myIndex = index % myAnswers.length;
        int myAnswer = myAnswers[myIndex];
        return answer == myAnswer;
    }

    public int[] getBAnswer(){
        return new int[]{2,1,2,3,2,4,2,5};
    }

    public int[] getCAnswer(){
        return new int[]{3,3,1,1,2,2,4,4,5,5};
    }
}

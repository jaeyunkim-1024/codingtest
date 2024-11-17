package practice.programmers.search.exhaustiveSearch.example;

import practice.util.Solution;
import practice.util.CustomLog;

import java.util.*;

/// 미해결
public class Dungeon implements Solution {
    @Override
    public void run() {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        int result = solution(k,dungeons);
        CustomLog.info(result);
    }

    public int solution(int k, int[][] dungeons) {
        int size = dungeons.length;
        int[] dungeonIndexArray = new int[size];
        for(int i = 0; i < size; i+=1){
            dungeonIndexArray[i] = i;
        }

        HashSet<String> hash = combine("",new HashSet<>(),dungeonIndexArray, new HashSet<>());
        List<String> list = new ArrayList<>(hash);
        int answer = -1;
        for(String routeStr: list){
            int[] route = Arrays.stream(routeStr.split(",")).mapToInt(Integer::parseInt).toArray();
            int cnt = clearDungeons(k,route,dungeons);
            answer = Math.max(answer,cnt);
        }

        return answer;
    }

    public int clearDungeons(int k, int[] route, int[][] dungeons){
        int clearDungeon = 0;
        for(int di : route){
            int rq = dungeons[di][0];
            int dc = dungeons[di][1];
            if(k >= rq){
                k -= dc;
                clearDungeon+=1;
            }
        }
        return clearDungeon;
    }

    public HashSet<String> combine(String preNumStr, HashSet<Integer> usedNumber, int[] arr, HashSet<String> result){
        for(int a : arr){
            if(!usedNumber.contains(a)){
                String str = preNumStr.isEmpty() ? "" + a : preNumStr + "," + a;
                result.add(str);
                HashSet<Integer> newUsedNumber = new HashSet<>(usedNumber);
                newUsedNumber.add(a);
                combine(str, newUsedNumber, arr, result);
            }
        }
        return result;
    }

}

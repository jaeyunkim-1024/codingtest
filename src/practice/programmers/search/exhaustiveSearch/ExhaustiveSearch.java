package practice.programmers.search.exhaustiveSearch;

import practice.util.Solution;
import practice.util.CustomLog;

import java.util.*;
import java.util.stream.Collectors;

public class ExhaustiveSearch implements Solution {
    /// 예시, 주어진 숫자로 조합할 수 있는 모든 숫자
    int[] arr = new int[2];

    @Override
    public void run(){
        int size = arr.length;
        for(int i =0; i<size; i+=1){
            arr[i] = i + 1;
        }

        HashSet<String> hash = combine("",new HashSet<>(),arr, new HashSet<>());
        List<String> list = new ArrayList<>(hash);
        List<Long> result = list.stream().map(Long::parseLong).sorted().collect(Collectors.toList());
        CustomLog.info(result);

    }

    public List<Long> getCombineByList(int[] arr){
        HashSet<String> hash = combine("",new HashSet<>(),arr, new HashSet<>());
        List<String> list = new ArrayList<>(hash);
        List<Long> result = list.stream().map(Long::parseLong).sorted().collect(Collectors.toList());
        return result;
    }

    public long[] getCombineByArray(int[] arr){
        HashSet<String> hash = combine("",new HashSet<>(),arr, new HashSet<>());
        List<String> list = new ArrayList<>(hash);
        List<Long> resultList = list.stream().map(Long::parseLong).sorted().collect(Collectors.toList());
        long[] array = new long[resultList.size()];
        for(int i =0; i<resultList.size(); i++){
            array[i] = resultList.get(i);
        }
        return array;
    }

    public HashSet<String> combine(String preNumStr, HashSet<Integer> usedNumber, int[] arr, HashSet<String> result){
        for(int a : arr){
            if(!usedNumber.contains(a)){
                String str = preNumStr + a;
                result.add(str);
                HashSet<Integer> newUsedNumber = new HashSet<>(usedNumber);
                newUsedNumber.add(a);
                combine(str, newUsedNumber, arr, result);
            }
        }
        return result;
    }


}

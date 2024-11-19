package practice.programmers.exhaustiveSearch.example;

import practice.util.CustomLog;
import practice.util.Solution;

import java.util.*;
import java.util.stream.Collectors;

public class Sosu implements Solution {
    @Override
    public void run() {
        int result = solution("011");
        CustomLog.e(result);
    }

    public int solution(String numbers) {
        int answer = 0;
        Set<String> list = getAllCombinations(numbers);
        Set<Integer> numList = list.stream().map(Integer::parseInt).collect(Collectors.toSet());
        for(Integer a : numList) {
            List<Integer> divisors = getDivisors(a);
            if(divisors.size() == 2){
                answer += 1;
            }
        }
        return answer;
    }

    // 주어진 문자열로 만들 수 있는 모든 순열을 반환
    // 모든 가능한 길이의 순열을 반환
    public Set<String> getAllCombinations(String str) {
        Set<String> result = new HashSet<>();

        // 각 길이의 부분 순열 생성 (1부터 str.length()까지)
        for (int i = 1; i <= str.length(); i++) {
            getCombinations(str, "", i, result);
        }

        return result;
    }

    // 재귀적으로 문자열의 지정된 길이의 모든 순열을 생성
    private void getCombinations(String str, String prefix, int length, Set<String> result) {
        if (prefix.length() == length) {
            result.add(prefix);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String rem = str.substring(0, i) + str.substring(i + 1);
            getCombinations(rem, prefix + str.charAt(i), length, result);
        }
    }

    public List<Integer> getDivisors(int square){
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(square); i++) {
            if (square % i == 0) {
                divisors.add(i);  // 약수 추가

                // number / i도 약수가 될 수 있으므로 중복을 피하기 위해 체크
                if (i != square / i) {
                    divisors.add(square / i);
                }
            }
        }
        Collections.sort(divisors);
        return divisors;
    }
}

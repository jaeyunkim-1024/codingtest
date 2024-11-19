package practice.programmers.exhaustiveSearch.example;

import practice.util.CustomLog;
import practice.util.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// 옐로우 타일이 카펫 중앙에 오게 넓이 구하기
public class Carpet implements Solution {
    @Override
    public void run() {
        int[] result = solution(24,24);
        CustomLog.arr(result);
    }

    public int[] solution(int brown, int yellow) {
        /// 옐로우 박스가 가운데 들어가게
        List<Integer> yellowDivisors = getDivisors(yellow);

        List<Integer> divisors = getDivisors(brown + yellow);
        List<Integer> widthList = new ArrayList<>();
        for(int yw: yellowDivisors){
            int yh = yellow / yw;
            for(int w : divisors){
                if(w > yw + 1 && (w - yw) % 2 == 0){
                    int h = (brown + yellow) / w;
                    if(h > yh + 1 && (h - yh) % 2 == 0){
                        widthList.add(w);
                    }
                }
            }
        }
        int a = widthList.get(0);
        int b = (brown + yellow) / a;
        int w = Math.max(a,b);
        int h = Math.min(a,b);
        return new int[]{w,h};
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

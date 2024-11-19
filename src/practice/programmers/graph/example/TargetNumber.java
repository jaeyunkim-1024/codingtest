package practice.programmers.graph.example;

import practice.util.Solution;

public class TargetNumber implements Solution {
    @Override
    public void run() {
        int[] numbers = new int[]{1,1,1,1,1};
        int target = 3;
        int r = solution(numbers, target);
        System.out.println(r);
    }
    /*
    주어진 n개의 숫자 numbers로 target을 만드는 방법 개수 리턴
    조건
    - 이 정수들을 순서를 바꾸지 않고
    - n은 양의 정수
     */
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = caculate(numbers,0,0,target, 0);
        return answer;
    }

    public int caculate(int[] numbers, int index, int sum, int target, int result) {
        if(index == numbers.length){
            if(sum == target){
                result += 1;
            }
            return result;
        }
        int num = numbers[index];
        int[] arr = new int[]{sum-num,sum+num};
        int nextIndex = index + 1;
        for(int nsum : arr){
            result = caculate(numbers,nextIndex,nsum,target, result);
        }
        return result;
    }

}

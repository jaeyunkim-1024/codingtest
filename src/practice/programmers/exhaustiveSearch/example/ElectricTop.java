package practice.programmers.exhaustiveSearch.example;

import practice.util.CustomLog;
import practice.util.Solution;

import java.util.*;

public class ElectricTop implements Solution {

    @Override
    public void run() {
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        int result = solution(9,wires);
    }

    public int solution(int n, int[][] wires) {
        /// 전체 경로 찾기
        HashSet<String> allRoute = new HashSet<>();
        for(int[] wire: wires){
            for(int wireNo: wire){
                List<String> list = search(wires,wireNo,"",new ArrayList<String>(),new boolean[n]);
                allRoute.addAll(list);
            }
        }

        /// 전체 경로 중 가장 긴 경로들 = 시작 노드와 종료 노드로 이어지는 길들
        List<List<String>> list = getRoute(n,allRoute);
        Map<Integer,Integer> nodeMap = new HashMap<>();
        for(int i = 1; i<=n; i+=1){
            boolean contains = true;
            for(List<String> route: list){
                if(!route.contains(String.valueOf(i))){
                    contains = false;
                    break;
                }
            }

            if(contains){
                nodeMap.put(i,0);
            }
        }

        /// 가장 많이 이어져있는 노드가 우리가 잘라야할 노드이다.
        int maxCnt = 0;
        int maxWireNo = 0;
        for(int wireNo: nodeMap.keySet()){
            for(int[] wire: wires){
                int a = wire[0];
                int b = wire[1];
                if(wireNo == a || wireNo == b){
                    Integer cnt = nodeMap.get(wireNo);
                    cnt += 1;
                    nodeMap.put(wireNo,cnt);
                }
            }

            int cnt = nodeMap.get(wireNo);
            if(cnt > maxCnt){
                maxWireNo = wireNo;
                maxCnt = cnt;
            }
        }

        CustomLog.info(maxWireNo);

        int answer = 99;

        return answer;
    }

    public List<List<String>> getRoute(int n, HashSet<String> allRoute){
        List<List<String>> result = new ArrayList<>();
        int maxSize = 0;
        for(String route: allRoute){
            String[] arr = route.split(",");
            maxSize = Math.max(maxSize,arr.length);
        }

        for(String route: allRoute){
            String[] arr = route.split(",");
            if(arr.length == maxSize){
                result.add(Arrays.asList(arr));
            }
        }
        return result;
    }

    public List<String> search(int[][] wires, int nextWireNo, String prevWiresStr, List<String> list, boolean[] visited){
        for(int[] wire: wires){
            int a = wire[0];
            int b = wire[1];

            if(nextWireNo == a){
                visited[a-1] = true;
                String nPrevWiresStr = prevWiresStr.isEmpty() ? "" + a : prevWiresStr + "," + a;
                int ri = list.indexOf(prevWiresStr);
                if(ri != -1){
                    list.remove(ri);
                }
                list.add(nPrevWiresStr);
                if(!visited[b-1]){
                    list = search(wires,b,nPrevWiresStr,list,visited);
                }
            }

            if(nextWireNo == b){
                visited[b-1] = true;
                String nPrevWiresStr = prevWiresStr.isEmpty() ? "" + b : prevWiresStr + "," + b;
                int ri = list.indexOf(prevWiresStr);
                if(ri != -1){
                    list.remove(ri);
                }
                list.add(nPrevWiresStr);
                if(!visited[a-1]){
                    list = search(wires,a,nPrevWiresStr,list,visited);
                }
            }
        }
        return list;
    }
}

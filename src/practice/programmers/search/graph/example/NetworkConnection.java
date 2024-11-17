package practice.programmers.search.graph.example;

import practice.util.CustomLog;
import practice.util.Solution;

import java.util.*;

public class NetworkConnection implements Solution {

    /**
     * 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고,
     * 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
     * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
     * 제한사항
     * 컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
     * 각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
     * i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
     * computer[i][i]는 항상 1입니다.
     */
    @Override
    public void run() {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int r = solution(n,computers);
        int expect = 1;
        CustomLog.info(r == expect);
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        ArrayList<Integer>[] graph = getGraph(n,computers);
        answer = search(n,graph);
        return answer;
    }

    public int search(int n, ArrayList<Integer>[] graph){
        boolean[] visited = new boolean[n];
        int startNode = 0;
        int networkCnt = 0;
        Set<Integer> result = new HashSet<>();
        while(result.size() != n || startNode < n){
            Stack<Integer> stack = new Stack<>();
            Set<Integer> path = new HashSet<>();
            stack.push(startNode);
            path.add(startNode);
            if(!result.contains(startNode)){
                Set<Integer> r = dfs(graph,visited,stack,path);
                result.addAll(r);
                networkCnt++;
            }
            startNode++;
        }
        return networkCnt;
    }

    public Set<Integer> dfs(ArrayList<Integer>[] graph, boolean[] visited, Stack<Integer> stack, Set<Integer> path){
        while(!stack.isEmpty()){
            Integer node = stack.pop();
            if(!visited[node]){
                visited[node] = true;
                for(Integer adj : graph[node]){
                    stack.push(adj);
                    path.add(adj);
                    dfs(graph, visited, stack, path);
                }
            }
        }
        return path;
    }

    public ArrayList<Integer>[] getGraph(int n, int[][] computers){
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            ArrayList<Integer> adj = new ArrayList<>();
            for(int j = 0; j < n; j++){
                if(i != j && isConnected(computers[i][j])){
                    adj.add(j);
                }
            }
            graph[i] = adj;
        }
        return graph;
    }

    public boolean isConnected(int n){
        return n == 1;
    }
}

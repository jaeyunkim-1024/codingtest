package practice.programmers.search.graph;

import practice.util.CustomLog;
import practice.util.Solution;

import java.util.*;

public class GraphSearch implements Solution {
    @Override
    public void run() {
        int[][] graph = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{5,7}};

        Map<Integer,List<Integer>> bfsNodeTree = new HashMap<>();
        Map<Integer,List<Integer>> dfsNodeTree = new HashMap<>();
        for(int[] node: graph){
            int a = node[0];
            int b = node[1];

            dfsNodeTree.putIfAbsent(a, new ArrayList<>());
            dfsNodeTree.putIfAbsent(b, new ArrayList<>());
            dfsNodeTree.get(a).add(b);
            dfsNodeTree.get(b).add(a);

            Collections.reverse(dfsNodeTree.get(a));
            Collections.reverse(dfsNodeTree.get(b));

            bfsNodeTree.putIfAbsent(a, new ArrayList<>());
            bfsNodeTree.putIfAbsent(b, new ArrayList<>());
            bfsNodeTree.get(a).add(b);
            bfsNodeTree.get(b).add(a);

            Collections.sort(bfsNodeTree.get(a));
            Collections.sort(bfsNodeTree.get(b));
        }
        List<Integer> dfsPath = dfs(dfsNodeTree,dfsNodeTree.keySet().size(),5);
        List<Integer> bfsPath = bfs(bfsNodeTree,bfsNodeTree.keySet().size(),7);
        CustomLog.info("깊이 우선 탐색" + dfsPath);
        CustomLog.info("너비 우선 탐색" + bfsPath);
    }

    public List<Integer> dfs(Map<Integer,List<Integer>> nodeTree, int nodeCnt, int startNode){
        List<Integer> path = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        boolean[] visited = new boolean[nodeCnt];

        while(!stack.isEmpty()){
            int node = stack.pop();
            if(!visited[node]){
                visited[node] = true;
            }

            path.add(node);

            for(Integer child: nodeTree.get(node)){
                if(!visited[child]){
                    stack.push(child);
                }
            }
        }

        return path;
    }

    public List<Integer> bfs(Map<Integer,List<Integer>> nodeTree, int nodeCnt, int startNode){
        List<Integer> path = new ArrayList<>();
        Queue<Integer> stack = new LinkedList<>();
        stack.offer(startNode);
        boolean[] visited = new boolean[nodeCnt];

        while(!stack.isEmpty()){
            int node = stack.poll();
            if(!visited[node]){
                visited[node] = true;
            }

            path.add(node);

            for(Integer child: nodeTree.get(node)){
                if(!visited[child]){
                    stack.offer(child);
                }
            }
        }

        return path;
    }
}

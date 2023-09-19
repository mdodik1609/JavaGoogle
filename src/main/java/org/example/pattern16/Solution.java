package org.example.pattern16;

import java.util.*;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2016%3A%20%F0%9F%94%8E%20Topological%20Sort%20(Graph).md
     *
     *  Pattern 16: Topological sort
     * */
    public static void main(String[] args){
//        System.out.println(
//                canFinish(
//                        2, new int[][]{{1,0}}
//                )
//        );
//        System.out.println(
//                findMinHeightTrees(
//                        4, new int[][]{ {1,0},{1,2},{1,3} }
//                )
//        );
        System.out.println(
                replace(
                        new HashMap<>(Map.of("USER", "admin", "HOME", "/%USER%/home")), "I am %USER% My home is %HOME%"
                )
        );
    }
    /**
     *  https://leetcode.com/problems/course-schedule/
     *
     *  207. Course Schedule
     * */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //creating adjacency list to represent the graph
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];

        //building a graph which is based on prerequisites
        for(int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            adjList.get(prereq).add(course);
            inDegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Add courses with no prerequisites to the queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int coursesTaken = 0;
        //starting topological sort
        while(!q.isEmpty()) {
            int course = q.poll();
            coursesTaken++;

            for(int adjCourse : adjList.get(course)) {
                inDegree[adjCourse]--;
                if(inDegree[adjCourse] == 0) q.add(adjCourse);
            }
        }

        return coursesTaken == numCourses;
     }
     /**
      *     https://leetcode.com/problems/course-schedule-ii/description/
      *
      *     210. Course Schedule II
      * */
     public int[] findOrder(int numCourses, int[][] prerequisites) {
         List<List<Integer>> adjList = new ArrayList<>(numCourses);
         for(int i = 0; i < numCourses;i++){
             adjList.add(new ArrayList<>());
         }

         int[] inDegree = new int[numCourses];
         for(int[] prereq : prerequisites){
             int course = prereq[0];
             int pre = prereq[1];
             adjList.get(pre).add(course);
             inDegree[course]++;
         }

         Queue<Integer> q = new LinkedList<>();
         for(int i = 0; i < numCourses; i++) {
             if(inDegree[i] == 0) {
                 q.add(i);
             }
         }

         int[] result = new int[numCourses];
         int coursesTaken = 0;

         while(!q.isEmpty()) {
             int currentCourse = q.poll();
             result[coursesTaken] = currentCourse;
             coursesTaken++;


             for(int adjCourse : adjList.get(currentCourse)) {
                 inDegree[adjCourse]--;
                 if(inDegree[adjCourse] == 0) q.add(adjCourse);
             }
         }
         if(coursesTaken != numCourses) return new int[]{};
         return result;
     }

     /**
      *     https://leetcode.com/problems/minimum-height-trees/
      *
      *     310. Minimum Height Trees
      * */
     public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
         if (n == 0) return Collections.emptyList();
         if (n == 1) {
             List<Integer> r1 = new ArrayList<>();
             r1.add(0);
             return r1;
         }

         List<List<Integer>> adjList = new ArrayList<>(n);
         for (int i = 0; i < n; i++) {
             adjList.add(new ArrayList<Integer>());
         }

         int[] inDegree = new int[n];
         for (int[] edge : edges) {
             int firstEdge = edge[0];
             int secondEdge = edge[1];

             adjList.get(firstEdge).add(secondEdge);
             adjList.get(secondEdge).add(firstEdge);

             inDegree[firstEdge]++;
             inDegree[secondEdge]++;
         }

         Queue<Integer> q = new LinkedList();
         for (int i = 0; i < n; i++) {
             if (inDegree[i] == 1) q.add(i);
         }

         List<Integer> result = new ArrayList<>();
         while (!q.isEmpty()) {
             result = new ArrayList<>();
             int size = q.size();
             for (int i = 0; i < size; i++) {
                 int node = q.poll();
                 result.add(node);
                 inDegree[node]--;
                 for (int childNode : adjList.get(node)) {
                     inDegree[childNode]--;
                     if (inDegree[childNode] == 1) q.add(childNode);
                 }
             }
         }
         return result;
     }

     /**
      *     https://leetcode.com/discuss/interview-question/2328651/Google-Phone-Interview-Question
      *
      *     Suppose we are creating a string replacement library. Given a map of string replacements, replace the value in the input string
      *
      *     Given map {X=>123, Y=456}
      *     Input: %X%_%Y%
      *     Output: 123_456
      *
      *     Given map {USER=>admin, HOME=>/%USER%/home}
      *     Input: I am %USER% My home is %HOME%
      *     Output: I am admin My home is /admin/home
      * */
     public static String replace(HashMap<String, String> replaceMap, String input) {
         Map<String, Integer> inDegreeMap = new HashMap<>();

         for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
             String key = entry.getKey();
             String value = entry.getValue();

             inDegreeMap.put(key, 0);

             for (char c : value.toCharArray()) {
                 if (c == '%') inDegreeMap.put(key, inDegreeMap.getOrDefault(key, 0) + 1);
             }
             inDegreeMap.put(key, inDegreeMap.getOrDefault(key, 0)/2);
         }

         Queue<String> q = new LinkedList<>();
         for (Map.Entry<String, Integer> entry : inDegreeMap.entrySet()) {
             if (entry.getValue() == 0) q.add(entry.getKey());
         }

         while(!q.isEmpty()) {
             String current = q.poll();

             current.substring(0, 1);

             for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
                 if (entry.getValue().contains(current)) {
                     replaceMap.put(entry.getKey(), entry.getValue().replace("%" + current + "%", replaceMap.get(current)));
                     inDegreeMap.put(entry.getKey(), inDegreeMap.get(entry.getKey()) - 1);
                     if (inDegreeMap.get(entry.getKey()) == 0) q.add(entry.getKey());
                 }
             }
         }

         for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
             input = input.replaceAll("%" + entry.getKey() + "%", entry.getValue());
         }

         System.out.format("Output is: %s", input);
         return input;
     }
}

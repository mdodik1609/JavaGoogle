package org.example.pattern16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2016%3A%20%F0%9F%94%8E%20Topological%20Sort%20(Graph).md
     *
     *  Pattern 16: Topological sort
     * */
    public static void main(String[] args){
        System.out.println(
                canFinish(
                        2, new int[][]{{1,0}}
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
}

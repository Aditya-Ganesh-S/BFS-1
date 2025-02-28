import java.util.*;
// Time Complexity: O(v+e)
// Space Complexity: O(v+e)
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0) return true;
        int[] indegrees = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge: prerequisites){
            indegrees[edge[0]]++;
            if(!map.containsKey(edge[1])){
                map.put(edge[1], new ArrayList<>());
            }
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for(int i =0; i < numCourses; i++){
            if(indegrees[i] == 0){
                q.add(i);
                count++;
            }
        }
        while(!q.isEmpty()){
            int curr = q.poll();
            List<Integer> li = map.get(curr);
            if(li != null){
                for(int edge: li){
                    indegrees[edge]--;
                    if(indegrees[edge] == 0){
                        q.add(edge);
                        count++;
                        if(count == numCourses) return true;
                    }
                }
            }
        }
        return false;
    }
}

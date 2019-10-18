import java.lang.reflect.Array;
import java.util.*;

public class Merge {

    public class MyComparable implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return Integer.compare(o1[0], o2[0]);
        }
    }

    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> tmpList = new ArrayList<>();
        int[][] result =new int[intervals.length][2];
        if(intervals.length == 0) return result;
        Collections.addAll(tmpList,intervals);
        tmpList.sort(new MyComparable());
        int i = 0;
        result[0] = tmpList.get(0);
        for(int j=1;j<tmpList.size();j++){
            if(tmpList.get(j)[0] <= result[i][1]){
                result[i][1] = tmpList.get(j)[1];
            }else{
                result[++i] = tmpList.get(j);
            }
        }
        return Arrays.copyOf(result, i++);
    }

}

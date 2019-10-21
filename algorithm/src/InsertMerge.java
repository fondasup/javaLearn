import java.util.Arrays;

public class InsertMerge {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] result = new int[intervals.length + 1][2];
        int len = 0;
        for(int i=0; i< intervals.length; i++){
            if(newInterval[0] <= intervals[i][0]) {
                System.arraycopy(intervals, 0, result, 0, i);
                if (intervals[i][1] >= newInterval[0]) {
                    int head = Math.min(intervals[i][0], newInterval[0]);
                    int tail = newInterval[1];
                    for (int j = i + 1; j < intervals.length; j++) {
                        if (intervals[j][0] <= tail) tail = Math.max(tail, intervals[0][1]);
                        else {
                            result[i] = new int[]{head, tail};
                            System.arraycopy(intervals, j, result, i + 1, intervals.length - j);
                            return Arrays.copyOf(result, i + 1 + intervals.length - j);
                        }
                    }
                    result[i] = new int[]{head, tail};
                    return Arrays.copyOf(result, i + 1);
                }
            }else{

            }
        }
        System.arraycopy(intervals,0,result,0,intervals.length);
        result[intervals.length] = newInterval;
        return  result;
    }

    public static void main(String[] args){
        int[][] intervals =new int[][]{{1,3},{6,9}};
        int[] newInterval =new int[]{4,5};
        int[][] result = insert(intervals, newInterval);
        System.out.println(Arrays.toString(result[1]));
    }
}

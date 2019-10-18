//给定一个非负整数数组，你最初位于数组的第一个位置。
//        数组中的每个元素代表你在该位置可以跳跃的最大长度。
//        判断你是否能够到达最后一个位置。
//        示例 1:
//        输入: [2,3,1,1,4]
//        输出: true
//        解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。



//自顶向下的动态规划，采用递归算法 是回溯算法的优化
//自下向上的动态规划 是先从已知条件入手 然后一步一步使已知条件靠近结果
class JumpGame
{
    public boolean canJump(int[] nums) {
        int len = nums.length - 1;
        int i = 0;
        while(i<=len){
            if(i + nums[i] >= len){
                return true;
            }else if(nums[i] == 0){
                return false; //要考虑到此处3为0, 永远也走不出去的情况
            }
            int maxStep = 0;
            int maxI = 0;
            for(int j=i+1; j<=Math.min(i+nums[i],len);j++){
                int tmpStep = j-i+nums[j];
                if(tmpStep >= maxStep ){
                    maxStep = tmpStep;
                    maxI = j;
                }
            }
            i = maxI;
        }
        return false;
    }
}
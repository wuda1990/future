package com.quantumn.future.algorithm.leetcode;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_15 {
    /**
     * 用递归的方法需要消耗掉O(n!)*O(n)== O(n3), 其中n!为递归的时间,O(n)为最差情况下需要判重循环的次数
     * 没有双指针快，在面对很多输入的时候超时了
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        permute(ans,nums, list, 0);
        return ans;
    }

    //例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    //-4,-1,-1,0,1,2
    //-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6
    private void permute(List<List<Integer>> ans, int[] nums, List<Integer> list, int index) {
        //list有3个数时计算3数之和
        if (list.size()==3) {
            if (nums[list.get(0)] + nums[list.get(1)] + nums[list.get(2)] == 0) {
                List<Integer> threeSum = new ArrayList<Integer>();
                threeSum.add(nums[list.get(0)]);
                threeSum.add(nums[list.get(1)]);
                threeSum.add(nums[list.get(2)]);
                Collections.sort(threeSum);
                //判重，不重复则添加
                for (List<Integer> ls : ans) {
                    if (ls.get(0)==threeSum.get(0) && ls.get(1)==threeSum.get(1) && ls.get(2) == threeSum.get(2))
                   {
                        return;
                    }
                }
                ans.add(threeSum);
            }
            return;
        } else {
            /**
             * nums[i] 对应候递归的数组或索引要变为从i开始
             */
            int j=0;
            for (int i = index; i < nums.length; i++) {
                if (!list.contains(i)) {
                    list.add(i);
                    permute(ans, nums, list, i);
                    list.remove(list.size()-1);
                }
            }
        }
    }


    public static void main(String[] args) {
        Subject_15 demo = new Subject_15();
        List<List<Integer>> ans = demo.threeSum2(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
        ans.stream().forEach(list->{
            list.stream().forEach(i-> System.out.print(i+","));
            System.out.println();
        }
        );
    }

    /**
     * 参考题解，用双指针解题
     * 标签：数组遍历
     * 首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L] 和 nums[R]，
     * 计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
     * 如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
     * 如果 nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sum == 0 时，nums[L] == nums[L+1]] 则会导致结果重复，应该跳过，L++
     * 当 sum == 0 时，nums[R] == nums[R−1] 则会导致结果重复，应该跳过，R−−
     * 时间复杂度：O(n2)，n 为数组长度
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * -4,-1,-1,0,1,2
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length < 3) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //i是三个数中最左边的数，如果nums[i]>0，因为nums[i]最小，所以三个数的和肯定大于0,继续下一个循环
            if (nums[i]>0)  break;
            if (i>0 && nums[i]==nums[i-1])
                continue;//导致结果重复，要去重
            int left = i+1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    /**
                     * 预防出现类似-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6这种情况，比方nums[i]=-4,（-4，-2，6）会出现多次
                     */
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                }else {
                    right--;
                }
            }

        }
        return ans;
    }

}

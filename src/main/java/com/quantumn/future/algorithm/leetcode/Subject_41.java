package com.quantumn.future.algorithm.leetcode;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 说明:
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_41 {
    /**
     * 不论官方题解还是评论区的方法，都是利用了数组下标和值应该对应这一条来解决问题。
     * 评论区的大神给的妙招：
     *     假设原始数组为 A。先构造一个临时数组 tmp，初始化为 0，大小为A.size(). 遍历 A，把 A[i] 复制到 tmp[A[i]-1] 的位置。如果 A[i] - 1 超过了 tmp 的范围，就直接扔掉。如此一来，tmp[0...size) 中就保存了一部分 A 的值。然后从位置 0 开始检查 tmp，如果发现该位置的值和索引号不匹配，就说明找到了缺失的数了。
     *
     * 注意上面的方案一，没办法优化成 in-place 算法，但是方案二可以。方案二，可以不使用 tmp 数组，直接在原始数组中操作，把每个数放到正确的位置。
     *
     * 我们使用一种 “座位交换法" 来达到 in-place 的目的：
     *
     *     从第一个位置开始，让座位上的乘客走到自己应该坐的位置，并让该位置的人坐到第一个位置。一直进行这样的操作，直到第一个位置的人坐到自己位置。不过有时候我们知道，有的人总喜欢逃票。因此终止条件就是，一旦发现第一个位置的人逃票（票号 <= 0，或 >= 最大座位号)，则终止交换。
     *     对第二到N个位置做相同的操作。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //座位交换法，关键是while循环退出的条件
            while (nums[i] > 0 && nums[i] < nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp-1] = temp;
            }
        }

        //原数组交换后，第一个数值和下标不对应的值就是第一个缺失的正数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i+1;
            }
        }
        return nums.length+1;
    }

    /**
     * 官方题解，有些复杂，把自己当作一个hashMap,key是数组下标，value为正负值，正值则为缺失的最小正整数。
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        //look up to find whether 1 exists
        boolean containsOne = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1){
                containsOne = true;
                break;
            }
        }
        if (!containsOne) return 1;
        //if (n == 1) return 2;
        //set the value to 1 when value <0,==0 or >nums.length
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i]=1;
            }
        }
        for (int i = 0; i < n; i++) {
            //这里使用绝对值也很关键
            int a = Math.abs(nums[i]);
            if (a == n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[a] = -Math.abs(nums[a]);
            }
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        if (nums[0] > 0) {
            return n;
        }

        return n+1;
    }

    public static void main(String[] args) {
        Subject_41 demo = new Subject_41();
        System.out.println(demo.firstMissingPositive2(new int[]{1}));
//        System.out.println(demo.firstMissingPositive2(new int[]{1,2,0}));
    }
}

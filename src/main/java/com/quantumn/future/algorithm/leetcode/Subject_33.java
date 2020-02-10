package com.quantumn.future.algorithm.leetcode;

/**
 * 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_33 {
    public int search(int[] nums, int target) {
        return  search(nums, 0, nums.length-1,target);
    }

    /**
     * 参考评论里的大神完成的此题
     * 一开始知道是二分法，可是具体怎么操作确有点乱，具体根据什么条件判断，去走上半段数组还是下半段数组不是特别清楚！
     * 这题是在有序数组查找数字的基础上将有序数组的某一个节点突然降序，即旋转，是有拔高的。
     * 解决方法就是除了比较middle和target的大小，还要比较target,left的大小和left,middle的大小。
     * 这样的问题需要通过具体的数组例子来抽象比较好理解
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int search(int[] nums, int left, int right, int target) {
        if(left>right) return -1;
        int middle = (left + right) / 2;
        if (target == nums[middle]) return middle;
        if (nums[middle] < nums[left]) {
            //middle将数组分成两半，一半肯定是有序的，一半是无序的，先判断target是否在有序的一半里
            // 4560123这种情况，if 0<=target<=4,则只需在数组右半边查找，否则在左半边查找，即4560
            if (nums[middle] < target && target < nums[left]) {
                return search(nums, middle + 1, right, target);
            } else {
                return search(nums, left, middle - 1, target);
            }
        } else {
            //middle将数组分成两半，一半肯定是有序的，一半是无序的，先判断target是否在有序的一半里
            //4567012这种情况，if 4<=target<=7,则需在数组左半边查找，否则在右半边查找
            if (nums[left] <= target && target < nums[middle]) {
                return search(nums, left, middle - 1, target);
            } else {
                return search(nums, middle + 1, right, target);
            }
        }
    }

    public static void main(String[] args) {
        Subject_33 demo = new Subject_33();
        System.out.println(demo.search(new int[]{5,1,2,3,4},1));
    }
}

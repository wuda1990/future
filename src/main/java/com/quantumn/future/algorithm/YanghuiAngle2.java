package com.quantumn.future.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class YanghuiAngle2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prevResult = new ArrayList<>();
        prevResult.add(1);
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= rowIndex; i++) {
            result.clear();
            result.add(1);
            for (int j = 1; j < i; j++) {
                result.add(prevResult.get(j-1)+prevResult.get(j));
            }
            result.add(1);
            prevResult.clear();
            prevResult.addAll(result);
        }
        return prevResult;
    }

    //通过队列的方式去更新list，也很巧妙
    //队列尾部添加一个元素，队列头部移出元素
    public List<Integer> getRowByLinkedList(int rowIndex) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add(1);
            for (int j = 1; j < i; j++) {
                result.add(result.get(0)+result.get(1));
                result.poll();
            }
            result.add(1);
            result.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        YanghuiAngle2 angle2 = new YanghuiAngle2();
        List<Integer> ans = angle2.getRow(3);
        for (Integer t : ans) {
            System.out.print(t+",");
        }
    }
}

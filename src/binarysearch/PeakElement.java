package binarysearch;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/find-peak-element/
 * <p>
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 */
public class PeakElement {


    public static void main(String[] args) {
        Map<Employee, String> map= new TreeMap<>();
        map.put(new Employee(9,"aaa"),"");
        map.put(new Employee(2,"ttt"),"sd");

        for (Map.Entry entry: map.entrySet()){
            System.out.println(entry.getKey());
        }

    }

}
class Employee {
    int id;
    String name;
    Employee (int id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
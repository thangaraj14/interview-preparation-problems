package structuralpattern.composite;

import java.util.ArrayList;

class Manager implements Employee {
    private String name;
    private long empId;
    private String position;
    private ArrayList<Employee> subordinates;

    public Manager(long empId, String name, String position) {
        this.empId = empId;
        this.name = name;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(empId + " " + name + " " + position);
    }
}
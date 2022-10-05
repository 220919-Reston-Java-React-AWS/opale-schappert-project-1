package com.revature.model;

import java.util.Objects;

public class Reimbursements {

    //Variables
    private int id;
    private float amount;
    private String description;
    private int employeeId;
    private int managerId;
    private String status;

    //Default Constructor
    public Reimbursements(){

    }
    //Parameterized Constructor
    public Reimbursements(int id, float amount, String description, int employeeId, int managerId, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursements that = (Reimbursements) o;
        return id == that.id && Float.compare(that.amount, amount) == 0 && employeeId == that.employeeId && managerId == that.managerId && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, employeeId, managerId, status);
    }

    @Override
    public String toString() {
        return "Reimbursements{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", employeeId=" + employeeId +
                ", managerId=" + managerId +
                ", status='" + status + '\'' +
                '}';
    }
}





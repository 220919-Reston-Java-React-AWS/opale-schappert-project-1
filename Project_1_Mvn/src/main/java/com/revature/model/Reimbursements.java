package com.revature.model;

import java.util.Objects;

public class Reimbursements {

    //Variables
    private int id;
    private double amount;
    private String descrip;
    private int employeeId;
    private int managerId;
    private String status;

    //Default Constructor
    public Reimbursements(){

    }
    //Parameterized Constructor
    public Reimbursements(int id, double amount, String descrip, int employeeId, int managerId, String status) {
        this.id = id;
        this.amount = amount;
        this.descrip = descrip;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
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
        return id == that.id && Double.compare(that.amount, amount) == 0 && employeeId == that.employeeId && managerId == that.managerId && Objects.equals(descrip, that.descrip) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, descrip, employeeId, managerId, status);
    }

    @Override
    public String toString() {
        return "Reimbursements{" +
                "id=" + id +
                ", amount=" + amount +
                ", descrip='" + descrip + '\'' +
                ", employeeId=" + employeeId +
                ", managerId=" + managerId +
                ", status='" + status + '\'' +
                '}';
    }
}





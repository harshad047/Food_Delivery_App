package com.tss.service;

import com.tss.model.payment.IPayment;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int idCounter = 1;

    private final int id;
    private String name;
    private String address;
    private String password;
    private IPayment paymentMethod;

    public Customer(String name, String address, String password, IPayment paymentMethod) {
        this.id = idCounter++;
        this.name = name;
        this.address = address;
        this.password = password;
        this.paymentMethod = paymentMethod;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
    public IPayment getPaymentMethod() { return paymentMethod; }

    public void setPaymentMethod(IPayment paymentMethod) { this.paymentMethod = paymentMethod; }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Address: " + address;
    }
}

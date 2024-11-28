package com.bank.offbank.model;

import java.io.Serializable;

public class CoinsModel  implements Serializable {
    private int id;
    private double money;
    private double creditCardLimit;

    public CoinsModel() {
    }

    public CoinsModel(double money, double creditCardLimit) {
        this.money = money;
        this.creditCardLimit = creditCardLimit;
    }

    @Override
    public String toString() {
        return "CoinsModel{" +
                "id=" + id +
                ", money=" + money +
                ", creditCardLimit=" + creditCardLimit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }
}

package com.example.RecargasCelular.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


@Entity
@Table(name = "recharge")
public class Recharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecharge;
    private double price;
    private Date rechargeTime;
    private int cpf;
    @Column(name = "id")
    private int idPayments;

    public int getIdRecharge() {
        return idRecharge;
    }

    public void setId_recharge(int idRecharge) {
        this.idRecharge = idRecharge;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }


    public void setIdRecharge(int idRecharge) {
        this.idRecharge = idRecharge;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getIdPayments() {
        return idPayments;
    }

    public void setIdPayments(int idPayments) {
        this.idPayments = idPayments;
    }
}

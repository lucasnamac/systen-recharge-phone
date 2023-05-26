package com.example.RecargasCelular.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
    @Id
    private int cpf;
    private String name;
    private int phone;

    public int getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}

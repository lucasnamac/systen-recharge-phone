package com.example.RecargasCelular.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table(name = "payments")
public class Payments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @JsonProperty("creditCardNumber")
    private int creditCardNumber;
    @JsonProperty("creditCardName")
    private String creditCardName;
    @JsonProperty("validThru")
    private Date validThru;
    @JsonProperty("password")
    private String password;
    @JsonProperty("idClient")
    private int idClient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public Date getValidThru() {
        return validThru;
    }

    public void setValidThru(Date validThru) {
        this.validThru = validThru;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int id_client) {
        this.idClient = id_client;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "id=" + id +
                ", creditCardNumber=" + creditCardNumber +
                ", creditCardName='" + creditCardName + '\'' +
                ", validThru=" + validThru +
                ", password='" + password + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}

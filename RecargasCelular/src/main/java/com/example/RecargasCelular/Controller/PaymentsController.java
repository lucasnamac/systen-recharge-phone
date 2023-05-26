package com.example.RecargasCelular.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.RecargasCelular.DAO.IPayments;
import com.example.RecargasCelular.model.Payments;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentsController {

    @Autowired
    private IPayments dao_Payments;

    @PostMapping("/registerPayment")
    public String RegisterPaymentMethod(@RequestBody Payments payments) {
        dao_Payments.save(payments);
        return "O cartão de crédito de número " + payments.getCreditCardNumber() + " foi adicionado com sucesso para o cliente " + payments.getIdClient();
    }
}

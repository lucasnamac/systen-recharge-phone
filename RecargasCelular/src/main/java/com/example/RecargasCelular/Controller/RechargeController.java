package com.example.RecargasCelular.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.RecargasCelular.DAO.IPayments;
import com.example.RecargasCelular.Rabbit.RechargeProducer;
import com.example.RecargasCelular.Rabbit.RechargeService;
import com.example.RecargasCelular.model.Payments;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.RecargasCelular.DAO.IRecharge;
import com.example.RecargasCelular.model.Recharge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RechargeController {

    @Autowired
    private IRecharge dao_recharge;

    @Autowired
    private IPayments dao_payments;
    private String queueName1 = "PhoneRechargeToRechargePlataform";
    private final RechargeService rechargeService;
    private final RechargeProducer rechargeProducer;

    private RabbitTemplate rabbitTemplate;

    public RechargeController(RechargeService rechargeService, RechargeProducer rechargeProducer) {
        this.rechargeService = rechargeService;
        this.rechargeProducer = rechargeProducer;
    }

    @PostMapping("/recharge")
    public String rechargeClient(@RequestBody Recharge recharge) throws InterruptedException {
        recharge.setRechargeTime(new Date());

        Optional<Payments> payments = dao_payments.findById(recharge.getIdPayments());
        dao_recharge.save(recharge);
        rechargeProducer.sendMessage(payments.get());
        String msg = rechargeService.consumer();
        System.out.println(msg);
        return msg;
    }

    @GetMapping("/listRecharge")
    public List<Recharge> listRecharge() {
        return (List<Recharge>) dao_recharge.findAll();
    }
}

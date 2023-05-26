package com.example.RecargasCelular.DAO;

import org.springframework.data.repository.CrudRepository;
import com.example.RecargasCelular.model.Payments;
import org.springframework.stereotype.Service;

@Service
public interface IPayments extends CrudRepository<Payments, Integer> {

}

package com.example.RecargasCelular.DAO;

import org.springframework.data.repository.CrudRepository;
import com.example.RecargasCelular.model.Client;

public interface IClient extends CrudRepository<Client, Integer> {

}

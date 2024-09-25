package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.cliente;
import com.example.demo.repository.clienteRepository;

@Service
public class clienteService {

    @Autowired
    clienteRepository clienteRepository;

    public List<cliente> getClientes(){
        return clienteRepository.findAll();
    }

    public cliente getClientes(Long id){
        return clienteRepository.findById(id).get();
    }

    public void saveOrUpdate(cliente cliente){
        clienteRepository.save(cliente);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

}

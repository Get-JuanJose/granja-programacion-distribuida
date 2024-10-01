package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.porcino;
import com.example.demo.repository.porcinoRepository;

@Service
public class porcinoService {

    @Autowired
    porcinoRepository porcinoRepository;

    public List<porcino> getPorcinos() {
        return porcinoRepository.findAll();
    }

    public porcino getPorcinos(Long id) {

           return porcinoRepository.findById(id).get();
    }

    public void saveOrUpdate(porcino porcino) {
        porcinoRepository.save(porcino);
    }

    public void delete(Long id) {
        porcinoRepository.deleteById(id);
    }

}

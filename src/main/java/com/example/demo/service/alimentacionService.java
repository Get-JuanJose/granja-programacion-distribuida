package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.alimentacion;
import com.example.demo.repository.alimentacionRepository;

@Service
public class alimentacionService {

    @Autowired
    alimentacionRepository alimentacionRepository;

    public List<alimentacion> getAlimentaciones() {
        return alimentacionRepository.findAll();
    }

    public alimentacion getAlimentaciones(Long id) {
        return alimentacionRepository.findById(id).get();
    }

    public void saveOrUpdate(alimentacion alimentacion) {
        alimentacionRepository.save(alimentacion);
    }

    public void delete(Long id) {
        alimentacionRepository.deleteById(id);
    }
}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tporcinos")
public class porcino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "raza", nullable = false)
    private String raza; // 1-York, 2-Hamp, 3-Duroc

    @Column(name = "edad")
    private int edad;

    @Column(name = "peso")
    private double peso;

    //Relación entidad cliente
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = true)
    private cliente cliente;

    //Relación entidad alimentacion
    @ManyToOne
    @JoinColumn(name = "alimentacionId", nullable = true)
    private alimentacion alimentacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public alimentacion getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }

    public porcino() {
    }

    public porcino(int id, String raza, int edad, double peso, cliente cliente, alimentacion alimentacion) {
        this.id = id;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.cliente = cliente;
        this.alimentacion = alimentacion;
    }

    public porcino(String raza, int edad, double peso, cliente cliente, alimentacion alimentacion) {        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.cliente = cliente;
        this.alimentacion = alimentacion;
    }  
}


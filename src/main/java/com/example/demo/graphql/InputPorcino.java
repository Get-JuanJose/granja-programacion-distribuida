package com.example.demo.graphql;

import jakarta.persistence.Column;

public class InputPorcino {


    private String raza; // 1-York, 2-Hamp, 3-Duroc
    private int edad;
    private float peso;
    private String cliente_id;
    private String alimentacion_id;

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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getAlimentacion_id() {
        return alimentacion_id;
    }

    public void setAlimentacion_id(String alimentacion_id) {
        this.alimentacion_id = alimentacion_id;
    }
}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TAlimentacion")
public class alimentacion {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="descripcion", nullable=false)
    private String descripcion;

    @Column(name="dosis", nullable=false)
    private int dosis;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getDosis() {
        return dosis;
    }
    
    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public alimentacion() {
    }

    public alimentacion(Long id, String descripcion, int dosis) {
        this.id = id;
        this.descripcion = descripcion;
        this.dosis = dosis;
    }   
    
    public alimentacion(String descripcion, int dosis) {
        this.descripcion = descripcion;
        this.dosis = dosis;
    }
    
}

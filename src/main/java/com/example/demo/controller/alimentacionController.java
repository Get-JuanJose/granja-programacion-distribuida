package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.alimentacion;
import com.example.demo.service.alimentacionService;

@Controller
@RequestMapping(path="/alimentacion")
public class alimentacionController {

    @Autowired
    private alimentacionService alimentacionService;

    @GetMapping
    public String listarAlimentaciones(Model model) {
        model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
        return "alimentaciones";
    }
    
    /*@GetMapping("/")
    public String mostrarPaginaInicio() {
        return "index";
    }*/

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaAlimentacion(Model model) {
        model.addAttribute("alimentacion", new alimentacion());
        return "alimentacion-form";
    }

    @PostMapping
    public String guardarAlimentacion(alimentacion alimentacion) {
        alimentacionService.saveOrUpdate(alimentacion);
        return "redirect:/alimentacion";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAlimentacion(@PathVariable Long id, Model model) {
        model.addAttribute("alimentacion", alimentacionService.getAlimentaciones(id));
        return "editar-alimentacion";
    }

    @PostMapping("/{id}")
    public String actualizarAlimentacion(@PathVariable Long id, @ModelAttribute("alimentacion") alimentacion alimentacion, Model model) {
        alimentacion alimentacionExistente = alimentacionService.getAlimentaciones(id);

        alimentacionExistente.setDescripcion(alimentacion.getDescripcion());
        alimentacionExistente.setDosis(alimentacion.getDosis());
        alimentacionService.saveOrUpdate(alimentacionExistente);
        return "redirect:/alimentacion";
    }

    @GetMapping("/eliminar/{id}") 
    public String eliminarAlimentacion(@PathVariable Long id) {
        alimentacionService.delete(id);
        return "redirect:/alimentacion"; 
    }
}

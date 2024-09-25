package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.porcino;
import com.example.demo.service.alimentacionService;
import com.example.demo.service.clienteService;
import com.example.demo.service.pdfService;
import com.example.demo.service.porcinoService;

@Controller
@RequestMapping(path={"/", "/porcinos"})
public class porcinoController {

    @Autowired
    porcinoService porcinoService;

    @Autowired
    clienteService clienteService;

    @Autowired
    alimentacionService alimentacionService;

    @Autowired
    private pdfService pdfService;

    @GetMapping
    public String listarPorcinos(Model model) {
        model.addAttribute("porcinos", porcinoService.getPorcinos());
        model.addAttribute("clientes", clienteService.getClientes()); 
        model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
        return "porcinos";
    }
    
    /*@GetMapping("/")
    public String mostrarPaginaInicio() {
        return "index";
    }*/

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoPorcino(Model model) {
        model.addAttribute("porcino", new porcino());
        model.addAttribute("clientes", clienteService.getClientes()); 
        model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
        return "porcino-form";
    }

    @PostMapping
    public String guardarPorcino(porcino porcino) {
        porcinoService.saveOrUpdate(porcino);
        return "redirect:/porcinos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPorcino(@PathVariable Long id, Model model) {
        model.addAttribute("porcino", porcinoService.getPorcinos(id));
        model.addAttribute("clientes", clienteService.getClientes()); 
        model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
        return "editar-porcino";
    }

    @PostMapping("/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("porcino") porcino porcino, Model model) {
        porcino porcinoExistente = porcinoService.getPorcinos(id);

        porcinoExistente.setRaza(porcino.getRaza());
        porcinoExistente.setEdad(porcino.getEdad());
        porcinoExistente.setPeso(porcino.getPeso());
        porcinoExistente.setCliente(porcino.getCliente());
        porcinoExistente.setAlimentacion(porcino.getAlimentacion());
        porcinoService.saveOrUpdate(porcinoExistente);

        return "redirect:/porcinos";
    }

    @GetMapping("/eliminar/{id}") 
    public String eliminarPorcino(@PathVariable Long id) {
        porcinoService.delete(id);
        return "redirect:/porcinos"; 
    }

    @GetMapping("pdf")
    public ResponseEntity<InputStreamResource> generarPdfClientes() {
        List<porcino> porcinos = porcinoService.getPorcinos();
        ByteArrayInputStream bis = pdfService.generatePorcinosPdf(porcinos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=porcinos.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}

package com.example.demo.controller;

import com.example.demo.entity.alimentacion;
import com.example.demo.entity.cliente;
import com.example.demo.graphql.InputPorcino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.porcino;
import com.example.demo.service.alimentacionService;
import com.example.demo.service.clienteService;
import com.example.demo.service.pdfService;
import com.example.demo.service.porcinoService;

import java.util.List;

@Controller
@RequestMapping(path = {"/", "/porcinos"})
public class porcinoController {

    @Autowired
    porcinoService porcinoService;

    @Autowired
    clienteService clienteService;

    @Autowired
    alimentacionService alimentacionService;

    @Autowired
    private pdfService pdfService;

//    @GetMapping
//    public String listarPorcinos(Model model) {
//        model.addAttribute("porcinos", porcinoService.getPorcinos());
//        model.addAttribute("clientes", clienteService.getClientes());
//        model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
//        return "porcinos";
//    }

    // /*@GetMapping("/")
    // public String mostrarPaginaInicio() {
    //     return "index";
    // }*/
    // @GetMapping("/nuevo")
    // public String mostrarFormularioNuevoPorcino(Model model) {
    //     model.addAttribute("porcino", new porcino());
    //     model.addAttribute("clientes", clienteService.getClientes());
    //     model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
    //     return "porcino-form";
    // }

    // @PostMapping
    // public String guardarPorcino(porcino porcino) {
    //     porcinoService.saveOrUpdate(porcino);
    //     return "redirect:/porcinos";
    // }

    // @GetMapping("/editar/{id}")
    // public String mostrarFormularioEditarPorcino(@PathVariable Long id, Model model) {
    //     model.addAttribute("porcino", porcinoService.getPorcinos(id));
    //     model.addAttribute("clientes", clienteService.getClientes());
    //     model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
    //     return "editar-porcino";
    // }

    // @PostMapping("/{id}")
    // public String actualizarCliente(@PathVariable Long id, @ModelAttribute("porcino") porcino porcino, Model model) {
    //     porcino porcinoExistente = porcinoService.getPorcinos(id);

    //     porcinoExistente.setRaza(porcino.getRaza());
    //     porcinoExistente.setEdad(porcino.getEdad());
    //     porcinoExistente.setPeso(porcino.getPeso());
    //     porcinoExistente.setCliente(porcino.getCliente());
    //     porcinoExistente.setAlimentacion(porcino.getAlimentacion());
    //     porcinoService.saveOrUpdate(porcinoExistente);

    //     return "redirect:/porcinos";
    // }

    // @GetMapping("/eliminar/{id}")
    // public String eliminarPorcino(@PathVariable Long id) {
    //     porcinoService.delete(id);
    //     return "redirect:/porcinos";
    // }

    // @GetMapping("pdf")
    // public ResponseEntity<InputStreamResource> generarPdfClientes() {
    //     List<porcino> porcinos = porcinoService.getPorcinos();
    //     ByteArrayInputStream bis = pdfService.generatePorcinosPdf(porcinos);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("Content-Disposition", "inline; filename=porcinos.pdf");

    //     return ResponseEntity
    //             .ok()
    //             .headers(headers)
    //             .contentType(MediaType.APPLICATION_PDF)
    //             .body(new InputStreamResource(bis));
    // }

    //Graphql

    //Find All Porcinos
    @QueryMapping(name = "findAllPorcino")
    public List<porcino> findAllPorcinos() {

        return porcinoService.getPorcinos();
    }

    //Find Porcinos By Id
    @QueryMapping(name = "findPorcinoByClient")
    public porcino findPorcinoByClient(@Argument(name = "idClient") String id) {
        return porcinoService.getPorcinos(Long.parseLong(id));
    }

    //Crear un porcino
    @MutationMapping(name = "createPorcino")
    public String createPorcino(@Argument(name = "inputPorcino") InputPorcino porcino) {

        porcino porcinoLocal = new porcino();
        cliente clienteLocal = clienteService.getClientes(Long.parseLong(porcino.getCliente_id()));
        alimentacion alimentacionLocal = alimentacionService.getAlimentacionesById(Long.parseLong(porcino.getAlimentacion_id()));


        porcinoLocal.setEdad(porcino.getEdad());
        porcinoLocal.setRaza(porcino.getRaza());
        porcinoLocal.setCliente(clienteLocal);
        porcinoLocal.setPeso(porcino.getPeso());
        porcinoLocal.setAlimentacion(alimentacionLocal);

        porcinoService.saveOrUpdate(porcinoLocal);

        return "Porcino creado exitosamente";


    }

    //Eliminar un porcino
    @MutationMapping(name = "deletePorcino")
    public String deletePorcino(@Argument(name = "idPorcino") String id ){

        porcinoService.delete(Long.parseLong(id));

        return "Porcino eliminado exitosamente";
    }
}

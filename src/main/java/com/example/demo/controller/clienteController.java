package com.example.demo.controller;

import java.util.List;
import java.io.ByteArrayInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import com.example.demo.entity.cliente;
import com.example.demo.service.clienteService;
import com.example.demo.service.pdfService;


@Controller
@RequestMapping(path = "/clientes")
public class clienteController {

    @Autowired
    private clienteService clienteService;

    @Autowired
    private pdfService pdfService;

//    @GetMapping
//    public String listarClientes(Model model) {
//        model.addAttribute("clientes", clienteService.getClientes());
//        return "clientes";
//    }
//
//    /*@GetMapping("/")
//    public String mostrarPaginaInicio() {
//        return "index";
//    }*/
//
//    @GetMapping("/nuevo")
//    public String mostrarFormularioNuevoCliente(Model model) {
//        model.addAttribute("cliente", new cliente());
//        return "cliente-form";
//    }
//
//    @PostMapping
//    public String guardarPersona(cliente persona) {
//        clienteService.saveOrUpdate(persona);
//        return "redirect:/clientes";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String mostrarFormularioEditarPersona(@PathVariable Long id, Model model) {
//        model.addAttribute("cliente", clienteService.getClientes(id));
//        return "editar-cliente";
//    }
//
//    @PostMapping("/{id}")
//    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("cliente") cliente cliente, Model model) {
//        cliente clienteExistente = clienteService.getClientes(id);
//
//        clienteExistente.setNombres(cliente.getNombres());
//        clienteExistente.setApellidos(cliente.getApellidos());
//        clienteExistente.setDireccion(cliente.getDireccion());
//        clienteExistente.setTelefono(cliente.getTelefono());
//        clienteService.saveOrUpdate(clienteExistente);
//        return "redirect:/clientes";
//    }
//
//    @GetMapping("/eliminar/{id}")
//    public String eliminarPersona(@PathVariable Long id) {
//        clienteService.delete(id);
//        return "redirect:/clientes";
//    }
//
//    @GetMapping("/ver/{id}")
//    public String verCliente(@PathVariable Long id, Model model) {
//        model.addAttribute("cliente", clienteService.getClientes(id));
//        return "ver-cliente";
//    }
//
//    @GetMapping("pdf")
//    public ResponseEntity<InputStreamResource> generarPdfClientes() {
//        List<cliente> clientes = clienteService.getClientes();
//        ByteArrayInputStream bis = pdfService.generateClientesPdf(clientes);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=clientes.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//    }

    //Graphql

    //Find All Clientes
    @QueryMapping(name = "findAllClientes")
    public List<cliente> findAllClientes(){ return clienteService.getClientes();}

    //Find Cliente x id
    @QueryMapping
    public cliente findClienteById( @Argument String id ){
        Long idClient = Long.parseLong(id);

        return clienteService.getClientes(idClient);
    }

    //Crear un nuevo cliente
    @MutationMapping(name = "createCliente")
    public String createCliente(@Argument(name = "inputCliente") cliente cliente){

        cliente clienteLocal  =  new cliente();

        clienteLocal.setNombres(cliente.getNombres());
        clienteLocal.setApellidos(cliente.getApellidos());
        clienteLocal.setDireccion( cliente.getDireccion());
        clienteLocal.setTelefono(cliente.getTelefono());

        clienteService.saveOrUpdate(clienteLocal);

        return "El cliente ha sido correctamente";
    }

    //Eliminar un cliente
    @MutationMapping(name = "deleteCliente")
    public String deleteCliente(@Argument(name = "clienteId") String id){
        clienteService.delete(Long.parseLong(id));
        return "El cliente ha sido eliminado";
    }

}

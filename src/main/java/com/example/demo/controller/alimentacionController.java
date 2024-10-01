package com.example.demo.controller;

import com.example.demo.graphql.InputAlimentacion;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.alimentacion;
import com.example.demo.service.alimentacionService;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

import java.util.List;

@Controller
//@RequestMapping(path="/alimentacion")
public class alimentacionController {

    @Autowired
    private alimentacionService alimentacionService;
    @Autowired
    private RestTemplate restTemplate;




//    @GetMapping
//    public String listarAlimentaciones(Model model) {
//        model.addAttribute("alimentaciones", alimentacionService.getAlimentaciones());
//        return "alimentaciones";
//    }
    
    /*@GetMapping("/")
    public String mostrarPaginaInicio() {
        return "index";
    }*/

    // @GetMapping("/nuevo")
    // public String mostrarFormularioNuevaAlimentacion(Model model) {
    //     model.addAttribute("alimentacion", new alimentacion());
    //     return "alimentacion-form";
    // }

    // @PostMapping
    // public String guardarAlimentacion(alimentacion alimentacion) {
    //     alimentacionService.saveOrUpdate(alimentacion);
    //     return "redirect:/alimentacion";
    // }

//     @GetMapping("/editar/{id}")
//     public String mostrarFormularioEditarAlimentacion(@PathVariable Long id, Model model) {
//         model.addAttribute("alimentacion", alimentacionService.getAlimentacionesById(id));
//         return "editar-alimentacion";
//     }

    // @PostMapping("/{id}")
    // public String actualizarAlimentacion(@PathVariable Long id, @ModelAttribute("alimentacion") alimentacion alimentacion, Model model) {
    //     alimentacion alimentacionExistente = alimentacionService.getAlimentaciones(id);

    //     alimentacionExistente.setDescripcion(alimentacion.getDescripcion());
    //     alimentacionExistente.setDosis(alimentacion.getDosis());
    //     alimentacionService.saveOrUpdate(alimentacionExistente);
    //     return "redirect:/alimentacion";
    // }

    // @GetMapping("/eliminar/{id}") 
    // public String eliminarAlimentacion(@PathVariable Long id) {
    //     alimentacionService.delete(id);
    //     return "redirect:/alimentacion"; 
    // }


    // Graphql


    //Find Alimentacion x id
    @QueryMapping(name = "mostrarAlimentacionesPorId")
    public alimentacion findAlimentacionById( @Argument(name = "id") String id){
        Long alimentacionId = Long.parseLong(id);
       return alimentacionService.getAlimentacionesById(alimentacionId);
    }

    //Find Alimentacion
    @QueryMapping(name = "findAllAlimentacion")
    public List<alimentacion> findAllAlimentacion (){
         return alimentacionService.getAlimentaciones();
    }

    //creacion de una nueva Alimentacion
    @MutationMapping(name = "createAlimentacion")
    public String createAlimentacion(@Argument InputAlimentacion inputAlimentacion){

        alimentacion alimentacion = new alimentacion();

        alimentacion.setId(inputAlimentacion.getId());
        alimentacion.setDescripcion(inputAlimentacion.getDescripcion());
        alimentacion.setDosis(inputAlimentacion.getDosis());

        alimentacionService.saveOrUpdate(alimentacion);

        return "La alimentacion ha sido creada correctamente ";

    }

    //Eliminacion de una alimentacion
    @MutationMapping(name = "deleteAlimentacionById")
    public String deleteAlimentacionById( @Argument(name = "alimentacionId") String id){
        Long alimentacionId = Long.parseLong(id);
        alimentacionService.delete(alimentacionId);

        return "Se eliminó la aliemetnacion correctamente";

    }


    //Graphql con Interfaz Grafica


    //Find Alimentacion, pero la muestra graficamente
    @GetMapping("/alimentaciones")
    public String getAlimentaciones(Model model) {

        // Definir la consulta GraphQL
        String query = "{\"query\":\"{ findAllAlimentacion { id descripcion dosis } }\"}";

        // Definir las cabeceras HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Crear la entidad HTTP
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        // Hacer la solicitud POST al endpoint GraphQL
        ResponseEntity<Map> response = restTemplate.exchange("http://localhost:8080/graphql",
                HttpMethod.POST, entity, Map.class);

        // Obtener los datos de la respuesta
        Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
        List<Map<String, Object>> alimentaciones = (List<Map<String, Object>>) data.get("findAllAlimentacion");

        // Pasar los datos al modelo
        model.addAttribute("alimentaciones", alimentaciones);

        return "alimentaciones"; // Nombre de la plantilla HTML que usaremos
    }
}


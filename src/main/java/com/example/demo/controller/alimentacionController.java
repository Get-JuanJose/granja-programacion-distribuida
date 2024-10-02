package com.example.demo.controller;

import com.example.demo.graphql.InputAlimentacion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.alimentacion;
import com.example.demo.service.alimentacionService;


import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Map;

import java.util.List;

@Controller
@RequestMapping(path="/alimentacion")
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
    @GetMapping
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

    //Nueva Alimentaciom, esta no inclute GraphQl... solo es un endpoint
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaAlimentacion(Model model) {
        model.addAttribute("alimentacion", new alimentacion());
        return "alimentacion-form";
    }

     @PostMapping
     public String guardarAlimentacion(alimentacion alimentacion) {
         return "redirect:/alimentacion";
     }

     @GetMapping("/eliminar")
     public String eliminarAlimentacion() {
         return "redirect:/alimentacion";
     }

//         @GetMapping("/editar/{id}")
//     public String mostrarFormularioEditarAlimentacion(@PathVariable int id ,  Model model) {
//             // Construir la consulta GraphQL
//             String query = obtenerConsultaPorId(id);
//
//             // Crear el objeto JSON para el cuerpo de la solicitud
//             String requestJson = String.format("{\"query\":\"%s\"}", query.replace("\n", "\\n").replace("\"", "\\\""));
//
//             // Configurar los encabezados
//             HttpHeaders headers = new HttpHeaders();
//             headers.setContentType(MediaType.APPLICATION_JSON);
//
//             // Crear la entidad de la solicitud
//             HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
//
//             // URL de tu endpoint GraphQL
//             String url = "http://localhost:8080/graphql"; // Reemplaza con tu URL real
//
////// Enviar la solicitud
////             ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
////
////             // Asegúrate de que la respuesta sea exitosa
////             if (response.getStatusCode() == HttpStatus.OK) {
////                 // Convertir la respuesta JSON a un Map
////                 ObjectMapper objectMapper = new ObjectMapper();
////                 Map<String, Object> responseBody;
////                 try {
////                     responseBody = objectMapper.readValue(response.getBody(), Map.class);
////                 } catch (Exception e) {
////                     e.printStackTrace();
////                     // Manejar error de deserialización
////                     return "error"; // Devuelve a una página de error si es necesario
////                 }
////
////                 // Obtener los datos de la respuesta
////                 Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
////
////                 // Cambia esto para asegurarte de que el tipo sea correcto
////                 Object alimentacionesObj = data.get("mostrarAlimentacionesPorId");
////
////                 List<Map<String, Object>> alimentaciones;
////                 if (alimentacionesObj instanceof List) {
////                     alimentaciones = (List<Map<String, Object>>) alimentacionesObj;
////                 } else {
////                     alimentaciones = new ArrayList<>();
////                     alimentaciones.add((Map<String, Object>) alimentacionesObj); // Agrega el objeto si es único
////                 }
////
////                 // Agregar los datos al modelo
////
////                 model.addAttribute("alimentaciones", alimentaciones);
////             } else {
////                 // Manejar error de respuesta
////                 System.out.println("Error al obtener datos: " + response.getStatusCode());
////             }
////
////             System.out.println(response);
//
//             // Enviar la solicitud
//             ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
//
//             // Asegúrate de que la respuesta sea exitosa
//             if (response.getStatusCode() == HttpStatus.OK) {
//                 // Convertir la respuesta JSON a un Map
//                 ObjectMapper objectMapper = new ObjectMapper();
//                 Map<String, Object> responseBody;
//                 try {
//                     responseBody = objectMapper.readValue(response.getBody(), Map.class);
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                     // Manejar error de deserialización
//                     System.out.println("Error al obtener el response");
//                 }
//
//                 // Obtener los datos de la respuesta
//                 Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
//
//                 // Cambia esto para asegurarte de que el tipo sea correcto
//                 Object alimentacionesObj = data.get("mostrarAlimentacionesPorId");
//
//                 // Creamos la instancia de Alimentacion
//                 alimentacion alimentacion;
//                 if (alimentacionesObj instanceof List) {
//                     List<Map<String, Object>> alimentaciones = (List<Map<String, Object>>) alimentacionesObj;
//                     if (!alimentaciones.isEmpty()) {
//                         Map<String, Object> alimentacionData = alimentaciones.get(0); // Obtener el primer objeto
//                         alimentacion = new alimentacion();
//                         alimentacion.setId((Long) alimentacionData.get("id"));
//                         alimentacion.setDescripcion((String) alimentacionData.get("descripcion"));
//                         alimentacion.setDosis((int)alimentacionData.get("dosis"));
//                     } else {
//                         // Manejar el caso donde no hay alimentaciones
//                         System.out.println("Error al obtener el response 2");
//                     }
//                 } else {
//                     // Manejar caso donde no es una lista
//                     System.out.println("Error al obtener el response 3");
//                 }
//
//                 // Mostrar la representación del objeto
//                 System.out.println(alimentacion); // Esto mostrará "alimentacion(id=1, descripcion=Está buena...pues no se ha muerto ninguno, dosis=1)"
//             } else {
//                 // Manejar error de respuesta
//                 System.out.println("Error al obtener datos: " + response.getStatusCode());
//             }
//             return "editar-alimentacion";
//     }

     @PostMapping("/{id}")
     public String actualizarAlimentacion(@PathVariable Long id, @ModelAttribute("alimentacion") alimentacion alimentacion, Model model) {
         alimentacion alimentacionExistente = alimentacionService.getAlimentacionesById(id);

         alimentacionExistente.setDescripcion(alimentacion.getDescripcion());
         alimentacionExistente.setDosis(alimentacion.getDosis());
         alimentacionService.saveOrUpdate(alimentacionExistente);
         return "redirect:/alimentacion";
     }

//    private String obtenerConsultaPorId(int id) {
//
//
//
//        return String.format("query {\n" +
//                "  mostrarAlimentacionesPorId(id: \"%s\") {\n" +
//                "    id\n" +
//                "    descripcion\n" +
//                "    dosis\n" +
//                "  }\n" +
//                "}", id);
//    }

//    @GetMapping("/editar/{id}")
//    public String mostrarFormularioEditarAlimentacion(@PathVariable Long id, Model model) {
//        model.addAttribute("alimentacion", alimentacionService.getAlimentacionesById(id));
//        System.out.println(alimentacionService.getAlimentacionesById(id));
//        return "editar-alimentacion";
//    }
}


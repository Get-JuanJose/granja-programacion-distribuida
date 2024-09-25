package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.cliente;
import com.example.demo.entity.alimentacion;
import com.example.demo.repository.alimentacionRepository;
import com.example.demo.repository.clienteRepository;

@SpringBootApplication
public class GranjaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GranjaApplication.class, args);
	}

	@Autowired
	clienteRepository clienteRepository;
	alimentacionRepository alimentacionRepository;

	@Override
	public void run(String... args) throws Exception {
		/*cliente cliente = new cliente("Juan Jose", "Gonzalez", "Calle 10", "1234567");
		clienteRepository.save(cliente);

		alimentacion alimentacion = new alimentacion("Blanda", 2);
		alimentacionRepository.save(alimentacion);*/
	}

}

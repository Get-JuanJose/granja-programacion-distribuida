package com.example.demo.service;

import com.example.demo.entity.cliente;
import com.example.demo.entity.porcino;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class pdfService {
    public ByteArrayInputStream generateClientesPdf(List<cliente> clientes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Título del documento
            document.add(new Paragraph("Informe de Clientes").setBold().setFontSize(18));
            
            // Crear tabla con columnas
            float[] columnWidths = {1, 3, 3, 3, 3};
            Table table = new Table(columnWidths);

            // Encabezados de tabla
            table.addHeaderCell("ID");
            table.addHeaderCell("Nombres");
            table.addHeaderCell("Apellidos");
            table.addHeaderCell("Dirección");
            table.addHeaderCell("Teléfono");

            // Llenar la tabla con los datos de los clientes
            for (cliente c : clientes) {
                //table.addCell(String.valueOf(c.getId()));
                table.addCell(String.valueOf(c.getCedula()));
                table.addCell(c.getNombres());
                table.addCell(c.getApellidos());
                table.addCell(c.getDireccion());
                table.addCell(c.getTelefono());
            }

            // Agregar tabla al documento
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generatePorcinosPdf(List<porcino> porcinos) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Título del documento
            document.add(new Paragraph("Informe de Clientes").setBold().setFontSize(18));
            
            // Crear tabla con columnas 
            float[] columnWidths = {1, 2, 1, 1, 4, 4};
            Table table = new Table(columnWidths);

            // Encabezados de tabla
            table.addHeaderCell("ID");
            table.addHeaderCell("Raza");
            table.addHeaderCell("Edad");
            table.addHeaderCell("Peso");
            table.addHeaderCell("Cliente");
            table.addHeaderCell("Alimentacion");

            // Llenar la tabla con los datos de los clientes
            for (porcino c : porcinos) {
                //table.addCell(String.valueOf(c.getId()));
                table.addCell(String.valueOf(c.getId()));
                table.addCell(c.getRaza());
                table.addCell(String.valueOf(c.getEdad()));
                table.addCell(String.valueOf(c.getPeso()));
                table.addCell(c.getCliente().toString());
                table.addCell(c.getAlimentacion().toString());
            }

            // Agregar tabla al documento
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

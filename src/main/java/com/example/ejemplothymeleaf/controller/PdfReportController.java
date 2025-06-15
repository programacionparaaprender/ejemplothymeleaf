package com.example.ejemplothymeleaf.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejemplothymeleaf.model.InvoiceItem;
import com.example.ejemplothymeleaf.service.impl.PdfGenerationService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class PdfReportController {

    private final PdfGenerationService pdfService;

    public PdfReportController(PdfGenerationService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/invoice")
    public ResponseEntity<byte[]> generateInvoiceReport() {
        // Datos para el reporte
        Map<String, Object> data = new HashMap<>();
        data.put("invoiceNumber", "INV-2023-001");
        data.put("customerName", "John Doe");
        data.put("items", List.of(
            new InvoiceItem("Product 1", 2, 29.99),
            new InvoiceItem("Product 2", 1, 59.99)
        ));
        data.put("total", 119.97);

        // Generar PDF
        byte[] pdfBytes = pdfService.generatePdfFromTemplate("invoice-template", data);

        // Configurar respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "invoice.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}

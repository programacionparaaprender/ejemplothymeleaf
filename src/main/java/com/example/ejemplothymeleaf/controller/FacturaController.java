package com.example.ejemplothymeleaf.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ejemplothymeleaf.model.InvoiceItem;
import com.example.ejemplothymeleaf.service.impl.PdfGenerationService;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@Controller
@RequestMapping("/facturas")
public class FacturaController {

    private final PdfGenerationService pdfService;

    public FacturaController(PdfGenerationService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/ver")
    public String verFactura(Model model) {
        // Datos de ejemplo para la factura
        Map<String, Object> data = new HashMap<>();
        data.put("numeroFactura", "FAC-2023-001");
        data.put("cliente", "Juan Pérez");
        data.put("fecha", "15/06/2023");
        data.put("items", List.of(
            new InvoiceItem("Producto A", 2, 25.50),
            new InvoiceItem("Producto B", 1, 75.00)
        ));
        data.put("total", 126.00);

        // Generar PDF y convertirlo a Base64 para visualización
        byte[] pdfBytes = pdfService.generatePdfFromTemplate("factura-template", data);
        String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);
        
        model.addAttribute("pdfBase64", pdfBase64);
        model.addAttribute("nombreArchivo", "factura-2023-001.pdf");
        model.addAttribute("datosFactura", data);
        
        return "visualizador-factura";
    }

    @GetMapping("/descargar")
    public ResponseEntity<ByteArrayResource> descargarFactura() {
        // Reutilizamos la misma lógica de generación
        Map<String, Object> data = new HashMap<>();
        data.put("numeroFactura", "FAC-2023-001");
        // ... (mismos datos que en /ver)
        
        byte[] pdfBytes = pdfService.generatePdfFromTemplate("factura-template", data);
        
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=factura-2023-001.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }
}
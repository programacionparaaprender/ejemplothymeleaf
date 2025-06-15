package com.example.ejemplothymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Map;

@Service
public class PdfGenerationService {

    private final TemplateEngine templateEngine;

    public PdfGenerationService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdfFromTemplate(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        
        // Procesar plantilla Thymeleaf a HTML
        String htmlContent = templateEngine.process(templateName, context);
        
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF", e);
        }
    }
    
    public String generatePdfBase64(String templateName, Map<String, Object> data) {
        byte[] pdfBytes = generatePdfFromTemplate(templateName, data);
        return Base64.getEncoder().encodeToString(pdfBytes);
    }
}

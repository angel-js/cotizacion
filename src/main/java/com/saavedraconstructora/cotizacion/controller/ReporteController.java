package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import com.saavedraconstructora.cotizacion.service.CotizacionService;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/reporte")
public class ReporteController {
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping("/cotizacion/{id}")
    public ResponseEntity<byte[]> generarReporte(@PathVariable Integer id) throws IOException, JRException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/static/reportes/reporteCotizacion2.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Map<String, Object> params = new HashMap<>();
            // llamar al id
            Optional<Cotizacion> cotOptional = cotizacionService.findById(id);
            if (cotOptional.isPresent()) {
                Cotizacion cot = cotOptional.get();
                params.put("cotizacionID", cot.getId());
                params.put("cotizacionDepartamento", cot.getDepartamento());
                params.put("cotizacionFecha", cot.getFecha_cotizacion());
                params.put("cotizacionDescripcion", cot.getDescripcion());
                params.put("cotizacionMonto", cot.getMonto());
                params.put("cotizacionMotivo", cot.getMotivo());
                log.info("Imprimiendo los parametros" + params);
            } else {
                log.info("The obcject is EMPTY!");
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("reporte", "reporte.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

            return response;
        } catch (Exception e) {
            System.out.println("ERROR -----------------------------------");
            System.out.println(e.getMessage());
            return null;
        }
    }
}

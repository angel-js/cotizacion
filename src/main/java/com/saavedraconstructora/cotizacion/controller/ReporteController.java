package com.saavedraconstructora.cotizacion.controller;

import com.saavedraconstructora.cotizacion.model.Cotizacion;
import com.saavedraconstructora.cotizacion.model.Item;
import com.saavedraconstructora.cotizacion.model.Supervisor;
import com.saavedraconstructora.cotizacion.model.Trabajo;
import com.saavedraconstructora.cotizacion.service.CotizacionService;
import com.saavedraconstructora.cotizacion.service.SupervisorService;
import com.saavedraconstructora.cotizacion.service.TrabajoService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin/reporte")
public class ReporteController {
    private static final Logger log = LoggerFactory.getLogger(CotizacionController.class);
    @Autowired
    private CotizacionService cotizacionService;
    @Autowired
    private SupervisorService supervisorService;
    @Autowired
    private TrabajoService trabajoService;

    @GetMapping("/cotizacion/{id}")
    public ResponseEntity<byte[]> generarReporte(@PathVariable Integer id) throws IOException, JRException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/static/reportes/cotizacionReporte.jrxml");
            InputStream logoEmpresa = this.getClass().getResourceAsStream("/static/reportes/img/ConstructoraSaavedra.png");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Map<String, Object> params = new HashMap<>();
            // llamar al id
            Optional<Cotizacion> cotOptional = cotizacionService.findById(id);
            if (cotOptional.isPresent()) {
                Cotizacion cot = cotOptional.get();
                params.put("logoEmpresa", logoEmpresa);
                params.put("cotizacionID", cot.getId());
                params.put("cotizacionDepartamento", cot.getDepartamento().getNombre());
                params.put("cotizacionFecha", cot.getFecha_cotizacion().toString());
                params.put("cotizacionDescripcion", cot.getDescripcion());
                params.put("cotizacionMonto", cot.getMonto());
                List<Supervisor> supervisores = supervisorService.findSupervisorsByLocalId(cot.getDepartamento().getId());
                //params.put("cotizacionMotivo", cot.getMotivo());
                Supervisor supervisor = supervisores.get(0);
                System.out.println("supervisor: " + supervisor.toString());
                String fullname = supervisor.getNombre().toString() + " " + supervisor.getApellido().toString();
                params.put("nombreSupervisor", fullname);
                params.put("correoSupervisor", supervisor.getCorreo());
                List<Cotizacion> cotizaciones = new ArrayList<>();
                List<Cotizacion> cotizaciones2 = new ArrayList<>();
                cotizaciones.add(new Cotizacion());
                cotizaciones2.add(cot);
                cotizaciones.addAll(cotizaciones2);
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(cotizaciones.toArray());
                params.put("ds", ds);
                log.info("Imprimiendo los parametros" + params);
            } else {
                log.info("The object is EMPTY!");
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String filename = "Cotizacion_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                    .format(new Date()) + ".pdf";
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

            return response;
        } catch (Exception e) {
            System.out.println("ERROR -----------------------------------");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/trabajo/usr/{id}")
    public ResponseEntity<byte[]> generarReporteTrabajoUsuario(@PathVariable Integer id) throws IOException, JRException {
        log.info("Obtener Informe de Trabajo - USUARIO");
        System.out.println("--------------------------------------------------------");
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/static/reportes/trabajoUsuarioReporte.jrxml");
            InputStream logoEmpresa = this.getClass().getResourceAsStream("/static/reportes/img/ConstructoraSaavedra.png");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Map<String, Object> params = new HashMap<>();
            JRBeanCollectionDataSource dataSource;

            // llamar al id
            Trabajo trbj = trabajoService.findById(id);
            List<Item> itemsTrabajo = trabajoService.findByTrabajoId(trbj.getId());
            if (trbj != null) {
                params.put("trabajoID", trbj.getId());
                params.put("logoEmpresa", logoEmpresa);
                List<Supervisor> supervisores = supervisorService.findSupervisorsByLocalId(trbj.getDepartamento().getId());
                Supervisor supervisor = supervisores.get(0);
                System.out.println("supervisor: " + supervisor.toString());
                String fullname = supervisor.getNombre().toString() + " " + supervisor.getApellido().toString();
                params.put("nombreSupervisor", fullname);
                params.put("correoSupervisor", supervisor.getCorreo());
                params.put("cotizacionDepartamento", trbj.getDepartamento().getNombre());
                params.put("cotizacionFecha", trbj.getFecha_trabajo().toString());
                String fullnameUsr = trbj.getUsuario().getName().toString() + " " + trbj.getUsuario().getLastname().toString();
                params.put("trabajador", fullnameUsr);
                params.put("itemsTrabajo", itemsTrabajo);
                log.info("ITEMS --------- Trabajo: " + itemsTrabajo.toString());
                dataSource = new JRBeanCollectionDataSource(itemsTrabajo);
                System.out.println("DATASOURCE: ------------------> " + dataSource.toString());
                log.info("DATASOURCE: ------------------> " + dataSource.toString());

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                String filename = "Trabajo_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                        .format(new Date()) + ".pdf";
                headers.setContentDispositionFormData("attachment", filename);
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
                return response;

            } else {
                log.info("The object is EMPTY!");
            }
        } catch (Exception e) {
            System.out.println("ERROR -----------------------------------");
            System.out.println(e.getMessage());
            return null;
        }
        ResponseEntity<byte[]> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return response;
    }
}

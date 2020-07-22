package jaspergenerator.demo.generator;

import com.lowagie.text.pdf.PdfWriter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import java.io.InputStream;
import java.util.Map;

public class Writer {

    public static void write(InputStream is, Map<String, Object> parameters, String location) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(is);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(location));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");
        exportConfig.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }

}

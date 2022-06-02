package com.example.SOPSbackend.documentUtils;

import com.example.SOPSbackend.dto.AdminVaccinationReportDto;
import com.itextpdf.text.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.util.Optional;

public class DocUtils {
    
    public static void addMetadata(Document doc, String title, String author){
        doc.addTitle(title);
        doc.addAuthor(author);
    }
    
    public static void addParagraph(Document doc, String title, String[] lines, Font font) throws DocumentException {
        if(title != null){
            Paragraph p = new Paragraph(title, font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
        }
        for (String line: lines) {
            doc.add(new Paragraph(line, font));
        }
    }

    public static ByteArrayOutputStream convertToBaosPDF(AdminVaccinationReportDto data) {
        var doc = new Document();
        var baos = new ByteArrayOutputStream();
        var font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        PdfWriter.getInstance(certificate, baos);
        doc.open();
        DocUtils.addMetadata(doc, "Vaccination Raport", "Admin");
        var diseases = data.getDiseases();
        for (var disease: diseases) {
            doc.add(new Paragraph(disease.getName() + ": " + disease.getCount(), font));
            for (var vaccine: disease.getVaccines()){
                var p = new Paragraph();
                p.setIndentLeft(40);
                p.add(vaccine.getName() + ": " + vaccine.getCount(), font);
            }
        }
        doc.close();
        return baos;
    }

}

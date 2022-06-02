package com.example.SOPSbackend.documentUtils;

import com.itextpdf.text.*;

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
}

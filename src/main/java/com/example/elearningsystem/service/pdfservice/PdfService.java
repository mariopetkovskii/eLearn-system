package com.example.elearningsystem.service.pdfservice;

import com.example.elearningsystem.model.User;
import com.example.elearningsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@AllArgsConstructor
public class PdfService {

    private final UserRepository userRepository;

    public void export(HttpServletResponse response, User user) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        String fullName = user.getName() + " " + user.getSurname();

        String certificateFor = "Certificate for " + fullName;

        Paragraph paragraph = new Paragraph(certificateFor, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Integer points = user.getPoints();
        String pointsInCert = "You have passed your exam with " + points + " points";

        Paragraph paragraph2 = new Paragraph(pointsInCert, fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();
    }
}
package smallpawsproject.services.impl;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Certificate;
import smallpawsproject.repositories.CertificateRepository;
import smallpawsproject.services.CertificateService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    /**
     * This is the method that creates a new certificate which
     * consists of the document that has some parts that are set here.
     * @param certificate
     */
    @Override
    public void addCertificate(Certificate certificate)  {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        document.addPage(page);
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.COURIER, 14);
            contentStream.beginText();
            contentStream.showText(certificate.getAdoptionRequestFrom().toString());
            contentStream.endText();
            contentStream.close();
            document.save(out);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        certificate.setDocument(out.toByteArray());
        certificateRepository.save(certificate);


    }

    @Override
    public List<Certificate> getCertificates() {
        return certificateRepository.findAll();
    }


}

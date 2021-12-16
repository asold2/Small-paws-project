package smallpawsproject.controllers;

import org.springframework.web.bind.annotation.*;
import smallpawsproject.model.Certificate;
import smallpawsproject.services.CertificateService;
import java.util.List;

@RestController
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certificates/{id}" )
    @ResponseBody
    public List<Certificate> getCertificatesByUserId(@PathVariable int id){
        return certificateService.getCertificatesByUserId(id);
    }
}

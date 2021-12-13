package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Certificate;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.CertificateService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private final ClientFactory clientFactory;

    private ClientRMI client;
    private List<Certificate> certificates;

    public CertificateServiceImpl() {

        clientFactory = new ClientFactory();
        client = clientFactory.getClient();
        try {
            client.connect();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            certificates = client.getCertificates();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addCertificate(Certificate certificate) {
        try {
            client.addCertificate(certificate);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Certificate> getCertificatesByUserId(int id) {
        List<Certificate> temp = new ArrayList<>();
        for(Certificate certificate:certificates){
            if(certificate.getPetOwnerId().getUserId().equals(id)){
                temp.add(certificate);
            }
        }
        return temp;
    }


}

package org.example;

import javax.jws.WebService;
import io.micrometer.core.instrument.Timer;

public class HelloServiceImpl implements HelloService {

    private final Timer timer = Timer.builder("soap_requests_duration")
            .description("Duree des requetes SOAP") // ✅ Sans accents
            .register(MetricsServer.getRegistry());

    @Override
    public String saveClient(Client client) {

        Timer.Sample sample = Timer.start();

        try {
            System.out.println("Client recu :   ID =" +  client.getId() + ", Nom = "  + client.getNom()+ ", Prenom = " + client.getPrenom()+ ", Email= " + client.getEmail());
            return "OK recu : " + client.getId();
        } finally {
            sample.stop(timer);
        }
    }
}

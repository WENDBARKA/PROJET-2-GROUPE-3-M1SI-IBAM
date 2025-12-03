package org.example;

import javax.xml.ws.Endpoint;


public class Server {

    public static void main(String[] args) throws Exception {

        // Lancer Prometheus
        MetricsServer.start(9090);

        System.out.println("📊 Metrics OK : http://localhost:9090/metrics");

        // Lancer le service SOAP
        Endpoint.publish("http://localhost:8081/hello", new HelloServiceImpl());

        System.out.println("🚀 Serveur SOAP démarré sur http://localhost:8081/hello?wsdl");
    }
}

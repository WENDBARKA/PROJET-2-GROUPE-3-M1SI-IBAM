package org.example;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MetricsServer {

    private static PrometheusMeterRegistry registry;

    public static PrometheusMeterRegistry start(int port) throws IOException {
        registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/metrics", exchange -> {
            String response = registry.scrape();

            // ✅ Ajout de l'en-tête Content-Type conforme à Prometheus
            exchange.getResponseHeaders().set("Content-Type", "text/plain; version=0.0.4");

            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        server.start();

        System.out.println("📊 Serveur de métriques Prometheus démarré sur : http://localhost:9090/metrics");
        return registry;
    }

    public static PrometheusMeterRegistry getRegistry() {
        return registry;
    }
}

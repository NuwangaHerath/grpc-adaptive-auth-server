package org.adaptive.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.adaptive.service.AdaptiveService;

import java.io.File;
import java.io.IOException;

public class AdaptiveServer {

    public static void main(String[] args) throws IOException, InterruptedException {

//        File certChainFile = new File("/home/nuwanga/wso2/event-handler-server/src/main/java/org/example/server/cert1/server-cert.pem");
//        File privateKeyFile = new File("/home/nuwanga/wso2/event-handler-server/src/main/java/org/example/server/cert1/server-key.pem");
//
////         SSL/TLS Authenticated server.
//        Server server = ServerBuilder.forPort(8040)
//                .useTransportSecurity(certChainFile, privateKeyFile)
//                .addService(new AdaptiveService())
//                .build();
        Server server = ServerBuilder.forPort(8060)
                .addService(new AdaptiveService())
                .build();

        server.start();
        System.out.println("Adaptive Server started at " + server.getPort());
        server.awaitTermination();
        server.shutdown();


    }


}

package org.adaptive.service;

import io.grpc.Context;
import io.grpc.Status;
import org.json.simple.JSONObject;
import io.grpc.stub.StreamObserver;
import org.adaptive.grpc.Service;
import org.adaptive.grpc.grpcServiceGrpc;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class AdaptiveService extends grpcServiceGrpc.grpcServiceImplBase{

    @Override
    public void grpcInvoke(Service.JsonRequest request, StreamObserver<Service.JsonResponse> responseObserver) {

        // Check for the cancellation of the call due deadline exceeding.
//        if (Context.current().isCancelled()) {
//            System.out.println("Deadline Exceeded!");
//            responseObserver.onError(Status.CANCELLED.withDescription("Deadline Exceeded!").asRuntimeException());
//            return;
//        }

        System.out.println("sendJson method is called!!");
        Service.JsonResponse.Builder jsonResponse = Service.JsonResponse.newBuilder();
        JSONObject responseJson = new JSONObject();
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();

        try {
            // Convert received Json sting into a Json object
            jsonObject = (JSONObject) parser.parse(request.getJsonString());
            boolean isReg = (boolean) jsonObject.get("isRegistered");
            long age= (long) jsonObject.get("age");
            if (isReg){
                System.out.println("test age");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Iterate through the Json object
        for (Object key : jsonObject.keySet()){

            if (jsonObject.get(key) == "user"){
                return;
            } else {

                responseJson.put(key, jsonObject.get(key) + "(checked)");
            }

        }

        // Convert modified Json object into a Json string
        String responseString = responseJson.toJSONString();

        System.out.println("Iterated through Json Object!!");
        System.out.println("Result found from Json Object- "+ responseString);

        // Create the response
        jsonResponse.setJsonString(responseString);
        responseObserver.onNext(jsonResponse.build());
        responseObserver.onCompleted();
    }
}

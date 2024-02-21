package com.kadiraksoy.productservice.service.grpc;

import com.kadiraksoy.grpc.DiscountRequest;
import com.kadiraksoy.grpc.DiscountResponse;
import com.kadiraksoy.grpc.DiscountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;

public class DiscountGrpcServiceImpl implements DiscountGrpcService {

    // Bir kanal oluşturduktan sonra bir servise istek attıktan sonra gerçekleşene kadar Stub oluşturur.
    private DiscountServiceGrpc.DiscountServiceBlockingStub discountServiceBlockingStub;
    // Host'u ve Port'u
    private ManagedChannel channel;

    public DiscountGrpcServiceImpl(@Value("${discount.grpc.host}") String grpcHost, @Value("${discount.grpc.port}") int grpcPort) {
        System.out.println("--> Discount grpc: " + grpcHost + " " + grpcPort);
        channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }
    @Override
    public DiscountResponse getDiscount(DiscountRequest discountRequest) {
        discountServiceBlockingStub = DiscountServiceGrpc.newBlockingStub(channel);
        DiscountResponse discountResponse = discountServiceBlockingStub.getDiscount(discountRequest);
        return discountResponse;
    }
}

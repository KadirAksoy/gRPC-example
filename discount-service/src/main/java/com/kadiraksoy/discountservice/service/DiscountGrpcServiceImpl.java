package com.kadiraksoy.discountservice.service;


import com.beratyesbek.grpc.DiscountRequest;
import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.grpc.DiscountServiceGrpc;
import com.beratyesbek.grpc.Response;
import com.kadiraksoy.discountservice.model.Category;
import com.kadiraksoy.discountservice.model.Discount;
import com.kadiraksoy.discountservice.repository.CategoryRepository;
import com.kadiraksoy.discountservice.repository.DiscountRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class DiscountGrpcServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase {

    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {
        Category category = categoryRepository.findByExternalId(String.valueOf(request.getExternalCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category has not been found by external category ID"));

        Optional<Discount> discount = discountRepository.findByCodeAndCategoryId(request.getCode(), category.getId());
        if (discount.isPresent()) {
            BigDecimal newPrice = discount.get().getDiscountPrice().subtract(BigDecimal.valueOf(request.getPrice())).multiply(BigDecimal.valueOf(-1));
            responseObserver.onNext(
                    DiscountResponse.newBuilder()
                            .setCode(discount.get().getCode())
                            .setOldPrice(request.getPrice())
                            .setNewPrice(newPrice.floatValue())
                            .setResponse(
                                    Response.newBuilder().setStatusCode(true).setMessage("Discount has been applied successfully!").build()
                            ).build()
            );
        } else {
            responseObserver.onNext(DiscountResponse.newBuilder()
                    .setOldPrice(request.getPrice())
                    .setNewPrice(request.getPrice())
                    .setCode(request.getCode())
                    .setResponse(Response.newBuilder().setMessage("Code and category are invalid.").setStatusCode(false).build())
                    .build()
            );
        }

        responseObserver.onCompleted();
    }
}

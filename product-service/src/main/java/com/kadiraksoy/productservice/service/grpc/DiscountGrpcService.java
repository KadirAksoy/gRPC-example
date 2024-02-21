package com.kadiraksoy.productservice.service.grpc;

import com.kadiraksoy.grpc.DiscountRequest;
import com.kadiraksoy.grpc.DiscountResponse;

public interface DiscountGrpcService {

    DiscountResponse getDiscount(DiscountRequest discountRequest);
}

package ru.vladigeras.springgrpc.server.api.impl;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.vladigeras.springgrpc.api.*;
import ru.vladigeras.springgrpc.server.service.CityService;

@GrpcService
@RequiredArgsConstructor
public class CityApiServiceImpl extends CityApiServiceGrpc.CityApiServiceImplBase {

    private final CityService cityService;

    @Override
    public void create(CreateCityRq rq, StreamObserver<CreateCityRs> responseObserver) {
        CreateCityRs rs = cityService.save(rq);
        responseObserver.onNext(rs);
        responseObserver.onCompleted();
    }

    @Override
    public void find(FindCityRq rq, StreamObserver<FindCityRs> responseObserver) {
        FindCityRs rs = cityService.find(rq);
        responseObserver.onNext(rs);
        responseObserver.onCompleted();
    }
}

package ru.vladigeras.springgrpc.server.api.impl;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.vladigeras.springgrpc.api.CountryApiServiceGrpc;
import ru.vladigeras.springgrpc.api.CreateCountryRq;
import ru.vladigeras.springgrpc.api.CreateCountryRs;
import ru.vladigeras.springgrpc.server.service.CountryService;

@GrpcService
@RequiredArgsConstructor
public class CountryApiServiceImpl extends CountryApiServiceGrpc.CountryApiServiceImplBase {

    private final CountryService countryService;

    @Override
    public void create(CreateCountryRq rq, StreamObserver<CreateCountryRs> responseObserver) {
        CreateCountryRs rs = countryService.save(rq);
        responseObserver.onNext(rs);
        responseObserver.onCompleted();
    }
}

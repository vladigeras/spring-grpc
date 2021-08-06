package ru.vladigeras.springgrpc.client.configuration;

import io.grpc.*;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@GrpcGlobalClientInterceptor
public class LogInterceptor implements ClientInterceptor {

    @Override
    public <Rq, Rs> ClientCall<Rq, Rs> interceptCall(MethodDescriptor<Rq, Rs> methodDescriptor, CallOptions callOptions, Channel next) {
        return new SimpleForwardingClientCall<>(next.newCall(methodDescriptor, callOptions)) {
            @Override
            public void sendMessage(Rq rq) {
                log.info("Method \"{}\" was invoked with RQ {}", methodDescriptor.getFullMethodName(), rq);
                super.sendMessage(rq);
            }

            @Override
            public void start(Listener<Rs> responseListener, Metadata headers) {
                super.start(new SimpleForwardingClientCallListener<>(responseListener) {
                    @Override
                    public void onMessage(Rs rs) {
                        log.info("Method \"{}\" was answered with RS {}", methodDescriptor.getFullMethodName(), rs);
                        super.onMessage(rs);
                    }
                }, headers);
            }
        };
    }
}

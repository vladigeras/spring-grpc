package ru.vladigeras.springgrpc.server.configuration;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@GrpcGlobalServerInterceptor
public class LogInterceptor implements ServerInterceptor {

    @Override
    public <Rq, Rs> ServerCall.Listener<Rq> interceptCall(ServerCall<Rq, Rs> serverCall, Metadata metadata, ServerCallHandler<Rq, Rs> next) {
        final MethodDescriptor<Rq, Rs> method = serverCall.getMethodDescriptor();
        SimpleForwardingServerCall<Rq, Rs> listener = new SimpleForwardingServerCall<>(serverCall) {
            @Override
            public void sendMessage(Rs rs) {
                log.info("Method \"{}\" was answered with RS {}", method.getFullMethodName(), rs);
                super.sendMessage(rs);
            }
        };
        return new SimpleForwardingServerCallListener<>(next.startCall(listener, metadata)) {
            @Override
            public void onMessage(Rq rq) {
                log.info("Method \"{}\" was invoked with RQ {}", method.getFullMethodName(), rq);
                super.onMessage(rq);
            }
        };
    }
}

package com.checkout.develcode.expections;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String string, Response response) {

        switch (response.status()) {
            case 400:
                return new CustomFeignException(response.status(), "Payment not authorized");
            default:
                return new CustomFeignException(response.status(), response.reason());
        }
    }

}

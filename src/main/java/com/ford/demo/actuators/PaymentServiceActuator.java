package com.ford.demo.actuators;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id="payments")
@Component
public class PaymentServiceActuator {

    static class PaymentServiceStatus{
        boolean status=true;
        public boolean getStatus(){
            return status;
        }
    }

    @Data
    @AllArgsConstructor
    static class PaymentResponse{

        private Integer serviceNo;
        private String serviceName;
        private Integer servicePort;
        private String serviceStatus;
    }

    @ReadOperation
    public Map<String,PaymentResponse> paymentServiceStatus(){

        Map<String,PaymentResponse> props=new HashMap<>();

        if(isServiceUp()){
            PaymentResponse paymentResponse=new PaymentResponse(1,"VX2C50",8086,"UP");
            props.put("status",paymentResponse);
        }else{
            PaymentResponse paymentResponse=new PaymentResponse(1,"VX2C50",8086,"DOWN");
            props.put("status",paymentResponse);
        }


        return  props;

    }


    public boolean isServiceUp(){

        PaymentServiceStatus pss = new PaymentServiceStatus();

        if(pss.getStatus()){
            return true;
        }
        return  false;
    }
}

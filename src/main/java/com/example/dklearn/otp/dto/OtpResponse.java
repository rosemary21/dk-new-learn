package com.example.dklearn.otp.dto;

import com.example.dklearn.admin.user.resp.ServiceResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponse {
    protected ServiceResponseStatus status;
    protected OtpResponseData data;

    public  OtpResponse successResponse(OtpResponseData data){
        OtpResponse cuResp = new OtpResponse();
        cuResp.setStatus(ServiceResponseStatus.SUCCESS);
        cuResp.setData(data);
        return cuResp;
    }

    public  OtpResponse errorResponse(OtpResponseData data){
        OtpResponse cuResp = new OtpResponse();
        cuResp.setStatus(ServiceResponseStatus.ERROR);
        cuResp.setData(data);

        return cuResp;
    }

    public  OtpResponse failureResponse(OtpResponseData data){
        OtpResponse cuResp = new OtpResponse();
        cuResp.setStatus(ServiceResponseStatus.FAILED);
        cuResp.setData(data);

        return cuResp;
    }
}

package com.example.dklearn.service;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HttpCallService {

    @Value("${idPass.api.key}")
    private String idPassApiKey;

    @Value("${idPass.api.id}")
    private String idPassApiId;

    @Value("${idPass.v2.api.id}")
    private String idPassApiIdV2;

    public String doBasicPost(String url, String payload) throws Exception{
        log.info("ENTRY -> doBasicPost Endpoint: {}", url);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e.getCause());
        }
        if(httpResponse.getStatus() == 200) return  httpResponse.getBody();
        else throw new Exception("Unable to perform basic post operation with status code: "+ httpResponse.getStatus() + " and message: "+ httpResponse.getBody());
    }



    public String doBasicPostFlutterWave(String url, String payload) throws Exception{
        log.info("ENTRY -> doBasicPost Endpoint: {}", url);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer FLWSECK_TEST-88f4060c4f04f5ea0a047230c5b3bb83-X")
                    .body(payload)
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e.getCause());
        }
        //if(httpResponse.getStatus() == 200)
            return  httpResponse.getBody();
       // else throw new Exception("Unable to perform basic post operation with status code: "+ httpResponse.getStatus() + " and message: "+ httpResponse.getBody());
    }


    public String doBasicGetFlutterWave(String url) throws Exception{
        log.info("ENTRY -> doBasicPost Endpoint: {}", url);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer FLWSECK_TEST-88f4060c4f04f5ea0a047230c5b3bb83-X")
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e.getCause());
        }
       // if(httpResponse.getStatus() == 200)
            return  httpResponse.getBody();
       // else throw new Exception("Unable to perform basic post operation with status code: "+ httpResponse.getStatus() + " and message: "+ httpResponse.getBody());
    }


    public String idVerifyPassUrlCall(String url, String payload){

        HttpResponse<String> response = null;
        log.info("ENTRY idVerifyPassUrlCall -> url: {} ",url);
        try {
            response = Unirest.post(url)
                    .header("Content-Type","application/json")
                    .header("x-api-key", idPassApiKey)
                    .header("app-id",idPassApiId)
                    .body(payload)
                    .asString();
        }catch (Exception e){

        }
        return response != null ? response.getBody() : "No_response_from_idVerificationPass";
    }

    public String idVerifyPassV2UrlCall(String url, String payload){

        HttpResponse<String> response = null;
        log.info("ENTRY idVerifyPassUrlCall -> url: {} ",url);
        try {
            response = Unirest.post(url)
                    .header("Content-Type","application/json")
                    .header("x-api-key", idPassApiKey)
                    .header("app-id",idPassApiIdV2)
                    .body(payload)
                    .asString();
        }catch (Exception e){
             e.printStackTrace();
        }
        return response != null ? response.getBody() : "No_response_from_idVerificationPass";
    }

}

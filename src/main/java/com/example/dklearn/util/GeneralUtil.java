//package com.example.dklearn.util;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//@Component
//@Slf4j
//@AllArgsConstructor
//public class GeneralUtil {
//
//    private HttpServletRequest servletRequest;
//    private MessageSource messageSource;
//
//
//    public  static  String formatToInternationalFormat(String number){
//        boolean value= number.startsWith("0");
//        String result = number.substring(1);
//        String interPhoneNumber="234"+result;
//        return interPhoneNumber;
//
//    }
//
//    public static String getEmailForAdmin(String token){
//
//        var objectMapper = new ObjectMapper();
//        var jwtToken = GeneralUtil.jwtDecode(token);
//        String email = null;
//
//        try {
//            Map<String, String> payload = objectMapper.readValue(jwtToken.getPayload(), HashMap.class);
//
//            if(!payload.containsKey("iss") ||
//                    !payload.get("iss").equalsIgnoreCase("bank"))
//                throw new ApplicationException(
//                        HttpStatus.FORBIDDEN.value(),
//                        "cv96",
//                        "Not permitted");
//
//            var sub = String.valueOf(payload.get("sub"));
//            email = sub;
//        } catch (Exception e) {
//            throw new ApplicationException(
//                    HttpStatus.BAD_REQUEST.value(),
//                    "cv500",
//                    "An unexpected exception occurred");
//        }
//
//        return email;
//    }
//
//    public String getToken(){
//        var token = servletRequest.getHeader("apiKey");
//
//        if(ObjectUtils.isEmpty(token)){
//            throw new ApplicationException(HttpStatus.FORBIDDEN.value(),
//                    messageSource("service.error.code"),
//                    messageSource("savings.restriction"));
//        }
//
//        return token;
//    }
//
//    public static boolean isAdmin(String token){
//        var objectMapper = new ObjectMapper();
//
//        var jwtToken = GeneralUtil.jwtDecode(token);
//
//        Map<String, String> payload = null;
//        try {
//            payload = objectMapper.readValue(jwtToken.getPayload(), HashMap.class);
//        } catch (JsonProcessingException e) {
//            return false;
//        }
//
//        if(!payload.containsKey("iss") || !payload.get("iss").equalsIgnoreCase("bank")){
//            return false;
//        }
//
//        return true;
//    }
//
//    public static JwtResponseDto jwtDecode(String token){
//        String[] chunks = token.split("\\.");
//
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//
//        String header = new String(decoder.decode(chunks[0]));
//        String payload = new String(decoder.decode(chunks[1]));
//
//        return JwtResponseDto.builder().header(header).payload(payload).build();
//    }
//
//    public void isAdminElseThrow(){
//        var isAdmin = GeneralUtil.isAdmin(getToken());
//
//        if(!isAdmin)
//            throw new ApplicationException(HttpStatus.FORBIDDEN.value(),
//                    messageSource("service.error.code"),
//                    messageSource("staff.restriction"));
//    }
//
//    public String messageSource(String code){
//        return messageSource(code, null);
//    }
//
//    public String messageSource(String code, Object[] args){
//        return messageSource(code, args, Locale.ENGLISH);
//    }
//
//    public String messageSource(String code, Object[] args, Locale lang){
//        return messageSource.getMessage(code, args, lang);
//    }
//
//    public boolean isNumeric(String strNum) {
//        if (strNum == null || StringUtils.isEmpty(strNum)) {
//            return false;
//        }
//        try {
//            double d = Double.parseDouble(strNum);
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//        return true;
//    }
//}

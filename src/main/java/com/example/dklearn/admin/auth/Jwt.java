package com.example.dklearn.admin.auth;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class Jwt {

    @Value("${secret.key}")
    private static  String secretKey;

    @Value("${jwt.millseconds}")
    private static String expirationTime;

    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString("key".getBytes());


    }

    public static String createAdminToken(String username){
        secretKey= Base64.getEncoder().encodeToString("key".getBytes());
        return Jwts.builder().setSubject(username)
                .setIssuer("Bank")
              //  .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();


    }

    public static String defaultTutorToken(String username){

        Date dt = new Date();
        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String base64Encoded = DatatypeConverter.printBase64Binary("key".getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Encoded);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId("1")
                .setExpiration(tomorrow)
                .setSubject(username)
                .setIssuer("Bank")
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();

    }


    public static String defaultTokenAdmin(String username){

        Date dt = new Date();
        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String base64Encoded = DatatypeConverter.printBase64Binary("key".getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Encoded);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId("1")
                .setExpiration(tomorrow)
                .setSubject(username)
                .setIssuer("Admin")
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();

    }

//    public static String defaultCustomerToken(String username){
//
//        Date dt = new Date();
//        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        String base64Encoded = DatatypeConverter.printBase64Binary("key".getBytes());
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Encoded);
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//        JwtBuilder builder = Jwts.builder().setId("1")
//                .setExpiration(tomorrow)
//                .setSubject(username)
//                .setIssuer("Customer")
//                .signWith(signatureAlgorithm, signingKey);
//        return builder.compact();
//
//    }

    public static String defaultCustomerToken(String username){

        Date dt = new Date();
        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String base64Encoded = DatatypeConverter.printBase64Binary("key".getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Encoded);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(username)
                .setExpiration(tomorrow)
                .setSubject(username)
                .setIssuer("Customer")
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();

    }

//    public static String createCustomerToken(String username){
//        secretKey= Base64.getEncoder().encodeToString("key".getBytes());
//        return Jwts.builder().setSubject(username)
//                .setIssuer("Customer")
//                .claim("Role_Name",new ArrayList<>().add("Customer"))
//                //  .setExpiration(calculateExpirationDate())
//                .signWith(SignatureAlgorithm.HS256,secretKey)
//                .compact();
//
//
//    }



//    private static Date calculateExpirationDate(){
//        Date now=new Date();
//        return new Date(now.getTime()+expirationTime);
//    }
}

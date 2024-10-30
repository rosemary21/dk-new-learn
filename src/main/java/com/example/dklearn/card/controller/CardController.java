package com.example.dklearn.card.controller;


import com.example.dklearn.card.dto.CardResponse;
import com.example.dklearn.card.dto.InitializeTransactionRequest;
import com.example.dklearn.card.service.CardService;
import com.example.dklearn.card.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dl/api/v1/card", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardController {

    @Autowired
    CardService service;
    @Autowired
    VerifyService verifyService;

    @RequestMapping(value = "/charge" ,method = RequestMethod.POST)
    public ResponseEntity<CardResponse> changeCard(@RequestBody InitializeTransactionRequest request) {
        try{

            CardResponse responseDto= service.initTransaction(request);
            if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
                return new ResponseEntity<CardResponse>(responseDto, HttpStatus.OK);
            }
            else
                return new ResponseEntity<CardResponse>(responseDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            return new ResponseEntity<CardResponse>(new CardResponse(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/forgot-password")
    public String getForgotPasswordPage(Model model){
        return "paystack";
    }

    @RequestMapping(value = "/{reference}" ,method = RequestMethod.POST)
    public ResponseEntity<CardResponse> verifyCard(@PathVariable(value = "reference") String reference) {
        try{
            CardResponse responseDto= verifyService.verifyTransaction(reference);
            if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
                return new ResponseEntity<CardResponse>(responseDto, HttpStatus.OK);
            }
            else
                return new ResponseEntity<CardResponse>(responseDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            return new ResponseEntity<CardResponse>(new CardResponse(), HttpStatus.BAD_REQUEST);

        }
    }
}

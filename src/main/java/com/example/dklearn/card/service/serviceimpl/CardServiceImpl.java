package com.example.dklearn.card.service.serviceimpl;


import com.example.dklearn.admin.auth.CustomerUserDetails;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.card.dto.CardDto;
import com.example.dklearn.card.dto.CardResponse;
import com.example.dklearn.card.dto.InitializeTransactionRequest;
import com.example.dklearn.card.dto.InitializeTransactionResponse;
import com.example.dklearn.card.model.Card;
import com.example.dklearn.card.repository.CardRepository;
import com.example.dklearn.card.service.CardService;
import com.example.dklearn.response.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Locale;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    ModelMapper modelMapper;

    @Value("${paystack.secret.key.url}")
    private String PAYSTACK_SECRET_KEY;

    @Value("${paystack.base.url}")
    private String PAYSTACK_BASE_URL;

    @Value("${paystack.callback.url}")
    private String PAYSTACK_CALLBACK_URL;

    @Value("${paystack.initialize.url}")
    private String PAYSTACK_INITIAL_URL;

    @Value("${paystack.verify.url}")
    private String PAYSTACK_VERIFY_URL;


    @Autowired
    UserRepository userRepository;

    @Override
    public CardResponse initTransaction(InitializeTransactionRequest request) throws Exception {
        CardResponse cardResponse=new CardResponse();
        ResponseDto responseDto=new ResponseDto();
        InitializeTransactionResponse initializeTransactionResponse = null;
        try {
            // convert transaction to json then use it as a body to post json
            Gson gson = new Gson();
            // add paystack chrges to the amount
            log.info("getting the request"+request.getEmail());
            String user=getCurrentuser();
            DekeralutiveUser user1= userRepository.findByUserName(user);
            BigDecimal currentAmount=  new BigDecimal(request.getAmount()).multiply(new BigDecimal(100));
            request.setAmount(currentAmount.intValue());
            request.setEmail(user1.getEmail());
            request.setCallback_url(PAYSTACK_CALLBACK_URL);

            StringEntity postingString = new StringEntity(gson.toJson(request));
            System.out.println("post String"+postingString);
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(PAYSTACK_BASE_URL+PAYSTACK_INITIAL_URL);
            post.setEntity(postingString);
            log.info("getting the email {}",user1.getEmail());
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer "+PAYSTACK_SECRET_KEY);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);
            System.out.println("Entry getting the response"+response.getEntity().getContent());
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();

            initializeTransactionResponse = mapper.readValue(result.toString(), InitializeTransactionResponse.class);
            cardResponse.setInitializeTransactionResponse(initializeTransactionResponse);
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("card.charge.success",null,Locale.ENGLISH));
            cardResponse.setResponseDto(responseDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializaing paystack transaction");
        }

        return cardResponse;
    }


    @Override
    public CardResponse addCard(InitializeTransactionResponse initializeTransactionResponse) {
        ResponseDto responseDto=new ResponseDto();
        CardResponse cardResponse=new CardResponse();
        CardDto cardDto=new CardDto();
        cardDto.setCardType(initializeTransactionResponse.getData().getAuthorization().getCard_type());
        cardDto.setAuthorizationCode(initializeTransactionResponse.getData().getAuthorization().getAuthorization_code());
        cardDto.setExpYear(initializeTransactionResponse.getData().getAuthorization().getExp_year());
        cardDto.setLastFourDigit(initializeTransactionResponse.getData().getAuthorization().getLast4());
        cardDto.setExpMonth(initializeTransactionResponse.getData().getAuthorization().getExp_month());
        cardDto.setSignature(initializeTransactionResponse.getData().getAuthorization().getSignature());
        cardDto.setBankName(initializeTransactionResponse.getData().getAuthorization().getBank());
        cardDto.setReference(initializeTransactionResponse.getData().getReference());
        cardDto.setChannel(initializeTransactionResponse.getData().getAuthorization().getChannel());
        Card card=   modelMapper.map(cardDto, Card.class);
        cardRepository.save(card);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("card.charge.success",null,Locale.ENGLISH));
        cardResponse.setResponseDto(responseDto);
        return cardResponse;
    }


    public String getCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomerUserDetails) {
            String username = ((CustomerUserDetails)principal).getUsername();
            if(username.contains("_")){
                String value []=username.split("_");
                return  value[0];
            }
            return  username;
//            return username;
        } else {
            //  String username = principal.toString();
            return null;
        }
    }
}

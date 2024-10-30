package com.example.dklearn.otp.service.impl;


import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.notification.dto.EmailDto;
import com.example.dklearn.notification.service.NotificationService;
import com.example.dklearn.otp.dto.OtpDto;
import com.example.dklearn.otp.dto.OtpResponseData;
import com.example.dklearn.otp.model.Otp;
import com.example.dklearn.otp.repository.OtpRepository;
import com.example.dklearn.otp.service.OtpService;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.service.HttpCallService;
import com.example.dklearn.sms.SendMailRequest;
import com.example.dklearn.sms.Sms;
import com.example.dklearn.sms.dto.SmsDto;
import com.example.dklearn.sms.dto.TermiMedia;
import com.example.dklearn.sms.dto.TermiSmsDto;
import com.example.dklearn.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Service
@Slf4j
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    HttpCallService httpCallService;

    @Value("${app.kafka.sms.url}")
    private String kafkaSmsServiceUrl;

    @Autowired
    ObjectMapper om;

    @Value("${app.sms.source}")
    private String smssource;

    @Value("${otp.duration}")
    private String otpTime;

    @Value("${app.sms.notificationservice.url}")
    private String smsNotificationServiceUrl;

    @Value("${app.notificationservice.url}")
    private String notificationServiceUrl;

    @Autowired
    UserRepository customerRepository;

    @Autowired
    NotificationService notificationService;


    @Value("${otp.subject}")
    private String otpSubject;


    private static final String DATE_FORMATTER= "dd-MMMM-yyyy HH:mm:ss a";


    @Autowired
    MessageSource messageSource;

    @Override
    public OtpResponseData authenticateOtp(OtpDto otpDto) {
        ResponseDto otpResponse=new ResponseDto();
        OtpResponseData otpResponse1=new OtpResponseData();
        Otp otp= otpRepository.findByOtpId(otpDto.getOtpId());
        if(otp==null){
            otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
            otpResponse.setMessage( messageSource.getMessage("otp.exist.nofound", null, Locale.ENGLISH));
            otpResponse1.setResp(otpResponse);
            return otpResponse1;
        }
        if(otp!=null){
            LocalDateTime localDateTime=otp.getSentTime();
            boolean value= DateUtil.isDateAfter(otp.getExpiryTime());
            if(value){
                if(!(otp.getOtp().equalsIgnoreCase(otpDto.getOtp()))){
                    log.info("wrong otp information");
                    String newPhoneNumber=null;
                    if(otp.getOtpId().startsWith("234")){
                        String phonenumber=otp.getOtpId().substring(3);
                        newPhoneNumber="0"+phonenumber;
                    }else {
                        newPhoneNumber=otp.getOtpId();
                    }

//                    if(customer!=null){
//                        log.info("getting the customer details");
//                        boolean check= validationService.validatePinOrPasswordOrOtpAttempt(newPhoneNumber, AuthType.OTP.name());
//                        if(!check){
//                            otpResponse.setCode(messageSource.getMessage("service.access.denied.code",null,Locale.ENGLISH));
//                            otpResponse.setMessage(messageSource.getMessage("blacklist.customer",null,Locale.ENGLISH));
//                            otpResponse1.setResp(otpResponse);
//                            return otpResponse1;
//                        }
//                    }
                    otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
                    otpResponse.setMessage( messageSource.getMessage("otp.wrong", null, Locale.ENGLISH));
                    otpResponse1.setResp(otpResponse);
                    return otpResponse1;
                }
                otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
                otpResponse.setMessage( messageSource.getMessage("otp.expired", null, Locale.ENGLISH));
                otpResponse1.setResp(otpResponse);
                return otpResponse1;
            }
            else {
                if(otp.getOtp().equalsIgnoreCase(otpDto.getOtp())){
                    otp.setValidateOtp(true);
                    otpRepository.save(otp);
                    otpResponse.setCode(messageSource.getMessage("service.success.code", null, Locale.ENGLISH));
                    otpResponse.setMessage( messageSource.getMessage("otp.success", null, Locale.ENGLISH));
                    otpResponse1.setResp(otpResponse);
                    return otpResponse1;
                }else {
                    String newPhoneNumber=null;
                    if(otp.getOtpId().startsWith("234")){
                        String phonenumber=otp.getOtpId().substring(3);
                        newPhoneNumber="0"+phonenumber;
                    }else{
                        newPhoneNumber=otp.getOtpId();
                    }
//                    if(customer!=null){
//                        log.info("getting the customer details");
//                        boolean check= validationService.validatePinOrPasswordOrOtpAttempt(newPhoneNumber, AuthType.OTP.name());
//                        if(!check){
//                            otpResponse.setCode(messageSource.getMessage("service.access.denied.code",null,Locale.ENGLISH));
//                            otpResponse.setMessage(messageSource.getMessage("blacklist.customer",null,Locale.ENGLISH));
//                            otpResponse1.setResp(otpResponse);
//                            return otpResponse1;
//                        }
//                    }
                    otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
                    otpResponse.setMessage( messageSource.getMessage("otp.wrong", null, Locale.ENGLISH));
                    otpResponse1.setResp(otpResponse);
                    return otpResponse1;
                }
            }
        }

        return null;
    }


    @Override
    public OtpResponseData validateOtp(OtpDto otpDto) {
        ResponseDto otpResponse=new ResponseDto();
        OtpResponseData otpResponse1=new OtpResponseData();
        log.info("getting the otp id {}",otpDto.getOtpId());
        Otp otp= otpRepository.findByOtpId(otpDto.getOtpId());
        log.info("Entring getting the otp id {}",otp);
        if(otp==null){
            otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
            otpResponse.setMessage( messageSource.getMessage("otp.exist.nofound", null, Locale.ENGLISH));
            otpResponse1.setResp(otpResponse);
            return otpResponse1;
        }
        if(otp!=null){
            if(otp.getOtp().equalsIgnoreCase(otpDto.getOtp())){
                otpResponse.setCode(messageSource.getMessage("service.success.code", null, Locale.ENGLISH));
                otpResponse.setMessage( messageSource.getMessage("otp.success", null, Locale.ENGLISH));
                otpResponse1.setResp(otpResponse);
                return otpResponse1;
            }else {
                otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
                otpResponse.setMessage( messageSource.getMessage("otp.wrong", null, Locale.ENGLISH));
                otpResponse1.setResp(otpResponse);
                return otpResponse1;
            }
            //  }
        }

        return null;
    }

    @Override
    public OtpResponseData getOtpDetails(OtpDto otpDto) {
        OtpResponseData otpRespData = new OtpResponseData();
        ResponseDto respDto =new ResponseDto();
        var otpResp = otpRepository.findByOtpIdAndOtp(otpDto.getOtpId(),otpDto.getOtp());
        if(otpResp!=null && !(otpResp.isValidateOtp())){
            respDto.setCode(messageSource.getMessage("service.error.code",null,Locale.ENGLISH));
            respDto.setMessage(messageSource.getMessage("otp.no.verified",null,Locale.ENGLISH));
            otpRespData.setResp(respDto);
            return otpRespData;
        }
        if(null == otpResp){
            String newPhoneNumber=null;
            if(otpDto.getOtpId().startsWith("234")){
                String phonenumber=otpDto.getOtpId().substring(3);
                newPhoneNumber="0"+phonenumber;
            }else{
                newPhoneNumber=otpDto.getOtpId();
            }
            DekeralutiveUser customer= customerRepository.findByUserName(newPhoneNumber);
//            if(customer!=null){
//                log.info("getting the customer details");
//                boolean check= validationService.validatePinOrPasswordOrOtpAttempt(newPhoneNumber, AuthType.OTP.name());
//                if(!check){
//                    respDto.setCode(messageSource.getMessage("service.access.denied.code",null,Locale.ENGLISH));
//                    respDto.setMessage(messageSource.getMessage("blacklist.customer",null,Locale.ENGLISH));
//                    otpRespData.setResp(respDto);
//                    return otpRespData;
//                }
//            }

            respDto.setCode(messageSource.getMessage("service.error.code",null,Locale.ENGLISH));
            respDto.setMessage(messageSource.getMessage("otp.exist.nofound",null,Locale.ENGLISH));

        }else {
            respDto.setCode(messageSource.getMessage("service.success.code",null,Locale.ENGLISH));
            respDto.setMessage(messageSource.getMessage("otp.success",null,Locale.ENGLISH));
            otpRespData.setOtpDetails(convertEntityToDto(otpResp));
        }
        otpRespData.setResp(respDto);
        return otpRespData;
    }

    @Override
    public boolean sendOtp(OtpDto otpDto) {
        boolean result=false;
        String payload;
        if(otpDto.getNotificationType().equalsIgnoreCase("M")){
            try{
                ObjectNode notificationData = new ObjectNode(JsonNodeFactory.instance);
                log.info("getting the email address {}",otpDto.getOtpId());
                notificationData.put("amount",otpDto.getTranAmount());
                notificationData.put("otp",otpDto.getOtp());
                notificationData.put("toAddress",otpDto.getOtpId());
                notificationData.put("expiryDateTime",otpDto.getSentExpiryTime());
                if(otpDto.getOtpType().equalsIgnoreCase("R")){
                    SendMailRequest sendMailRequest = new SendMailRequest(otpSubject, notificationData, "regotp");
                    var newPayLoad = om.writerWithDefaultPrettyPrinter().writeValueAsString(sendMailRequest);
                    log.info("getting the pay {}",newPayLoad);
                    EmailDto emailDto=new EmailDto();
                    emailDto.setEmailAddressTo(otpDto.getOtpId());
                    emailDto.setTemplateName("regotp");
                    emailDto.setOtp(otpDto.getOtp());
                    emailDto.setExpiryDateTime(otpDto.getSentExpiryTime());
                    notificationService.sendOtpEmail(emailDto);
                    result=true;
                }
                if(otpDto.getOtpType().equalsIgnoreCase("T")){
                    SendMailRequest sendMailRequest = new SendMailRequest(otpSubject, notificationData, "tranotp");
                    var newPayLoad = om.writerWithDefaultPrettyPrinter().writeValueAsString(sendMailRequest);
                    log.info("getting the pay {}",newPayLoad);
                    httpCallService.doBasicPost(notificationServiceUrl, newPayLoad);
                    result=true;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(otpDto.getNotificationType().equalsIgnoreCase("S")){
            try{
                SmsDto smsDto=new SmsDto();
                String  newSendSource="";
                String formatStr="";
                TermiSmsDto termiSmsDto=new TermiSmsDto();


                smsDto.setSrc(smssource);
                if(otpDto.getOtpId().startsWith("234")){
                    smsDto.setDest(otpDto.getOtpId());
                }else{
                    String  formmatedSendSource = otpDto.getOtpId().substring(1);  // str is now "ello"
                      newSendSource="234"+formmatedSendSource;
                    log.info("The formamatted send source {}",newSendSource);
                    smsDto.setDest(newSendSource);
                }
                if(otpDto.getOtpType().equalsIgnoreCase("R")){
                    String value= messageSource.getMessage("otp.login.message", null, Locale.ENGLISH);
                     formatStr = String.format(value, otpDto.getOtp(),otpDto.getSentExpiryTime());
                    log.info("getting the format string {}",formatStr);
                    smsDto.setText(formatStr);
                }
                if(otpDto.getOtpType().equalsIgnoreCase("T")){
                    String value= messageSource.getMessage("otp.transaction.message", null, Locale.ENGLISH);
                     formatStr = String.format(value,otpDto.getTranAmount(), otpDto.getOtp(),otpDto.getSentExpiryTime());
                    log.info("getting the format string {}",formatStr);
                    smsDto.setText(formatStr);
                }
                Sms sms=new Sms();
                TermiMedia media=new TermiMedia();
                sms.setSms(smsDto);
                termiSmsDto.setTo(newSendSource);
                termiSmsDto.setFrom("dkeralutive");
                termiSmsDto.setSms(formatStr);
                termiSmsDto.setChannel("generic");
                termiSmsDto.setType("plain");
                termiSmsDto.setApi_key("TLijvYZbDvuUivCFEJULJkPjcYfXaFJCBFkXltkplHjDlvTvToPZKEsosafFxb");
                media.setUrl("https://media.example.com/file");
                media.setCaption("your media file");
                termiSmsDto.setMedia(media);
                payload = om.writerWithDefaultPrettyPrinter().writeValueAsString(termiSmsDto);
                log.info("getting the termi payload {}",payload);
                String response = httpCallService.doBasicPost(smsNotificationServiceUrl, payload);
                result=true;

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return result;

    }


    @Override
    public OtpDto generateOtp() {
        OtpDto newOtp=new OtpDto();
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String value=String.format("%06d", number);
        newOtp.setOtp(value);
        return newOtp;
    }

    @Override
    public OtpResponseData requestOtp(OtpDto otpDto) {

        Otp oldOtp= otpRepository.findByOtpId(otpDto.getOtpId());
        if(oldOtp!=null){
            otpRepository.delete(oldOtp);
        }
        OtpResponseData otpResponseData=new OtpResponseData();
        ResponseDto otpResponse=new ResponseDto();
        OtpDto newOtp=generateOtp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        Otp otp=convertDtoToEntity(otpDto);
        otpDto.setSentExpiryTime(LocalDateTime.now().plusHours(1).plusMinutes(Long.valueOf(otpTime)).format(formatter));
        otp.setSentTime(LocalDateTime.now());
        otpDto.setExpireTime(otp.getSentTime().plusMinutes(Long.valueOf(otpTime)).format(formatter));
        otp.setOtp(newOtp.getOtp());
       // otp.setOtp("123456");
        otp.setExpiryTime(otp.getSentTime().plusMinutes(Long.valueOf(otpTime)));
        log.info("getting the otp {}",otp.getOtp());
        otpDto.setOtp(newOtp.getOtp());
       // otpDto.setOtp("123456");
        Otp otp1= otpRepository.save(otp);
        log.info("Entring the otp details {}",otp1.getOtpId());
        boolean value=sendOtp(otpDto);
//        if(!value){
//            otpResponse.setCode(messageSource.getMessage("service.error.code", null, Locale.ENGLISH));
//            otpResponse.setMessage( messageSource.getMessage("otp.sent.failed", null, Locale.ENGLISH));
//            otpResponseData.setResp(otpResponse);
//            return otpResponseData;
//        }
        otpResponse.setCode(messageSource.getMessage("service.success.code", null, Locale.ENGLISH));
        otpResponse.setMessage( messageSource.getMessage("otp.sent", null, Locale.ENGLISH));
        otpResponseData.setResp(otpResponse);
        return otpResponseData;
    }

    public Otp convertDtoToEntity(OtpDto otpDto){
        Otp otp=modelMapper.map(otpDto,Otp.class);
        return otp;
    }

    private OtpDto convertEntityToDto(Otp otp){
        return modelMapper.map(otp,OtpDto.class);
    }


    @Override
    public boolean checkOtp(String otpId,String otp) {
        Otp otp1=otpRepository.findByOtpIdAndOtp(otpId,otp);
        if(otp1==null){
            return false;
        }
        return true;
    }

}

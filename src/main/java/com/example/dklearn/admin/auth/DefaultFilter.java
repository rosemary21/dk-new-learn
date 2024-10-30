package com.example.dklearn.admin.auth;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;

@Slf4j
@Service
public class DefaultFilter {


    @Value("${staff.login.url}")
    private String staffLoginUrl;

    @Value("${customer.login.url}")
    private String customerLoginUrl;

    @Value("${customer.login.saving.url}")
    private String savingCustomerLoginUrl;

    @Value("${staff.login.saving.url}")
    private String savingStaffLoginUrl;

//    boolean getDefaultFilter(String value){
//        try{
//            boolean result =false;
//
//
////         URL defaultStaffUrl=new URL("http://creditvilleprelive.com/userservice/api/v1/login/staff");
//            URL resetpasswordurl=new URL("http://localhost:8082/dl/api/v1/user/resetpassword");
//            URL allCourses=new URL("http://localhost:8082/dl/api/v1/course/all");
//
//            URL defaultCustomerUrl=new URL("http://localhost:8082/dl/api/v1/login/api/v1/login/customer");
//            URL defaultStaffUrl=new URL("http://localhost:8082/dl/api/v1/login/staff");
//            URL defaultAdminUrl=new URL("http://localhost:8082/dl/api/v1/login/admin");
//            URL otpUrl=new URL("http://localhost:8082/dl/api/v1/otp/request");
//            URL defaultPhoneUrl=new URL("http://localhost:8082/dl/api/v1/customer/validate/phonenumber");
//            URL defaultUl=new URL("http://localhost:8082/dl/api/v1/login/customer");
//            URL completeCustomerUrl = new URL("http://localhost:8082/dl/api/v1/customer/update");
//            URL defaultUrl=new URL("http://localhost:8082/dl/api/v1/login/customer");
//            URL defaultbvnUrl=new URL("http://localhost:8082/dl/api/v1/bvn");
//            URL defaultgetbvnUrl=new URL("http://localhost:8082/dl/api/v1/bvn/verify");
//            URL defaultSavingsUrl=new URL("http://localhost:8082/dl/api/v1/login/savingsservice");
//            URL defaultSavingsStaffUrl=new URL("http://localhost:8082/dl/api/v1/login/staff/savingservice");
//            URL validateUrl = new URL("http://localhost:8082/dl/api/v1/customer/validate");
//            URL authenticateotpUrl = new URL("http://localhost:8082/dl/api/v1/otp/authenticate");
//            URL createCustomerUrl = new URL("http://localhost:8082/dl/api/v1/user/add");
//            URL createStaffUrl = new URL("http://localhost:8082/dl/api/v1/staff/add");
//
//            URL setpasswordUrl = new URL("http://localhost:8082/dl/api/v1/password/mobile/set-firsttime-password");
//            URL forgetUrl = new URL("http://localhost:8082/dl/api/v1/password/mobile/forgot-password");
//            URL requestUrl=new URL(value);
//
//            if(requestUrl.equals(defaultStaffUrl)||requestUrl.equals(allCourses)||requestUrl.equals(resetpasswordurl)||requestUrl.equals(otpUrl)||requestUrl.equals(defaultAdminUrl)||requestUrl.equals(createStaffUrl) ||(requestUrl.equals(defaultPhoneUrl))||(requestUrl.equals(defaultUrl)) || (requestUrl.equals(defaultSavingsUrl))||(requestUrl.equals(defaultSavingsStaffUrl)) ||(requestUrl.equals(validateUrl))||((requestUrl.equals(authenticateotpUrl)))||((requestUrl.equals(createCustomerUrl)))||((requestUrl.equals(defaultUl)))){
//                return true;
//            }
//            if((requestUrl.equals(defaultbvnUrl)) ||(requestUrl.equals(defaultgetbvnUrl))||(requestUrl.equals(forgetUrl))||(requestUrl.equals(completeCustomerUrl))||(requestUrl.equals(defaultCustomerUrl)||(requestUrl.equals(setpasswordUrl)))){
//                return true;
//            }
//        }
//        catch (Exception e){
//            return false;
//        }
//        return false;
//    }


    boolean getDefaultFilter(String value){
        try{
            boolean result =false;


//         URL defaultStaffUrl=new URL("http://creditvilleprelive.com/userservice/api/v1/login/staff");
            URL resetpasswordurl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/user/resetpassword");
            URL resetpasswordadminurl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/admin/resetpassword");

            URL resetpasswordtutorurl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/staff/resetpassword");
            URL defaultCustomerUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/api/v1/login/customer");
            URL defaultStaffUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/staff");
            URL defaultPhoneUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/staff/add");

            URL defaultAdminUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/admin");
            URL defaultAdminAddUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/admin/add");

            URL defaultViewAdminUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/course/all/view/student/courses");
            URL defaultSingleCourseUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/course/view/singlecourse");

            URL defaultUl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/customer");
            URL completeCustomerUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/user/add");
            URL defaultUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/customer");
            URL defaultbvnUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/bvn");
            URL defaultgetbvnUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/bvn/verify");
            URL defaultSavingsUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/savingsservice");
            URL defaultSavingsStaffUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/login/staff/savingservice");
            URL validateUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/customer/validate");
            URL requestotpUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/otp/request");
            URL authenticateotpUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/otp/authenticate");
            URL createCustomerUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/customer/create");
            URL setpasswordUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/password/mobile/set-firsttime-password");
            URL categoryLocUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/productdescription/category/location");
            URL descriptionIdUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/productdescription/id");
            URL searchCourses = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/course/search");

            URL forgetUrl = new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/password/mobile/forgot-password");
            URL searchUrl=new URL("https://javapaas-171468-0.cloudclusters.net/dl/api/v1/productdescription/search");
            URL requestUrl=new URL(value);

            if(requestUrl.equals(defaultStaffUrl)||requestUrl.equals(resetpasswordtutorurl) ||requestUrl.equals(defaultViewAdminUrl)||requestUrl.equals(defaultSingleCourseUrl) ||requestUrl.equals(defaultAdminUrl)|| requestUrl.equals(defaultAdminAddUrl)||(requestUrl.equals(defaultPhoneUrl))||(requestUrl.equals(defaultUrl)) || (requestUrl.equals(defaultSavingsUrl))||(requestUrl.equals(defaultSavingsStaffUrl)) || (requestUrl.equals(requestotpUrl))||(requestUrl.equals(validateUrl))||((requestUrl.equals(authenticateotpUrl)))||((requestUrl.equals(createCustomerUrl)))||((requestUrl.equals(defaultUl)))){
                return true;
            }
            if((requestUrl.equals(defaultbvnUrl))||(requestUrl.equals(resetpasswordadminurl))||(requestUrl.equals(searchCourses))||(requestUrl.equals(resetpasswordurl)) ||(requestUrl.equals(defaultgetbvnUrl))||(requestUrl.equals(forgetUrl))||(requestUrl.equals(completeCustomerUrl))||(requestUrl.equals(defaultCustomerUrl)||(requestUrl.equals(setpasswordUrl))||(requestUrl.equals(categoryLocUrl))||(requestUrl.equals(searchUrl))||(requestUrl.equals(descriptionIdUrl)))){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

}

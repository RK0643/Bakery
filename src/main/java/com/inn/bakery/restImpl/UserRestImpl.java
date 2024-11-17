package com.inn.bakery.restImpl;

import com.inn.bakery.rest.UserRest;
import com.inn.bakery.service.UserService;
import com.inn.bakery.utills.cafeUtills;
import com.inn.bakery.constents.cafeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {


    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> singUp(Map<String, String> requestmap) {
        try{
            return userService.signUp(requestmap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return cafeUtills.getResponseEntity(cafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


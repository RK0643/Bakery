package com.inn.bakery.serviceImpl;

import com.inn.bakery.constents.cafeConstants;
import com.inn.bakery.service.UserService;
import com.inn.bakery.POJO.User;
import com.inn.bakery.dao.UserDao;
import com.inn.bakery.utills.cafeUtills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signUp {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return cafeUtills.getResponseEntity("Registration Successful......!!", HttpStatus.OK);
                } else {
                    return cafeUtills.getResponseEntity("Email Already Exists.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return cafeUtills.getResponseEntity(cafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cafeUtills.getResponseEntity(cafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(Objects.requireNonNull(requestMap.get("contactNumber")));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("False");
        user.setRole("user");
        return user;
    }
}
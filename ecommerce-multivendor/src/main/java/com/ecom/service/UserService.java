package com.ecom.service;

import com.ecom.model.User;

public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;
    User findByUserEmail(String email) throws Exception;
}

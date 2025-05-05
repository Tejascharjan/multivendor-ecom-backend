package com.ecom.controller;

import com.ecom.domain.USER_ROLE;
import com.ecom.model.User;
import com.ecom.model.VerificationCode;
import com.ecom.repository.UserRepository;
import com.ecom.request.LoginOtpRequest;
import com.ecom.request.LoginRequest;
import com.ecom.response.ApiResponse;
import com.ecom.response.AuthResponse;
import com.ecom.response.SignupRequest;
import com.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> creUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt = authService.createUser(req);
        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("register success");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/send/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sendLoginOtp(req.getEmail(),req.getRole());

        ApiResponse response = new ApiResponse();
        response.setMessage("otp send successfully");
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.signing(req);
        return  ResponseEntity.ok(authResponse);
    }
}

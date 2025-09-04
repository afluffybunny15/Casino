package usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usermanager.dto.LoginRequestDto;
import usermanager.dto.RegisterRequestDto;
import usermanager.service.AuthService;
import usermanager.service.UserService;

@CrossOrigin ( "*" )
@RequestMapping ( "/auth" )
@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping ( "/register" )
    public ResponseEntity< ? > register ( @RequestBody final RegisterRequestDto req ) {
        return authService.register( req );
    }

    @PostMapping ( "/login" )
    public ResponseEntity< ? > login ( @RequestBody final LoginRequestDto req ) {

        return authService.login( req );
    }
}

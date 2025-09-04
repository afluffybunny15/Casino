package usermanager.service;

import org.springframework.http.ResponseEntity;

import usermanager.dto.AuthResponseDto;
import usermanager.dto.LoginRequestDto;
import usermanager.dto.RegisterRequestDto;

public interface AuthService {
    public ResponseEntity< ? > register ( final RegisterRequestDto req );

    public ResponseEntity<AuthResponseDto> login ( final LoginRequestDto req );
}

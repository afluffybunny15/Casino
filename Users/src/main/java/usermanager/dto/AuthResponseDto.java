package usermanager.dto;

public record AuthResponseDto ( String accessToken, String tokenType ) {
    public AuthResponseDto ( final String accessToken ) {
        this( accessToken, "Bearer" );
    }
}

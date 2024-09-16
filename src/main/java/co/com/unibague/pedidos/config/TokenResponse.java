package co.com.unibague.pedidos.config;

public class TokenResponse {

    private String authToken;

    public TokenResponse(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

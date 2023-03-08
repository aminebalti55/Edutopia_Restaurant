package com.example.edutopia_res.configuration;


//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.JsonNode;
        import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KeycloakProvider {

    final static String serverUrl = "http://localhost:8080/auth/";
    public final static String realm = "Edutopia";

    final static String clientId = "Edutopia_Restaurant";
    final static String clientSecret = "NkEpKJKNFnRKeOuxY0JmZO1pG1mWoSxg";

    private static Keycloak keycloak = null;
    final static String userName = "admin_user";
    final static String password = "98625232";

    public KeycloakProvider() {
    }

    public Keycloak getInstance() {
        if (keycloak == null) {

            return KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverUrl)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
        }
        return keycloak;
    }


    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(String username, String password) {
        return KeycloakBuilder.builder() //
                .realm(realm) //
                .serverUrl(serverUrl)//
                .clientId(clientId) //
                .clientSecret(clientSecret) //
                .username(userName) //
                .password(password);
    }

   public JsonNode refreshToken(String refreshToken) throws UnirestException {
        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";
        return Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", clientId)
                .field("client_secret", clientSecret)
                .field("refresh_token", refreshToken)
                .field("grant_type", "refresh_token")
                .asJson().getBody();
    }
}
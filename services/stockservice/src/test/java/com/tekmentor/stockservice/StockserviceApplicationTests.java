package com.tekmentor.stockservice;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.tekmentor.stockservice.config.CognitoConfiguration;
import com.tekmentor.stockservice.model.Stock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureJsonTesters
@ActiveProfiles("default")
@AutoConfigureMockMvc
class StockserviceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AWSCognitoIdentityProvider cognitoIdentityProvider;

    @Autowired
    JacksonTester json;

//    @MockBean
    OAuth2AuthorizedClientService authorizedClientService;

//    @Autowired
    ClientRegistrationRepository registrations;

    @Test
    @WithMockUser(username = "arunstiwari@gmail.com", authorities = {"SCOPE_stocks/write"})
    void addNewStock() throws Exception {

        Stock stock = new Stock();
        stock.setTickerName("MSFT");
        stock.setId("MSFT");
        stock.setPrice(23.2);
        stock.setClosePrice(23.1);
//        OAuth2AuthenticationToken authenticationToken = createToken();
//        OAuth2AuthorizedClient authorizedClient = createAuthorizedClient(authenticationToken);

//        Mockito.when(this.authorizedClientService.loadAuthorizedClient(Mockito.anyString(), Mockito.anyString()))
//                .thenReturn(authorizedClient);
        mockMvc.perform(post("/stocks")
                        .header("Authorization", "Bearer: eyJraWQiOiJoZVhPZWx0NzlOTVwvelQ3bURUaXJkMUFEenVreE1VV2FvUmVqMFNxUmdsUT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJmZmFkNWM2Yy0wZGNjLTQ4ZmMtYTdlNi00ZTYyZDFmNGNiNTgiLCJjb2duaXRvOmdyb3VwcyI6WyJVc2VyIl0sImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwcm9maWxlIjoiczNcL2FiYyIsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfeHBzVjBKMnhhIiwiY3VzdG9tOmdyb3VwIjoiVXNlciIsImNvZ25pdG86dXNlcm5hbWUiOiJmZmFkNWM2Yy0wZGNjLTQ4ZmMtYTdlNi00ZTYyZDFmNGNiNTgiLCJvcmlnaW5fanRpIjoiZWUyZDYxNDgtZjhjMi00Zjc2LTkxMTYtODcyN2ZkNWMzOWU0IiwiYXVkIjoiMjltaGp0a28xbTc5NGFua2M2aHI1ZXBmN2QiLCJldmVudF9pZCI6IjZlMGViM2MzLWI5ZGItNGRkNi04MzFjLWZjYTc3ODQ5ZTZmMSIsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNjI4MjI2MTUwLCJuYW1lIjoiYXJ1bnN0aXdhcmlAZ21haWwuY29tIiwiZXhwIjoxNjI4MjI5NzUwLCJpYXQiOjE2MjgyMjYxNTAsImp0aSI6IjdiMDY2NWI2LWMxNmYtNDMwYi1hNzcyLTg1YTljNTg1M2JhNyIsImVtYWlsIjoiYXJ1bnN0aXdhcmlAZ21haWwuY29tIn0.MYy6FPuE2fsf6W2Q-yVDMLdEuppopQnLL-hdKm5db7J_dsxtyPLgU1BdjsePoaK55XgGmVx9iOTyzXOsVrenyLDPesQcFCKcfKlb0WW8T-zitVxRXdbtjC5aT2Brz-LRcf-xrVYrVIxxZuYeRp2t456RwwJMQtf0TdBwiVnNR1O-bUo8LZk9tMk4A4i8FdqIaLtMPDHuu-N1bkz5kdn_vOe9OnGIiLafs3P58ohRk6QXBCZsPLT92-MevF2fXWw1J-dXHIrI0xN8UJ-AxaouPdmxD3AsIzdPXWJ2CaXbdU9RYmlYLC2SDo5CHlOAD1iv6BVARAetP9XLzk2g_T6VxA")
                        .content(json.write(stock).getJson())
                .contentType(MediaType.APPLICATION_JSON)

        ).andDo(print()).andExpect(status().isCreated()) ;

    }

    private OAuth2AuthorizedClient createAuthorizedClient(OAuth2AuthenticationToken authenticationToken) {
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, "a", Instant.now(), Instant.now().plus(
                Duration.ofDays(1)));

        ClientRegistration clientRegistration = this.registrations.findByRegistrationId(authenticationToken.getAuthorizedClientRegistrationId());
        return new OAuth2AuthorizedClient(clientRegistration, authenticationToken.getName(), accessToken);
    }

    private OAuth2AuthenticationToken createToken() {
        Set<GrantedAuthority> authorities = new HashSet<>(AuthorityUtils.createAuthorityList("USER"));
        OAuth2User oAuth2User = new DefaultOAuth2User(authorities, Collections.singletonMap("name", "rob"), "name");
        return new OAuth2AuthenticationToken(oAuth2User, authorities, "login-client");
    }

}
/**
 * private String id;
 *     private String tickerName;
 *     private double price;
 *     private double closePrice;
 */
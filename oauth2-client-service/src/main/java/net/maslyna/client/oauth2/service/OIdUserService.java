package net.maslyna.client.oauth2.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OIdUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcUserService delegate = new OidcUserService();

        OidcUser user = delegate.loadUser(userRequest);
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        Set<GrantedAuthority> authorities = null;
        try {
            JWT jwt = JWTParser.parse(accessToken.getTokenValue());
            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
            Collection<String> jwtAuthorities = claimsSet.getStringListClaim("authorities");

            authorities = jwtAuthorities.stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        } catch (ParseException e) {
            log.error("Error OAuth2UserService: {}", e.getMessage());
        }
        user = new DefaultOidcUser(authorities, user.getIdToken(), user.getUserInfo());
        return user;
    }
}

package io.deffun.jacqueline.bootifullib;

import graphql.schema.visibility.GraphqlFieldVisibility;
import io.deffun.jacqueline.sunglasses.AuthorizationSecurity;
import io.deffun.jacqueline.sunglasses.AuthorizeFieldVisibility;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SpringComponentsFactory {
    private SpringComponentsFactory() {
    }

    public static GraphqlFieldVisibility authorizeFieldVisibility() {
        return new AuthorizeFieldVisibility(new AuthorizationSecurity() {
            @Override
            public boolean isAuthn() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return authentication != null && authentication.isAuthenticated();
            }

            @Override
            public boolean hasRole(String role) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
            }
        });
    }
}

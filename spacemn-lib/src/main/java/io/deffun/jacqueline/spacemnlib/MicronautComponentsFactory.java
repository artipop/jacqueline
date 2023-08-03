package io.deffun.jacqueline.spacemnlib;

import graphql.schema.visibility.GraphqlFieldVisibility;
import io.deffun.jacqueline.sunglasses.AuthorizationSecurity;
import io.deffun.jacqueline.sunglasses.AuthorizeFieldVisibility;
import io.micronaut.security.utils.SecurityService;

public final class MicronautComponentsFactory {
    private MicronautComponentsFactory() {
    }

    public static GraphqlFieldVisibility authorizeFieldVisibility(SecurityService securityService) {
        return new AuthorizeFieldVisibility(new AuthorizationSecurity() {
            @Override
            public boolean isAuthn() {
                return securityService.isAuthenticated();
            }

            @Override
            public boolean hasRole(String role) {
                return securityService.hasRole(role);
            }
        });
    }
}

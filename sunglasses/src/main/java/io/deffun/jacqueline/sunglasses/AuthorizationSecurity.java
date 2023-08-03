package io.deffun.jacqueline.sunglasses;

public interface AuthorizationSecurity {
    boolean isAuthn();

    boolean hasRole(String role);
}

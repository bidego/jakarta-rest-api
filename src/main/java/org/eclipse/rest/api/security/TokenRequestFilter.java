package org.eclipse.rest.api.security;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

@Provider
@PreMatching
public class TokenRequestFilter implements ContainerRequestFilter {
    @Inject
    Logger logger;
    @Override
    public void filter(final ContainerRequestContext requestContext) {

        logger.info(requestContext.getUriInfo().getPathSegments().toString());
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = parseAuthToken(authHeader);
        if (!isValid(token)) {
            throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
        }

        String methodOverride = requestContext.getHeaderString("X-Http-Method-Override");
        if (methodOverride != null && !methodOverride.isBlank()) {
            requestContext.setMethod(methodOverride);
        }
    }

    private String parseAuthToken(String httpHeader) {
        if (httpHeader == null || httpHeader.isEmpty()) {
            throw new NotAuthorizedException("Bearer");
        }
        return httpHeader;
    }

    private boolean isValid(String token) {
        return token != null && !token.isBlank();
    }

}

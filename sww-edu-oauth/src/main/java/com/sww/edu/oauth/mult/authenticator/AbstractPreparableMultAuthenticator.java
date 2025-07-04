package com.sww.edu.oauth.mult.authenticator;

import com.sww.edu.oauth.mult.MultAuthentication;
import com.sww.edu.user.api.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractPreparableMultAuthenticator implements MultAuthenticator {

    @Override
    public abstract UserDTO authenticate(MultAuthentication multAuthentication);

    @Override
    public abstract void prepare(MultAuthentication multAuthentication, HttpServletResponse response);

    @Override
    public abstract boolean support(MultAuthentication multAuthentication);

    @Override
    public void complete(MultAuthentication multAuthentication) {

    }
}

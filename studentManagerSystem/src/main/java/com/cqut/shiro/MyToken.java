package com.cqut.shiro;

import java.util.Arrays;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyToken extends UsernamePasswordToken{
	private static final long serialVersionUID = 1L;
	
	private String identify;//用户身份
	
	public MyToken(final String username, final String password, final String identify) {
		super(username, password);
		this.identify = identify;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	@Override
	public String toString() {
		return "MyToken [identify=" + identify + ", getUsername()=" + getUsername() + ", getPassword()="
				+ Arrays.toString(getPassword()) + ", getPrincipal()=" + getPrincipal() + ", getCredentials()="
				+ getCredentials() + ", getHost()=" + getHost() + ", isRememberMe()=" + isRememberMe() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}

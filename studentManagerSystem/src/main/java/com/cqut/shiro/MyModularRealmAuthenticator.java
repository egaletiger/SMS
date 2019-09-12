package com.cqut.shiro;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator{
	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		assertRealmsConfigured();//判断realm是否为空
		MyToken token = (MyToken)authenticationToken;//转为自定义token
		String identify = token.getIdentify();
		
        Collection<Realm> realms = getRealms();//所有的realm
        Collection<Realm> myRealms = new ArrayList<>();//自定义的realm
           
        for(Realm realm : realms) {
        	if(realm.getName().contains(identify)) {
        		myRealms.add(realm);//有可能只添加一个，就是自己定义的那个
        	}
        }
        //让用户去对应的realm中校验，虽然可能符合要求的realm只有一个
        if (myRealms.size() == 1) {
            return doSingleRealmAuthentication(myRealms.iterator().next(), token);
        } else {
            return doMultiRealmAuthentication(myRealms, token);
        }
	}

}

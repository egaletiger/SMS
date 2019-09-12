package com.cqut.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cqut.pojo.Admin;
import com.cqut.pojo.Permission;
import com.cqut.pojo.Teacher;
import com.cqut.pojo.User;
import com.cqut.service.TeacherService;

public class TeacherRealm extends AuthorizingRealm {	
	@Autowired 
	@Qualifier("teacherServiceImpl")
	private TeacherService ts;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "teacherRealm";
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		if(!(user instanceof Teacher)) {
			return null;
		}
		Teacher teacher = (Teacher)user;
		String role = ts.getRole(teacher.getRid());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(role);
		List<String> permissionsName = new ArrayList<>();
		List<Permission> permissions = teacher.getPermissions();
		for(int i = 0; i < permissions.size(); i++) {
			permissionsName.add(permissions.get(i).getPname());
		}
		info.addStringPermissions(permissionsName);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		MyToken token = (MyToken)authenticationToken;
		//获取用户名
		String num = token.getUsername();//这里的username指的是学号/职工号
		Teacher user = ts.findByNum(num);
		
		if(user == null) {
			return null;
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPwd(),
				ByteSource.Util.bytes(user.getNum()),this.getName()); 
		
		return info;
	}
	
	/*当用户权限发生改变时，它会自动调用清理缓存的方法，
    清理缓存，下次再需要权限认证时，他会走重新授权的方法*/
    @Override
    protected void clearCache(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        super.clearCache(subject.getPrincipals());
    }
}

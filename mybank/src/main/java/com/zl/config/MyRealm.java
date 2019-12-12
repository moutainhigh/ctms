package com.zl.config;

import com.zl.pojo.User;
import com.zl.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录认证类
 * @author 徐浩杰
 * @version 1.0 2019-12-12
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService us;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的账号
        String accNo = (String) token.getPrincipal();
        System.out.println("token:"+token);
        User user = us.queryNameByAccNo(accNo);
        if(user == null){
            return null;
        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user,
//                user.getUserPwd(),
//                ByteSource.Util.bytes(user.)
//        );

        return null;
    }
}
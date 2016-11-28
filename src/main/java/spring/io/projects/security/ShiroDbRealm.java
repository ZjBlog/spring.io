package spring.io.projects.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spring.io.projects.entity.User;
import spring.io.projects.repository.UserRepository;
import spring.io.projects.util.SpringContextUtil;

/**
 * 角色管理 并不由spring容器管理
 * 
 * @author Mr.Zhang
 * @Date 2016年11月23日
 * @Email zhangjun150429@qq.com
 */
public class ShiroDbRealm extends AuthorizingRealm {

    public final static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // Primary Principal
        User user = (User) principals.getPrimaryPrincipal();
        // Simple Authorization Info
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        // Role List
        List<String> roleLst = new ArrayList<>();

        // Permission List
        List<String> permissionLst = new ArrayList<String>();

        sai.addRoles(roleLst);
        sai.addStringPermissions(permissionLst);
        return sai;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken customToken = (UsernamePasswordToken) token;

        // 此类并不是由spring容器管理 所以使用 SpringContextUtil.getBean()方法获取UserRepository
        UserRepository userRepository = SpringContextUtil.getBean(UserRepository.class);

        // 1....................................................................
        // username
        String pri = customToken.getUsername();
        // password
        String crd = new String(customToken.getPassword());
        // encrypt
        crd = new Md5Hash(crd).toHex();

        // 通过手机号码获取用户
        User user = userRepository.findByMobile(pri);

        // ## -1 用于回显
        SecurityUtils.getSubject().getSession().setAttribute("login_fail_review_username", customToken.getPrincipal());

        // ##
        // 未知用户
        if (user == null) { throw new UnknownAccountException(); }

        // ##
        // 密码错误
        if (!StringUtils.trimToEmpty(user.getPassWord()).equals(crd)) { throw new IncorrectCredentialsException(); }

        return new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
    }

}

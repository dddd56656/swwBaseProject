package com.sww.edu.oauth.oauth2.userdetail;

import com.sww.edu.oauth.entity.Role;
import com.sww.edu.oauth.entity.UserJwt;
import com.sww.edu.oauth.mult.MultAuthentication;
import com.sww.edu.oauth.mult.MultAuthenticationContext;
import com.sww.edu.oauth.mult.authenticator.MultAuthenticator;
import com.sww.edu.oauth.service.IRoleService;
import com.sww.edu.user.api.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 多方式认证用户服务实现类。
 *
 * 本类是 Spring Security 的 UserDetailsService 实现，用于支持多类型登录（如账号密码、手机号验证码、第三方登录）。
 * 负责根据用户名加载用户信息，并返回包含权限信息的 UserDetails 对象。
 *
 * ⚙️ 当前实现依赖以下组件：
 * - MultAuthenticator：多种认证方式接口（账号密码、短信等）
 * - IRoleService：用户角色服务
 * - UserJwt：自定义 UserDetails 实现类（需实现权限、密码、状态等信息）
 */
@Service("userDetailsService") // 注册为 Spring Bean，beanName 必须为 "userDetailsService"，供 Spring Security 自动使用
@Slf4j // 引入日志打印支持
public class MultUserDetailsService implements UserDetailsService {

    /**
     * 多种认证方式列表（由 Spring 自动注入）。
     * 每种认证方式需实现 MultAuthenticator 接口，并声明支持的类型。
     */
    private List<MultAuthenticator> authenticators;

    /**
     * 注入角色服务，用于获取用户角色。
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 使用 Spring 的方式注入所有认证器的集合。
     * 可用于支持如账号密码、短信登录、钉钉、微信等不同登录模式。
     * @param authenticators 认证器列表（可选）
     */
    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<MultAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    /**
     * 根据用户名加载用户详情信息，供 Spring Security 调用。
     *
     * @param username 用户名（来自登录表单）
     * @return 封装后的用户信息对象（实现 UserDetails 接口）
     * @throws UsernameNotFoundException 用户不存在或认证失败
     */
    @Override
    public UserJwt loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从上下文中获取认证信息（如登录类型、额外参数）
        MultAuthentication multAuthentication = MultAuthenticationContext.get();

        // 如果上下文未设置，创建默认认证对象（通常用于标准账号密码模式）
        if (multAuthentication == null) {
            multAuthentication = new MultAuthentication();
        }

        // 设置用户名，供后续认证器使用
        multAuthentication.setUsername(username);

        // 执行多模式认证逻辑（遍历认证器并匹配）
        UserDTO user = this.authenticate(multAuthentication);

        // 若认证失败，抛出异常（Spring Security 会捕捉并返回错误）
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 构建自定义用户信息对象，包含账户状态和权限信息
        return new UserJwt(
                user.getName(),                      // 登录账号
                user.getPassword(),                 // 密码（由认证器提供）
                !user.getIsDel(),                   // 是否启用（逻辑删除字段）
                user.getAccountNonExpired(),        // 账号是否未过期
                user.getCredentialsNonExpired(),    // 凭证是否未过期
                user.getAccountNonLocked(),         // 账号是否未锁定
                this.obtainGrantedAuthorities(user),// 权限集合
                user.getId().toString()             // 用户ID（作为唯一标识）
        );
    }


    /**
     * 查询用户所有角色，并转换为权限集合。
     *
     * @param user 用户数据对象
     * @return 权限集合（Spring Security 识别的 GrantedAuthority）
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(UserDTO user) {
        try {
            // 根据用户 ID 查询角色集合（角色表中获取）
            Set<Role> roles = roleService.queryUserRolesByUserId(user.getId().toString());

            // 打印角色调试信息（便于排查权限异常）
            log.info("查询角色：userId={}, 返回角色数量={}", user.getId(), roles.size());
            roles.forEach(role ->
                    log.info("角色信息：id={}, code={}, name={}",
                            role.getId(), role.getCode(), role.getName())
            );

            // 将角色转换为 GrantedAuthority（权限码即角色码，必须符合 'ROLE_' 或业务约定）
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getCode()))
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            log.error("加载权限失败，userId=" + user.getId(), e);

            // 如果权限查询异常，默认给予空权限集合，防止系统崩溃
            HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("NONE")); // 降级默认权限
            return grantedAuthorities;
        }
    }

    /**
     * 执行认证器链，依次尝试支持当前认证上下文的认证器，返回认证结果。
     *
     * @param multAuthentication 当前认证上下文
     * @return 用户信息（认证成功），否则返回 null
     */
    private UserDTO authenticate(MultAuthentication multAuthentication) {
        // 遍历所有注册的认证器
        if (this.authenticators != null) {
            for (MultAuthenticator authenticator : authenticators) {
                // 找到支持当前认证方式的认证器
                if (authenticator.support(multAuthentication)) {
                    // 执行认证并返回用户信息
                    return authenticator.authenticate(multAuthentication);
                }
            }
        }
        // 没有认证器支持当前类型 → 认证失败
        return null;
    }
}

package com.cxx.utils;

import com.cxx.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author 陈喜喜
 *
 * Spring security 会将登录用户数据保存在 Session 中。但是，为了使用方便 Spring Security 在此基础上还做了一些改进，
 * 其中最主要的一个变化就是线程绑定。当用户登录成功后,Spring Security 会将登录成功的用户信息保存到 SecurityContextHolder 中。
 * 通过ThreadLocal+策略模式实现
 * SecurityContextHolder 中的数据保存默认景通过Threadlocal 来实现的，使用 Threadlocal 创建的变量只能被当前线程访问，
 * 不能被其他线程访问和修改，也就是用户数据和请求线程绑定在一起。当登录请求处理完毕后，Spring Security会将Security ContextHolder
 * 中的数据拿出来保存到 Session 中，同时将 SecurityContexHolder 中的数据清空。以后每当有请求到来时，Spring Security 就会先从
 * Session 中取出用户登录数据，保存到SecuritvContextHolder 中，方便在该请求的后续处理过程中使用，同时在请求结束时
 * 将 SecurityContextHolder 中的数据拿出来保存到 Session 中，然后将 Security SecurityContextHolder 中的数据清空，
 * 实际上 SecurityContextHolder 中存储是 SecurityContext. 在 SecurityContext 中存储是 Authentication.
 *
 * SecurityContextHolder将所有登录的用户信息都保存，每个登录的用户都可以通过SecurityContextHolder.getContext().getAuthentication()
 * 方式获取当前自己保存的用户信息多用户系统，比如典型的Web系统，整个生命周期可能同时有多个用户在使用。这时候应用需要保存多个
 * SecurityContext（安全上下文），需要利用ThreadLocal进行保存，每个线程都可以利用ThreadLocal获取其自己的SecurityContext，
 * 及安全上下文。
 *
 */
public class SecurityUtils
{

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && id.equals(1L);
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
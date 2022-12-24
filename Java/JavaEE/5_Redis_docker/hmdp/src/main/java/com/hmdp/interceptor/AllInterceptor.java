package com.hmdp.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hmdp.dto.UserDTO;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AllInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public AllInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取 token
        String token = request.getHeader("authorization");
        if(StringUtils.isBlank(token)){
            return true;
        }
        // // 1.获取session
        // HttpSession session = request.getSession();
        // 2.获取用户信息
        String key = RedisConstants.LOGIN_USER_KEY + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        UserDTO user = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        // Object user = session.getAttribute("user");
        // 3.判断用户是否存在
        if(user == null || StringUtils.isBlank(user.getNickName())){
            // 4.用户为空，则返回401异常
            return true;
        }
        // 5.用户不为空，将用户保存到 ThreadLocal中
        UserHolder.saveUser((UserDTO) user);
        // 6.刷新token有效期
        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        // 7.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
 }

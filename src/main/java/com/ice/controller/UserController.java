package com.ice.controller;

/**
 * @Title: UserController
 * @Auth: Ice
 * @Date: 2023/3/27 11:30
 * @Version: 1.0
 * @Desc:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ice.common.R;
import com.ice.entity.User;
import com.ice.service.UserService;
import com.ice.utils.SMSUtils;
import com.ice.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user) {
        //获取手机号
        String phone = user.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            //调用阿里云提供的短信服务API完成发送短信
            SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", phone, code);

            //需要将生成的验证码保存到 redis
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.success("手机验证码短信发送成功");
        }
        return R.error("手机短信发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info("map:{}", map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从Session中获取保存的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);
        //进行验证码比对（页面提交的验证码和Session中保存的验证码比对）
        if (codeInRedis != null && codeInRedis.equals(code)) {

            //如果能够比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                //判断当前手机号是否为新用户，如果是新用户则自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            // Delete the verification code in Redis
            redisTemplate.delete(phone);
            return R.success(user);
        }
        return R.error("登陆失败");
    }

    //用户登出
    @PostMapping("/loginout")
    public R<String> loginout(HttpServletRequest request) {
        //清理Session中保存的当前用户登录的id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }


}

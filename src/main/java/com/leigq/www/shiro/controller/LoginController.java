package com.leigq.www.shiro.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leigq.www.shiro.bean.CacheUser;
import com.leigq.www.shiro.bean.Response;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.service.IUserService;
import com.leigq.www.shiro.util.RedisUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:55
 * @description：登录Controller
 */
@RestController
public class LoginController {
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private IUserService iUserService;

    @Resource
    private Response response;
    
    @Autowired
    private RedisUtil redisUtil;
	
	@Autowired
	private RedisSessionDAO redisSessionDAO;

    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 17:11
     *
     * @return 登录结果
     */
    @ApiOperation(value = "登录接口", notes = "登录接口", httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userName", value = "姓名", required = true, dataType = "string"),
    	@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @RequestMapping("/login")
    public Response login(@RequestParam String userName, @RequestParam String password) {
        log.warn("进入登录.....");

        if (StringUtils.isBlank(userName)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        CacheUser loginUser = iUserService.login(userName, password);
        // 登录成功返回用户信息
        return response.success("登录成功！", loginUser);
    }

    /**
     * description: 更新shiro redis中的用户信息
     * create time: 2019/6/28 17:11
     * @return 结果
     */
    @ApiOperation(value = "更新我的信息接口", notes = "更新我的信息接口", httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userName", value = "姓名", required = true, dataType = "string")
    })
    @RequestMapping("/updateUserInfo")
    public Response updateMyInfo(@RequestParam String userName) {
    	/*获取所有session
    	 * Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		for (Session session : sessions) {
		}*/
    	Session session = SecurityUtils.getSubject().getSession(false);
		SimplePrincipalCollection simplePrincipal = (SimplePrincipalCollection)session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
		User user = (User)simplePrincipal.getPrimaryPrincipal();
		user.setUserName("newName");
		//simplePrincipal.add(sysUser2, sysUser2.getUserName());;
		//session.setAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY", simplePrincipal);
		redisSessionDAO.update(session);
        // 登录成功返回用户信息
        return response.success("操作成功！", user);
    }

    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 17:11
     *
     * @return 登录结果
    @PostMapping("/login")
    public Response login(User user) {
        log.warn("进入登录.....");

        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtils.isBlank(userName)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        CacheUser loginUser = iUserService.login(userName, password);
        // 登录成功返回用户信息
        return response.success("登录成功！", loginUser);
    }
     */

    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 17:37
     */
    @ApiOperation(value = "登出接口", notes = "登出接口", httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping("/logout")
    public Response logOut() {
        iUserService.logout();
        return response.success("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return  
     */
    @ApiOperation(value = "用户未登录接口", notes = "用户未登录接口", httpMethod = "GET")
    @RequestMapping("/un_auth")
    public Response unAuth() {
        return response.failure(HttpStatus.UNAUTHORIZED, "用户未登录！", null);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @ApiOperation(value = "用户无权限接口", notes = "用户无权限接口", httpMethod = "GET")
    @RequestMapping("/unauthorized")
    public Response unauthorized() {
        return response.failure(HttpStatus.FORBIDDEN, "用户无权限！", null);
    }
}

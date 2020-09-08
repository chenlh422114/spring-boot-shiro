package com.leigq.www.shiro.controller;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leigq.www.shiro.bean.Response;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.service.IUserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private Response response;

    /**
     * 用户查询.
     * @return
     */
    @ApiOperation(value = "用户列表接口", notes = "用户列表接口")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @RequiresPermissions("user:view")//权限管理;
    public Response listUsers(){
        List<User> users = iUserService.listUsers();
        return response.success("查询成功！", users);
    }

    /**
     * 用户添加;
     * @return
     */
    @ApiOperation(value = "用户列表接口", notes = "用户列表接口")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/userAdd", method = RequestMethod.POST)
    @RequiresPermissions("user:add")//权限管理;
    public Response userInfoAdd(@RequestBody User user){
    	iUserService.save(user);
        return response.success("操作成功！", user);
    }

    /**
     * 用户删除;
     * @return
     */
    @ApiOperation(value = "删除用户接口", notes = "删除用户接口")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "query")
    })
    @RequestMapping(value = "/userDel", method = RequestMethod.POST)
    @RequiresPermissions("user:del")//权限管理;
    public Response userDel(@RequestParam Long id){
    	System.out.println("删除用户id:" + id);
    	return response.success("操作成功！", id);
    }

}

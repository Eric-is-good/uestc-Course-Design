package eric.chatservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import eric.chatservice.entity.Message;
import eric.chatservice.entity.User;
import eric.chatservice.service.MessageService;
import eric.chatservice.service.UserService;
import eric.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-11
 */
@RestController
@RequestMapping("/chatservice/user")
@CrossOrigin
@Api("有关用户的操作")
public class UserController {

    @Autowired
    private UserService userService;

    private String ID;
    private String USERNAME;
    private String PHOTO;

    @ApiOperation("登录")
    @PostMapping("login")
    public R login(@RequestBody User user) {
        System.out.println(user);
        QueryWrapper<User> wrapperlogin = new QueryWrapper<>();
        wrapperlogin.eq("account",user.getAccount());
        wrapperlogin.eq("password",user.getPassword());
        User user1 = userService.getOne(wrapperlogin);
        if(user1 != null) {
            ID = user1.getId();
            USERNAME = user1.getUsername();
            PHOTO = user1.getPhoto();
            return R.ok().data("token","admin");
        }
        else{
            return R.error();
        }
    }

    @ApiOperation("注销")
    @GetMapping("logout")
    public R login() {
        return R.ok();
    }

    @ApiOperation("登录信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles",ID).data("name",USERNAME).data("avatar",PHOTO);
    }


    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public R addUser(@RequestBody User user){
        String account = user.getAccount();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        User user1 = userService.getOne(wrapper);
        if(user1 == null) {
            boolean save = userService.save(user);
            if (save) {
                return R.ok();
            } else {
                return R.error();
            }
        }else {
            System.out.println("用户已存在");
            return R.error().message("用户已存在,请更改账号");
        }
    }


    @ApiOperation("修改用户")
    @PostMapping("updateUser")
    public R updateUser(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        if(flag) {
            System.out.println("修改了用户信息");
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("上传头像的方法")
    @PostMapping
    public R uploadAvatarOssFile(MultipartFile file) {
        String url = userService.uploadFileAvatar(file);
        if(url != null) {
            return R.ok().data("url",url);
        } else {
            return R.error();
        }
    }

    @ApiOperation("获取用户头像")
    @GetMapping("getPhoto/{id}")
    public R getPhoto(@PathVariable String id) {
        User user = userService.getById(id);
        String url = user.getPhoto();
        return R.ok().data("photo", url);
    }


    @ApiOperation("获取用户信息")
    @GetMapping("getUser/{userId}")
    public R getUser(@PathVariable String userId) {
        System.out.println("获取了用户信息");
        User user = userService.getById(userId);
        return R.ok().data("user", user);
    }





}


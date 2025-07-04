package com.sww.edu.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sww.edu.user.api.UserRemoteService;
import com.sww.edu.user.api.dto.UserDTO;
import com.sww.edu.user.api.param.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sww
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserRemoteService userRemoteService;

    @GetMapping("/getUserById")
    public UserDTO getUserById(@RequestParam("userId") Integer userId) {
        return this.userRemoteService.getUserById(userId);
    }

    @GetMapping("/getUserByPhone")
    public UserDTO getUserByPhone(@RequestParam("phone") String phone) {
        return this.userRemoteService.getUserByPhone(phone);
    }

    @GetMapping("/isRegister")
    public boolean isRegister(@RequestParam("phone") String phone) {
        return this.userRemoteService.isRegister(phone);
    }

    @GetMapping("/getPagesCourses")
    public Page<UserDTO> getPagesUsers(@RequestParam("currentPage") Integer currentPage,
                                       @RequestParam("pageSize") Integer pageSize) {
        return null;
    }

    @PostMapping(value = "/saveUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return this.userRemoteService.saveUser(userDTO);
    }


    @PostMapping(value = "/updateUser")
    public boolean updateUser(@RequestBody UserDTO userDTO) {
        return this.userRemoteService.updateUser(userDTO);
    }

    @GetMapping("/isUpdatedPassword")
    public boolean isUpdatedPassword(@RequestParam("userId") Integer userId) {
        return this.userRemoteService.isUpdatedPassword(userId);
    }

    @PostMapping("/setPassword")
    public boolean setPassword(@RequestParam("userId") Integer userId, @RequestParam("password") String password, @RequestParam("configPassword") String configPassword) {
        return this.userRemoteService.setPassword(userId, password, configPassword);
    }

    @PostMapping("/updatePassword")
    boolean updatePassword(@RequestParam("userId") Integer userId, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("configPassword") String configPassword) {
        return this.userRemoteService.updatePassword(userId, oldPassword, newPassword, configPassword);
    }

    /**
     * 后台查询课程列表的接口
     */
    @PostMapping("/getUserPages")
    public Page<UserDTO> getQueryCourses(@RequestBody UserQueryParam userQueryParam) {
        return this.userRemoteService.getUserPages(userQueryParam);
    }

    @PostMapping("/forbidUser")
    public boolean forbidUser(@RequestParam Integer userId) {
        return this.userRemoteService.forbidUser(userId);
    }
}

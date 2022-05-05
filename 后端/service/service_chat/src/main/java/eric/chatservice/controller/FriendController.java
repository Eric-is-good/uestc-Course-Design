package eric.chatservice.controller;


import eric.chatservice.service.FriendService;
import eric.chatservice.service.MessageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-11
 */
@RestController
@RequestMapping("/chatservice/friend")
@CrossOrigin
@Api("当 A 关注 B 后才有这个")
public class FriendController {

}


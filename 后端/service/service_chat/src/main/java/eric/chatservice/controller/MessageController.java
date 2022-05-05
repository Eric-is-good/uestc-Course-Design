package eric.chatservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import eric.chatservice.entity.Message;
import eric.chatservice.entity.Reply;
import eric.chatservice.entity.User;
import eric.chatservice.service.MessageService;
import eric.chatservice.service.ReplyService;
import eric.chatservice.service.UserService;
import eric.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Member;
import java.sql.Date;
import java.util.ArrayList;
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
@RequestMapping("/chatservice/message")
@CrossOrigin
@Api("和说说有关")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReplyService replyService;

    @ApiOperation("上传图片的方法")
    @PostMapping("upPic")
    public R uploadPhotoOssFile(MultipartFile file) {
        String url = userService.uploadFileAvatar(file);
        if(url != null) {
            System.out.println("图片上传成功"+ url);
            return R.ok().data("url",url);
        } else {
            System.out.println("图片上传失败");
            return R.error();
        }
    }


    @ApiOperation("添加消息")
    @PostMapping("addMessage")
    public R addMessage(@RequestBody Message message){
        Date date = new Date(System.currentTimeMillis());
        message.setTime(date);
        message.setLikeNum(0);
        message.setReplyNum(0);
        message.setForwardNum(0);
        message.setIsReply(0);
        message.setDel(0);
        System.out.println(message);
        boolean save = messageService.save(message);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("获取所有消息")
    @GetMapping("getAllMessageList")
    public R getAllMessageList(){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("is_reply",0);
        wrapper.eq("del",0);
        List<Message> list = messageService.list(wrapper);
        return R.ok().data("items",list);
    }

    @ApiOperation("分页获取消息")
    @GetMapping("getPageMessageList/{current}")
    public R getPageMessageList(@PathVariable long current) {
        //创建page对象
        System.out.println("current:"+current);
        long limit = 6;
        Page<Message> pageMessage = new Page<>(current, limit);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("is_reply",0);
        wrapper.eq("del",0);
        wrapper.orderByDesc("time");
        messageService.page(pageMessage,wrapper);
        long total = pageMessage.getTotal();
        List<Message> records = pageMessage.getRecords();
        for(int i = 0; i < records.size(); i++){
            String userid = records.get(i).getUserId();
            String username = userService.getById(userid).getUsername();
            records.get(i).setUsername(username);
            String userPhoto = userService.getById(userid).getPhoto();
            records.get(i).setUserPhoto(userPhoto);
        }
        return R.ok().data("total",total).data("list",records);
    }




    @ApiOperation("获取消息详情")
    @GetMapping("getMessageDetail/{messageId}")
    public R getMessageDetail(@PathVariable String messageId){
        // 先获取本消息
        Message message = messageService.getById(messageId);


        String userid = message.getUserId();
        String username = userService.getById(userid).getUsername();
        message.setUsername(username);
        String userPhoto = userService.getById(userid).getPhoto();
        message.setUserPhoto(userPhoto);


        // 获取所有回复
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.eq("host_message_id",messageId);
        wrapper.eq("del",0);
        wrapper.orderByDesc("time");
        List<Reply> replyList = replyService.list(wrapper);
        System.out.println("----------------------");
        List<Message> list = new ArrayList<>();

        for(int i = 0; i < replyList.size(); i++){
            Message replyMessage = messageService.getById(replyList.get(i).getReplyerMessageId());

            String userid1 = replyMessage.getUserId();
            String username1 = userService.getById(userid1).getUsername();
            replyMessage.setUsername(username1);
            String userPhoto1 = userService.getById(userid1).getPhoto();
            replyMessage.setUserPhoto(userPhoto1);

            list.add(replyMessage);
        }

        System.out.println("message:"+ message);
        System.out.println("replyList:"+ list);
        return R.ok().data("message",message).data("list",list);
    }


    @ApiOperation("添加回复,前端要返回被回复的 Message 的 id")
    @PostMapping("addReply")
    public R addReply(@RequestBody Message message){
        // 添加消息
        Date date = new Date(System.currentTimeMillis());
        message.setTime(date);
        message.setLikeNum(0);
        message.setReplyNum(0);
        message.setForwardNum(0);
        message.setIsReply(1);
        message.setDel(0);
        System.out.println(message);
        boolean save1 = messageService.save(message);
        System.out.println(message);
        // 添加reply
        Reply reply = new Reply();

        Message message1 = messageService.getById(message.getFromMessage());
        String hostId = message1.getUserId();

        reply.setHostId(hostId);
        reply.setHostMessageId(message.getFromMessage());

        reply.setReplyerId(message.getUserId());
        reply.setReplyerMessageId(message.getId());
        reply.setDel(0);
        reply.setTime(date);

        boolean save2 = replyService.save(reply);
        System.out.println(reply);
        if(save1 && save2) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("删除说说或回复")
    @DeleteMapping("delMessage/{messageId}")
    public R delMessage(@PathVariable String messageId){
        System.out.println("进入删除操作");
        Message message = messageService.getById(messageId);
        if(message.getIsReply() == 0){
            // 是说说
            message.setDel(1);
            messageService.updateById(message);
            List<Reply> replyList = replyService.list(new QueryWrapper<Reply>().eq("host_message_id",messageId));
            for (Reply reply : replyList) {
                reply.setDel(1);
                replyService.updateById(reply);
            }
            List<Message> messageList = messageService.list(new QueryWrapper<Message>().eq("from_message",messageId));
            for (Message message1 : messageList) {
                message1.setDel(1);
                messageService.updateById(message1);
            }
            return R.ok();
        }else {
            // 是回复
            message.setDel(1);
            messageService.updateById(message);
            String hostMessageId = message.getFromMessage();
            Reply reply = replyService.getOne(new QueryWrapper<Reply>().eq("replyer_message_id",messageId)
                    .eq("host_message_id",hostMessageId));
            reply.setDel(1);
            replyService.updateById(reply);
            return R.ok();
        }
    }



}


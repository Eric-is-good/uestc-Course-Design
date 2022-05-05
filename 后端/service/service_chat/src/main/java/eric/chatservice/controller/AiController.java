package eric.chatservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import eric.chatservice.ai.Getfive;
import eric.chatservice.ai.forjava.Jiuge;
import eric.chatservice.entity.Message;
import eric.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/chatservice/ai")
@CrossOrigin
public class AiController {

    public static class FivePoem{
        public String title;
    }

    @ApiOperation("获取五言律诗")
    @PostMapping("getFivePoem")
    public R getFivePoem(@RequestBody FivePoem fivePoem){
        System.out.println(fivePoem.title);
        Jiuge jiuge = new Jiuge();
        String peom = jiuge.getPoem(fivePoem.title);
        System.out.println("ok=====================");
        System.out.println(peom);
        return R.ok().data("peom",peom);
    }



}


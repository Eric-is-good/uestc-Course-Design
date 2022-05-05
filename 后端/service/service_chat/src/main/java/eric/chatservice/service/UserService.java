package eric.chatservice.service;

import eric.chatservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-11
 */
public interface UserService extends IService<User> {

    String uploadFileAvatar(MultipartFile file);
}

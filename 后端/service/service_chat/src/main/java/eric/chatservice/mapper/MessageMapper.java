package eric.chatservice.mapper;

import eric.chatservice.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-11
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}

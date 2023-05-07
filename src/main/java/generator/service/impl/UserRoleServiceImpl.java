package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.UserRole;
import generator.service.UserRoleService;
import generator.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2023-05-07 18:25:19
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}





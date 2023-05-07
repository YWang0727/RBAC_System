package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.Permission;
import generator.service.PermissionService;
import generator.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Sarah Wang
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-05-07 18:25:05
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}





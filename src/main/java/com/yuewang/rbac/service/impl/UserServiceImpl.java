package com.yuewang.rbac.service.impl;

//StrUtil: offers functions like checking if a string is blank or empty, trimming whitespace, joining strings, replacing parts of a string, and many more
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
//JSONUtil: provides methods to parse JSON strings, convert Java objects to JSON, and vice versa. It also supports operations like extracting values from JSON, modifying JSON objects, formatting JSON strings, and more
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.exception.ApiException;
import com.yuewang.rbac.model.VO.UserDetailsVO;
import com.yuewang.rbac.model.VO.UserPageVO;
import com.yuewang.rbac.model.VO.UserVO;
import com.yuewang.rbac.model.entity.User;
import com.yuewang.rbac.model.param.LoginParam;
import com.yuewang.rbac.model.param.UserParam;
import com.yuewang.rbac.security.JwtManager;
import com.yuewang.rbac.service.PermissionService;
import com.yuewang.rbac.service.RoleService;
import com.yuewang.rbac.service.UserService;
import com.yuewang.rbac.mapper.UserMapper;
import com.yuewang.rbac.util.SecurityContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
* @author Yue Wang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-07 18:07:33
*/
@Slf4j
@Service  //to note an implementation class in MyBatis - which is a bean
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService, UserDetailsService {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User getUserByUsername(String username) throws ApiException {
        if(StrUtil.isBlank(username)){
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        return this.lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public UserVO login(LoginParam loginParam) throws ApiException {
        // Verify user from database
        User user = this.lambdaQuery()  //a lambda style query constructor provided by MyBatisPlus for building SQL query language
                .eq(StrUtil.isNotBlank(loginParam.getUsername()), User::getUsername, loginParam.getUsername())
//                .eq(StrUtil.isNotBlank(loginParam.getPhone()), User::getPhone, loginParam.getPhone())
                .one();  //return one data if it is not null

        // Throw error if user or password is wrong
        if(user == null || !passwordEncoder.matches(loginParam.getPassword(), user.getPassword())){
            throw new ApiException(ResultCode.VALIDATE_FAILED, "Username or password is incorrect！");
        }
        // Generate token, get and put user permissions in UserVO object
        UserVO userVO = new UserVO();
        userVO.setId(user.getId()).setUsername(user.getUsername())
                .setToken(JwtManager.generate(user.getUsername()))  //generate a JWT for authentication and authorisation
                .setPermissionIds(permissionService.getIdsByUserId(user.getId()));
        return userVO;
    }

    @Override
    public void createUser(UserParam param){
        if (lambdaQuery().eq(User::getUsername, param.getUsername()).one() != null) {
            throw new ApiException(ResultCode.FAILED,"Username already exists.");
        }
        User user = new User();
        // Default password = username
        user.setUsername(param.getUsername()).setPassword(passwordEncoder.encode(param.getUsername()));        save(user);  //save(): from MyBatis, do INSERT, save the entity to database
        if(CollectionUtil.isEmpty(param.getRoleIds())){
            return;
        }
        //if not empty, add roles to table [user-role]
        roleService.insertRolesByUserId(user.getId(), param.getRoleIds());
    }

    @Override
    public Set<Long> myPermission() throws ApiException {  // get current user's permission set
        Long currentUserId = SecurityContextUtil.getCurrentUserId();
        return permissionService.getIdsByUserId(currentUserId);
    }

//    // check if the username extracted from the JWT token is valid and consistent with the currently logged-in user
//    private static User checkTokenWithUsername(String username) throws ApiException {
//        // cast json string into object, and get the username from the token from front end
//        username = (String) JSONUtil.parseObj(username).get("username");
//        if(StrUtil.isBlank(username)){
//            throw new ApiException(ResultCode.VALIDATE_FAILED, "Invalid username.");
//        }
//        // get the current user from the security context and compares it with the username from JWT token
//        User user = SecurityContextUtil.getCurrentUser();
//        if(!username.equals(user.getUsername())){
//            throw new ApiException(ResultCode.VALIDATE_FAILED, "The currently logged-in user is not consistent with the user in the token.");
//        }
//        return user;
//    }

    @Override
    public String updateToken() throws ApiException {
        String username = SecurityContextUtil.getCurrentUser().getUsername();
        //JwtManager.generate(): takes a username and generates a new JWT token with an expiration time and other claims
        //refresh the JWT token for the current user
        return JwtManager.generate(username);
    }

    @Override
    public void update(UserParam param){
        updateRoles(param);
    }

    private void updateRoles(UserParam param){
        //delete the old user role
        roleService.removeByUserId(param.getId());
        //if new role ids is empty, return directly
        if(CollectionUtil.isEmpty(param.getRoleIds())){
            return;
        }
        //else, add new user roles to the user
        roleService.insertRolesByUserId(param.getId(), param.getRoleIds());
    }

    @Override
    public IPage<UserPageVO> selectPage(Page<UserPageVO> page){
        QueryWrapper<UserPageVO> queryWrapper = new QueryWrapper<>();
        Long myId = SecurityContextUtil.getCurrentUserId();
        // set condition: exclude super admin (id:1) and current user
        queryWrapper.ne("id", myId).ne("id", 1);
        // Get page info
        IPage<UserPageVO> pages = baseMapper.selectPage(page, queryWrapper);
        // Get roles for all users
        for (UserPageVO vo : pages.getRecords()) {
            vo.setRoleIds(roleService.getIdsByUserId(vo.getId()));
        }
        return pages;
    }

    // to implement UserDetailsService interface from Spring Security
    // get user info from database by username, and convert it into a UserDetails object for Spring Security using
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Get user by username
        User user = this.getUserByUsername(username);
        // Get permissionIds and tranfer them to `SimpleGrantedAuthority` Object
        Set<SimpleGrantedAuthority> authorities = permissionService.getIdsByUserId(user.getId())
                .stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return new UserDetailsVO(user, authorities);
    }

}





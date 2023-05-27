package com.yuewang.rbac.config;

import cn.hutool.core.collection.CollectionUtil;
import com.yuewang.rbac.annotation.Auth;
import com.yuewang.rbac.model.entity.Permission;
import com.yuewang.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ApplicationStartUp
 * @Description batch insert API info with annotation into db
 * @Author Yue Wang
 * @Date 2023/5/14 21:01
 **/
@Component
public class ApplicationStartUp implements ApplicationRunner {

    @Autowired
    private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;
    @Autowired
    private PermissionService permissionService;


    @Override
    public void run(ApplicationArguments args) throws Exception{
        // scan and get all interfaces which need permission to access
        List<Permission> list = getAuthResources();
        // delete all operation permission type resources first (No foreign key in the db, otherwise will unsuccessful)
        permissionService.deletePermissionByType(1);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        // add permission data to db
        permissionService.insertPermissions(list);
    }

    private List<Permission> getAuthResources(){
        List<Permission> list = new LinkedList<>();
        // get all API info, and iterate all
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((info, handlerMethod) -> {
            // get class/module's permission annotation
            Auth moduleAuth = handlerMethod.getBeanType().getAnnotation(Auth.class);
            // get API method's permission annotation
            Auth methodAuth = handlerMethod.getMethod().getAnnotation(Auth.class);
            // both annotation needs to be not null
            if (moduleAuth == null || methodAuth == null) {
                return;
            }

            // get the request method of the API method
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            // if it marked more than one request methods, then can't recognize
            if (methods.size() != 1) {
                return;
            }
            // put the request method and path together with ":", e.g. GET:/user/{id}、POST:/user/{id}
            String url = methods.toArray()[0] + ":" + info.getPatternsCondition().getPatterns().toArray()[0];
            // combine permission name\path\type into the permission object and add to list
            Permission permission = new Permission();
            permission.setType(1)
                    .setUrl(url)
                    .setName(methodAuth.name())
                    .setId(moduleAuth.id() + methodAuth.id());
            list.add(permission);
        });
        return list;

    }
}

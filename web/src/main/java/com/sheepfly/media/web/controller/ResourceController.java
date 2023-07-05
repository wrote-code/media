package com.sheepfly.media.web.controller;


import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.common.http.ResponseData;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Controller
@RequestMapping(value = "/resource", produces = "application/json;charset=utf-8")
public class ResourceController {
    @Autowired
    private IResourceService service;

    @PostMapping("/queryResourceList")
    @ResponseBody
    public ProTableObject<ResourceVo> queryResourceList(@RequestBody ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form) {

        return service.queryResourceVoList(form);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseData<ResourceVo> add(@RequestBody @Validated ResourceData resourceData)
            throws InvocationTargetException, IllegalAccessException {
        Resource resource = new Resource();
        BeanUtils.copyProperties(resource, resourceData);
        Date date = new Date();
        resource.setCreateTime(date);
        resource.setSaveTime(date);
        File file = new File(resource.getDir());
        if (file.isFile()) {
            resource.setFilename(file.getName());
            resource.setDir(file.getParentFile().getAbsolutePath());
        } else {
            resource.setDir(file.getAbsolutePath());
        }
        Resource savedResource = service.save(resource);
        return ResponseData.success(savedResource);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseData<ResourceVo> delete(@RequestBody @NotNull String id) throws BusinessException {
        Resource resource = service.safeLogicDeleteById(id, Resource.class, ErrorCode.DELETE_NOT_EXIST_DATA);
        return ResponseData.success(resource);
    }
}

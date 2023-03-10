package com.sheepfly.media.service;

import com.sheepfly.media.entity.ResourceType;
import com.sheepfly.media.repository.ResourceTypeRepository;

/**
 * <p>
 * 资源和类型关联 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IResourceTypeService extends BaseJpaService<ResourceType, String, ResourceTypeRepository> {

}

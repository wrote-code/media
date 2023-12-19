package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sheepfly.media.dataaccess.mapper.AuthorMapper;
import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.dataaccess.entity.Resource_;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.filter.AuthorFilter;
import com.sheepfly.media.dataaccess.repository.AuthorRepository;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.service.base.IAuthorService;
import com.sheepfly.media.dataaccess.vo.AuthorVo;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 创作人员 服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Service
public class AuthorServiceImpl extends BaseJpaServiceImpl<Author, String, AuthorRepository> implements IAuthorService {
    @Autowired
    private AuthorMapper mapper;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public ProTableObject<AuthorVo> queryForAuthorList(
            ProComponentsRequestVo<AuthorFilter, AuthorFilter, AuthorFilter> vo) throws BusinessException {
        AuthorFilter params = vo.getParams();
        Page<Object> page = PageHelper.startPage(params.getCurrent(), params.getPageSize());
        List<AuthorVo> authorList = mapper.queryAuthorVoList(vo);
        return ProTableObject.success(authorList, page.getTotal());
    }

    @Override
    public boolean isAuthorCanBeDelete(String id) {
        long count = resourceRepository.count(
                (root, query, builder) -> builder.equal(root.get(Resource_.AUTHOR_ID), id));
        return count == 0;
    }
}

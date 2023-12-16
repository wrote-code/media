package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.repository.TagsRepository;

public interface TagService extends BaseJpaService<Tag, String, TagsRepository> {
}

package com.sheepfly.media.repository;

import com.sheepfly.media.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

/**
 * 作者仓库。
 *
 * @author wrote-code
 */
public interface AuthorRepository extends JpaRepository<Author, String>, JpaSpecificationExecutor<Author> {
    int countBySiteId(@Param("siteId") String siteId);
}

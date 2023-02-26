package com.sheepfly.media.repository;

import com.sheepfly.media.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 网站仓库。
 *
 * @author sheepfly
 */
public interface SiteRepository extends JpaRepository<Site, String>, JpaSpecificationExecutor<Site> {
}

package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.common.util.BeanCopier;
import com.sheepfly.media.dataaccess.entity.baseinterface.Copy;
import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import lombok.Getter;
import lombok.Setter;import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Getter
@Setter
@ToString
@Table(schema = "MEDIA", name = "RESOURCE")
public class Resource implements Serializable, EntityInterface, LogicDelete, Copy {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 文件名
     */
    @Column(name = "FILENAME", nullable = false, length = 90)
    private String filename;

    /**
     * 作者id
     */
    @Column(name = "AUTHOR_ID", nullable = false, length = 19)
    private String authorId;

    /**
     * 专辑id
     */
    @Column(name = "ALBUM_ID", length = 19)
    private String albumId;

    /**
     * 创建时间。
     *
     * <p>创建时间是资源生成时间。对视频和照片来说，创建时间就是拍摄时间。如果是新闻，则创建时间
     * 是新闻发生时间，而不是发稿时间，不管是视频稿还是文字稿。</p>
     *
     * <p>默认等于保存时间。</p>
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 保存时间（入库时间）。
     *
     * <p>资源保存到数据库的时间。</p>
     */
    @Column(name = "SAVE_TIME", nullable = false)
    private Date saveTime;

    /**
     * 是否有效，用于逻辑删除。
     *
     * <p>0未删除1已删除。</p>
     */
    @Column(name = "DELETE_STATUS", nullable = false)
    private Integer deleteStatus;
    /**
     * 目录代码。
     *
     * <p>{@link Directory}</p>
     */
    @Column(name = "DIR_CODE", nullable = false)
    private Long dirCode;
    /**
     * 封面文件id。
     */
    @Column(name = "COVER_ID", nullable = false)
    private String coverId;

    @Override
    public void copyFrom(Object source) {
        BeanCopier.copyFrom(source, this);
    }

    @Override
    public void copyTo(Object target) {
        BeanCopier.copyTo(this, target);
    }
}

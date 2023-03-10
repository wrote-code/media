package com.sheepfly.media.vo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 资源。
 *
 * @author sheepfly
 */
public class ResourceVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 资源目录
     */
    private String dir;

    /**
     * 作者。
     */
    private AuthorVo authorVo;

    /**
     * 专辑。
     */
    private AlbumVo albumVo;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    @Override
    public String toString() {
        return "ResourceVo{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", dir='" + dir + '\'' +
                ", author=" + authorVo +
                ", albumVo=" + albumVo +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public AuthorVo getAuthorVo() {
        return authorVo;
    }

    public void setAuthorVo(AuthorVo authorVo) {
        this.authorVo = authorVo;
    }

    public AlbumVo getAlbumVo() {
        return albumVo;
    }

    public void setAlbumVo(AlbumVo albumVo) {
        this.albumVo = albumVo;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }
}

package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 创作人员
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@TableName("AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 用户在站点注册时的id
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 注册站点ID
     */
    @TableField("SITE")
    private String site;

    /**
     * 主页
     */
    @TableField("HOMEPAGE")
    private String homepage;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDate updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
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

    @Override
    public String toString() {
        return "Author{" +
        "id=" + id +
        ", userId=" + userId +
        ", username=" + username +
        ", site=" + site +
        ", homepage=" + homepage +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

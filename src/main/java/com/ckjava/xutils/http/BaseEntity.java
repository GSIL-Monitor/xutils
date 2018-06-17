package com.ckjava.xutils.http;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 实体类共同属性
 *
 */
@ApiModel
public class BaseEntity {

    @ApiModelProperty("表的主键")
    protected Long id;	  //流水号

    @ApiModelProperty("备注")
    protected String remarks;	  //备注
    @ApiModelProperty("创建人")
    protected String createUser;	  //创建人
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createDate;	  //创建时间
    @ApiModelProperty("更新人")
    protected String updateUser;	  //更新人
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateDate;	  //更新时间
    @ApiModelProperty(hidden = true, value = "删除标识 1 删除 0 正常")
    protected String delFlag;	  //删除标识 1 删除 0 正常

    @ApiModelProperty(hidden = true, value = "用户名")
    protected transient String username;
    @ApiModelProperty(hidden = true, value = "排序字段")
    protected transient String orderBy;
    @ApiModelProperty(hidden = true, value = "排序方式 ")
    protected transient Boolean desc;
    @ApiModelProperty(hidden = true, value = "起始行数")
    protected transient Integer start;
    @ApiModelProperty(hidden = true, value = "每页数据大小")
    protected transient Integer pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append("id=").append(id);
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", createUser='").append(createUser).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser='").append(updateUser).append('\'');
        sb.append(", updateDate=").append(updateDate);
        sb.append(", delFlag='").append(delFlag).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", orderBy='").append(orderBy).append('\'');
        sb.append(", desc=").append(desc);
        sb.append(", start=").append(start);
        sb.append(", pageSize=").append(pageSize);
        sb.append('}');
        return sb.toString();
    }
}
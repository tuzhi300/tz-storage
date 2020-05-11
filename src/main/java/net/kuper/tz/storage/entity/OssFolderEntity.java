package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-04-02 12:08:53
 */
@ApiModel(value = "")
@Data
public class OssFolderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 用户类型：1系统管理员，2用户
     */
    @ApiModelProperty(value = "用户类型：1系统管理员，2用户")
    private Integer userType;
    /**
     * 拥有用户ID
     */
    @ApiModelProperty(value = "拥有用户ID")
    private String userId;
    /**
     * 上级目录
     */
    @ApiModelProperty(value = "上级目录")
    private String parentId;
    /**
     * 文件夹名称
     */
    @ApiModelProperty(value = "文件夹名称")
    private String folder;
    /**
     * 文件夹类型，如系统创建的相册，自定义创建
     */
    @ApiModelProperty(value = "文件夹类型，如系统创建的相册，自定义创建")
    private String folderType;
    /**
     * 权限：0只读，2可写
     */
    @ApiModelProperty(value = "权限：0只读，2可写")
    private Integer rightType;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 修改用户
     */
    @ApiModelProperty(value = "修改用户")
    private String updateUserId;
    /**
     * 修改用户类型
     */
    @ApiModelProperty(value = "修改用户类型")
    private Integer updateUserType;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户")
    private String createUserId;
    /**
     * 创建用户类型
     */
    @ApiModelProperty(value = "创建用户类型")
    private Integer createUserType;
    /**
     * 删除：>=1已删除，0否
     */
    @ApiModelProperty(value = "删除：>=1已删除，0否")
    private Date deleteTime;

}

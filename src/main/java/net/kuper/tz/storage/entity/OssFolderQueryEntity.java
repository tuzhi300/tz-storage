package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.kuper.tz.core.mybatis.PaginationQuery;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
/**
 * 分页查询
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-04-02 12:08:53
 */
@ApiModel(value = "分页查询")
@Data
public class OssFolderQueryEntity extends PaginationQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户类型：1系统管理员，2用户
    */
    @ApiModelProperty(value = "用户类型：1系统管理员，2用户", required = true, position = 1 )
    private Integer userType;
    /**
    * 拥有用户ID
    */
    @Length(max = 45 ,message = "拥有用户ID不能超过45个字符")
    @ApiModelProperty(value = "拥有用户ID，最大长度：45", required = true, position = 2 )
    private String userId;
    /**
    * 上级目录
    */
    @Length(max = 45 ,message = "上级目录不能超过45个字符")
    @ApiModelProperty(value = "上级目录，最大长度：45", position = 3 )
    private String parentId;
    /**
    * 文件夹名称
    */
    @Length(max = 50 ,message = "文件夹名称不能超过50个字符")
    @ApiModelProperty(value = "文件夹名称，最大长度：50", required = true, position = 4 )
    private String folder;
    /**
    * 文件夹类型，如系统创建的相册，自定义创建
    */
    @Length(max = 50 ,message = "文件夹类型，如系统创建的相册，自定义创建不能超过50个字符")
    @ApiModelProperty(value = "文件夹类型，如系统创建的相册，自定义创建，最大长度：50", required = true, position = 5 )
    private String folderType;
    /**
    * 权限：0只读，2可写
    */
    @ApiModelProperty(value = "权限：0只读，2可写，默认值：1", required = true, position = 6 )
    private Integer rightType;
    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间", position = 7 )
    private Date updateTime;
    /**
    * 修改用户
    */
    @Length(max = 45 ,message = "修改用户不能超过45个字符")
    @ApiModelProperty(value = "修改用户，最大长度：45", position = 8 )
    private String updateUserId;
    /**
    * 修改用户类型
    */
    @ApiModelProperty(value = "修改用户类型", position = 9 )
    private Integer updateUserType;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 10 )
    private Date createTime;
    /**
    * 创建用户
    */
    @Length(max = 45 ,message = "创建用户不能超过45个字符")
    @ApiModelProperty(value = "创建用户，最大长度：45", required = true, position = 11 )
    private String createUserId;
    /**
    * 创建用户类型
    */
    @ApiModelProperty(value = "创建用户类型", required = true, position = 12 )
    private Integer createUserType;
    /**
    * 删除：>=1已删除，0否
    */
    @ApiModelProperty(value = "删除：>=1已删除，0否，默认值：0", required = true, position = 13 )
    private Date deleteTime;

}

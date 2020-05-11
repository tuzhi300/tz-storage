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
 * @date 2020-03-30 11:55:49
 */
@ApiModel(value = "分页查询")
@Data
public class OssFileQueryEntity extends PaginationQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = 45 ,message = "不能超过45个字符")
    @ApiModelProperty(value = "，最大长度：45", required = true, position = 1 )
    private String ossId;
    @Length(max = 45 ,message = "不能超过45个字符")
    @ApiModelProperty(value = "，最大长度：45", required = true, position = 2 )
    private String folderId;
    /**
    * 文件显示名称
    */
    @Length(max = 255 ,message = "文件显示名称不能超过255个字符")
    @ApiModelProperty(value = "文件显示名称，最大长度：255", required = true, position = 3 )
    private String displayName;
    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间", position = 4 )
    private Date updateTime;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 5 )
    private Date createTime;
    /**
    * 删除：0未删除，>=1已删除
    */
    @ApiModelProperty(value = "删除：0未删除，>=1已删除，默认值：0", required = true, position = 6 )
    private Date deleteTime;

}

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
 * @date 2020-03-30 11:55:49
 */
@ApiModel(value = "")
@Data
public class OssFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String ossId;
    @ApiModelProperty(value = "")
    private String folderId;
    /**
     * 文件显示名称
     */
    @ApiModelProperty(value = "文件显示名称")
    private String displayName;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 删除：0未删除，>=1已删除
     */
    @ApiModelProperty(value = "删除：0未删除，>=1已删除")
    private Date deleteTime;

}

package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.kuper.tz.core.validator.group.AddGroup;
import net.kuper.tz.core.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 变更
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
@ApiModel(value = "变更")
@Data
public class OssFileUpdateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id为必填", groups = {UpdateGroup.class})
    @ApiModelProperty(value = "，最大长度：45", required = true, position = 0 )
    private String id;
    @NotNull(message = "为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 45 ,message = "不能超过45个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "，最大长度：45", required = true, position = 1 )
    private String ossId;
    @NotNull(message = "为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 45 ,message = "不能超过45个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "，最大长度：45", required = true, position = 2 )
    private String folderId;
    /**
     * 文件显示名称
     */
    @NotNull(message = "文件显示名称为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "文件显示名称不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 255 ,message = "文件显示名称不能超过255个字符", groups = {AddGroup.class, UpdateGroup.class})
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
    @NotNull(message = "创建时间为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 5 )
    private Date createTime;
    /**
     * 删除：0未删除，>=1已删除
     */
    @NotNull(message = "删除：0未删除，>=1已删除为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "删除：0未删除，>=1已删除，默认值：0", required = true, position = 6 )
    private Date deleteTime;

}

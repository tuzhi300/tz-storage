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
 * 变更文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-04-02 09:05:54
 */
@ApiModel(value = "变更文件上传")
@Data
public class OssUpdateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id为必填", groups = {UpdateGroup.class})
    @ApiModelProperty(value = "，最大长度：45", required = true, position = 0 )
    private String id;
    /**
     * 路径
     */
    @NotNull(message = "路径为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "路径不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 125 ,message = "路径不能超过125个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "路径，最大长度：125", required = true, position = 1 )
    private String key;

    @NotNull(message = "物理目录为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "物理目录不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 50 ,message = "物理目录不能超过50个字符")
    @ApiModelProperty(value = "物理目录，最大长度：50", required = true, position = 1 )
    private String folder;
    /**
     * 文件格式
     */
    @NotNull(message = "文件格式为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "文件格式不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 20 ,message = "文件格式不能超过20个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "文件格式，最大长度：20", required = true, position = 2 )
    private String format;
    /**
     * 大小
     */
    @NotNull(message = "大小为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "大小", required = true, position = 3 )
    private Long size;
    /**
     * 文件MD5
     */
    @NotNull(message = "文件MD5为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "文件MD5不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 64 ,message = "文件MD5不能超过64个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "文件MD5，最大长度：64", required = true, position = 4 )
    private String md5;
    /**
     * 文件被处理前的Md5（如图片压缩存储）
     */
    @NotNull(message = "文件被处理前的Md5（如图片压缩存储）为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "文件被处理前的Md5（如图片压缩存储）不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 64 ,message = "文件被处理前的Md5（如图片压缩存储）不能超过64个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "文件被处理前的Md5（如图片压缩存储），最大长度：64", required = true, position = 5 )
    private String originalMd5;
    /**
     * 文件SHA1
     */
    @NotNull(message = "文件SHA1为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "文件SHA1不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 64 ,message = "文件SHA1不能超过64个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "文件SHA1，最大长度：64", required = true, position = 6 )
    private String sha1;
    /**
     * 文件原始SHA1
     */
    @NotNull(message = "文件原始SHA1为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "文件原始SHA1不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 64 ,message = "文件原始SHA1不能超过64个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "文件原始SHA1，最大长度：64", required = true, position = 7 )
    private String originalSha1;
    /**
     * 平台：0本地，1七牛，2腾讯，3阿里云
     */
    @NotNull(message = "平台：0本地，1七牛，2腾讯，3阿里云为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "平台：0本地，1七牛，2腾讯，3阿里云", required = true, position = 8 )
    private Integer planform;
    /**
     * 变更时间
     */
    @ApiModelProperty(value = "变更时间", position = 9 )
    private Date updateTime;
    /**
     * 修改人
     */
    @Length(max = 45 ,message = "修改人不能超过45个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "修改人，最大长度：45", position = 10 )
    private String updateUserId;
    /**
     * 修改用户类型
     */
    @ApiModelProperty(value = "修改用户类型", position = 11 )
    private Integer updateUserType;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 12 )
    private Date createTime;
    /**
     * 创建人
     */
    @NotNull(message = "创建人为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "创建人不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 45 ,message = "创建人不能超过45个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "创建人，最大长度：45", required = true, position = 13 )
    private String createUserId;
    /**
     * 创建用户类型
     */
    @NotNull(message = "创建用户类型为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "创建用户类型", required = true, position = 14 )
    private Integer createUserType;
    /**
     * 删除： >=1:是 ，0:否
     */
    @NotNull(message = "删除： >=1:是 ，0:否为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "删除： >=1:是 ，0:否，默认值：0", required = true, position = 15 )
    private Date deleteTime;

}

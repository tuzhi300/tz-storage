package net.kuper.tz.storage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.kuper.tz.core.mybatis.PaginationQuery;
import net.kuper.tz.core.validator.group.AddGroup;
import net.kuper.tz.core.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 分页查询文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-04-02 09:05:54
 */
@ApiModel(value = "分页查询文件上传")
@Data
public class OssQueryEntity extends PaginationQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路径
     */
    @Length(max = 100, message = "路径不能超过100个字符")
    @ApiModelProperty(value = "路径，最大长度：100", required = true, position = 1)
    private String key;

    @Length(max = 50, message = "物理目录不能超过50个字符")
    @ApiModelProperty(value = "物理目录，最大长度：50", required = true, position = 1)
    private String folder;

    /**
     * 文件格式
     */
    @Length(max = 20, message = "文件格式不能超过20个字符")
    @ApiModelProperty(value = "文件格式，最大长度：20", required = true, position = 2)
    private String format;
    /**
     * 大小
     */
    @ApiModelProperty(value = "大小", required = true, position = 3)
    private Long size;
    /**
     * 文件MD5
     */
    @Length(max = 64, message = "文件MD5不能超过64个字符")
    @ApiModelProperty(value = "文件MD5，最大长度：64", required = true, position = 4)
    private String md5;
    /**
     * 文件被处理前的Md5（如图片压缩存储）
     */
    @Length(max = 64, message = "文件被处理前的Md5（如图片压缩存储）不能超过64个字符")
    @ApiModelProperty(value = "文件被处理前的Md5（如图片压缩存储），最大长度：64", required = true, position = 5)
    private String originalMd5;
    /**
     * 文件SHA1
     */
    @Length(max = 64, message = "文件SHA1不能超过64个字符")
    @ApiModelProperty(value = "文件SHA1，最大长度：64", required = true, position = 6)
    private String sha1;
    /**
     * 文件原始SHA1
     */
    @Length(max = 64, message = "文件原始SHA1不能超过64个字符")
    @ApiModelProperty(value = "文件原始SHA1，最大长度：64", required = true, position = 7)
    private String originalSha1;
    /**
     * 平台：0本地，1七牛，2腾讯，3阿里云
     */
    @ApiModelProperty(value = "平台：0本地，1七牛，2腾讯，3阿里云", required = true, position = 8)
    private Integer planform;
    /**
     * 变更时间
     */
    @ApiModelProperty(value = "变更时间", position = 9)
    private Date updateTime;
    /**
     * 修改人
     */
    @Length(max = 45, message = "修改人不能超过45个字符")
    @ApiModelProperty(value = "修改人，最大长度：45", position = 10)
    private String updateUserId;
    /**
     * 修改用户类型
     */
    @ApiModelProperty(value = "修改用户类型", position = 11)
    private Integer updateUserType;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 12)
    private Date createTime;
    /**
     * 创建人
     */
    @Length(max = 45, message = "创建人不能超过45个字符")
    @ApiModelProperty(value = "创建人，最大长度：45", required = true, position = 13)
    private String createUserId;
    /**
     * 创建用户类型
     */
    @ApiModelProperty(value = "创建用户类型", required = true, position = 14)
    private Integer createUserType;
    /**
     * 删除： >=1:是 ，0:否
     */
    @ApiModelProperty(value = "删除： >=1:是 ，0:否，默认值：0", required = true, position = 15)
    private Date deleteTime;

}

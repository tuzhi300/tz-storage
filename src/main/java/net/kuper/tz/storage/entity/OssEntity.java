package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-04-02 09:05:54
 */
@ApiModel(value = "文件上传")
@Data
public class OssEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String key;
    /**
     * 物理目录
     */
    @ApiModelProperty(value = "物理目录")
    private String folder;
    /**
     * 文件格式
     */
    @ApiModelProperty(value = "文件格式")
    private String format;
    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    private Long size;
    /**
     * 文件MD5
     */
    @ApiModelProperty(value = "文件MD5")
    private String md5;
    /**
     * 文件被处理前的Md5（如图片压缩存储）
     */
    @ApiModelProperty(value = "文件被处理前的Md5（如图片压缩存储）")
    private String originalMd5;
    /**
     * 文件SHA1
     */
    @ApiModelProperty(value = "文件SHA1")
    private String sha1;
    /**
     * 文件原始SHA1
     */
    @ApiModelProperty(value = "文件原始SHA1")
    private String originalSha1;
    /**
     * 平台：0本地，1七牛，2腾讯，3阿里云
     */
    @ApiModelProperty(value = "平台：0本地，1七牛，2腾讯，3阿里云")
    private Integer planform;
    /**
     * 变更时间
     */
    @ApiModelProperty(value = "变更时间")
    private Date updateTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUserId;
    /**
     * 创建用户类型
     */
    @ApiModelProperty(value = "创建用户类型")
    private Integer createUserType;
    /**
     * 删除： >=1:是 ，0:否
     */
    @ApiModelProperty(value = "删除： >=1:是 ，0:否")
    private Date deleteTime;

}

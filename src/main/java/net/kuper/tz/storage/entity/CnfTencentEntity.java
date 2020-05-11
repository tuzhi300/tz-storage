package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("腾讯云存储")
public class CnfTencentEntity {

    @NotBlank(message="appId不能为空")
    @ApiModelProperty("appId")
    private String appId;

    @NotBlank(message="secretId不能为空")
    @ApiModelProperty("secretId")
    private String secretId;

    @NotBlank(message="sercretKey不能为空")
    @ApiModelProperty("secretKey")
    private String secretKey;

    @ApiModelProperty("域名")
    @NotBlank(message = "绑定的域名不能为空")
    @URL(message = "绑定的域名格式不正确")
    private String domain;

    @NotBlank(message="region不能为空")
    @ApiModelProperty("bucket所在的区域，华南：gz 华北：tj 华东：sh")
    private String region;

    @ApiModelProperty("存储对象名")
    @NotBlank(message="Bucket不能为空")
    private String bucket;
}

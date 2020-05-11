package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("阿里云配置")
public class CnfAliyunEntity {

    @ApiModelProperty("accessKeyId")
    @NotBlank(message = "AccessKeyId不能为空")
    private String accessKeyId;

    @ApiModelProperty("accessKeySecret")
    @NotBlank(message = "AccessKeySecret不能为空")
    private String accessKeySecret;

    @ApiModelProperty("endPoint")
    @NotBlank(message = "EndPoint不能为空")
    private String endPoint;

    @ApiModelProperty("bucket")
    @NotBlank(message = "Bucket不能为空")
    private String bucket;

    @ApiModelProperty("domain")
    @NotBlank(message = "绑定的域名不能为空")
    @URL(message = "绑定的域名格式不正确")
    private String domain;

}

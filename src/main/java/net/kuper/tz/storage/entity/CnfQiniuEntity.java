package net.kuper.tz.storage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("七牛云配置")
public class CnfQiniuEntity {

    @ApiModelProperty("accessKey")
    @NotBlank(message = "accessKey不能为空")
    private String accessKey;
    @ApiModelProperty("secretKey")
    @NotBlank(message = "secretKey不能为空")
    private String secretKey;
    @ApiModelProperty("bucket")
    @NotBlank(message = "bucket不能为空")
    private String bucket;
    @ApiModelProperty("domain")
    @NotBlank(message = "绑定的域名不能为空")
    @URL(message = "绑定的域名格式不正确")
    private String domain;

}

package net.kuper.tz.storage.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kuper.tz.core.controller.Res;
import net.kuper.tz.core.controller.log.Log;
import net.kuper.tz.core.controller.log.LogType;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.core.validator.ValidatorUtils;
import net.kuper.tz.storage.entity.OssEntity;
import net.kuper.tz.storage.entity.OssQueryEntity;
import net.kuper.tz.storage.service.OssService;
import net.kuper.tz.storage.service.impl.OssTool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
@Api(value = "文件上传" , description = "文件上传" )
@RestController
@RequestMapping("storage/oss" )
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 分页查询：文件上传
     * @param ossQueryEntity
     */
    @Log(type = LogType.QUERY, value = "分页查询文件上传")
    @ApiOperation("分页查询文件上传" )
    @RequiresPermissions("storage:oss:list" )
    @ResponseBody
    @GetMapping
    public Res<Pagination<OssEntity>> list(OssQueryEntity ossQueryEntity) {
        ValidatorUtils.validateEntity(ossQueryEntity);
        Pagination pagination = ossService.queryList(ossQueryEntity);
        return Res.ok(pagination);
    }


    /**
     * 文件上传详情查询
     *
     * @param id
     * @return
     */
    @Log(type = LogType.QUERY, value = "文件上传详情查询")
    @ApiOperation("文件上传详情查询" )
    @RequiresPermissions("storage:oss:detail" )
    @ResponseBody
    @GetMapping(value = "/{id}" )
    public Res<OssEntity> detail(@PathVariable("id") String id) {
        OssEntity  oss = ossService.queryObject(id);
        return Res.ok(oss);
    }

//    /**
//     * 添加文件上传
//     *
//     * @param ossUpdateEntity
//     */
//    @Log(type = LogType.SAVE,value = "添加文件上传" )
//    @ApiOperation("添加文件上传" )
//    @RequiresPermissions("storage:oss:add" )
//    @ResponseBody
//    @PostMapping
//    public Res<OssEntity> save(@RequestBody OssUpdateEntity ossUpdateEntity) {
//        ValidatorUtils.validateEntity(ossUpdateEntity, AddGroup.class);
//        OssEntity ossEntity =ossService.save(ossUpdateEntity);
//        return Res.ok(ossEntity);
//    }

//    /**
//     * 修改文件上传
//     * @param ossUpdateEntity
//     */
//    @Log(type = LogType.UPDATE,value = "修改文件上传" )
//    @ApiOperation("修改文件上传" )
//    @RequiresPermissions("storage:oss:update" )
//    @ResponseBody
//    @PutMapping
//    public Res update(@RequestBody OssUpdateEntity ossUpdateEntity) {
//        ValidatorUtils.validateEntity(ossUpdateEntity, UpdateGroup.class);
//        ossService.update(ossUpdateEntity);
//        return Res.ok();
//    }

//    /**
//     * 删除文件上传
//     * @param id
//     */
//    @Log(type = LogType.DELETE, value = "删除文件上传" )
//    @ApiOperation("删除文件上传" )
//    @RequiresPermissions("storage:oss:delete" )
//    @ResponseBody
//    @DeleteMapping(value = "/{id}" )
//    public Res delete(@PathVariable("id") String id) {
//        ossService.delete(id);
//        return Res.ok();
//    }

}
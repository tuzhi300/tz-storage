//package net.kuper.tz.storage.controller;
//
//
//import net.kuper.tz.core.controller.Res;
//import net.kuper.tz.core.controller.log.Log;
//import net.kuper.tz.core.controller.log.LogType;
//import net.kuper.tz.core.mybatis.Pagination;
//import net.kuper.tz.core.validator.ValidatorUtils;
//import net.kuper.tz.core.validator.group.AddGroup;
//import net.kuper.tz.core.validator.group.UpdateGroup;
//import net.kuper.tz.storage.entity.OssFileEntity;
//import net.kuper.tz.storage.entity.OssFileUpdateEntity;
//import net.kuper.tz.storage.entity.OssFileQueryEntity;
//import net.kuper.tz.storage.service.OssFileService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//
//
///**
// *
// *
// * @author kuper
// * @email shengongwen@163.com
// * @date 2020-03-30 11:55:49
// */
//@Api(value = "" , description = "" )
//@RestController
//@RequestMapping("storage/ossfile" )
//public class OssFileController {
//
//    @Autowired
//    private OssFileService ossFileService;
//
//    /**
//     * 分页查询：
//     * @param ossFileQueryEntity
//     */
//    @Log(type = LogType.QUERY, value = "分页查询")
//    @ApiOperation("分页查询" )
//    @RequiresPermissions("storage:ossfile:list" )
//    @ResponseBody
//    @GetMapping
//    public Res<Pagination<OssFileEntity>> list(OssFileQueryEntity ossFileQueryEntity) {
//        ValidatorUtils.validateEntity(ossFileQueryEntity);
//        Pagination pagination = ossFileService.queryList(ossFileQueryEntity);
//        return Res.ok(pagination);
//    }
//
//
//    /**
//     * 详情查询
//     *
//     * @param id
//     * @return
//     */
//    @Log(type = LogType.QUERY, value = "详情查询")
//    @ApiOperation("详情查询" )
//    @RequiresPermissions("storage:ossfile:detail" )
//    @ResponseBody
//    @GetMapping(value = "/{id}" )
//    public Res<OssFileEntity> detail(@PathVariable("id") String id) {
//        OssFileEntity  ossFile = ossFileService.queryObject(id);
//        return Res.ok(ossFile);
//    }
//
//    /**
//     * 添加
//     *
//     * @param ossFileUpdateEntity
//     */
//    @Log(type = LogType.SAVE,value = "添加" )
//    @ApiOperation("添加" )
//    @RequiresPermissions("storage:ossfile:add" )
//    @ResponseBody
//    @PostMapping
//    public Res<OssFileEntity> save(@RequestBody OssFileUpdateEntity ossFileUpdateEntity) {
//        ValidatorUtils.validateEntity(ossFileUpdateEntity, AddGroup.class);
//        OssFileEntity ossFileEntity =ossFileService.save(ossFileUpdateEntity);
//        return Res.ok(ossFileEntity);
//    }
//
//    /**
//     * 修改
//     * @param ossFileUpdateEntity
//     */
//    @Log(type = LogType.UPDATE,value = "修改" )
//    @ApiOperation("修改" )
//    @RequiresPermissions("storage:ossfile:update" )
//    @ResponseBody
//    @PutMapping
//    public Res update(@RequestBody OssFileUpdateEntity ossFileUpdateEntity) {
//        ValidatorUtils.validateEntity(ossFileUpdateEntity, UpdateGroup.class);
//        ossFileService.update(ossFileUpdateEntity);
//        return Res.ok();
//    }
//
//    /**
//     * 删除
//     * @param id
//     */
//    @Log(type = LogType.DELETE, value = "删除" )
//    @ApiOperation("删除" )
//    @RequiresPermissions("storage:ossfile:delete" )
//    @ResponseBody
//    @DeleteMapping(value = "/{id}" )
//    public Res delete(@PathVariable("id") String id) {
//        ossFileService.delete(id);
//        return Res.ok();
//    }
//
//}
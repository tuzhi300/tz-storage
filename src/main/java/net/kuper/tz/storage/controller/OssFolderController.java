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
//import net.kuper.tz.storage.entity.OssFolderEntity;
//import net.kuper.tz.storage.entity.OssFolderUpdateEntity;
//import net.kuper.tz.storage.entity.OssFolderQueryEntity;
//import net.kuper.tz.storage.service.OssFolderService;
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
//@RequestMapping("storage/ossfolder" )
//public class OssFolderController {
//
//    @Autowired
//    private OssFolderService ossFolderService;
//
//    /**
//     * 分页查询：
//     * @param ossFolderQueryEntity
//     */
//    @Log(type = LogType.QUERY, value = "分页查询")
//    @ApiOperation("分页查询" )
//    @RequiresPermissions("storage:ossfolder:list" )
//    @ResponseBody
//    @GetMapping
//    public Res<Pagination<OssFolderEntity>> list(OssFolderQueryEntity ossFolderQueryEntity) {
//        ValidatorUtils.validateEntity(ossFolderQueryEntity);
//        Pagination pagination = ossFolderService.queryList(ossFolderQueryEntity);
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
//    @RequiresPermissions("storage:ossfolder:detail" )
//    @ResponseBody
//    @GetMapping(value = "/{id}" )
//    public Res<OssFolderEntity> detail(@PathVariable("id") String id) {
//        OssFolderEntity  ossFolder = ossFolderService.queryObject(id);
//        return Res.ok(ossFolder);
//    }
//
//    /**
//     * 添加
//     *
//     * @param ossFolderUpdateEntity
//     */
//    @Log(type = LogType.SAVE,value = "添加" )
//    @ApiOperation("添加" )
//    @RequiresPermissions("storage:ossfolder:add" )
//    @ResponseBody
//    @PostMapping
//    public Res<OssFolderEntity> save(@RequestBody OssFolderUpdateEntity ossFolderUpdateEntity) {
//        ValidatorUtils.validateEntity(ossFolderUpdateEntity, AddGroup.class);
//        OssFolderEntity ossFolderEntity =ossFolderService.save(ossFolderUpdateEntity);
//        return Res.ok(ossFolderEntity);
//    }
//
//    /**
//     * 修改
//     * @param ossFolderUpdateEntity
//     */
//    @Log(type = LogType.UPDATE,value = "修改" )
//    @ApiOperation("修改" )
//    @RequiresPermissions("storage:ossfolder:update" )
//    @ResponseBody
//    @PutMapping
//    public Res update(@RequestBody OssFolderUpdateEntity ossFolderUpdateEntity) {
//        ValidatorUtils.validateEntity(ossFolderUpdateEntity, UpdateGroup.class);
//        ossFolderService.update(ossFolderUpdateEntity);
//        return Res.ok();
//    }
//
//    /**
//     * 删除
//     * @param id
//     */
//    @Log(type = LogType.DELETE, value = "删除" )
//    @ApiOperation("删除" )
//    @RequiresPermissions("storage:ossfolder:delete" )
//    @ResponseBody
//    @DeleteMapping(value = "/{id}" )
//    public Res delete(@PathVariable("id") String id) {
//        ossFolderService.delete(id);
//        return Res.ok();
//    }
//
//}
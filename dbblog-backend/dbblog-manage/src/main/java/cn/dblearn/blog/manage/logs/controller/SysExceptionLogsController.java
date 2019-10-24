package cn.dblearn.blog.manage.logs.controller;

import java.util.List;
import java.util.Map;

import cn.dblearn.blog.entity.logs.SysExceptionLogs;
import cn.dblearn.blog.manage.logs.service.SysExceptionLogsService;
import cn.dblearn.dbblog.common.base.BaseController;
import cn.dblearn.dbblog.common.util.StringUtilEx;
import cn.dblearn.dbblog.common.warpper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@Api(value = "异常日志表", description = "异常日志表Api")
@RestController(value = "adminSysExceptionLogsController")
@RequestMapping("/admin/sysExceptionLogs")
public class SysExceptionLogsController extends BaseController {

    @Autowired
    private SysExceptionLogsService sysExceptionLogsService;

    /**
     * 列表
     */
    @ApiOperation(value = "分页查询", notes = "分页查询api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "条数", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public PageInfo<SysExceptionLogs> getPage(@ModelAttribute PageBean page, @ModelAttribute SysExceptionLogs entity
            , @RequestParam(name = "startTime", required = false) String startTime
            , @RequestParam(name = "endTime", required = false) String endTime
            , @RequestParam(name = "siteId", required = false) Long siteId,
                                              HttpServletRequest request) {
        PageHelper.startPage(page.getPage(), page.getRows());
        Map<String, Object> queryfilter = Maps.newHashMap();
        if (StringUtilEx.isNotEmpty(startTime)) {
            queryfilter.put("startTime", startTime);
        }
        if (StringUtilEx.isNotEmpty(startTime)) {
            queryfilter.put("endTime", endTime);
        }
        entity.setQueryfilter(queryfilter);
        List<SysExceptionLogs> list = sysExceptionLogsService.findListByEntity(entity);
        return new PageInfo<SysExceptionLogs>(list);
    }

    /**
     * 查询明细
     */
    @ApiOperation(value = "查询明细", notes = "查询明细api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public SysExceptionLogs detail(Long id) {
        return sysExceptionLogsService.selectByKey(id);
    }

}

package com.cn.yf.blog.controller.admin;

import com.cn.yf.blog.entity.BlogTool;
import com.cn.yf.blog.service.ToolService;
import com.cn.yf.blog.util.PageQueryUtil;
import com.cn.yf.blog.util.ResultGenerator;
import com.cn.yf.blog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ToolController {

    @Autowired
    ToolService toolService;

    @GetMapping("/tools")
    public String toolPage(HttpServletRequest request){
        request.setAttribute("path","tools");
        return "admin/tool";
    }

    /**
     * 点击页面就会请求数据
     * @param params
     * @return
     */
    @GetMapping("/tools/list")
    @ResponseBody
    public Result list(@RequestParam Map<String ,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(toolService.getBlogToolPage(pageQueryUtil));
    }

    /**
     * 保存在线工具
     * @param toolName
     * @param toolUrl
     * @param toolDescription
     * @return
     */
    @PostMapping("/tools/save")
    @ResponseBody
    public Result save(@RequestParam("toolName") String toolName,
                       @RequestParam("toolUrl") String toolUrl,
                       @RequestParam("toolDescription") String toolDescription){
        if(StringUtils.isEmpty(toolName) || StringUtils.isEmpty(toolUrl) || StringUtils.isEmpty(toolDescription)){
            return ResultGenerator.genFailResult("参数异常");
        }
        BlogTool blogTool = new BlogTool();
        blogTool.setToolName(toolName);
        blogTool.setToolUrl(toolUrl);
        blogTool.setToolDescription(toolDescription);
        return ResultGenerator.genSuccessResult(toolService.saveTool(blogTool));
    }

    /**
     * 获取一条在线工具的详细信息
     * @param id
     * @return
     */
    @GetMapping("/tools/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){
        BlogTool blogTool = toolService.selectById(id);
        if(blogTool == null){
            return ResultGenerator.genFailResult("没有该数据");
        }
        return ResultGenerator.genSuccessResult(blogTool);
    }

    /**
     * 在线工具修改
     * @param toolId
     * @param toolName
     * @param toolUrl
     * @param toolDescription
     * @return
     */
    @PostMapping("/tools/update")
    @ResponseBody
    public Result update(@RequestParam("toolId") Integer toolId,
                         @RequestParam("toolName") String toolName,
                         @RequestParam("toolUrl") String toolUrl,
                         @RequestParam("toolDescription") String toolDescription){
        BlogTool blogTool = toolService.selectById(toolId);
        if(blogTool == null){
            return ResultGenerator.genFailResult("没有该数据");
        }
        if(StringUtils.isEmpty(toolName) || StringUtils.isEmpty(toolUrl) || StringUtils.isEmpty(toolDescription)){
            return ResultGenerator.genFailResult("参数异常");
        }
        BlogTool blogTool1 = new BlogTool();
        blogTool.setToolName(toolName);
        blogTool.setToolUrl(toolUrl);
        blogTool.setToolDescription(toolDescription);
        return ResultGenerator.genSuccessResult(toolService.updateTool(blogTool));
    }

    /**
     * 删除在线工具
     * @param ids
     * @return
     */
    @PostMapping("/tools/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length < 1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(toolService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult(toolService.deleteBatch(ids));
        }else{
            return ResultGenerator.genFailResult("删除失败");
        }
    }





}

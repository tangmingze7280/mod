package com.demo.mod.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.mod.dao.DepartmentDao;
import com.demo.mod.entity.Department;
import com.demo.mod.result.CommonResult;
import com.demo.mod.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class ApiController {
    private final static Logger LOGGER= LoggerFactory.getLogger(ApiController.class);
    @Autowired
    MainService mainService;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/getAllListOfDepartment")
    public CommonResult getAllListOfDepartment(){
        CommonResult commonResult=new CommonResult();
        List<Department> allListOfDepartment = mainService.getAllListOfDepartment();
        commonResult.setData(allListOfDepartment);
        return commonResult;
    }
    @RequestMapping("/adddep")
    public CommonResult addDep(@RequestParam Map<String,Object> map){
        CommonResult commonResult=new CommonResult();
        Department department=new Department();
        department.setDpId(Integer.valueOf((String)map.get("dpid")));
        department.setName((String)map.get("name"));
        department.setPid(Integer.valueOf((String)map.get("pid")));
        commonResult.setMsg("新增成功");
        List<Department> allByName = departmentDao.findAllByName(department.getName());
        if(allByName.size()!=0){
            commonResult.setMsg("不允许同名");
            commonResult.setState(500);
            return commonResult;
        }
        List<Department> allByPid = departmentDao.getAllByPid(department.getPid());
        if(allByPid.size()!=0){
            commonResult.setMsg("不允许形同部门id!");
            commonResult.setState(500);
            return commonResult;
        }
        if(!mainService.addDep(department)) {
            commonResult.setMsg("新增失败");
            commonResult.setState(500);
        }
        return commonResult;
    }
    @RequestMapping(value="/deleteDep",produces = "application/json;charset=utf-8;",
            method = {RequestMethod.POST})
    public CommonResult deleteDep(@RequestBody String jsonData){
        CommonResult commonResult=new CommonResult();
        List<Department> departments = JSON.parseArray(jsonData, Department.class);
        for(Department department :departments){
            List<Department> allByPid = departmentDao.getAllByPid(department.getDpId());
            if (allByPid.size()!=0){
                commonResult.setMsg(department.getName()+"存在下级部门不能删除");
                return commonResult;
            }
        }
        for(Department depart : departments){

            departmentDao.deleteById(depart.getId());
        }
        commonResult.setMsg("删除成功！");
        return commonResult;
    }
    @RequestMapping(value="/updateDep")
    public CommonResult updateDep(@RequestParam Map<String,Object> map){
        String name = (String)map.get("name");
        CommonResult commonResult=new CommonResult();
        commonResult.setMsg("修改成功！！！");
        List<Department> allByName = departmentDao.findAllByName(name);
        if(allByName.size()!=0){
            commonResult.setMsg("不能有重复的名字！！！");
            commonResult.setState(500);
            return commonResult;
        }
        Integer id=Integer.valueOf((String)map.get("dpid"));
        departmentDao.updateOfname(name,id);
        return commonResult;
    }
}

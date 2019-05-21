package com.demo.mod.controller;

import com.demo.mod.entity.Department;
import com.demo.mod.result.CommonResult;
import com.demo.mod.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class ApiController {
    private final static Logger LOGGER= LoggerFactory.getLogger(ApiController.class);
    @Autowired
    MainService mainService;
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
        if(!mainService.addDep(department))
            commonResult.setMsg("新增失败");
        return commonResult;
    }

}

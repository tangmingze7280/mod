package com.demo.mod.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.mod.bean.Person;
import com.demo.mod.dao.DepartmentDao;
import com.demo.mod.dao.JobInstructionsDao;
import com.demo.mod.dao.PersonnativDao;
import com.demo.mod.dao.ResponsieDao;
import com.demo.mod.entity.Department;
import com.demo.mod.entity.JobInstructions;
import com.demo.mod.entity.Personnativ;
import com.demo.mod.entity.Responsie;
import com.demo.mod.result.CommonResult;
import com.demo.mod.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class ApiController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    MainService mainService;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    PersonnativDao personnativDao;
    @Autowired
    JobInstructionsDao jobInstructionsDao;
    @Autowired
    ResponsieDao responsieDao;
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping("/getAllListOfDepartment")
    public CommonResult getAllListOfDepartment() {
        CommonResult commonResult = new CommonResult();
        List<Department> allListOfDepartment = mainService.getAllListOfDepartment();
        commonResult.setData(allListOfDepartment);
        return commonResult;
    }

    @RequestMapping("/adddep")
    public CommonResult addDep(@RequestParam Map<String, Object> map) {
        CommonResult commonResult = new CommonResult();
        Department department = new Department();
        department.setDpId(Integer.valueOf((String) map.get("dpid")));
        department.setName((String) map.get("name"));
        department.setPid(Integer.valueOf((String) map.get("pid")));
        commonResult.setMsg("新增成功");
        List<Department> allByName = departmentDao.findAllByName(department.getName());
        if (allByName.size() != 0) {
            commonResult.setMsg("不允许同名");
            commonResult.setState(500);
            return commonResult;
        }
        List<Department> allByPid = departmentDao.getAllByPid(department.getPid());
        if (allByPid.size() != 0) {
            commonResult.setMsg("不允许形同部门id!");
            commonResult.setState(500);
            return commonResult;
        }
        if (!mainService.addDep(department)) {
            commonResult.setMsg("新增失败");
            commonResult.setState(500);
        }
        return commonResult;
    }

    @RequestMapping(value = "/deleteDep", produces = "application/json;charset=utf-8;",
            method = {RequestMethod.POST})
    public CommonResult deleteDep(@RequestBody String jsonData) {
        CommonResult commonResult = new CommonResult();
        List<Department> departments = JSON.parseArray(jsonData, Department.class);
        for (Department department : departments) {
            List<Department> allByPid = departmentDao.getAllByPid(department.getDpId());
            if (allByPid.size() != 0) {
                commonResult.setMsg(department.getName() + "存在下级部门不能删除");
                return commonResult;
            }
        }
        for (Department depart : departments) {

            departmentDao.deleteById(depart.getId());
        }
        commonResult.setMsg("删除成功！");
        return commonResult;
    }

    @RequestMapping(value = "/updateDep")
    public CommonResult updateDep(@RequestParam Map<String, Object> map) {
        String name = (String) map.get("name");
        CommonResult commonResult = new CommonResult();
        commonResult.setMsg("修改成功！！！");
        List<Department> allByName = departmentDao.findAllByName(name);
        if (allByName.size() != 0) {
            commonResult.setMsg("不能有重复的名字！！！");
            commonResult.setState(500);
            return commonResult;
        }
        Integer id = Integer.valueOf((String) map.get("dpid"));
        departmentDao.updateOfname(name, id);
        return commonResult;
    }

    @RequestMapping(value = "/sreachTab")
    public CommonResult selectItemForTable(@RequestParam Map<String, Object> map) {
        CommonResult commonResult = new CommonResult();
        StringBuffer sb = new StringBuffer("select  p.id id,p.dp_id did,p.name pname, p.xz_id xid,p.zj_id zid,d.name dname,j.name jname,a.name aname from  ")
                .append(" personnativ p,department d , job_instructions j, responsie  a ")
                .append("where 1=1 and p.dp_id=d.dp_id and p.xz_id= a.xz_id and p.zj_id=j.zj_id ");
        String name = (String) map.get("name");
        Integer dpId=new Integer(0);
        Integer xzId=new Integer(0);
        Integer zjId=new Integer(0);
        if (!StringUtils.isEmpty(name)) {
            sb.append(" and p.name=:name");
        }
        if (!StringUtils.isEmpty((String) map.get("dpId"))) {
             dpId = Integer.valueOf((String) map.get("dpId"));
            sb.append("  and p.dp_id=:dpId");
        }
        if (!StringUtils.isEmpty((String) map.get("xzId"))) {
            xzId = Integer.valueOf((String) map.get("xzId"));
            sb.append("   and p.xz_id=:xzId ");
        }
        if (!StringUtils.isEmpty((String) map.get("zjId"))) {
            zjId = Integer.valueOf((String) map.get("zjId"));
            sb.append(" and p.zj_id=:zjId ");
        }
        Query dataQuery = entityManager.createNativeQuery(sb.toString());
        if (sb.toString().indexOf(":name") != -1) {
            dataQuery.setParameter("name", name);
        }
        if (sb.toString().indexOf(":dpId") != -1) {
            dataQuery.setParameter("dpId",dpId);
        }
        if (sb.toString().indexOf(":xzId") != -1) {
            dataQuery.setParameter("xzId", xzId);
        }
        if (sb.toString().indexOf(":zjId") != -1) {
            dataQuery.setParameter("zjId", zjId);
        }
        List<Object[]> someOnePositionList = dataQuery.getResultList();
        List<Person> resultList = new LinkedList<>();
        for (Object[] obj : someOnePositionList) {
            Person person = new Person((Integer) obj[0], (String) obj[2], (String) obj[7], (String) obj[6], (String) obj[5], (Integer) obj[4], (Integer) obj[3], (Integer) obj[1]);
            resultList.add(person);
        }
        commonResult.setData(resultList);
        return commonResult;
    }

    @RequestMapping("/addperson")
    public CommonResult addPerson(@RequestParam Map<String, Object> map) {
        CommonResult commonResult = new CommonResult();
        String name = (String) map.get("pname");//人名
        Integer zjId = Integer.valueOf((String) map.get("zjinp"));//政治上的
        Integer xzId = Integer.valueOf((String) map.get("zwinp"));//职级
        Integer dpId = Integer.valueOf((String) map.get("depid"));//部门
        Personnativ personnativ = new Personnativ();
        personnativ.setName(name);
        personnativ.setZjId(zjId);
        personnativ.setXzId(xzId);
        personnativ.setDpId(dpId);
        personnativDao.save(personnativ);
        commonResult.setMsg("新增成功！");
        return commonResult;
    }

    @RequestMapping("/initSelect")
    public CommonResult initSelect(@RequestParam Map<String, Object> map) {
        CommonResult commonResult = new CommonResult();
        List<JobInstructions> all = (List<JobInstructions>) jobInstructionsDao.findAll();
        List<Responsie> all1 = (List<Responsie>) responsieDao.findAll();
        List<Department> all2 = (List<Department>) departmentDao.findAll();
        Map<String, Object> resMap = new Hashtable<>();
        resMap.put("job", all);
        resMap.put("resp", all1);
        resMap.put("dep", all2);
        commonResult.setData(resMap);
        return commonResult;
    }

    @RequestMapping(value="/delPerson", produces = "application/json;charset=utf-8;",
            method = {RequestMethod.POST})
    public CommonResult delPerson(@RequestBody String jsonData) {
        CommonResult commonResult = new CommonResult();
        List<Person> personList = JSON.parseArray(jsonData, Person.class);
        commonResult.setMsg("删除成功！");
        for (Person person : personList) {
            personnativDao.delete(person.getId());
        }
        return commonResult;
    }

}

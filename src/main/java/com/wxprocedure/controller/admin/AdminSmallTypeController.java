package com.wxprocedure.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxprocedure.dto.SmallTypeSearchDto;
import com.wxprocedure.entity.PageBean;
import com.wxprocedure.entity.R;
import com.wxprocedure.entity.SmallType;
import com.wxprocedure.service.IBigTypeService;
import com.wxprocedure.service.ISmallTypeService;
import com.wxprocedure.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员-商品小类Controller控制器
 */

@RestController
@RequestMapping("/admin/smallType")
public class AdminSmallTypeController {

    @Autowired
    private ISmallTypeService smallTypeService;

    @Autowired
    private IBigTypeService bigTypeService;


    /**
     * 根据条件分页查询
     *
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestBody SmallTypeSearchDto smallTypeSearchDto) {
        //已修复
        PageBean pageBean = smallTypeSearchDto.getPageBean();
        String smallTypeName = smallTypeSearchDto.getSmallTypeName().trim();
        Page<SmallType> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        Page<SmallType> pageResult = smallTypeService.page(page, new QueryWrapper<SmallType>().like(StringUtil.isNotEmpty(smallTypeName), "name", smallTypeName));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("smallTypeList", pageResult.getRecords());
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        smallTypeService.removeById(id);
        return R.ok();
    }


    /**
     * 添加或修改
     *
     * @param smallType
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody SmallType smallType) {
        smallType.setBigTypeId(smallType.getBigType().getId());
        if (smallType.getId() == null || smallType.getId() == -1) {
            smallTypeService.save(smallType);
        } else {
            smallTypeService.saveOrUpdate(smallType);
        }
        return R.ok();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R findById(@PathVariable(value = "id") Integer id) {
        SmallType smallType = smallTypeService.getById(id);
        smallType.setBigType(bigTypeService.getById(smallType.getBigTypeId()));
        Map<String, Object> map = new HashMap<>();
        map.put("smallType", smallType);
        return R.ok(map);
    }

    /**
     * 根据商品大类id，查询所有小类
     *
     * @param bigTypeId
     * @return
     */
    @GetMapping("/listAll/{bigTypeId}")
    public R listAll(@PathVariable(value = "bigTypeId") Integer bigTypeId) {
        Map<String, Object> map = new HashMap<>();
        map.put("smallTypeList", smallTypeService.list(new QueryWrapper<SmallType>().eq("bigTypeId", bigTypeId)));
        return R.ok(map);
    }
}

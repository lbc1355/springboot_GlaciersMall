package com.lioch3cooh.glaciersmall.controller;

import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.service.CategoryService;
import com.lioch3cooh.glaciersmall.service.GoodsService;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/category")
@ResponseBody
@Controller
public class categoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据分类ID 查找推荐物品
     *
     * @param id 分类ID
     * @return
     */
    @GetMapping("")
    public VoResult category(Integer id) {
        VoResult voResult = new VoResult();
        voResult.setCode(0);
        voResult.setMsg("查询失败");

        Map<String, Object> result = new HashMap<>();
        //获取顶级分类
        Category category = categoryService.getCategoryById(id);
        if (category != null){
            result.put("id", String.valueOf(category.getId()));
            result.put("name", category.getName());
            result.put("picture", category.getPicture());
            List children = new ArrayList();
            result.put("children", children);
            //获取二级分类
            List<Category> levelTwoCategorys = categoryService.listCategoryByParentId(id);
            for (Category levelTwoCategory : levelTwoCategorys) {
                Map<String, Object> map = new HashMap<>();
                List<Goods> goods =
                        goodsService.listColumnGoodsByCgyId(levelTwoCategory.getId());
                map.put("id", levelTwoCategory.getId());
                map.put("name", levelTwoCategory.getName());
                map.put("picture", levelTwoCategory.getPicture());
                map.put("goods", goods);
                children.add(map);
            }

            voResult.setResult(result);
            voResult.setMsg("查询成功");
            voResult.setCode(1);
        }
        return voResult;
    }
}

package com.lioch3cooh.glaciersmall.controller;

import com.lioch3cooh.glaciersmall.entity.*;
import com.lioch3cooh.glaciersmall.service.*;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/home")
@ResponseBody
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private BrandsService brandsService;

    @Autowired
    private HomeService homeService;

    /**
     * 获取首页分类信息 包括推荐商品
     *
     * @return
     */
    @GetMapping("/category/head")
    public VoResult headCategory() {

        VoResult voResult = new VoResult();
        //默认失败
        voResult.setCode(0);
        List<Map<String, Object>> result = new ArrayList<>();
        //寻找顶级类目
        List<Category> topCategorys = categoryService.listTopCategory();
        for (Category topCategory : topCategorys) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", topCategory.getId());
            map.put("name", topCategory.getName());
            map.put("picture", topCategory.getPicture());

            //寻找二级类目
            List<Category> leveltwocategorys = homeService.listCategoryByParentId(topCategory.getId());

            map.put("children", leveltwocategorys);

            List<Goods> goods = new ArrayList<>();
            //获取二级类别下 推荐商品
            for (Category leveltwocategory : leveltwocategorys) {
                List<Goods> recommondGoods = homeService.getRecomGoodByCgyId(leveltwocategory.getId());
                goods.addAll(recommondGoods);
            }


            map.put("goods", goods);
            result.add(map);
        }
        //查询成功
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        voResult.setResult(result);
        return voResult;
    }

    /**
     * 获取轮播图
     *
     * @return VoResult
     */
    @GetMapping("/banner")
    public VoResult getBanner() {
        VoResult voResult = new VoResult(0, "查询失败", null);
        List<Banner> bannerContext = bannerService.getBannerContext();
        voResult.setResult(bannerContext);
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        return voResult;
    }

    /**
     * 获取推荐品牌
     *
     * @param limit 查询个数
     * @return VoResult
     */
    @GetMapping("/brand")
    public VoResult getRecommendedBrand(@RequestParam("limit") Integer limit) {
        VoResult voResult = new VoResult(0, "查询失败", null);
        List<Brands> brands = brandsService.listRecommendedBrand(limit);
        voResult.setResult(brands);
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        return voResult;
    }

    /**
     * 获取新鲜好物
     *
     * @return VoResult
     */
    @GetMapping("/new")
    public VoResult listNewGoods() {
        VoResult voResult = new VoResult(0, "查询失败", null);
        List<Goods> goods = homeService.listNewGoods();
        voResult.setResult(goods);
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        return voResult;
    }

    /**
     * 获取人气推荐
     *
     * @return VoResult
     */
    @GetMapping("/hot")
    public VoResult litHomeHot() {
        VoResult voResult = new VoResult(0, "查询失败", null);
        List<Hot> hots = homeService.listHomeHot();
        voResult.setResult(hots);
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        return voResult;
    }

    /**
     * 获取首页最新专题
     *
     * @return VoResult
     */
    @GetMapping("/special")
    public VoResult listHomeSpecial() {
        VoResult voResult = new VoResult(0, "查询失败", null);
        List<Special> specials = homeService.listHomeSpecial();
        voResult.setResult(specials);
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        return voResult;
    }

    /**
     * 首页获取产品专区
     */
    @GetMapping("/goods")
    public VoResult listHomeProduct() {
        VoResult voResult = new VoResult(0, "查询失败", null);
        // 获取分类
        List<VoHomeProductCategory> voHomeProductCategories = homeService.listProductCategory();
        // 获取物品
        List<Goods> goods = homeService.listProductGoods();
        for (VoHomeProductCategory voHomeProductCategory : voHomeProductCategories) {
            // 放置二级分类
            List<Category> levelTwoCategorys =
                    categoryService.listCategoryByParentId(voHomeProductCategory.getId());
            voHomeProductCategory.setChildren(levelTwoCategorys);
            List<Goods> goodsList = new ArrayList<>();
            for (Category levelTwoCategory : levelTwoCategorys) {
                for (Goods good : goods) {
                    if (levelTwoCategory.getId() == good.getCategoryId()) {
                        goodsList.add(good);
                    }
                }
            }
            //放置物品
            voHomeProductCategory.setGoods(goodsList);
        }
        voResult.setResult(voHomeProductCategories);
        voResult.setCode(1);
        voResult.setMsg("查询成功");
        return voResult;
    }

}

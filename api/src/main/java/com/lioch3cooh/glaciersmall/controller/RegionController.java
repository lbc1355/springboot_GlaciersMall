package com.lioch3cooh.glaciersmall.controller;

import com.lioch3cooh.glaciersmall.entity.Region;
import com.lioch3cooh.glaciersmall.entity.vo.VoRegion;
import com.lioch3cooh.glaciersmall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/region")
@ResponseBody
@Controller
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("")
    public List<VoRegion<Region>> listRegion(){
        List<VoRegion<Region>> voRegions = regionService.listAllCity();
        return voRegions;
    }
}

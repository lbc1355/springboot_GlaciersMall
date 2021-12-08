package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.SpecificationsDao;
import com.lioch3cooh.glaciersmall.entity.SpecVaule;
import com.lioch3cooh.glaciersmall.entity.Specifications;
import com.lioch3cooh.glaciersmall.entity.vo.VoProduct;
import com.lioch3cooh.glaciersmall.entity.vo.VoSku;
import com.lioch3cooh.glaciersmall.entity.vo.VoSpecs;
import com.lioch3cooh.glaciersmall.service.GoodSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodSkuServiceImpl implements GoodSkuService {

    @Autowired
    private SpecificationsDao specificationsDao;

    @Override
    public VoProduct getVoProduct(Integer goodId) {
        VoProduct voProduct = new VoProduct();
        List<Specifications> specifications = specificationsDao.listSpecifications(goodId);
        List<VoSpecs> specs = new ArrayList<>();
        VoSpecs voSpecs = null;
        for (Specifications specification : specifications) {
            voSpecs = new VoSpecs();
            voSpecs.setName(specification.getSpecName());
            List<SpecVaule> specVaules = specificationsDao.listSpecValue(specification.getSpecId());
            voSpecs.setValues(specVaules);
            specs.add(voSpecs);
        }
        voProduct.setSpecs(specs);
        return voProduct;
    }
}

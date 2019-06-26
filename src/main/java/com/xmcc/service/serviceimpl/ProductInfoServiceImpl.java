package com.xmcc.service.serviceimpl;

import com.xmcc.common.ProductEnums;
import com.xmcc.common.ResultEnums;
import com.xmcc.common.ResultResponse;
import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.dto.ProductInfoDto;
import com.xmcc.entity.ProductCategory;
import com.xmcc.entity.ProductInfo;
import com.xmcc.repository.ProductCategoryRepository;
import com.xmcc.repository.ProductInfoRepository;
import com.xmcc.service.ProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ResultResponse queryList() {
        List<ProductCategory> all = productCategoryRepository.findAll();
        if(all.isEmpty()){
            return ResultResponse.fail();
        }

        List<ProductCategoryDto> categorydtoList = all.stream()
                .map(productCategory -> ProductCategoryDto.build(productCategory))
                .collect(Collectors.toList());

        //获得类目编号集合
        List<Integer> categoryTypeList = categorydtoList.stream().map(categorydto -> categorydto.getCategoryType()).collect(Collectors.toList());
        //查询商品列表  这里商品上下架可以用枚举 方便扩展
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusAndCategoryTypeIn(ResultEnums.PRODUCT_UP.getCode(), categoryTypeList);
        //多线程遍历 取出每个商品类目编号对应的 商品列表 设置进入类目中
        List<ProductCategoryDto> finalResultList = categorydtoList.parallelStream().map(categorydto -> {
            categorydto.setProductInfoDtoList(productInfoList.stream().
                    filter(productInfo -> productInfo.getCategoryType() == categorydto.getCategoryType()).map(productInfo ->
                    ProductInfoDto.build(productInfo)).collect(Collectors.toList()));
            return categorydto;
        }).collect(Collectors.toList());
        return ResultResponse.success(finalResultList);
    }

    @Override
    public ResultResponse<ProductInfo> queryById(String productId) {
        if(StringUtils.isBlank(productId)){
            return ResultResponse.fail(ResultEnums.PARAM_ERROR.getMsg()+":"+productId);
        }
        Optional<ProductInfo> byId = productInfoRepository.findById(productId);
        if(!byId.isPresent()){
            return ResultResponse.fail(productId+":"+ResultEnums.NOT_EXITS.getMsg());
        }
        ProductInfo productInfo = byId.get();
        //判断商品是否下架
        if(productInfo.getProductStatus()== ProductEnums.PRODUCT_DOWN.getCode()){
            return ResultResponse.fail(ProductEnums.PRODUCT_DOWN.getMsg());
        }
        return ResultResponse.success(productInfo);
    }

    @Override
    @Transactional
    public void updateProduct(ProductInfo productInfo) {
        productInfoRepository.save(productInfo);

    }
}

package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ResultResponse<List<ProductCategory>> findAll();
}

package com.ecom.service;

import com.ecom.model.HomeCategory;
import com.ecom.model.HomePage;

import java.util.List;

public interface HomeService {
    public HomePage createHomePageData(List<HomeCategory> allCategories);
}

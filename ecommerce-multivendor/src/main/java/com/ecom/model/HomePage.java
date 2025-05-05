package com.ecom.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Data
public class HomePage {
    private List<HomeCategory> grid;

    private List<HomeCategory> shopByCategories;

    private List<HomeCategory> electricCategories;

    private List<HomeCategory> dealCategories;

    private List<Deal> deals;
}

package com.example.batch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String main_cat;//主要标签  极少部分也是没有的

    private String title;//商品名 极少部分商品没有 【需要处理】

    private String asin;//商品编号 都有

    private List<String> category;//标签

    private List<String> imageURLHighRes;//高清图  大概只有一半有 这就属于一票否决项了——我哪里来那么多图片网址？

    private String price;//价格 不是每个人都有 【需要处理】
}

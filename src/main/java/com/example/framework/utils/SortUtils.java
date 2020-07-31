package com.example.framework.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/3 11:00
 * @Text:
 **/
public class SortUtils {
    /**
     * Sort.by()可以一个或多个字段排序
     * Assert.isNull/isTrue为断言（Spring Assert 断言 - 243573295 - 博客园  https://www.cnblogs.com/hwaggLee/p/4778101.html）
     * @param direction
     * @param properties
     * @return
     */
    public static Sort by(Sort.Direction direction,String... properties){
        Assert.isNull(direction,"Direction must not be null!");
        Assert.isNull(properties,"properties must not be null！");
        Assert.isTrue(properties.length>0,"At least one properties must be given!");
        return Sort.by(Arrays.stream(properties).map(x->new Sort.Order(direction,x)).collect(Collectors.toList()));
    }
}

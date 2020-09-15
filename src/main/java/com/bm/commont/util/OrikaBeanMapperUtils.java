package com.bm.commont.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Collection;
import java.util.List;

/**
 * 使用orika进行对象转换
 *
 * @author QINGUOQING
 */
public class OrikaBeanMapperUtils {

    private static final MapperFacade MAPPER_FACADE;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).mapNulls(true).build();
        MAPPER_FACADE = mapperFactory.getMapperFacade();
    }

    public static <S, D> void map(S from, D to) {
        MAPPER_FACADE.map(from, to);
    }

    public static <S, D> D map(S from, Class<D> model) {
        return MAPPER_FACADE.map(from, model);
    }

    public static MapperFacade getMapperFacade() {
        return MAPPER_FACADE;
    }

    public static <E, T> List<E> mapAsList(Collection<T> data, Class<E> toClass) {
        return MAPPER_FACADE.mapAsList(data, toClass);
    }
}

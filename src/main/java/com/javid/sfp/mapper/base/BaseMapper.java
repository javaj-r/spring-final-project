package com.javid.sfp.mapper.base;

import java.util.List;
import java.util.Set;

/**
 * @param <E> Entity class
 * @param <D> DTO class
 * @author javid
 * Created on 5/9/2022
 */
public interface BaseMapper<E, D> {

    E mapToEntity(D dto);

    D mapToDto(E entity);

    Set<E> mapToEntity(Set<D> set);

    Set<D> mapToDto(Set<E> set);

    List<E> mapToEntity(List<D> list);

    List<D> mapToDto(List<E> list);
}

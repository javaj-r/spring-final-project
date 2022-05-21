package com.javid.spring.last.project.phase3.mapper.base;

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
}

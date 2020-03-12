package cn.dormirr.commonmodule.base;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface BaseMapper<D, E> {

    /**
     * DTO 转实体
     *
     * @param dto DTO
     * @return 实体
     */
    E toEntity(D dto);

    /**
     * DTO 集合转实体集合
     *
     * @param dtoList DTO 集合
     * @return 实体集合
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * 实体转 DTO
     *
     * @param entity 实体
     * @return DTO
     */
    D toDto(E entity);

    /**
     * 实体集合转 DTO 集合
     *
     * @param entityList 实体集合
     * @return DTO 集合
     */
    List<D> toDto(List<E> entityList);
}

package com.apple.mapper;

import com.apple.entity.Menu;

import java.util.List;

/**
 * menu的mapper接口
 */
public interface MenuMapper {

    public List<Menu> getMenuTreeList(Integer pid);
    public List<Menu> getMenuTreeListByFunction(Integer pid);
}

package com.apple.entity;

import java.util.List;

/**
 * @description: 菜单类
 * @author: Apple
 * @create: 2019-07-13 23:13
 **/
public class Menu {
    private int        id;
    private String     name;
    private int        parentId;
    private List<Menu> childMenus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", childMenus=" + childMenus +
                '}';
    }
}

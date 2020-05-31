package com.apple.easytest;

import com.apple.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 菜单操作类
 * @author: Apple
 * @create: 2019-07-13 23:16
 **/
public class MenuDao {
    /**
     * @return 返回所有的菜单集合
     */
    public List<Menu> getAllMenus() {
        ArrayList<Menu> allMenus = new ArrayList();
        //构建三个一级菜单
        Menu menu = new Menu();
        menu.setId(1);
        menu.setName("一级菜单-1");
        menu.setParentId(0);
        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setName("一级菜单-2");
        menu2.setParentId(0);
        Menu menu3 = new Menu();
        menu3.setId(3);
        menu3.setName("一级菜单-3");
        menu3.setParentId(0);

        Menu menu4 = new Menu();
        menu4.setId(4);
        menu4.setName("二级菜单-1");
        menu4.setParentId(1);  //设置在一级菜单-1 下面
        Menu menu5 = new Menu();
        menu5.setId(5);
        menu5.setName("二级菜单-2");
        menu5.setParentId(1);  //设置在一级菜单-1 下面

        Menu menu6 = new Menu();
        menu6.setId(6);
        menu6.setName("三级菜单-1");
        menu6.setParentId(4);  //设置在二级菜单-1 下面
        Menu menu7 = new Menu();
        menu7.setId(7);
        menu7.setName("二级菜单-3");
        menu7.setParentId(2);  //设置在一级菜单-2 下面
        //添加所有菜单
        allMenus.add(menu);
        allMenus.add(menu2);
        allMenus.add(menu3);
        allMenus.add(menu4);
        allMenus.add(menu5);
        allMenus.add(menu6);
        allMenus.add(menu7);
        return allMenus;
    }

    /**
     * @return 返回一级菜单集合
     */
    public List<Menu> getOneMenuList() {
        List<Menu> allMenus    = getAllMenus();
        List<Menu> oneMenuList = new ArrayList<Menu>();
        for (Menu menu : allMenus) {
            //如果当前菜单的parentId为0代表是顶级（一级）菜单
            if (menu.getParentId() == 0) {
                oneMenuList.add(menu);
            }
        }
        return oneMenuList;
    }

    /**
     * @param id
     * @return 返回下一层菜单集合
     */
    public List<Menu> getNextMenuList(int id) {
        List<Menu> allMenus     = getAllMenus();
        List<Menu> nextMenuList = new ArrayList<Menu>();
        for (Menu menu : allMenus) {
            if (menu.getParentId() == id) {  //判断上一层菜单的id是否是下一层的parentId
                nextMenuList.add(menu);
            }
        }
        return nextMenuList;
    }

    /**
     * 递归调用
     *
     * @param menuList
     */
    public void getMenuTree(List<Menu> menuList) {
        //第一次获得的是一级菜单集合，后面传入的是下一级菜单的集合
        List<Menu> oneMenuList = menuList;
        for (int i = 0; i < oneMenuList.size(); i++) {
            Menu menu = oneMenuList.get(i);
            //获得下一级的菜单集合
            List<Menu> nextMenuList = getNextMenuList(menu.getId());
            //递归终止条件判断，如果当前菜单的下一级菜单集合为空，则当前菜单为最后一级菜单
            if (nextMenuList.size() != 0) {
                //设置当前菜单的下一级菜单列表
                menu.setChildMenus(nextMenuList);
                //递归调用第二层的
                getMenuTree(nextMenuList);
            }


        }

    }

    /**
     * 测试输出结果
     * @param args
     */
    public static void main(String[] args) {
        MenuDao menuDao = new MenuDao();
        //先获得顶级菜单集合
        List<Menu> menuList = menuDao.getOneMenuList();
        //递归调用，最终目的是为了获得每一个Menu当中的childMenus
        menuDao.getMenuTree(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu.toString());
        }
    }

}

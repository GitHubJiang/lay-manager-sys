package com.lay.shop.modules.sys.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lay.shop.SpringTest;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.utils.TreeUtils;

public class MenuDaoTest extends SpringTest{
    
    @Autowired
    private MenuDao menuDao;
    @Test
    public void findUserInfoByLoginNameTest(){
        List<MenuCommand> menu = this.menuDao.findIndexMenu("LayJiang");
        MenuCommand menuCommand = (MenuCommand)TreeUtils.getInstance().toTreeNode(menu, 0l);
        ;
        this.printPrettyJson(menuCommand.getChildren().getList());
        ;
    }
    
}

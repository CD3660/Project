package com.example.and_mini_project;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuDAO {
    ArrayList<MenuVO> list;
    public ArrayList<MenuVO> initList() {
       list = new ArrayList<>();
        MenuVO vo1 = new MenuVO();
        vo1.setName("라면");
        vo1.setPrice(3000);
        vo1.setStock(10);
        MenuVO vo2 = new MenuVO();
        vo2.setName("김밥");
        vo2.setPrice(2500);
        vo2.setStock(10);
        MenuVO vo3 = new MenuVO();
        vo3.setName("만두");
        vo3.setPrice(4000);
        vo3.setStock(10);
        MenuVO vo4 = new MenuVO();
        vo4.setName("감자튀김");
        vo4.setPrice(5000);
        vo4.setStock(10);
        MenuVO vo5 = new MenuVO();
        vo5.setName("잔치국수");
        vo5.setPrice(5000);
        vo5.setStock(10);
        MenuVO vo6 = new MenuVO();
        vo6.setName("소떡소떡");
        vo6.setPrice(4000);
        vo6.setStock(10);
        MenuVO vo7 = new MenuVO();
        vo7.setName("순대");
        vo7.setPrice(6000);
        vo7.setStock(10);
        MenuVO vo8 = new MenuVO();
        vo8.setName("토스트");
        vo8.setPrice(3000);
        vo8.setStock(10);
        MenuVO vo9 = new MenuVO();
        vo9.setName("떡볶이");
        vo9.setPrice(6000);
        vo9.setStock(10);
        MenuVO vo10 = new MenuVO();
        vo10.setName("우동");
        vo10.setPrice(6000);
        vo10.setStock(10);

        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);
        list.add(vo7);
        list.add(vo8);
        list.add(vo9);
        list.add(vo10);

        return list;
    }

    public void eventSet(MenuActivity activity){
        ViewDTO dto = new ViewDTO();
        dto.btn_minus = activity.ramen_minus;
        dto.btn_plus = activity.ramen_plus;
        dto.vo = list.get(0);
        dto.tv_quantity = activity.ramen_quantity;
        dto.tv_stock = activity.ramen_stock;

    }
}

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

    public void eventSet(MenuActivity activity) {
        ViewDTO dto1 = new ViewDTO(
                activity.findViewById(R.id.ramen_plus),
                activity.findViewById(R.id.ramen_minus),
                activity.findViewById(R.id.ramen_stock),
                activity.findViewById(R.id.ramen_total),
                activity.findViewById(R.id.ramen_quantity),
                activity.findViewById(R.id.total_price),
                list.get(0)
        );
        ViewDTO dto2 = new ViewDTO(
                activity.findViewById(R.id.kimbab_plus),
                activity.findViewById(R.id.kimbab_minus),
                activity.findViewById(R.id.kimbab_stock),
                activity.findViewById(R.id.kimbab_total),
                activity.findViewById(R.id.kimbab_quantity),
                activity.findViewById(R.id.total_price),
                list.get(1)
        );
        ViewDTO dto3 = new ViewDTO(
                activity.findViewById(R.id.mandoo_plus),
                activity.findViewById(R.id.mandoo_minus),
                activity.findViewById(R.id.mandoo_stock),
                activity.findViewById(R.id.mandoo_total),
                activity.findViewById(R.id.mandoo_quantity),
                activity.findViewById(R.id.total_price),
                list.get(2)
        );
        ViewDTO dto4 = new ViewDTO(
                activity.findViewById(R.id.frenchfry_plus),
                activity.findViewById(R.id.frenchfry_minus),
                activity.findViewById(R.id.frenchfry_stock),
                activity.findViewById(R.id.frenchfry_total),
                activity.findViewById(R.id.frenchfry_quantity),
                activity.findViewById(R.id.total_price),
                list.get(3)
        );
        ViewDTO dto5 = new ViewDTO(
                activity.findViewById(R.id.noodle_plus),
                activity.findViewById(R.id.noodle_minus),
                activity.findViewById(R.id.noodle_stock),
                activity.findViewById(R.id.noodle_total),
                activity.findViewById(R.id.noodle_quantity),
                activity.findViewById(R.id.total_price),
                list.get(4)
        );
        ViewDTO dto6 = new ViewDTO(
                activity.findViewById(R.id.sotteok_plus),
                activity.findViewById(R.id.sotteok_minus),
                activity.findViewById(R.id.sotteok_stock),
                activity.findViewById(R.id.sotteok_total),
                activity.findViewById(R.id.sotteok_quantity),
                activity.findViewById(R.id.total_price),
                list.get(5)
        );
        ViewDTO dto7 = new ViewDTO(
                activity.findViewById(R.id.sundae_plus),
                activity.findViewById(R.id.sundae_minus),
                activity.findViewById(R.id.sundae_stock),
                activity.findViewById(R.id.sundae_total),
                activity.findViewById(R.id.sundae_quantity),
                activity.findViewById(R.id.total_price),
                list.get(6)
        );
        ViewDTO dto8 = new ViewDTO(
                activity.findViewById(R.id.toast_plus),
                activity.findViewById(R.id.toast_minus),
                activity.findViewById(R.id.toast_stock),
                activity.findViewById(R.id.toast_total),
                activity.findViewById(R.id.toast_quantity),
                activity.findViewById(R.id.total_price),
                list.get(7)
        );
        ViewDTO dto9 = new ViewDTO(
                activity.findViewById(R.id.tteokbokki_plus),
                activity.findViewById(R.id.tteokbokki_minus),
                activity.findViewById(R.id.tteokbokki_stock),
                activity.findViewById(R.id.tteokbokki_total),
                activity.findViewById(R.id.tteokbokki_quantity),
                activity.findViewById(R.id.total_price),
                list.get(8)
        );
        ViewDTO dto10 = new ViewDTO(
                activity.findViewById(R.id.udon_plus),
                activity.findViewById(R.id.udon_minus),
                activity.findViewById(R.id.udon_stock),
                activity.findViewById(R.id.udon_total),
                activity.findViewById(R.id.udon_quantity),
                activity.findViewById(R.id.total_price),
                list.get(9)
        );



    }

    public void parchase(){


         if (v.getId() == R.id.purchase) {
            Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
            ArrayList<OrderListVO> oList = new ArrayList<>();
            OrderListVO vo1 = new OrderListVO();
            vo1.setName(list.get(0).getName());
            vo1.setPrice(list.get(0).getPrice());
            vo1.setQuantity(Integer.parseInt(ramen_quantity.getText().toString()));
            OrderListVO vo2 = new OrderListVO();
            vo2.setName(list.get(1).getName());
            vo2.setPrice(list.get(1).getPrice());
            vo2.setQuantity(Integer.parseInt(kimbab_quantity.getText().toString()));
            OrderListVO vo3 = new OrderListVO();
            vo3.setName(list.get(2).getName());
            vo3.setPrice(list.get(2).getPrice());
            vo3.setQuantity(Integer.parseInt(mandoo_quantity.getText().toString()));
            OrderListVO vo4 = new OrderListVO();
            vo4.setName(list.get(3).getName());
            vo4.setPrice(list.get(3).getPrice());
            vo4.setQuantity(Integer.parseInt(frenchfry_quantity.getText().toString()));
            OrderListVO vo5 = new OrderListVO();
            vo5.setName(list.get(4).getName());
            vo5.setPrice(list.get(4).getPrice());
            vo5.setQuantity(Integer.parseInt(noodle_quantity.getText().toString()));
            OrderListVO vo6 = new OrderListVO();
            vo6.setName(list.get(5).getName());
            vo6.setPrice(list.get(5).getPrice());
            vo6.setQuantity(Integer.parseInt(sotteok_quantity.getText().toString()));
            OrderListVO vo7 = new OrderListVO();
            vo7.setName(list.get(6).getName());
            vo7.setPrice(list.get(6).getPrice());
            vo7.setQuantity(Integer.parseInt(sundae_quantity.getText().toString()));
            OrderListVO vo8 = new OrderListVO();
            vo8.setName(list.get(7).getName());
            vo8.setPrice(list.get(7).getPrice());
            vo8.setQuantity(Integer.parseInt(toast_quantity.getText().toString()));
            OrderListVO vo9 = new OrderListVO();
            vo9.setName(list.get(8).getName());
            vo9.setPrice(list.get(8).getPrice());
            vo9.setQuantity(Integer.parseInt(tteokbokki_quantity.getText().toString()));
            OrderListVO vo10 = new OrderListVO();
            vo10.setName(list.get(9).getName());
            vo10.setPrice(list.get(9).getPrice());
            vo10.setQuantity(Integer.parseInt(udon_quantity.getText().toString()));
            oList.add(vo1);
            oList.add(vo2);
            oList.add(vo3);
            oList.add(vo4);
            oList.add(vo5);
            oList.add(vo6);
            oList.add(vo7);
            oList.add(vo8);
            oList.add(vo9);
            oList.add(vo10);
            intent.putExtra("oList", oList);
            startActivity(intent);


    }
}

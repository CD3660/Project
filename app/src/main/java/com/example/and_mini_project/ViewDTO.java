package com.example.and_mini_project;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewDTO {
    Button btn_plus, btn_minus;
    TextView tv_stock, tv_total, tv_quantity, total_price;
    MenuVO vo;

    public ViewDTO(Button btn_plus, Button btn_minus, TextView tv_stock, TextView tv_total, TextView tv_quantity, TextView total_price, MenuVO vo) {
        this.btn_plus = btn_plus;
        this.btn_minus = btn_minus;
        this.tv_stock = tv_stock;
        this.tv_total = tv_total;
        this.tv_quantity = tv_quantity;
        this.total_price = total_price;
        this.vo = vo;
        setEvent();
    }

    public void setEvent(){
        btn_plus.setOnClickListener(v->{
            if (vo.getStock() == 0) {
                return;
            } else {
                String before = tv_quantity.getText().toString();
                tv_quantity.setText(Integer.parseInt(before) + 1 + "");
                vo.setStock(vo.getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + vo.getPrice() + "");
                tv_total.setText(Integer.parseInt(tv_total.getText().toString()) + vo.getPrice() + "");
                tv_stock.setText(Integer.parseInt(tv_stock.getText().toString()) - 1 + "");
            }
        });
        btn_minus.setOnClickListener(v -> {
            if (tv_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = tv_quantity.getText().toString();
                tv_quantity.setText(Integer.parseInt(before) - 1 + "");
                vo.setStock(vo.getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - vo.getPrice() + "");
                tv_total.setText(Integer.parseInt(tv_total.getText().toString()) - vo.getPrice() + "");
                tv_stock.setText(Integer.parseInt(tv_stock.getText().toString()) + 1 + "");
            }
        });
    }

}
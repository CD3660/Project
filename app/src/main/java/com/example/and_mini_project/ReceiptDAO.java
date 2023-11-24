package com.example.and_mini_project;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReceiptDAO {
    ArrayList<ReceiptVO> rList;
TextView receipt_total;

    public void initViewList(Receipt_Activity activity) {

        receipt_total = activity.findViewById(R.id.receipt_total);

        rList = new ArrayList<>();

        ReceiptVO vo1 = new ReceiptVO();
        ReceiptVO vo2 = new ReceiptVO();
        ReceiptVO vo3 = new ReceiptVO();
        ReceiptVO vo4 = new ReceiptVO();
        ReceiptVO vo5 = new ReceiptVO();
        ReceiptVO vo6 = new ReceiptVO();
        ReceiptVO vo7 = new ReceiptVO();
        ReceiptVO vo8 = new ReceiptVO();
        ReceiptVO vo9 = new ReceiptVO();
        ReceiptVO vo10 = new ReceiptVO();

        rList.add(vo1);
        rList.add(vo2);
        rList.add(vo3);
        rList.add(vo4);
        rList.add(vo5);
        rList.add(vo6);
        rList.add(vo7);
        rList.add(vo8);
        rList.add(vo9);
        rList.add(vo10);


        rList.get(0).setLayout(activity.findViewById(R.id.receipt_1));
        rList.get(1).setLayout(activity.findViewById(R.id.receipt_2));
        rList.get(2).setLayout(activity.findViewById(R.id.receipt_3));
        rList.get(3).setLayout(activity.findViewById(R.id.receipt_4));
        rList.get(4).setLayout(activity.findViewById(R.id.receipt_5));
        rList.get(5).setLayout(activity.findViewById(R.id.receipt_6));
        rList.get(6).setLayout(activity.findViewById(R.id.receipt_7));
        rList.get(7).setLayout(activity.findViewById(R.id.receipt_8));
        rList.get(8).setLayout(activity.findViewById(R.id.receipt_9));
        rList.get(9).setLayout(activity.findViewById(R.id.receipt_10));

        rList.get(0).setName(activity.findViewById(R.id.receipt_name1));
        rList.get(1).setName(activity.findViewById(R.id.receipt_name2));
        rList.get(2).setName(activity.findViewById(R.id.receipt_name3));
        rList.get(3).setName(activity.findViewById(R.id.receipt_name4));
        rList.get(4).setName(activity.findViewById(R.id.receipt_name5));
        rList.get(5).setName(activity.findViewById(R.id.receipt_name6));
        rList.get(6).setName(activity.findViewById(R.id.receipt_name7));
        rList.get(7).setName(activity.findViewById(R.id.receipt_name8));
        rList.get(8).setName(activity.findViewById(R.id.receipt_name9));
        rList.get(9).setName(activity.findViewById(R.id.receipt_name10));


        rList.get(0).setPrice(activity.findViewById(R.id.receipt_price1));
        rList.get(1).setPrice(activity.findViewById(R.id.receipt_price2));
        rList.get(2).setPrice(activity.findViewById(R.id.receipt_price3));
        rList.get(3).setPrice(activity.findViewById(R.id.receipt_price4));
        rList.get(4).setPrice(activity.findViewById(R.id.receipt_price5));
        rList.get(5).setPrice(activity.findViewById(R.id.receipt_price6));
        rList.get(6).setPrice(activity.findViewById(R.id.receipt_price7));
        rList.get(7).setPrice(activity.findViewById(R.id.receipt_price8));
        rList.get(8).setPrice(activity.findViewById(R.id.receipt_price9));
        rList.get(9).setPrice(activity.findViewById(R.id.receipt_price10));

        rList.get(0).setQuantity(activity.findViewById(R.id.receipt_quantity1));
        rList.get(1).setQuantity(activity.findViewById(R.id.receipt_quantity2));
        rList.get(2).setQuantity(activity.findViewById(R.id.receipt_quantity3));
        rList.get(3).setQuantity(activity.findViewById(R.id.receipt_quantity4));
        rList.get(4).setQuantity(activity.findViewById(R.id.receipt_quantity5));
        rList.get(5).setQuantity(activity.findViewById(R.id.receipt_quantity6));
        rList.get(6).setQuantity(activity.findViewById(R.id.receipt_quantity7));
        rList.get(7).setQuantity(activity.findViewById(R.id.receipt_quantity8));
        rList.get(8).setQuantity(activity.findViewById(R.id.receipt_quantity9));
        rList.get(9).setQuantity(activity.findViewById(R.id.receipt_quantity10));


        rList.get(0).setTotal(activity.findViewById(R.id.receipt_total1));
        rList.get(1).setTotal(activity.findViewById(R.id.receipt_total2));
        rList.get(2).setTotal(activity.findViewById(R.id.receipt_total3));
        rList.get(3).setTotal(activity.findViewById(R.id.receipt_total4));
        rList.get(4).setTotal(activity.findViewById(R.id.receipt_total5));
        rList.get(5).setTotal(activity.findViewById(R.id.receipt_total6));
        rList.get(6).setTotal(activity.findViewById(R.id.receipt_total7));
        rList.get(7).setTotal(activity.findViewById(R.id.receipt_total8));
        rList.get(8).setTotal(activity.findViewById(R.id.receipt_total9));
        rList.get(9).setTotal(activity.findViewById(R.id.receipt_total10));


    }

    public ArrayList<OrderListVO> initList() {
        ArrayList<OrderListVO> oList = new ArrayList<>();
        OrderListVO vo1 = new OrderListVO();
        OrderListVO vo2 = new OrderListVO();
        OrderListVO vo3 = new OrderListVO();
        OrderListVO vo4 = new OrderListVO();
        OrderListVO vo5 = new OrderListVO();
        OrderListVO vo6 = new OrderListVO();
        OrderListVO vo7 = new OrderListVO();
        OrderListVO vo8 = new OrderListVO();
        OrderListVO vo9 = new OrderListVO();
        OrderListVO vo10 = new OrderListVO();
        vo1.setName("라면");
        vo2.setName("라면");
        vo3.setName("라면");
        vo4.setName("라면");
        vo5.setName("라면");
        vo6.setName("라면");
        vo7.setName("라면");
        vo8.setName("라면");
        vo9.setName("라면");
        vo10.setName("라면");

        vo1.setPrice(3000);
        vo2.setPrice(3000);
        vo3.setPrice(3000);
        vo4.setPrice(3000);
        vo5.setPrice(3000);
        vo6.setPrice(3000);
        vo7.setPrice(3000);
        vo8.setPrice(3000);
        vo9.setPrice(3000);
        vo10.setPrice(3000);

        vo1.setQuantity(2);
        vo2.setQuantity(2);
        vo3.setQuantity(2);
        vo4.setQuantity(2);
        vo5.setQuantity(2);
        vo6.setQuantity(2);
        vo7.setQuantity(2);
        vo8.setQuantity(2);
        vo9.setQuantity(2);
        vo10.setQuantity(2);


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
        return oList;
    }

    public void display(ArrayList<OrderListVO> oList) {


        for (int i = 0; i < 10; i++) {
            if (oList.get(i).getQuantity() == 0) {
                rList.get(i).getLayout().setVisibility(View.GONE);
            } else {
                rList.get(i).getName().setText(oList.get(i).getName());
                rList.get(i).getPrice().setText(oList.get(i).getPrice() + " 원");
                rList.get(i).getQuantity().setText(oList.get(i).getQuantity() + " 개");
                rList.get(i).getTotal().setText(oList.get(i).getPrice() * oList.get(i).getQuantity() + " 원");
            }
        }

       int sum = 0;
    for(int i =0; i<10; i++) {
       sum += oList.get(i).getPrice() * oList.get(i).getQuantity();

    }

    receipt_total.setText(sum+"");


    }
}

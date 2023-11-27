package com.example.and_mini_project;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class PayDAO {
    ArrayList<OrderListVO> oList;
    TextView pay_total;

    public void payViewList(PayActivity activity) {
        ArrayList<TextView> tvList = new ArrayList<>();
        TextView tv1 = activity.findViewById(R.id.listview_list1);
        TextView tv2 = activity.findViewById(R.id.listview_list2);
        TextView tv3 = activity.findViewById(R.id.listview_list3);
        TextView tv4 = activity.findViewById(R.id.listview_list4);
        TextView tv5 = activity.findViewById(R.id.listview_list5);
        TextView tv6 = activity.findViewById(R.id.listview_list6);
        TextView tv7 = activity.findViewById(R.id.listview_list7);
        TextView tv8 = activity.findViewById(R.id.listview_list8);
        TextView tv9 = activity.findViewById(R.id.listview_list9);
        TextView tv10 = activity.findViewById(R.id.listview_list10);
        tvList.add(tv1);
        tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);
        tvList.add(tv5);
        tvList.add(tv6);
        tvList.add(tv7);
        tvList.add(tv8);
        tvList.add(tv9);
        tvList.add(tv10);

        for (int i = 0; i <= 9; i++) {
            if(oList.get(i).getQuantity() == 0){
                tvList.get(i).setVisibility(View.GONE);
            }
            tvList.get(i).setText(oList.get(i).getName() + " " +
                    oList.get(i).getPrice() + " " +
                    oList.get(i).getQuantity() + " "
                    + (oList.get(i).getPrice() * oList.get(i).getQuantity()));
        }
    }

    public int total (){
        int total = 0;
        for (int i = 0; i <= 9; i++) {
            total += oList.get(i).getPrice() * oList.get(i).getQuantity();
        }
        return total;
    }

    public PayDAO(ArrayList<OrderListVO> oList) {
        this.oList = oList;
    }
}

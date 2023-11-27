package com.example.and_mini_project;

import android.widget.LinearLayout;
import android.widget.TextView;

public class ReceiptVO {
    private LinearLayout layout;
    private TextView name, price, quantity, total;



    public TextView getCharge() {
        return charge;
    }

    public void setCharge(TextView charge) {
        this.charge = charge;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getQuantity() {
        return quantity;
    }

    public void setQuantity(TextView quantity) {
        this.quantity = quantity;
    }

    public TextView getTotal() {
        return total;
    }

    public void setTotal(TextView total) {
        this.total = total;
    }
}

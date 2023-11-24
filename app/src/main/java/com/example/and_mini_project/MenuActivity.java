package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button ramen_minus, ramen_plus, frenchfry_minus, frenchfry_plus, kimbab_plus, kimbab_minus, mandoo_plus, mandoo_minus;
    Button noodle_minus, noodle_plus, sotteok_minus, sotteok_plus, sundae_minus, sundae_plus, toast_minus, toast_plus;
    Button tteokbokki_minus, tteokbokki_plus, udon_minus, udon_plus, purchase;
    TextView frenchfry_quantity, kimbab_quantity, mandoo_quantity, noodle_quantity, ramen_quantity, sotteok_quantity;
    TextView sundae_quantity, toast_quantity, tteokbokki_quantity, udon_quantity, total_price;
    TextView ramen_stock, frenchfry_stock, kimbab_stock, mandoo_stock, noodle_stock, sotteok_stock, sundae_stock, toast_stock;
    TextView tteokbokki_stock, udon_stock;
    TextView frenchfry_total, kimbab_total, mandoo_total, noodle_total, ramen_total, sotteok_total;
    TextView sundae_total, toast_total, tteokbokki_total, udon_total;
    ArrayList<MenuVO> list;
    final String TAG = "클릭";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuDAO dao = new MenuDAO();
        setContentView(R.layout.activity_menu);
        list = dao.initList();
        idCheck();
        buttonOnClickSet();
    }

    public void idCheck() {
        ramen_minus = findViewById(R.id.ramen_minus);
        ramen_plus = findViewById(R.id.ramen_plus);
        frenchfry_minus = findViewById(R.id.frenchfry_minus);
        frenchfry_plus = findViewById(R.id.frenchfry_plus);
        kimbab_plus = findViewById(R.id.kimbab_plus);
        kimbab_minus = findViewById(R.id.kimbab_minus);
        mandoo_plus = findViewById(R.id.mandoo_plus);
        mandoo_minus = findViewById(R.id.mandoo_minus);
        noodle_minus = findViewById(R.id.noodle_minus);
        noodle_plus = findViewById(R.id.noodle_plus);
        sotteok_minus = findViewById(R.id.sotteok_minus);
        sotteok_plus = findViewById(R.id.sotteok_plus);
        sundae_minus = findViewById(R.id.sundae_minus);
        sundae_plus = findViewById(R.id.sundae_plus);
        toast_minus = findViewById(R.id.toast_minus);
        toast_plus = findViewById(R.id.toast_plus);
        tteokbokki_minus = findViewById(R.id.tteokbokki_minus);
        tteokbokki_plus = findViewById(R.id.tteokbokki_plus);
        udon_minus = findViewById(R.id.udon_minus);
        udon_plus = findViewById(R.id.udon_plus);
        purchase = findViewById(R.id.purchase);
        frenchfry_quantity = findViewById(R.id.frenchfry_quantity);
        kimbab_quantity = findViewById(R.id.kimbab_quantity);
        mandoo_quantity = findViewById(R.id.mandoo_quantity);
        noodle_quantity = findViewById(R.id.noodle_quantity);
        ramen_quantity = findViewById(R.id.ramen_quantity);
        sotteok_quantity = findViewById(R.id.sotteok_quantity);
        sundae_quantity = findViewById(R.id.sundae_quantity);
        toast_quantity = findViewById(R.id.toast_quantity);
        tteokbokki_quantity = findViewById(R.id.tteokbokki_quantity);
        udon_quantity = findViewById(R.id.udon_quantity);
        total_price = findViewById(R.id.total_price);
        ramen_stock = findViewById(R.id.ramen_stock);
        frenchfry_stock = findViewById(R.id.frenchfry_stock);
        kimbab_stock = findViewById(R.id.kimbab_stock);
        mandoo_stock = findViewById(R.id.mandoo_stock);
        noodle_stock = findViewById(R.id.noodle_stock);
        sotteok_stock = findViewById(R.id.sotteok_stock);
        sundae_stock = findViewById(R.id.sundae_stock);
        toast_stock = findViewById(R.id.toast_stock);
        tteokbokki_stock = findViewById(R.id.tteokbokki_stock);
        udon_stock = findViewById(R.id.udon_stock);
        frenchfry_total = findViewById(R.id.frenchfry_total);
        kimbab_total = findViewById(R.id.kimbab_total);
        mandoo_total = findViewById(R.id.mandoo_total);
        noodle_total = findViewById(R.id.noodle_total);
        ramen_total = findViewById(R.id.ramen_total);
        sotteok_total = findViewById(R.id.sotteok_total);
        sundae_total = findViewById(R.id.sundae_total);
        toast_total = findViewById(R.id.toast_total);
        tteokbokki_total = findViewById(R.id.tteokbokki_total);
        udon_total = findViewById(R.id.udon_total);
    }

    public void buttonOnClickSet() {
        ramen_minus.setOnClickListener(this);
        ramen_plus.setOnClickListener(this);
        frenchfry_minus.setOnClickListener(this);
        frenchfry_plus.setOnClickListener(this);
        kimbab_plus.setOnClickListener(this);
        kimbab_minus.setOnClickListener(this);
        mandoo_plus.setOnClickListener(this);
        mandoo_minus.setOnClickListener(this);
        noodle_minus.setOnClickListener(this);
        noodle_plus.setOnClickListener(this);
        sotteok_minus.setOnClickListener(this);
        sotteok_plus.setOnClickListener(this);
        sundae_minus.setOnClickListener(this);
        sundae_plus.setOnClickListener(this);
        toast_minus.setOnClickListener(this);
        toast_plus.setOnClickListener(this);
        tteokbokki_minus.setOnClickListener(this);
        tteokbokki_plus.setOnClickListener(this);
        udon_minus.setOnClickListener(this);
        udon_plus.setOnClickListener(this);
        purchase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ramen_minus) {
            if (ramen_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = ramen_quantity.getText().toString();
                ramen_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(0).setStock(list.get(0).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(0).getPrice() +"" );
                ramen_total.setText(Integer.parseInt(ramen_total.getText().toString()) - list.get(0).getPrice() +"" );
                ramen_stock.setText(Integer.parseInt(ramen_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.ramen_plus) {
            if (list.get(0).getStock() == 0) {
                return;
            } else {
                String before = ramen_quantity.getText().toString();
                ramen_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(0).setStock(list.get(0).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(0).getPrice() +"" );
                ramen_total.setText(Integer.parseInt(ramen_total.getText().toString()) + list.get(0).getPrice() +"" );
                ramen_stock.setText(Integer.parseInt(ramen_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.kimbab_minus) {
            if (kimbab_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = kimbab_quantity.getText().toString();
                kimbab_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(1).setStock(list.get(1).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(1).getPrice() +"" );
                kimbab_total.setText(Integer.parseInt(kimbab_total.getText().toString()) - list.get(1).getPrice() +"" );
                kimbab_stock.setText(Integer.parseInt(kimbab_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.kimbab_plus) {
            if (list.get(1).getStock() == 0) {
                return;
            } else {
                String before = kimbab_quantity.getText().toString();
                kimbab_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(1).setStock(list.get(1).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(1).getPrice() +"" );
                kimbab_total.setText(Integer.parseInt(kimbab_total.getText().toString()) + list.get(1).getPrice() +"" );
                kimbab_stock.setText(Integer.parseInt(kimbab_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.mandoo_minus) {
            if (mandoo_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = mandoo_quantity.getText().toString();
                mandoo_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(2).setStock(list.get(2).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(2).getPrice() +"" );
                mandoo_total.setText(Integer.parseInt(mandoo_total.getText().toString()) - list.get(2).getPrice() +"" );
                mandoo_stock.setText(Integer.parseInt(mandoo_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.mandoo_plus) {
            if (list.get(2).getStock() == 0) {
                return;
            } else {
                String before = mandoo_quantity.getText().toString();
                mandoo_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(2).setStock(list.get(2).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(2).getPrice() +"" );
                mandoo_total.setText(Integer.parseInt(mandoo_total.getText().toString()) + list.get(2).getPrice() +"" );
                mandoo_stock.setText(Integer.parseInt(mandoo_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.frenchfry_minus) {
            if (frenchfry_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = frenchfry_quantity.getText().toString();
                frenchfry_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(3).setStock(list.get(3).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(3).getPrice() +"" );
                frenchfry_total.setText(Integer.parseInt(frenchfry_total.getText().toString()) - list.get(3).getPrice() +"" );
                frenchfry_stock.setText(Integer.parseInt(frenchfry_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.frenchfry_plus) {

            if (list.get(3).getStock() == 0) {
                return;
            } else {
                Log.d(TAG, "onClick: ");
                String before = frenchfry_quantity.getText().toString();
                frenchfry_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(3).setStock(list.get(3).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(3).getPrice() +"" );
                frenchfry_total.setText(Integer.parseInt(frenchfry_total.getText().toString()) + list.get(3).getPrice() +"" );
                frenchfry_stock.setText(Integer.parseInt(frenchfry_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.noodle_minus) {
            if (noodle_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = noodle_quantity.getText().toString();
                noodle_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(4).setStock(list.get(4).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(4).getPrice() +"" );
                noodle_total.setText(Integer.parseInt(noodle_total.getText().toString()) - list.get(4).getPrice() +"" );
                noodle_stock.setText(Integer.parseInt(noodle_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.noodle_plus) {
            if (list.get(4).getStock() == 0) {
                return;
            } else {
                String before = noodle_quantity.getText().toString();
                noodle_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(4).setStock(list.get(4).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(4).getPrice() +"" );
                noodle_total.setText(Integer.parseInt(noodle_total.getText().toString()) + list.get(4).getPrice() +"" );
                noodle_stock.setText(Integer.parseInt(noodle_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.sotteok_minus) {
            if (sotteok_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = sotteok_quantity.getText().toString();
                sotteok_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(5).setStock(list.get(5).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(5).getPrice() +"" );
                sotteok_total.setText(Integer.parseInt(sotteok_total.getText().toString()) - list.get(5).getPrice() +"" );
                sotteok_stock.setText(Integer.parseInt(sotteok_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.sotteok_plus) {
            if (list.get(5).getStock() == 0) {
                return;
            } else {
                String before = sotteok_quantity.getText().toString();
                sotteok_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(5).setStock(list.get(5).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(5).getPrice() +"" );
                sotteok_total.setText(Integer.parseInt(sotteok_total.getText().toString()) + list.get(5).getPrice() +"" );
                sotteok_stock.setText(Integer.parseInt(sotteok_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.sundae_minus) {
            if (sundae_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = sundae_quantity.getText().toString();
                sundae_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(6).setStock(list.get(6).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(6).getPrice() +"" );
                sundae_total.setText(Integer.parseInt(sundae_total.getText().toString()) - list.get(6).getPrice() +"" );
                sundae_stock.setText(Integer.parseInt(sundae_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.sundae_plus) {
            if (list.get(6).getStock() == 0) {
                return;
            } else {
                String before = sundae_quantity.getText().toString();
                sundae_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(6).setStock(list.get(6).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(6).getPrice() +"" );
                sundae_total.setText(Integer.parseInt(sundae_total.getText().toString()) + list.get(6).getPrice() +"" );
                sundae_stock.setText(Integer.parseInt(sundae_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.toast_minus) {
            if (toast_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = toast_quantity.getText().toString();
                toast_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(7).setStock(list.get(7).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(7).getPrice() +"" );
                toast_total.setText(Integer.parseInt(toast_total.getText().toString()) - list.get(7).getPrice() +"" );
                toast_stock.setText(Integer.parseInt(toast_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.toast_plus) {
            if (list.get(7).getStock() == 0) {
                return;
            } else {
                String before = toast_quantity.getText().toString();
                toast_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(7).setStock(list.get(7).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(7).getPrice() +"" );
                toast_total.setText(Integer.parseInt(toast_total.getText().toString()) + list.get(7).getPrice() +"" );
                toast_stock.setText(Integer.parseInt(toast_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.tteokbokki_minus) {
            if (tteokbokki_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = tteokbokki_quantity.getText().toString();
                tteokbokki_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(8).setStock(list.get(8).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(8).getPrice() +"" );
                tteokbokki_total.setText(Integer.parseInt(tteokbokki_total.getText().toString()) - list.get(8).getPrice() +"" );
                tteokbokki_stock.setText(Integer.parseInt(tteokbokki_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.tteokbokki_plus) {
            if (list.get(8).getStock() == 0) {
                return;
            } else {
                String before = tteokbokki_quantity.getText().toString();
                tteokbokki_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(8).setStock(list.get(8).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(8).getPrice() +"" );
                tteokbokki_total.setText(Integer.parseInt(tteokbokki_total.getText().toString()) + list.get(8).getPrice() +"" );
                tteokbokki_stock.setText(Integer.parseInt(tteokbokki_stock.getText().toString()) - 1 +"" );
            }
        }  else if (v.getId() == R.id.udon_minus) {
            if (udon_quantity.getText().toString().equals("0")) {
                return;
            } else {
                String before = udon_quantity.getText().toString();
                udon_quantity.setText(Integer.parseInt(before) - 1 + "");
                list.get(9).setStock(list.get(9).getStock() + 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) - list.get(9).getPrice() +"" );
                udon_total.setText(Integer.parseInt(udon_total.getText().toString()) - list.get(9).getPrice() +"" );
                udon_stock.setText(Integer.parseInt(udon_stock.getText().toString()) + 1 +"" );
            }
        } else if (v.getId() == R.id.udon_plus) {
            if (list.get(9).getStock() == 0) {
                return;
            } else {
                String before = udon_quantity.getText().toString();
                udon_quantity.setText(Integer.parseInt(before) + 1 + "");
                list.get(9).setStock(list.get(9).getStock() - 1);
                total_price.setText(Integer.parseInt(total_price.getText().toString()) + list.get(9).getPrice() +"" );
                udon_total.setText(Integer.parseInt(udon_total.getText().toString()) + list.get(9).getPrice() +"" );
                udon_stock.setText(Integer.parseInt(udon_stock.getText().toString()) - 1 +"" );
            }
        } else if (v.getId() == R.id.purchase) {
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



}
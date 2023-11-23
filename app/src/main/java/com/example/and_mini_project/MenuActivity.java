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
        setContentView(R.layout.activity_menu);
        list = initList();
        idCheck();
        buttonOnClickSet();
    }

    public ArrayList<MenuVO> initList() {
        ArrayList<MenuVO> list = new ArrayList<>();
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
        vo1.setStock(10);
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
            Log.d(TAG, "onClick: ");
            if (list.get(3).getStock() == 0) {
                return;
            } else {
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
//            Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
//            startActivity(intent);
        }
    }


}
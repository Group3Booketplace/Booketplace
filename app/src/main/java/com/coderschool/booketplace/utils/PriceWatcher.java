package com.coderschool.booketplace.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by dattran on 11/18/16.
 */

// TODO: divide large number by comma
public class PriceWatcher implements TextWatcher {
    private EditText et;
    private DecimalFormat numberFormat;

    public PriceWatcher(EditText editText) {
        this.et = editText;
        this.numberFormat = new DecimalFormat("#,###");
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        et.setText(getFormattedPrice());
    }

    @Override
    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);

    }

    private String getFormattedPrice() {
        String price = et.getText().toString();
        long amount = Long.parseLong(price);
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }
}

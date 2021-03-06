package ru.lee.dmitry.myapplication;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etVol;
    EditText etPrice;
    TextView tvResult;
    Button btnResult;
    Double volume, price, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etVol = (EditText) findViewById(R.id.etVol);
        etPrice = (EditText) findViewById(R.id.etPrice);
        tvResult = (TextView) findViewById(R.id.tvResult);
        etPrice.addTextChangedListener(textWatcher);
        etVol.addTextChangedListener(textWatcher);
        etVol.setOnTouchListener(onTouchListener);
        etPrice.setOnTouchListener(onTouchListener);
        tvResult.setOnClickListener(clickListener);
    }

    private TextView.OnClickListener clickListener = new TextView.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvResult.setText("");
            etPrice.setText("");
            etVol.setText("");
        }
    };


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.toString().equals("")) {
                return;
            }

            String vol = etVol.getText().toString();
            String pr = etPrice.getText().toString();

            if (vol.equals("") || pr.equals("")){
                return;
            }

            volume = Double.parseDouble(vol);
            price = Double.parseDouble(pr);

            result = 1000 * price / volume;
            String res = getResources().getString(R.string.result_tag);
            tvResult.setText(String.format("%s : %.2f руб.", res, result));

        }
    };

    private EditText.OnTouchListener onTouchListener = new EditText.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                final Drawable x = getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel);
                if (event.getX() > v.getWidth() - v.getPaddingRight()- x.getIntrinsicWidth()){
                    ((EditText) v).setText("");
                    tvResult.setText("");
                }
            }
            return false;
        }
    };

}

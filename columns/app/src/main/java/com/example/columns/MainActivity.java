package com.example.columns;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout llMain1;
    LinearLayout llMain2;
    LinearLayout llMain3;
    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain1 = (LinearLayout) findViewById(R.id.llMain1);
        llMain2 = (LinearLayout) findViewById(R.id.llMain2);
        llMain3 = (LinearLayout) findViewById(R.id.llMain3);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCreate:
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int btnGravity = Gravity.CENTER_HORIZONTAL;

                switch (rgGravity.getCheckedRadioButtonId()){
                    case R.id.rbLeft:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        lParams.gravity = btnGravity;
                        Button btn1 = new Button(this);
                        btn1.setText(etName.getText().toString());
                        llMain1.addView(btn1, lParams);
                        break;
                    case R.id.rbCenter:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        lParams.gravity = btnGravity;
                        Button btn2 = new Button(this);
                        btn2.setText(etName.getText().toString());
                        llMain2.addView(btn2, lParams);
                        break;
                    case R.id.rbRight:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        lParams.gravity = btnGravity;
                        Button btn3 = new Button(this);
                        btn3.setText(etName.getText().toString());
                        llMain3.addView(btn3, lParams);
                        break;
                }

            case R.id.btnClear:
                llMain1.removeAllViews();
                llMain2.removeAllViews();
                llMain3.removeAllViews();
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package com.example.administrator.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button bt1;
    private EditText et1;
    private RadioButton mrb1;
    private RadioButton wrb1;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.calculate);
        et1 = (EditText) findViewById(R.id.weight);
        mrb1 = (RadioButton) findViewById(R.id.man);
        wrb1 = (RadioButton) findViewById(R.id.woman);
        tv1 = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }
    private void registerEvent(){
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!et1.getText().toString().trim().equals("")){
                    if(mrb1.isChecked()||wrb1.isChecked()){
                        Double weight = Double.parseDouble(et1.getText().toString());
                        StringBuffer sbf = new StringBuffer();
                        sbf.append("---------评估结果-----------\n");
                        if(mrb1.isChecked()){
                            sbf.append("男性标准身高");
                            double result = evaluateHeight(weight,"男");
                            sbf.append((int)result+"厘米");
                        }else{
                            sbf.append("女性标准身高");
                            double result = evaluateHeight(weight,"女");
                            sbf.append((int)result+"厘米");
                        }
                        tv1.setText(sbf.toString());
                    }else{
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请输入体重");
                }
            }
        });
    }
    private void showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统提示");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    private double evaluateHeight(double weight,String sex){
        double height = 0;
        if(sex == "男"){
            height = 170 -(62 - weight)/0.6;
        }else if(sex == "女"){
            height = 158 - (52-weight)/0.5;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.axle;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    private static HashMap<String, Integer> priorities = new HashMap(){{
        put("+", 1);
        put("-", 1);
        put("*", 2);
        put("/", 2);
        put("^", 3);
    }};

    EditText etNum2;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnClear, btnSin, btnPoint, btnDiv, btnRes, btnMinus, btnPlus, btnResult, btnLeftBrackets, btnRightBrackets;

    TextView tvResult;

    String result = "";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим элементы
        etNum2 = (EditText) findViewById(R.id.etNum2);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnSin = (Button) findViewById(R.id.btnSin);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnRes = (Button) findViewById(R.id.btnRes);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnLeftBrackets = (Button) findViewById(R.id.btnLeftBrackets);
        btnRightBrackets = (Button) findViewById(R.id.btnRightBrackets);

        // прописываем обработчик
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnClear.setOnClickListener(this);
        btnSin.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnRes.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        float num1 = 0;
        float num2 = 0;
        //float result = 0;
        String tmp = "";

        // определяем нажатую кнопку и выполняем соответствующую операцию
        // в oper пишем операцию, потом будем использовать в выводе

        switch (v.getId()) {
            case R.id.btn0:
                tmp = "0";
                break;
            case R.id.btn1:
                tmp = "1";
                break;
            case R.id.btn2:
                tmp = "2";
                break;
            case R.id.btn3:
                tmp = "3";
                break;
            case R.id.btn4:
                tmp = "4";
                break;
            case R.id.btn5:
                tmp = "5";
                break;
            case R.id.btn6:
                tmp = "6";
                break;
            case R.id.btn7:
                tmp = "7";
                break;
            case R.id.btn8:
                tmp = "8";
                break;
            case R.id.btn9:
                tmp = "9";
                break;
            case R.id.btnClear:
                tvResult.setText("");
                result = "";
                break;
            case R.id.btnSin:
                tmp = "SIN";
                break;
            case R.id.btnPoint:
                tmp = ".";
                break;
            case R.id.btnDiv:
                tmp = " / ";
                break;
            case R.id.btnRes:
                tmp = " * ";
                break;
            case R.id.btnMinus:
                tmp = " - ";
                break;
            case R.id.btnPlus:
                tmp = " + ";
                break;
            case R.id.btnResult:
                tmp = "=";
                calculate();
                return;
            case R.id.btnLeftBrackets:
                tmp = " ( ";
                break;
            case R.id.btnRightBrackets:
                tmp = " ) ";
                break;
            default:
                break;
        }

//      result = "1.2 + 5.3"
//        if (tmp.equals("=")){
//            String[] subStr = result.split(" ");
////            subStr[0] - первое число
////            subStr[1] - знак
////            subStr[2] - второе число
//            double a = Double.parseDouble(subStr[0]); // преобразование из строки в число
//            double b = Double.parseDouble(subStr[2]); // преобразование из строки в число
//            String sign =  subStr[1];
//            if(sign.equals("+")){
//               result = Double.toString(a + b);
//            }
//            if(sign.equals("-")){
//                result = Double.toString(a - b);
//            }
//            if(sign.equals("*")){
//                result = Double.toString(a * b);
//            }
//            if(sign.equals("/")){
//                if (b == 0){
//                result = Double.toString(a / b);
//                }
//            }
//            if(sign.equals("SIN")){
//                result = Double.toString(Math.sin(a));
//            }
//
//        }

        // формируем строку вывода
        result = result + tmp + "";
        etNum2.setText(result);

    }

    private void calculate() {
        String expresion = etNum2.getText().toString();
        String[] elements = expresion.split(" ");
        Stack<Double> numbers = new Stack<>();
        Stack<String> operations = new Stack<>();
        int i = 0;
        while (i < elements.length){
            if (isNumber(elements[i])){
                numbers.push(Double.parseDouble(elements[i]));
                i++;
            } else {


                if(elements[i].equals("(")){
                    operations.push(elements[i]);
                    i++;
                    continue;
                }

                if(elements[i].equals(")")){
                    //TODO calculate
                    continue;
                }


                if (operations.empty() || isMajorPriority(elements[i], operations.peek()) ){
                    operations.push(elements[i]);
                    i++;
                    continue;
                }

                if (!isMajorPriority(elements[i], operations.peek()) ){
                    Double lastNumber = numbers.pop();
                    Double prevNumber = numbers.pop();
                    String lastOperation = operations.pop();
                    Double result = execOperation(prevNumber, lastOperation, lastNumber);
                    numbers.push(result);
                }
            }

        }

        System.out.println("Size of numbers : " + numbers.size());
        System.out.println("Size of operations : " + operations.size());
        Double lastNumber = numbers.pop();
        Double prevNumber = numbers.pop();
        String lastOperation = operations.pop();
        result = execOperation(prevNumber, lastOperation, lastNumber) + " ";
        tvResult.setText(result + "");
    }

    private Double execOperation (Double a, String operation, Double b){
        if (operation.equals("+")){
            return a + b;
        }
        if (operation.equals("-")){
            return a - b;
        }
        if (operation.equals("/")){
            return a / b;
        }
        if (operation.equals("*")){
            return a * b;
        }
        return null;
    }

    private boolean isMajorPriority(String currentElement, String lastElement) {

        if (priorities.get(currentElement) > priorities.get(lastElement)){
            return true;
        } else {
            return false;
        }

    }

    private boolean isNumber (String n){
        try {
            Double.parseDouble(n);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// TODO Auto-generated method stub
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");
        return super.onCreateOptionsMenu(menu);
    }

    // обработка нажатий на пункты меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// TODO Auto-generated method stub
        switch (item.getItemId()) {
            case MENU_RESET_ID:
// очищаем поля
                etNum2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
// выход из приложения
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

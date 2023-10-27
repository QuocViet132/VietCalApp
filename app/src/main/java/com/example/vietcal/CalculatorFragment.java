package com.example.vietcal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;

public class CalculatorFragment extends Fragment {
    private View mView;
    private TextView tv_screen;
    private Double firstNum = 0.0;
    private Double secondNum = 0.0;
    private Double results = 0.0;
    private String showScreen;
    private String gExpression;
    DecimalFormat format = new DecimalFormat("0.######");
    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_calculator, container, false);
        ListenClick();
        return mView;
    }

    // method ListenClick() is used to listen for button click events to perform appropriate actions.
    private void ListenClick() {
        tv_screen = mView.findViewById(R.id.tv_result_calculate);

        Button btnAc = mView.findViewById(R.id.btn_ac);
        Button btnPlus = mView.findViewById(R.id.btn_plus);
        Button btnSubtraction = mView.findViewById(R.id.btn_subtraction);
        Button btnMultiply = mView.findViewById(R.id.btn_multiply);
        Button btnDivide = mView.findViewById(R.id.btn_divide);
        Button btnDot = mView.findViewById(R.id.btn_dot);
        Button btnPercent = mView.findViewById(R.id.btn_percent);
        Button btnNegate = mView.findViewById(R.id.btn_negate);
        Button btnRemove = mView.findViewById(R.id.btn_remove);
        Button btnEqual = mView.findViewById(R.id.btn_equal);

        Button btn0 = mView.findViewById(R.id.btn_0);
        Button btn1 = mView.findViewById(R.id.btn_1);
        Button btn2 = mView.findViewById(R.id.btn_2);
        Button btn3 = mView.findViewById(R.id.btn_3);
        Button btn4 = mView.findViewById(R.id.btn_4);
        Button btn5 = mView.findViewById(R.id.btn_5);
        Button btn6 = mView.findViewById(R.id.btn_6);
        Button btn7 = mView.findViewById(R.id.btn_7);
        Button btn8 = mView.findViewById(R.id.btn_8);
        Button btn9 = mView.findViewById(R.id.btn_9);

        ArrayList<Button> listNum = new ArrayList<>();
        listNum.add(btn0);
        listNum.add(btn1);
        listNum.add(btn2);
        listNum.add(btn3);
        listNum.add(btn4);
        listNum.add(btn5);
        listNum.add(btn6);
        listNum.add(btn7);
        listNum.add(btn8);
        listNum.add(btn9);
        listNum.add(btnDot);

        for(Button num: listNum) {
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!tv_screen.getText().toString().equals("0")) {
                        tv_screen.setText(MessageFormat.format("{0}{1}",
                                tv_screen.getText().toString(),
                                num.getText().toString()));
                    }else {
                        tv_screen.setText(num.getText().toString());
                    }
                }
            });
        }

        ArrayList<Button> listBtnFunction = new ArrayList<>();
        listBtnFunction.add(btnAc);
        listBtnFunction.add(btnPlus);
        listBtnFunction.add(btnSubtraction);
        listBtnFunction.add(btnMultiply);
        listBtnFunction.add(btnDivide);
        listBtnFunction.add(btnPercent);
        listBtnFunction.add(btnNegate);
        listBtnFunction.add(btnEqual);
        listBtnFunction.add(btnRemove);

        for(Button button: listBtnFunction) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(button.getText().toString().equals("AC")) {
                        tv_screen.setText("0");
                        firstNum = 0.0;
                        secondNum = 0.0;
                    }else if (button.getText().toString().equals("+")) {
                        calculateExpressions("+");
                    }else if(button.getText().toString().equals("-")) {
                        calculateExpressions("-");
                    }else if(button.getText().toString().equals("X")) {
                        calculateExpressions("x");
                    }else if(button.getText().toString().equals("/")) {
                        calculateExpressions("/");
                    }else if(button.getText().toString().equals("%")) {
                        if(checkCase() == 3) {
                            firstNum /= 100;
                            tv_screen.setText(format.format(firstNum));
                        } else if (checkCase() == 1) {
                            secondNum /= 100;
                            tv_screen.setText(MessageFormat.format("{0}{1}\n{2}",
                                    format.format(firstNum),
                                    gExpression,
                                    format.format(secondNum)));
                        }
                    }else if(button.getText().toString().equals("=")) {
                        if(!tv_screen.getText().toString().isEmpty() && checkCase()==1) {
                            calculateExpressions(gExpression);
                        }
                        if(gExpression != null && !tv_screen.getText().toString().equals("Math Error")) {
                            tv_screen.setText(MessageFormat.format("{0} {1} {2} =\n{3}",
                                    format.format(firstNum),
                                    gExpression,
                                    format.format(secondNum),
                                    format.format(results)));
                        }
                    }else if (button.getText().toString().equals("+/-")) {
                        if(checkCase() == 3) {
                            firstNum = -firstNum;
                            tv_screen.setText(format.format(firstNum));
                        } else if (checkCase() == 1) {
                            secondNum = -secondNum;
                            tv_screen.setText(MessageFormat.format("{0}{1}\n{2}",
                                    format.format(firstNum),
                                    gExpression,
                                    format.format(secondNum)));
                        }
                    }else {
                        showScreen = tv_screen.getText().toString();
                        String strNum;
                        if(!showScreen.isEmpty() && !showScreen.equals("0") && !showScreen.equals("Math Error")) {
                            if(checkCase() == 3) {
                                strNum = format.format(firstNum);
                                tv_screen.setText(strNum.substring(0,strNum.length()-1));
                            }else if (checkCase() == 1) {
                                strNum = format.format(secondNum);
                                tv_screen.setText(MessageFormat.format("{0}{1}\n{2}",
                                        format.format(firstNum),
                                        gExpression,
                                        strNum.substring(0,strNum.length()-1)));
                            }
                        }else {
                            tv_screen.setText("0");
                        }
                    }
                }
            });
        }
    }

    /** Method checkCase() is used to check possible cases before performing calculations **
    * @ return = 1 when the screen is displayed: firstNum + expression + "\n" + secondNum  *
    * @ return = 2 when the screen is displayed: firstNum + expression + "\n"              *
    * @ return = 3 when the screen is displayed: firstNum                                  *
    ****************************************************************************************/
    private int checkCase() {
        showScreen = tv_screen.getText().toString();
        if(showScreen.contains("\n")) {
            String strAfterExpression;
            strAfterExpression = showScreen.substring(showScreen.indexOf("\n")+1);
            if(!strAfterExpression.isEmpty()) {
                try {
                    firstNum = Double.parseDouble(showScreen.substring(0,showScreen.indexOf("\n")-1));
                    secondNum = Double.parseDouble(strAfterExpression);
                    gExpression = showScreen.substring(showScreen.indexOf("\n")-1, showScreen.indexOf("\n"));
                    return 1;
                }catch (Exception NumberFormatException) {
                    tv_screen.setText(showScreen);
                    return 1;
                }
            }else {
                firstNum = Double.parseDouble(showScreen.substring(0,showScreen.indexOf("\n")-1));
                secondNum = 0.0;
                return 2;
            }
        }else {
            firstNum = Double.parseDouble(showScreen);
            return 3;
        }
    }

    private void calculateExpressions(String expression) {
        showScreen = tv_screen.getText().toString();
        if(!showScreen.isEmpty()) {
            if(checkCase() == 1) {
                switch (gExpression) {
                    case "+":
                        results = firstNum + secondNum;
                        break;
                    case "-":
                        results = firstNum - secondNum;
                        break;
                    case "x":
                        results = firstNum * secondNum;
                        break;
                    case "/":
                        if(secondNum != 0) {
                            results = firstNum / secondNum;
                        }else {
                            tv_screen.setText("Math Error");
                            return;
                        }
                        break;
                }
                tv_screen.setText(MessageFormat.format("{0}{1}\n", format.format(results), expression));
            }else if (checkCase() == 3) {
                tv_screen.setText(MessageFormat.format("{0}{1}\n", format.format(firstNum), expression));
            }
        }
    }
}
package com.example.exam_simplesettings;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Debug Variable
    private boolean                 D   = true;
    private final static String     TAG = "MainActivity";


    // View Variable
    private LinearLayout            layoutEntire;

    private EditText                idEditText;

    private Button                  btnModify;
    private Button                  btnSave;
    private Button                  btnCancel;

    private CheckBox                checkbox_first;
    private CheckBox                checkbox_second;

    private RadioGroup              radioGroup;

    // Memeber Variable
    private String                  idValue;
    private String                  radioButtonValue;
    private String                  checkboxValue;



    // ----------------------------
    // ---- Override Method -------
    // ----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(D == true)
            Log.i(TAG, "onCreate()");
        init();
    }


    // --------------------------
    // ---- Member Method -------
    // --------------------------

    // 값 초기화 함수
    public void init() {
        layoutEntire        = findViewById(R.id.layout_entire);
        idEditText          = findViewById(R.id.editText_Id);
        radioGroup          = findViewById(R.id.radiogroup_thema);
        checkbox_first      = findViewById(R.id.checkBox_first);
        checkbox_second     = findViewById(R.id.checkBox_second);

        idValue             = "";
        radioButtonValue    = "";
        checkboxValue       = "";

        setCheckboxListener();
    }

    // 체크 박스 내 리스너 함수 설정
    public void setCheckboxListener() {
        checkbox_first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkboxValue += buttonView.getText().toString() + "\n";
                    Log.i(TAG, "checkbox click value =>" + checkboxValue);
                } else {
                    checkboxValue = "";
                    Log.i(TAG, "checkbox unclick value =>" + checkboxValue);
                }

            }
        });
        checkbox_second.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkboxValue += buttonView.getText().toString() + "\n";
                    Log.i(TAG, "checkbox click value =>" + checkboxValue);
                } else {
                    checkboxValue = "";
                    Log.i(TAG, "checkbox unclick value =>" + checkboxValue);
                }

            }
        });
    }

    // Modify 버튼 클릭 시 editText 박스를 출력 가능하도록
    public void click_btnModify(View v) {
        idEditText.setEnabled(true);
    }

    // 라디오 버튼 클릭 시 저장된 텍스트 값 저장
    public void click_RadioBtn(View v) {
        boolean checked = ((RadioButton)v).isChecked();

        if(checked)
            radioButtonValue = ((RadioButton) v).getText().toString();
        Log.i(TAG, "clicked radio value => " + radioButtonValue);
    }

    // SAVE 버튼 클릭 시 EditText enable false로, Thema 적용, Select 버튼 클릭 해제 및 내용 Toast로 출력 
    public void click_btnSave(View v) {
        if(D == true)
            Log.i(TAG, "click_btnSave()");

        if((checkboxValue != "") && (radioButtonValue != "")) {
            idValue = idEditText.getText().toString();

            // USER 부분 enable을 false로 변경
            idEditText.setEnabled(false);
            changeLayoutThema();
            Toast.makeText(this.getApplicationContext(), checkboxValue, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.getApplicationContext(), R.string.text_noSave, Toast.LENGTH_LONG).show();
        }

        Log.i(TAG, "input id value => " + idValue);

    }

    // CANCEL 버튼 클릭 시, 라디오 버튼 및 체크 박스 버튼 해제
    public void click_btnCancel(View v) {
        radioGroup.clearCheck(); // 라디오 버튼 체크 해제
        layoutEntire.setBackgroundColor(Color.WHITE);
        checkbox_first.setChecked(false); // 첫 번째 체크 박스 해제
        checkbox_second.setChecked(false); // 두 번째 체크 박스 해제
    }

    // 레이아웃 색상 변경
    public void changeLayoutThema() {
        switch(radioButtonValue) {
            case "White":
                layoutEntire.setBackgroundColor(Color.WHITE);
                break;
            case "Dark":
                layoutEntire.setBackgroundColor(Color.BLACK);
                break;
            case "Blue":
                layoutEntire.setBackgroundColor(Color.BLUE);
                break;
            default:
                return;
        }
    }

}
package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.alipay.PayResult;
import com.jaydenxiao.common.commonutils.DisplayUtil;

import butterknife.Bind;


/**
 * author:lengxue
 * date:2017/4/20 11:40
 * description:含有编辑框对话框，积分商城的收货地址对话框
 */

public class MyEditDialog extends Dialog {

    private TextView titleTV;
    private EditText nameET;
    private EditText phoneET;
    private EditText detailET;
    private EditText appedET;
    private Button okBT;

    private Spinner citySP;
    private Spinner roadSP;
    private Context mContext;
    private String city;
    private String road;

    public MyEditDialog(Context context) {
        super(context,R.style.MyDialognew);
        mContext=context;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_pointsmall_address2, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addContentView(view, params);

        titleTV=(TextView)view.findViewById(R.id.dlg_addr_title);
        nameET=(EditText)view.findViewById(R.id.dlg_addr_name);
        phoneET=(EditText)view.findViewById(R.id.dlg_addr_phone);
        detailET=(EditText)view.findViewById(R.id.dlg_addr_detail);
        appedET=(EditText)view.findViewById(R.id.dlg_addr_append);
        okBT=(Button)view.findViewById(R.id.dlg_addr_sure);

        citySP=(Spinner)view.findViewById(R.id.dlg_addr_spin_city);
        roadSP=(Spinner)view.findViewById(R.id.dlg_addr_spin_road);

        //获取资源文件中的数组
//        区域
        final String[] cityarray = getContext().getResources().getStringArray(R.array.city);
        ArrayAdapter<String> cityadapter = new ArrayAdapter<String>(
                mContext,
                android.R.layout.simple_list_item_1,
                cityarray
        );
        cityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySP.setAdapter(cityadapter);
        citySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("tag", cityarray[i]+"onItemSelected:i-------> " + i);
                city = cityarray[i];
                Log.e("tag", "onItemSelected--------->" + city);
                Toast.makeText(mContext, city, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //      街道
        final String[] roadarray=getContext().getResources().getStringArray(R.array.road);
        ArrayAdapter<String> roadadapter = new ArrayAdapter<String>(
                mContext,
                android.R.layout.simple_list_item_1,
                roadarray
        );
        roadadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roadSP.setAdapter(roadadapter);
        roadSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("tag", "onItemSelected:i-------> " + i);
                road = roadarray[i];
                Log.e("tag", "onItemSelected--------->" + road);
                Toast.makeText(mContext, road, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public MyEditDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context,themeResId);
    }

    public void setTitle(String title){
        titleTV.setText(title);
    }
    public void setTitle(int titleRes){
        titleTV.setText(titleRes);
    }

    public void setOkListener(String okStr, View.OnClickListener listener){
        okBT.setText(okStr);
        okBT.setOnClickListener(listener);
    }

    public String getCity(){
        return city;
    }
    public String getRoad(){
        return road;
    }

    public String getNameET() {
        return nameET.getText().toString();
    }
    public void setNameET(String  name) {
        nameET.setText(name);
    }

    public String getPhoneET() {
        return phoneET.getText().toString();
    }
    public void setPhoneET(String phone) {
        phoneET.setText(phone);
    }

    public String getDetailET() {
        return detailET.getText().toString();
    }
    public void setDetailET(String  detail) {
       detailET.setText(detail);
    }

    public String getAppedET() {
        return appedET.getText().toString();
    }
    public void setAppedET(String apped) {
       appedET.setText(apped);
    }




}

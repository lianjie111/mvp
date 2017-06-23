package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.R;

import java.util.ArrayList;
import java.util.List;

public class BankNamePickerDialog extends Dialog implements android.view.View.OnClickListener {
    private TextView title;
    private ListView bankNameListview;
    private Button btnCancel;
    private Button btnOk;
    private OnBankNamePickerListener mListener;
    private BankNameAdapter bankNameAdapter;
    private List<BankNameModel> bankNames;
    private MSharePreferences sharePreferences;

    public interface OnBankNamePickerListener {
        void bankNamePicker(String bankName, int index);
    }

    public BankNamePickerDialog(Context context,
                                OnBankNamePickerListener onBankNamePickerListener) {
        this(context, R.style.CustomDialog, onBankNamePickerListener);
        // TODO Auto-generated constructor stub
    }

    public BankNamePickerDialog(Context context, int theme, OnBankNamePickerListener onBankNamePickerListener) {
        super(context, theme);
        mListener = onBankNamePickerListener;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.province_listview, null);
        title = (TextView) view.findViewById(R.id.title);
        bankNameListview = (ListView) view.findViewById(R.id.provinceList);
        bankNameListview.setOverScrollMode(View.OVER_SCROLL_NEVER);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnOk = (Button) view.findViewById(R.id.btnOk);
        setContentView(view);
        ;
        setCancelable(true);
        sharePreferences = MSharePreferences.getInstance();
        sharePreferences.getSharedPreferences(getContext());
        bankNames = new ArrayList<BankNameModel>();
        initProvinceDatas();
        initBankName();
        initDialogSize2();
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

/*	        */
    }

    private boolean isCity;

    public void initBankName() {
        title.setText(R.string.bank);
        bankNameAdapter = new BankNameAdapter(getContext(), bankNames);
        bankNameListview.setAdapter(bankNameAdapter);
        bankNameListview.setSelection(sharePreferences.getInt(
                Tools.KEY_BANKNAME, 0));

        isCity = true;
    }

    public void initDialogSize2() {

        Window dialogWindow = getWindow();
        DisplayMetrics d = getContext().getResources().getDisplayMetrics();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.height = LayoutParams.WRAP_CONTENT;
        p.width = (int) (d.widthPixels * 0.8);
        dialogWindow.setAttributes(p);

    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnCancel) {
            Log.i("99", view.getId() + "");
            dismiss();

        } else if (i == R.id.btnOk) {
            Log.i("99", view.getId() + "");
            int bankNamePosition = bankNameAdapter.getSelectedIndex();
            String bankName = bankNames.get(bankNamePosition).getName();
            int index = bankNames.get(bankNamePosition).getIndex();
            mListener.bankNamePicker(bankName, index);


            dismiss();

        } else {
        }
    }

    public void initProvinceDatas() {
        BankNameModel model = new BankNameModel();
        model.setIndex(1);
        model.setName("中国银行");
        bankNames.add(model);

        BankNameModel model1 = new BankNameModel();
        model1.setIndex(2);
        model1.setName("工商银行");
        bankNames.add(model1);

        BankNameModel model2 = new BankNameModel();
        model2.setIndex(3);
        model2.setName("农业银行");
        bankNames.add(model2);

        BankNameModel model3 = new BankNameModel();
        model3.setIndex(4);
        model3.setName("交通银行");
        bankNames.add(model3);

        BankNameModel model4 = new BankNameModel();
        model4.setIndex(5);
        model4.setName("广发银行");
        bankNames.add(model4);

        BankNameModel model5 = new BankNameModel();
        model5.setIndex(6);
        model5.setName("深发银行");
        bankNames.add(model5);

        BankNameModel model6 = new BankNameModel();
        model6.setIndex(7);
        model6.setName("建设银行");
        bankNames.add(model6);

        BankNameModel model7 = new BankNameModel();
        model7.setIndex(8);
        model7.setName("上海浦发银行");
        bankNames.add(model7);

        BankNameModel model8 = new BankNameModel();
        model8.setIndex(9);
        model8.setName("浙江泰隆商业银行");
        bankNames.add(model8);

        BankNameModel model9 = new BankNameModel();
        model9.setIndex(10);
        model9.setName("招商银行");
        bankNames.add(model9);

        BankNameModel model10 = new BankNameModel();
        model10.setIndex(11);
        model10.setName("邮政储蓄银行");
        bankNames.add(model10);

        BankNameModel model11 = new BankNameModel();
        model11.setIndex(12);
        model11.setName("民生银行");
        bankNames.add(model11);

        BankNameModel model12 = new BankNameModel();
        model12.setIndex(13);
        model12.setName("兴业银行");
        bankNames.add(model12);

        BankNameModel model13 = new BankNameModel();
        model13.setIndex(14);
        model13.setName("广东发展银行");
        bankNames.add(model13);

        BankNameModel model14 = new BankNameModel();
        model14.setIndex(15);
        model14.setName("东莞银行");
        bankNames.add(model14);

        BankNameModel model15 = new BankNameModel();
        model15.setIndex(16);
        model15.setName("深圳发展银行");
        bankNames.add(model15);

        BankNameModel model16 = new BankNameModel();
        model16.setIndex(17);
        model16.setName("中信银行");
        bankNames.add(model16);

        BankNameModel model17 = new BankNameModel();
        model17.setIndex(18);
        model17.setName("华夏银行");
        bankNames.add(model17);

        BankNameModel model18 = new BankNameModel();
        model18.setIndex(19);
        model18.setName("中国光大银行");
        bankNames.add(model18);

        BankNameModel model19 = new BankNameModel();
        model19.setIndex(20);
        model19.setName("北京银行");
        bankNames.add(model19);

        BankNameModel model20 = new BankNameModel();
        model20.setIndex(21);
        model20.setName("上海银行");
        bankNames.add(model20);

        BankNameModel model21 = new BankNameModel();
        model21.setIndex(22);
        model21.setName("天津银行");
        bankNames.add(model21);

        BankNameModel model22 = new BankNameModel();
        model22.setIndex(23);
        model22.setName("大连银行");
        bankNames.add(model22);

        BankNameModel model23 = new BankNameModel();
        model23.setIndex(24);
        model23.setName("杭州银行");
        bankNames.add(model23);

        BankNameModel model24 = new BankNameModel();
        model24.setIndex(25);
        model24.setName("宁波银行");
        bankNames.add(model24);

        BankNameModel model25 = new BankNameModel();
        model25.setIndex(26);
        model25.setName("厦门银行");
        bankNames.add(model25);

        BankNameModel model26 = new BankNameModel();
        model26.setIndex(27);
        model26.setName("广州银行");
        bankNames.add(model26);

        BankNameModel model27 = new BankNameModel();
        model27.setIndex(28);
        model27.setName("平安银行");
        bankNames.add(model27);

        BankNameModel model28 = new BankNameModel();
        model28.setIndex(29);
        model28.setName("浙商银行");
        bankNames.add(model28);

        BankNameModel model29 = new BankNameModel();
        model29.setIndex(30);
        model29.setName("上海农村商业银行");
        bankNames.add(model29);

        BankNameModel model30 = new BankNameModel();
        model30.setIndex(31);
        model30.setName("重庆银行");
        bankNames.add(model30);

        BankNameModel model31 = new BankNameModel();
        model31.setIndex(32);
        model31.setName("江苏银行");
        bankNames.add(model31);

        BankNameModel model32 = new BankNameModel();
        model32.setIndex(33);
        model32.setName("农村信用合作社");
        bankNames.add(model32);

        BankNameModel model33 = new BankNameModel();
        model33.setIndex(34);
        model33.setName("广州农村商业银行");
        bankNames.add(model33);

        BankNameModel model34 = new BankNameModel();
        model34.setIndex(35);
        model34.setName("四川成都龙泉驿稠州村镇银行");
        bankNames.add(model34);

        BankNameModel model35 = new BankNameModel();
        model35.setIndex(36);
        model35.setName("内蒙古银行");
        bankNames.add(model35);

        BankNameModel model36 = new BankNameModel();
        model36.setIndex(37);
        model36.setName("深圳农村商业银行");
        bankNames.add(model36);

        BankNameModel model37 = new BankNameModel();
        model37.setIndex(38);
        model37.setName("贵阳银行");
        bankNames.add(model37);

        BankNameModel model38 = new BankNameModel();
        model38.setIndex(39);
        model38.setName("贵州银行");
        bankNames.add(model38);

        BankNameModel model39 = new BankNameModel();
        model39.setIndex(40);
        model39.setName("贵阳农村商业银行");
        bankNames.add(model39);

        BankNameModel model40 = new BankNameModel();
        model40.setIndex(41);
        model40.setName("南充市商业银行");
        bankNames.add(model40);

        BankNameModel model41 = new BankNameModel();
        model41.setIndex(42);
        model41.setName("东莞农商银行");
        bankNames.add(model41);

        BankNameModel model42 = new BankNameModel();
        model42.setIndex(43);
        model42.setName("徽商银行");
        bankNames.add(model42);

        BankNameModel model43 = new BankNameModel();
        model43.setIndex(44);
        model43.setName("河北银行");
        bankNames.add(model43);

        BankNameModel model44 = new BankNameModel();
        model44.setIndex(45);
        model44.setName("重庆农村商业银行");
        bankNames.add(model44);

        BankNameModel model45 = new BankNameModel();
        model45.setIndex(46);
        model45.setName("呼和浩特金谷农村商业银行");
        bankNames.add(model45);

        BankNameModel model46 = new BankNameModel();
        model46.setIndex(47);
        model46.setName("吴江农村商业银行");
        bankNames.add(model46);

        BankNameModel model47 = new BankNameModel();
        model47.setIndex(48);
        model47.setName("宁夏银行");
        bankNames.add(model47);

        BankNameModel model48 = new BankNameModel();
        model48.setIndex(49);
        model48.setName("石嘴山银行");
        bankNames.add(model48);

        BankNameModel model49 = new BankNameModel();
        model49.setIndex(50);
        model49.setName("黄河农村商业银行");
        bankNames.add(model49);

        BankNameModel model50 = new BankNameModel();
        model50.setIndex(51);
        model50.setName("德阳银行");
        bankNames.add(model50);

        BankNameModel model51 = new BankNameModel();
        model51.setIndex(52);
        model51.setName("昆明富滇银行");
        bankNames.add(model51);

        BankNameModel model52 = new BankNameModel();
        model52.setIndex(53);
        model52.setName("云南省农村信用社");
        bankNames.add(model52);

        BankNameModel model53 = new BankNameModel();
        model53.setIndex(54);
        model53.setName("郑州银行");
        bankNames.add(model53);

        BankNameModel model54 = new BankNameModel();
        model54.setIndex(55);
        model54.setName("潍坊银行");
        bankNames.add(model54);

        BankNameModel model55 = new BankNameModel();
        model55.setIndex(56);
        model55.setName("渤海银行");
        bankNames.add(model55);

        BankNameModel model56 = new BankNameModel();
        model56.setIndex(57);
        model56.setName("安徽凤阳农村商业银行");
        bankNames.add(model56);

        BankNameModel model57 = new BankNameModel();
        model57.setIndex(58);
        model57.setName("富滇银行");
        bankNames.add(model57);

        BankNameModel model58 = new BankNameModel();
        model58.setIndex(59);
        model58.setName("玉溪市商业银行");
        bankNames.add(model58);

        BankNameModel model59 = new BankNameModel();
        model59.setIndex(60);
        model59.setName("曲靖市商业银行");
        bankNames.add(model59);

        BankNameModel model60 = new BankNameModel();
        model60.setIndex(61);
        model60.setName("泰安市商业银行");
        bankNames.add(model60);

        BankNameModel model61 = new BankNameModel();
        model61.setIndex(62);
        model61.setName("汇丰银行");
        bankNames.add(model61);

        BankNameModel model62 = new BankNameModel();
        model62.setIndex(63);
        model62.setName("河北邯郸农村信用社");
        bankNames.add(model62);

        BankNameModel model63 = new BankNameModel();
        model63.setIndex(64);
        model63.setName("江苏江南农村商业银行");
        bankNames.add(model63);

        BankNameModel model64 = new BankNameModel();
        model64.setIndex(65);
        model64.setName("湖北省农村信用社");
        bankNames.add(model64);

        BankNameModel model65 = new BankNameModel();
        model65.setIndex(66);
        model65.setName("湖北银行");
        bankNames.add(model65);

        BankNameModel model66 = new BankNameModel();
        model66.setIndex(67);
        model66.setName("汉口银行");
        bankNames.add(model66);

        BankNameModel model67 = new BankNameModel();
        model67.setIndex(68);
        model67.setName("襄阳市农村商业银行");
        bankNames.add(model67);

        BankNameModel model68 = new BankNameModel();
        model68.setIndex(69);
        model68.setName("南京银行");
        bankNames.add(model68);

        BankNameModel model69 = new BankNameModel();
        model69.setIndex(70);
        model69.setName("贵州花溪农村商业银行");
        bankNames.add(model69);

        BankNameModel model70 = new BankNameModel();
        model70.setIndex(71);
        model70.setName("包商银行");
        bankNames.add(model70);

        BankNameModel model71 = new BankNameModel();
        model71.setIndex(72);
        model71.setName("柳州银行");
        bankNames.add(model71);

        BankNameModel model72 = new BankNameModel();
        model72.setIndex(73);
        model72.setName("广西农村信用社");
        bankNames.add(model72);

        BankNameModel model73 = new BankNameModel();
        model73.setIndex(74);
        model73.setName("桂林银行");
        bankNames.add(model73);

        BankNameModel model74 = new BankNameModel();
        model74.setIndex(75);
        model74.setName("广西北部湾银行");
        bankNames.add(model74);

        BankNameModel model75 = new BankNameModel();
        model75.setIndex(76);
        model75.setName("贵州贞丰农村商业银行股份有限公司");
        bankNames.add(model75);

        BankNameModel model76 = new BankNameModel();
        model76.setIndex(77);
        model76.setName("四川农村信用社");
        bankNames.add(model76);

        BankNameModel model77 = new BankNameModel();
        model77.setIndex(78);
        model77.setName("长春农商银行");
        bankNames.add(model77);

        BankNameModel model78 = new BankNameModel();
        model78.setIndex(79);
        model78.setName("吉林省农业信用社");
        bankNames.add(model78);

        BankNameModel model79 = new BankNameModel();
        model79.setIndex(80);
        model79.setName("吉林银行");
        bankNames.add(model79);

        BankNameModel model80 = new BankNameModel();
        model80.setIndex(81);
        model80.setName("浙江农信银行");
        bankNames.add(model80);

        BankNameModel model81 = new BankNameModel();
        model81.setIndex(82);
        model81.setName("苏州银行");
        bankNames.add(model81);

        BankNameModel model82 = new BankNameModel();
        model82.setIndex(83);
        model82.setName("江苏长江商业银行");
        bankNames.add(model82);

        BankNameModel model83 = new BankNameModel();
        model83.setIndex(84);
        model83.setName("北京农村商业银行");
        bankNames.add(model83);

        BankNameModel model84 = new BankNameModel();
        model84.setIndex(85);
        model84.setName("合肥科技农村商业银行");
        bankNames.add(model84);

        BankNameModel model85 = new BankNameModel();
        model85.setIndex(86);
        model85.setName("湖北嘉鱼农村商业银行");
        bankNames.add(model85);

        BankNameModel model86 = new BankNameModel();
        model86.setIndex(87);
        model86.setName("广东顺德农村商业银行");
        bankNames.add(model86);
    }
}

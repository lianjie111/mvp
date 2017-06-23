package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityPickerDialog extends Dialog implements
        android.view.View.OnClickListener {
    // private Context mContext;

    private TextView title;

    private ListView provinceListview;
    private Button btnCancel;
    private Button btnOk;
    // private Dialog customDialog;
    private MSharePreferences sharePreferences;
    private OnCityPikerListener mlistener;
    private PlistEntity plist;
    //private List<ProvinceModel> provinces;
    private ProvinceAdapter provinceAdapter;
    private ProvinceAdapter cityAdapter;

    //城市选择器监听
    public interface OnCityPikerListener {
        void onCityPicker(String province, String city, int pindex, int cindex);
    }

    public CityPickerDialog(Context context,
                            OnCityPikerListener onCityPickerListener) {
        // super(context,R.style.customdialog);
        // TODO Auto-generated constructor stub
        this(context, R.style.customdialog, onCityPickerListener);
    }

    public CityPickerDialog(Context context, int theme,
                            OnCityPikerListener onCityPickerListener) {
        super(context, theme);
        mlistener = onCityPickerListener;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.province_listview, null);
        title = (TextView) view.findViewById(R.id.title);
        provinceListview = (ListView) view.findViewById(R.id.provinceList);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnOk = (Button) view.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        setContentView(view);
        setCancelable(true);

        sharePreferences = MSharePreferences.getInstance();
        sharePreferences.getSharedPreferences(getContext());
        try {
            matchAndParseData();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //initProvinceDatas();
        initProvince();
        initDialogSize();

    }

    private boolean isCity;

    public void initProvince() {
        title.setText(R.string.province);
        provinceAdapter = new ProvinceAdapter(getContext(), plist);
        provinceListview.setAdapter(provinceAdapter);
        provinceListview.setSelection(sharePreferences.getInt(
                Tools.KEY_PROVINCE, 0));
        isCity = true;
    }

    public void initDialogSize() {

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
            dismiss();

        } else if (i == R.id.btnOk) {
            int provincePosition = provinceAdapter.getSelectedIndex();
            String province = plist.dictentity.get(0).dict.get(provincePosition).string;
            String city;
            int pindex = Integer.parseInt(plist.dictentity.get(0).key.get(provincePosition));
            int cindex;
            if (isCity) {

                title.setText(R.string.city);
                cityAdapter = new ProvinceAdapter(getContext(), plist.dictentity.get(0).dict.get(provincePosition).threeDictEntity, false);
                provinceListview.setAdapter(cityAdapter);
                isCity = false;
            } else {
                city = plist.dictentity.get(0).dict.get(provincePosition).threeDictEntity.string
                        .get(cityAdapter.getSelectedIndex());
                cindex = Integer.parseInt(plist.dictentity.get(0).dict.get(provincePosition).threeDictEntity.key
                        .get(cityAdapter.getSelectedIndex()));
                sharePreferences.putInt(Tools.KEY_PROVINCE, provincePosition);
                mlistener.onCityPicker(province, city, pindex, cindex);

                dismiss();
            }


        } else {
        }

    }

    /**
     * 匹配获取和解析xml文件内容
     *
     * @throws Exception
     */
    private void matchAndParseData() throws Exception {
        InputStream inputStream = getContext().getAssets().open("province_datas.xml");
        String dataContent = convertStreamToString(inputStream);
        String content = "";
        //正则表达式匹配
        Pattern DATA_PATTERN = Pattern.compile("[\\S\\s]*(<plist[\\s]*version=[\\S]*>[\\s\\S]*</plist>)[\\s\\S]*");
        Matcher matcher = DATA_PATTERN.matcher(dataContent);

        if (matcher.matches()) {
            //获取包含dataContent元素的内容
            content = matcher.group(1);
        }
        if (!TextUtils.isEmpty(content)) {
            plist = parseToBean(PlistEntity.class, content);
            Log.i("99bt", plist.version);
            plist.dictentity.get(0).dict.size();
            Log.i("99bt", plist.dictentity.get(0).dict.size() + "======" + plist.dictentity.get(0).key.size());
        }
    }

    /**
     * 将xml文件内容解析成实体类
     *
     * @param type
     * @param data
     * @return
     */
    private <T> T parseToBean(Class<T> type, String data) {

        try {
            return new Persister().read(type, data, false);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    /**
     * 输入流转字符串
     *
     * @param is
     * @return
     */
    public String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Log.i("99bt", sb.toString());
        return sb.toString();

    }
}

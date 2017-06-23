package com.jaydenxiao.common.commonutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Base64;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.security.Md5Security;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

public class AppUtil {

	/*
	 * public static String getClientId() { String clientid =
	 * UserCenterInfo.getClienId(); if (!isInvalide(clientid)) { // clientid =
	 * PushManager.getInstance().getClientid(MyApplication.getInstance());
	 * clientid = UmengRegistrar.getRegistrationId(MyApplication.getInstance());
	 * UserCenterInfo.setClienId(clientid); } LogUtil.i("client id: " +
	 * ComValue.CLIENTID); return clientid; }
	 */

    /**
     * 将指定的内容生成成二维码
     *
     * @param content
     *            将要生成二维码的内容
     * @return 返回生成好的二维码事件
     * @throws WriterException
     *             WriterException异常
     */
	/*
	 * @Deprecated public static Bitmap CreateDCode(String content, int w, int
	 * h, BarcodeFormat type) throws WriterException { //
	 * 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败 // BarcodeFormat.QR_CODE,
	 * BarcodeFormat.CODE_128 BitMatrix matrix = new
	 * MultiFormatWriter().encode(content, type, w, h); int width =
	 * matrix.getWidth(); int height = matrix.getHeight(); //
	 * 二维矩阵转为一维像素数组,也就是一直横着排了 int[] pixels = new int[width * height]; for (int y
	 * = 0; y < height; y++) { for (int x = 0; x < width; x++) { if
	 * (matrix.get(x, y)) { pixels[y * width + x] = 0xffffffff; } } }
	 *
	 * Bitmap bitmap = Bitmap.createBitmap(width, height,
	 * Bitmap.Config.ARGB_8888);
	 *
	 * // 通过像素数组生成bitmap,具体参考api bitmap.setPixels(pixels, 0, width, 0, 0, width,
	 * height); return bitmap; }
	 */

    /**
     * textView 追加富文本
     *
     * @param tv
     * @param text
     * @param start
     * @param end
     * @param color
     */
    public static void setColorAndSizeSpan(TextView tv, String text, int start, int end, int color) {
        setColorAndSizeSpan(tv, text, start, end, color, 1.0f);
    }

    /**
     * textView 追加富文本
     *
     * @param tv
     * @param text
     * @param start
     * @param end
     * @param color
     * @param size
     */
    public static void setColorAndSizeSpan(TextView tv, String text, int start, int end, int color, float size) {
        SpannableString spanString = new SpannableString(text);
        ForegroundColorSpan fColorSpan = new ForegroundColorSpan(color);
        RelativeSizeSpan rSizeSpan = new RelativeSizeSpan(size);
        spanString.setSpan(fColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(rSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long timeStampThisDateS() {
        long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 获取当前时间格式化
     * @param dataFormat
     * @return
     */
    public static String timeStampThisDateS(String dataFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dataFormat);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 遍历字符串去空格
     * @param s
     * @return
     */
    public static String getNoEmptyString(String s) {
        String noEmptyString="";
        if (!s.isEmpty() && !"".equals(s)) {
            for (int i = 0;i<s.length();i++) {
                if (!" ".equals(s.substring(i, i + 1))) {
                    noEmptyString += s.substring(i, i + 1);
                }
            }
        }
        return noEmptyString;
    }

    /**
     * jsonArray按照字段tm正序排列
     *
     * @param origin
     * @param tm
     * @return
     */
    public static JSONArray sortJArrayDesc(JSONArray origin, String tm) {
        if (!isInvalide(origin)) {
            return origin;
        }
        Double key = 0d;
        JSONObject temp;
        int j;
        try {
            for (int i = 1; i < origin.length(); i++) {
                temp = origin.optJSONObject(i);
                j = i - 1;
                key = temp.optDouble(tm);
                while (j > -1 && origin.optJSONObject(j).optDouble(tm) >= key) {
                    origin.put(j + 1, origin.optJSONObject(j));
                    origin.put(j, temp);
                    j = j - 1;
                }
            }
        } catch (Exception e) {
        }
        return origin;
    }

    /**
     * List按照字段tm正序排列
     *
     * @param origin
     * @param tm
     * @return
     */
    public static List<JSONObject> sortJListDesc(List<JSONObject> origin, String tm) {
        if (!AppUtil.isInvalide(origin)) {
            return origin;
        }
        final String key = tm;
        Collections.sort(origin, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                double p1 = 0;
                double p2 = 0;

                if (AppUtil.isInvalide(lhs))
                    p1 = lhs.optDouble(key);

                if (AppUtil.isInvalide(rhs))
                    p2 = rhs.optDouble(key);
                // LogUtil.i("p1: " + p1 + " p2: " + p2);
                if (p1 < p2) {
                    return -1; // p1 排在p2前面
                } else {
                    return 1; // p1排在p2后面
                }
            }

        });
        return origin;
    }

    /**
     * jsonArray按照字段tm倒序排列
     *
     * @param origin
     * @param tm
     * @return
     */
    public static JSONArray sortJArrayAsc(JSONArray origin, String tm) {
        if (!isInvalide(origin)) {
            return origin;
        }
        int len = origin.length();
        Double key;
        JSONObject temp;
        int j;
        try {
            for (int i = 1; i < len; i++) {
                temp = origin.optJSONObject(i);
                j = i - 1;
                key = temp.optDouble(tm);

                while (j > -1 && origin.optJSONObject(j).optDouble(tm) <= key) {
                    origin.put(j + 1, origin.optJSONObject(j));
                    origin.put(j, temp);
                    j = j - 1;
                }
            }
        } catch (Exception e) {
        }
        return origin;
    }

    /**
     * List按照字段tm倒序排列
     *
     * @param origin
     * @param tm
     * @return
     */
    public static List<JSONObject> sortJListAsc(List<JSONObject> origin, String tm) {
        if (!AppUtil.isInvalide(origin)) {
            return origin;
        }
        final String key = tm;
        Collections.sort(origin, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                double p1 = 0;
                double p2 = 0;

                if (AppUtil.isInvalide(lhs)) {
                    p1 = lhs.optDouble(key);
                }

                if (AppUtil.isInvalide(rhs)) {
                    p2 = rhs.optDouble(key);
                }
                // LogUtil.i("p1: " + p1 + " p2: " + p2);
                if (p2 > p1) {
                    return -1; // p1 排在p2前面
                } else {
                    return 1; // p1排在p2后面
                }
            }

        });
        return origin;
    }

    /**
     * 查找list中包含有
     *
     * @param jsonObjects
     * @param drid
     * @return
     */
    public static JSONObject findJsonObject(List<JSONObject> jsonObjects, String drid) {
        if (isInvalide(jsonObjects)) {
            for (JSONObject json : jsonObjects) {
                String id = json.optString("dr_id");
                id = id.replace("-", "");
                drid = drid.replace("-", "");
                // LogUtil.i("id: " + id + " drid: " + drid);
                if (drid.equals(id)) {
                    return json;
                }
            }
        }
        return null;
    }

    /**
     * 验证国内手机号
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 校验用户名
     *
     * @param name
     *            用户姓名 最多6位汉字
     * @return
     */
    public static boolean isCorrectName(String name) {
        String regex = "^[\u4E00-\u9FA5]{2,6}$";
        return Pattern.matches(regex, name);
    }

    /**
     * 校验用户密码
     *
     * @param pwd
     *            用户密码 6~20位密码，只能包含字母、数字、下划线
     * @return
     */
    public static boolean isCorrectPwd(String pwd) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";
        return Pattern.matches(regex, pwd);
    }

    /**
     * 验证Email
     *
     * @param email
     *            email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证URL地址
     *
     * @param url
     *            格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或
     *            http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * 验证身份证号码
     *
     * @param idNumber
     *            居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idNumber) {
        if (null == idNumber || idNumber.trim().isEmpty()) {
            return false;
        }
        boolean isAvailable = false;
        int len = idNumber.length();
        final int OLD_ID_NUMBER_LEN = 15;
        final int NEW_ID_NUMBER_LEN = 18;
        String regExp = "(\\d{17}([Xx]|\\d))|(\\d{15})";
        isAvailable = regExpMacth(idNumber, regExp);
        if (!isAvailable) {
            return false;
        }
        if (NEW_ID_NUMBER_LEN == len) {
            char[] numChar = idNumber.toCharArray();
            int[] numbers = new int[17];
            int[] coefficients = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
            String[] verifyCodes = new String[] { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
            String last = "";
            int remainder = -1;
            try {
                for (int i = 0; i < 17; i++) {
                    numbers[i] = Integer.parseInt(numChar[i] + "");
                }

            } catch (Exception e) {
                return false;
            }
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                sum += numbers[i] * coefficients[i];
            }

            remainder = sum % 11;
            last = (numChar[17] + "");
            return verifyCodes[remainder].equalsIgnoreCase(last);

        } else if (OLD_ID_NUMBER_LEN == len) {
            regExp = "(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82)\\d{13}";
            isAvailable = regExpMacth(idNumber, regExp);
            if (isAvailable) {
                isAvailable = false;
                int year = Integer.parseInt(idNumber.substring(6, 8));
                int month = Integer.parseInt(idNumber.substring(8, 10));
                int day = Integer.parseInt(idNumber.substring(10, 12));
                if (year >= 0 && year <= 99) {
                    if (month >= 1 && month <= 12) {
                        if (day >= 0 && day <= 31) {
                            isAvailable = true;
                        }
                    }
                }
            }
            return isAvailable;
        } else {
            return false;
        }
    }

    private static boolean regExpMacth(String idNumber, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(idNumber);
        return matcher.matches();
    }

    /**
     * 将一张图片转换成圆形
     *
     * @param source
     * @param min
     *            image size
     * @return
     * @throws MalformedURLException
     */
    public static Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }



    /**
     * Base图片转码
     *
     * @param bitmap
     * @return
     */

    public static String bitmap2base64(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        byte[] array = out.toByteArray();
        return Base64.encodeToString(array, Base64.DEFAULT);
    }

    public static <T> boolean isInvalide(Map<String, T> data) {
        return !(null == data || data.size() < 1);
    }

    public static boolean isInvalide(String data) {
        return !(null == data || data.length() < 1 || "null".equals(data));
    }

    public static boolean isInvalide(JSONArray data) {
        return !(null == data || data.length() < 1);
    }

    public static boolean isInvalide(JSONObject data) {
        return !(null == data || data.length() < 1);
    }

    public static <T> boolean isInvalide(List<T> data) {
        return !(null == data || data.size() < 1);
    }

    /**
     * 修改数字显示格式，用于保密 0123456789 -> 0123***789
     *
     * @param origin
     * @param beginStart
     * @param beginEnd
     * @param endStart
     * @return
     */
    public static String hideNumber(String origin, int beginStart, int beginEnd, int endStart) {
        if (isInvalide(origin)) {
            String subBegin = origin.substring(beginStart, beginEnd);
            String subEnd = origin.substring(endStart);
            String star = "****";
            return subBegin + star + subEnd;
        } else {
            return "";
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static Map<String, JSONObject> jsonArrayToMap(JSONArray array, String key) {
        Map<String, JSONObject> map = new HashMap<String, JSONObject>();
        if (isInvalide(array)) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject json = array.getJSONObject(i);
                    if (isInvalide(json)) {
                        map.put(json.optString(key), json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public static List<String> jsonArrayToString(JSONArray array, String key) {
        List<String> lst = new ArrayList<String>();
        if (isInvalide(array)) {
            for (int i = 0; i < array.length(); i++) {
                lst.add(array.optJSONObject(i).optString(key));
            }
        }
        return lst;
    }

    public static List<JSONObject> JsonArrayToList(JSONArray resultArray) {
        List<JSONObject> lst = new ArrayList<JSONObject>();
        if (isInvalide(resultArray)) {
            for (int i = 0; i < resultArray.length(); i++) {
                lst.add(resultArray.optJSONObject(i));
            }
        }
        return lst;
    }


    /**
     * 删除jsonarray中最小的一项
     *
     * @param array
     * @param tm
     * @return
     */
    @SuppressLint("NewApi")
    public static JSONArray deleteLeastItem(JSONArray array, String tm) {
        array = sortJArrayAsc(array, tm);
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            array = remove(array, array.length() - 1);
        } else {
            array.remove(array.length() - 1);
        }
        return array;
    }

    /**
     * 比较历史记录中是否有相同记录，有则更新，没有不做处理
     *
     * @param array
     * @param newHistory
     * @return
     */
    @SuppressLint("NewApi")
    public static JSONArray findSameItem(JSONArray array, String newHistory) {
        try {
            if (AppUtil.isInvalide(array)) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json = array.getJSONObject(i);
                    if (newHistory.equals(json.optString("name"))) {
                        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
                            array = remove(array, i);
                        } else {
                            array.remove(i);
                        }
                        return array;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static JSONArray remove(JSONArray jsonArray, int index) {
        JSONArray mJsonArray = new JSONArray();

        if (index < 0)
            return mJsonArray;
        if (index > jsonArray.length())
            return mJsonArray;

        for (int i = 0; i < index; i++) {
            try {
                mJsonArray.put(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = index + 1; i < jsonArray.length(); i++) {
            try {
                mJsonArray.put(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mJsonArray;
    }

    /**
     * 保留两位小数
     */
    public static String reserveTwoDecimalPlaces(String origin) {
        double number = Double.valueOf(origin);
        if (0.0 == number) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(number);
    }
    /**
     * 保留三位小数
     */
    public static String reserveTwoDecimalPlacesint(double d) {
        double number = d;
        if (0.0 == number) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(number);
    }
    /**
     * 保留四位小数
     */
    public static String reserveFourDecimalPlacesint(double d) {
        double number = d;
        if (0.0 == number) {
            return "0.0000";
        }
        DecimalFormat df = new DecimalFormat("#0.0000");
        return df.format(number);
    }

    /**
     * 保留1位小数，传递double类型返回String
     *
     * @param origin
     * @return
     */
    public static String reserveTwoDecimalPlacesDouble(double origin) {
        double number = origin;
        if (0.0 == number) {
            return "0.0";
        }
        DecimalFormat df = new DecimalFormat("#0.0");
        return df.format(number);
    }

    public static String reserveStringThree(int originString) {
        return String.format("%1$03d", originString);
    }

    /**
     * 重新计算ExpandableListView的高度
     *
     * @param listView
     */
    public static void setExpandableListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 重新计算ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * @param listView
     * @param group
     */
    public static void setExpandableListViewChildHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        LayoutParams params = listView.getLayoutParams();
        int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    @SuppressLint("NewApi")
    @Deprecated
    public static void setGridViewHeigth(GridView gridview, int number) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = gridview.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount() / number; i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, gridview);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        LayoutParams params = gridview.getLayoutParams();
        params.height = totalHeight + (gridview.getHorizontalSpacing() * (listAdapter.getCount() / number));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridview.setLayoutParams(params);
    }


    public static JSONArray mergeTwoJsonArray(JSONArray mdes, JSONArray msrc) {
        if (isInvalide(mdes)) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < mdes.length(); i++) {
                jsonArray.put(mdes.optJSONObject(i));
            }
            for (int i = 0; i < msrc.length(); i++) {
                jsonArray.put(msrc.optJSONObject(i));
            }
            // jsonArray = mergeTwoArray(mdes, msrc);
            // jsonArray = sortJArrayAsc(jsonArray, "tm");
            return jsonArray;
        } else {
            // msrc = sortJArrayAsc(msrc, "tm");
            return msrc;
        }
    }

    // ----osc
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {
        long size = 0;

        File file = new File(filePath);
        if (file != null && file.exists()) {
            size = file.length();
        }
        return size;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName) {
        if (AppUtil.isEmpty(fileName))
            return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * Zlib解压缩
     *
     * @param data
     * @return
     */
    public static String decompress(byte[] data) {
        byte[] output = new byte[0];
        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
        return new String(output);
    }

    public static JSONObject getJSONFromArray(JSONArray jsonArray, long time) {
        JSONObject json = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            json = jsonArray.optJSONObject(i);
            long time1 = json.optLong("sche_date") * 1000;
			/*
			 * if (DateHelper.isSameDay(time, time1)) { return json; } else {
			 * json = null; }
			 */
            if (time == time1) {
                return json;
            } else {
                json = null;
            }
        }
        return json;
    }

    /**
     * 获取当前日期凌晨的时间戳
     *
     * @return
     */
    public static long getCurrentMillis() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return currentDate.getTimeInMillis();
    }

    public static void makePhoneCall(Context ctx, String telephone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telephone));
        ctx.startActivity(intent);
    }

    /**
     * 如果url没有http://，为其加入
     *
     * @param url
     * @return
     */
    public static String checkUrl(String url) {
        int find = url.indexOf("http");
        if (-1 == find) {
            url = "http://" + url;
        }
        return url;
    }


    public static float calculateStarNumber(String stars) {
        float number = Float.valueOf(stars);
        if (number == 0) {
            return 0.5f;
        }
        int dot = stars.indexOf(".");
        if (dot > 0) {
            int integers = Integer.valueOf(stars.substring(0, dot));
            int decimal = Integer.valueOf(stars.substring(dot + 1, stars.length()));
            if (decimal <= 0.5) {
                return integers + 0.5f;
            } else {
                return (float) (integers + 1);
            }
        } else {
            return Float.valueOf(stars);
        }
    }

    /**
     * 新闻列表中用到的图片加载配置
     */
    public static DisplayImageOptions getListOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // // 设置图片在下载期间显示的图片
                // .showImageOnLoading(R.drawable.small_image_holder_listpage)
                // // 设置图片Uri为空或是错误的时候显示的图片
                // .showImageForEmptyUri(R.drawable.small_image_holder_listpage)
                // // 设置图片加载/解码过程中错误时候显示的图片
                // .showImageOnFail(R.drawable.small_image_holder_listpage)
                .cacheInMemory(true)
                // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)
                // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true).imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Config.RGB_565)// 设置图片的解码类型
                // .decodingOptions(android.graphics.BitmapFactory.Options
                // decodingOptions)//设置图片的解码配置
                .considerExifParams(true)
                // 设置图片下载前的延迟
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // 。preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                // .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))// 淡入
                .build();
        return options;
    }


    public static String send(byte[] byteArray, String fileName, String fileType) {

        URL url = null;
        String result = null;
        try {
            url = new URL("http://121.40.121.33:10000/file");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(120000);// 设置连接超时时间
            httpURLConnection.setDoInput(true); // 打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true); // 打开输出流，以便向服务器提交数据
            httpURLConnection.setUseCaches(false); // 使用Post方式不能使用缓存
            httpURLConnection.setRequestMethod("POST");// 设置以Post方式提交数据
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            // //设置请求体的类型是文本类型
            // httpURLConnection.setRequestProperty("Content-Type",
            // "application/x-www-form-urlencoded");
            // //设置请求体的长度
            // httpURLConnection.setRequestProperty("Content-Length",
            // String.valueOf(data.length));
            String twoHyphens = "--";
            String boundary = "****************fD4fH3gL0hK7aI6"; // 数据分隔符
            String lineEnd = System.getProperty("line.separator"); // The value
            // is "\r\n"
            // in
            // Windows.
            StringBuilder split = new StringBuilder();
            split.append("Content-Disposition: form-data; name=\"filedata\" ; filename=\""
                    + fileName.substring(0, fileName.indexOf(".")) + "\"" + "\r\n");
            split.append("Content-Type: " + fileType + "\r\n" + "\r\n");
            // 获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            String data = split.toString();
            byte[] b = data.getBytes();
            outputStream.write(b);
            outputStream.write(byteArray);
            String end = twoHyphens + boundary + twoHyphens + lineEnd;
            byte[] c = end.getBytes();
            outputStream.write(c);
            outputStream.flush();
            int response = httpURLConnection.getResponseCode(); // 获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = inptStream.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString(); // 处理服务器的响应结果
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String sendPinjia(String timestamp, String kf, String customer, String evalval, String evaltext) {
        String result = null;
        URL url = null;
        try {
            url = new URL("http://121.40.121.33:10000/api/evaluations");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(120000);// 设置连接超时时间
            httpURLConnection.setDoInput(true); // 打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true); // 打开输出流，以便向服务器提交数据
            httpURLConnection.setUseCaches(false); // 使用Post方式不能使用缓存
            httpURLConnection.setRequestMethod("POST");// 设置以Post方式提交数据
            // 获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            JSONObject js = new JSONObject();
            String data = " timestamp=\"" + timestamp + "\"" + " customer=\"" + customer + "\"" + " evalval=\""
                    + evalval + "\"" + " evaltext=\"" + evaltext + "\"" + " ; kf=\"" + kf + "\"" + "\r\n";
            byte[] b = data.getBytes();
            outputStream.write(b);
            outputStream.flush();
            int code = httpURLConnection.getResponseCode();
            System.out.print(code);
            if (code == 400 || code == 404 || code == 500) {
                return "";
            }
            InputStream im = httpURLConnection.getInputStream();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    // SHA1 加密实例
    public static String encryptToSHA(String info) {
        byte[] digesta = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            // 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds
     *            精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 获取当前时间戳去掉后三位,保留前10位
     *
     * @return
     */
    public static long timeStampThisDate() {
        long time = System.currentTimeMillis();
        String strtime = time + "";
        strtime = strtime.substring(0, strtime.length() - 3);
        return Long.parseLong(strtime);
    }

    /**
     * 获取网落图片资源
     *
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            // 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            // 连接设置获得数据流
            conn.setDoInput(true);
            // 不使用缓存
            conn.setUseCaches(false);
            // 这句可有可无，没有影响
            // conn.connect();
            // 得到数据流
            InputStream is = conn.getInputStream();
            // 解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            // 关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;

    }

    /**
     * 把Base64位图的String转换bitmap图片
     *
     * @param string
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        byte[] bitmapArray;
        bitmapArray = Base64.decode(string, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        return bitmap;
    }

    /**
     * 把Base64字符串的String转换Json
     *
     * @param string
     * @return
     */
    public static JSONObject getBasetoJSONObject(String string) {
        JSONObject jsonObject = null;
        String str = new String(Base64.decode(string.getBytes(), Base64.DEFAULT));
        str = str.substring(str.indexOf("{"), str.length());
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 得到文件路径
     * @param filePath
     * @param fileName
     * @return
     */
    public static File getFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }
    /**
     * 没有文件路径是创建
     * @param filePath
     */
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }
    public static String getMd5(String uid) {
        String key="xinchungemeibugxinfugeyouqianhua";
        return Md5Security.getMD5(key+uid);
    }
//    public static String md5(String string) {
//        if (TextUtils.isEmpty(string)) {
//            return "";
//        }
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//            byte[] bytes = md5.digest(string.getBytes());
//            String result = "";
//            for (byte b : bytes) {
//                String temp = Integer.toHexString(b & 0xff);
//                if (temp.length() == 1) {
//                    temp = "0" + temp;
//                }
//                result += temp;
//            }
//            return result;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

}

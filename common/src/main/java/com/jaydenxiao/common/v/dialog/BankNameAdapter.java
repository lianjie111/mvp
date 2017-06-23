package com.jaydenxiao.common.v.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.jaydenxiao.common.R;

import java.util.HashMap;
import java.util.List;

public class BankNameAdapter extends BaseAdapter {
private Context context;//得到一个上下文对象
private LayoutInflater mInflater;//得到一个布局解析器对象

private int selectedIndex=0;
//用于记录每个radiobutton的状态
private HashMap<String,Boolean> states=new HashMap<String, Boolean>();
private List<BankNameModel> bankNames;
private boolean defaultSelectPosition=true;
public int getSelectedIndex() {
    return selectedIndex;
}

public void setSelectedIndex(int selectedIndex) {
    this.selectedIndex = selectedIndex;
}
public BankNameAdapter(Context context, List<BankNameModel> bankNames){
	this.context=context;
	this.bankNames=bankNames;
	this.mInflater= LayoutInflater.from(context);
	
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return bankNames.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return bankNames.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		 MSharePreferences sharePreferences = MSharePreferences
	                .getInstance();
	        sharePreferences.getSharedPreferences(context);
		if (convertView==null) {
			viewHolder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.province_adapter, null);
			viewHolder.bankNameItem=(RadioButton) convertView.findViewById(R.id.province_choice);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.bankNameItem.setText(bankNames.get(position).getName());
		viewHolder.bankNameItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (String key:states.keySet()) {
					states.put(key, false);
					
				}
				defaultSelectPosition=false;
				states.put(String.valueOf(position), viewHolder.bankNameItem.isChecked());
				BankNameAdapter.this.notifyDataSetChanged();
			}
		});
		//用于判断该item是否被选中
		boolean res=false;
		if (states.get(String.valueOf(position))==null||states.get(String.valueOf(position))==false) {
			res=false;
			states.put(String.valueOf(position), false);
			
		}
		else{
			selectedIndex=position;
			res=true;
		}
		viewHolder.bankNameItem.setChecked(res);
		
		
		if (defaultSelectPosition&&position==sharePreferences.getInt(Tools.KEY_BANKNAME, 0)) {
			selectedIndex=position;
			viewHolder.bankNameItem.setChecked(true);
		}
		return convertView;
	}
private class ViewHolder{
	RadioButton bankNameItem;
}
}

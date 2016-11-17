package com.adapter;

import java.util.ArrayList;
import java.util.List;
import com.mod.Info;
import com.qu.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DateAdapter extends BaseAdapter {

	private Context context;
	/** 列表. */
	private List<Info> lstDate;
	/** 名字. */
	private TextView txtName;
	/** 性别. */
	private TextView txtSex;
	/** 年龄. */
	private TextView txtAge;

	// 每页显示的Item个数
	public static final int SIZE = 12;

	public DateAdapter(Context mContext, List<Info> list, int page) {
		this.context = mContext;
		lstDate = new ArrayList<Info>();
		int i = page * SIZE;
		int iEnd = i + SIZE;
		while ((i < list.size()) && (i < iEnd)) {
			lstDate.add(list.get(i));
			i++;
		}
	}

	@Override
	public int getCount() {
		return lstDate.size();
	}

	@Override
	public Object getItem(int position) {
		return lstDate.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Info user = lstDate.get(position);
		convertView = LayoutInflater.from(context).inflate(R.layout.item, null);

		txtName = (TextView) convertView.findViewById(R.id.txt_userName);
		txtSex = (TextView) convertView.findViewById(R.id.txt_userSex);
		txtAge = (TextView) convertView.findViewById(R.id.txt_userAge);
 
		txtName.setText(user.getName()+"王" + position);
		txtSex.setText("性别：" + user.getSex());
		txtAge.setText("年龄段：" + user.getAge());

		return convertView;
	}

}

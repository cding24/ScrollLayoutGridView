package com.qu;

import java.util.ArrayList;
import java.util.List;
import com.adapter.DateAdapter;
import com.mod.Info;
import com.util.ScrollLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 该例子显示仿Launcher中的WorkSapce，可以左右滑动切换屏幕的处理。
 * 如果滑动到不到一屏幕或半屏幕情况下，怎么自动滑动到左边或者右边的Item项上。
 * 主要是ScrollLayout类
 * @author linghu
 *
 */
public class ScrollGridActivity extends Activity {
	
	/** 总页数. */
	private int PageCount;
	/** 当前页码. */
	private int PageCurrent;
	/** 被选中的. */
	private int gridID = -1;
	/** 每页显示的数量，Adapter保持一致. */
	private static final int PAGE_SIZE = 12;
	/** GridView. */
	private GridView gridView;
	/** 自定义的左右滑动. */
	private ScrollLayout curPage;
	/** 页码条布局. */
	private LinearLayout layoutBottom;
	/** 数据集. */
	private List<Info> listDate = new ArrayList<Info>();
	/** 数据. */
	private Info info;
	/** 页码条. */
	private ImageView indicatorView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViews();
		
		setGrid();
		
		setCurPage(0);
		
		curPage.setPageListener(new ScrollLayout.PageListener() {
			@Override
			public void page(int page) {
				setCurPage(page);
			}
		});
	}

	/**
	 * 初始化
	 */
	private void findViews() {
		curPage = (ScrollLayout) findViewById(R.id.scr);
		layoutBottom = (LinearLayout) findViewById(R.id.layout_scr_bottom);
		curPage.getLayoutParams().height = this.getWindowManager().getDefaultDisplay().getHeight() * 2 / 3;
		for (int i = 0; i < 20; i++) {
			info = new Info();
			info.setName("你的姓名");
			info.setSex("2");
			info.setAge("" + i);
			listDate.add(info);
		}
	}

	/**
	 * 添加GridView
	 */
	private void setGrid() {
		PageCount = (int) Math.ceil(listDate.size() * 1.0f / PAGE_SIZE);
		if (gridView != null) {
			curPage.removeAllViews();
		}
		for (int i = 0; i < PageCount; i++) {
			gridView = new GridView(ScrollGridActivity.this);
			gridView.setAdapter(new DateAdapter(ScrollGridActivity.this, listDate, i));
			gridView.setNumColumns(3);
			gridView.setHorizontalSpacing(15);
			gridView.setVerticalSpacing(20);
			gridView.setPadding(10, 0, 10, 0);
//			if(i == 0){
//				gridView.setPadding(20, 0, 10, 0);
//			}else if(i == PageCount -1){
//				gridView.setPadding(10, 0, 20, 0);
//			}else{
//				gridView.setPadding(10, 0, 10, 0);
//			}
			
			// 去掉点击时的黄色背景
			gridView.setSelector(R.drawable.bg_grid_item);
			gridView.setOnItemClickListener(gridListener);
			curPage.addView(gridView);
		}
	}

	/**
	 * GridView的监听事件
	 */
	public OnItemClickListener gridListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			PageCurrent = curPage.getCurScreen();
			gridID = arg2 + PageCurrent * 12;

			if (((GridView) arg0).getTag() != null) {
				((View) ((GridView) arg0).getTag()).setBackgroundResource(R.drawable.bg_grid_item);
			}
			((GridView) arg0).setTag(arg1);
			arg1.setBackgroundResource(R.drawable.bg_grid_item_false);
		}
	};

	/**
	 * 更新当前页码
	 */
	public void setCurPage(int page) {
		layoutBottom.removeAllViews();
		for (int i = 0; i < PageCount; i++) {
			indicatorView = new ImageView(ScrollGridActivity.this);
			indicatorView.setBackgroundResource(R.drawable.bg_img_item);
			indicatorView.setScaleType(ScaleType.FIT_CENTER);
			indicatorView.setId(i);
			// 判断当前页码来更新
			if (indicatorView.getId() == page) {
				indicatorView.setBackgroundResource(R.drawable.bg_img_item_true);
			}
			layoutBottom.addView(indicatorView);
		}
	}
	
}
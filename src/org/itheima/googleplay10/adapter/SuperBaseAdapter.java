package org.itheima.googleplay10.adapter;

import java.util.List;

import org.itheima.googleplay10.base.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.adapter
 * @类名: SuperBaseAdapter
 * @创建者: 肖琦
 * @创建时间: 2015-8-23 上午11:26:18
 * @描述: TODO
 * 
 * @svn版本: $Rev: 20 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 15:58:38 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public abstract class SuperBaseAdapter<T> extends BaseAdapter
{
	private List<T>	mDatas;

	public SuperBaseAdapter(List<T> datas) {
		this.mDatas = datas;
	}

	@Override
	public int getCount()
	{
		if (mDatas != null) { return mDatas.size(); }
		return 0;
	}

	@Override
	public Object getItem(int position)
	{
		if (mDatas != null) { return mDatas.get(position); }
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// ############# 1. 初始化View ############################
		BaseHolder holder = null;
		if (convertView == null)
		{
			// 2. 初始化holder
			holder = getItemHolder();

			// 没有复用
			// 1. 加载布局
			convertView = holder.getRootView();
			// 3. 设置标记
			convertView.setTag(holder);
			// 4. 查找view---》交给holder实现
			// holder.tv1 = (TextView) convertView.findViewById(R.id.tmp_tv_1);
			// holder.tv2 = (TextView) convertView.findViewById(R.id.tmp_tv_2);
		}
		else
		{
			// 有复用
			holder = (BaseHolder) convertView.getTag();
		}
		// #####################################################

		// ################ 2. 给view设置数据 #####################
		// 给view设置数据
		// String data = mDatas.get(position);
		// holder.tv1.setText("标题 : " + data);
		// holder.tv2.setText("内容 : " + data);
		T data = mDatas.get(position);
		holder.setData(data);
		// #####################################################

		return convertView;
	}

	protected abstract BaseHolder<T> getItemHolder();

	// // ( TODO: holder写死了 )
	// private class ViewHolder
	// {
	// TextView tv1;
	// TextView tv2;
	// }

}

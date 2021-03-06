package org.itheima.googleplay10.base;

import android.view.View;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.base
 * @类名: BaseHolder
 * @创建者: 肖琦
 * @创建时间: 2015-8-23 下午2:14:41
 * @描述: holder的基类
 * 
 * @svn版本: $Rev: 19 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 15:11:23 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public abstract class BaseHolder<T>
{
	protected View	mRootView;
	protected T		mData;

	public BaseHolder() {
		mRootView = initView();
	}

	/**
	 * 初始化view的方法
	 * 
	 * @return
	 */
	protected abstract View initView();

	/**
	 * UI显示
	 * 
	 * @param data
	 */
	protected abstract void refreshUI(T data);

	/**
	 * 获得根view
	 * 
	 * @return
	 */
	public View getRootView()
	{
		return mRootView;
	}

	/**
	 * 给holder设置数据
	 * 
	 * @param data
	 */
	public void setData(T data)
	{
		this.mData = data;

		// 刷新UI
		refreshUI(data);
	}

}

package org.itheima.googleplay10.base;

import org.itheima.googleplay10.fragment.LoadingUI;
import org.itheima.googleplay10.fragment.LoadingUI.ResultState;
import org.itheima.googleplay10.utils.UIUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10
 * @类名: BaseFragment
 * @创建者: 肖琦
 * @创建时间: 2015-8-22 上午11:24:49
 * @描述: TODO
 * 
 * @svn版本: $Rev: 17 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 10:49:07 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public abstract class BaseFragment extends Fragment
{

	private LoadingUI	mLoadingUI;

	// UI角度:
	// 共同点: 加载中，加载失败，加载为空
	// 不同点: 加载成功后的显示
	// 页面顺序显示： 加载中 ---》 加载失败
	// ---> 加载为空
	// ---> 加载成功

	// 行为：
	// 加载数据--加载的方式不一样
	// ----> 加载失败 --->显示失败
	// ----> 加载为空 --->显示为空
	// ----> 加载成功--->显示成功

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		// 容器(加载中，加载失败，加载为空,加载成功)//切换页面显示
		if (mLoadingUI == null)
		{
			mLoadingUI = new LoadingUI(UIUtils.getContext()) {

				@Override
				protected ResultState onLoadData()
				{
					return onStartLoadData();
				}

				@Override
				protected View onLoadSuccessView()
				{
					return onInitSuccessView();
				}
			};
		}
		else
		{
			// 移除view
			ViewParent parent = mLoadingUI.getParent();
			// ViewGroup
			if (parent instanceof ViewGroup)
			{
				((ViewGroup) parent).removeView(mLoadingUI);
			}
		}
		return mLoadingUI;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// 去加载数据

		// // 去网络加载数据--->
		// mLoadingUI.loadData();
	}

	/**
	 * 加载数据的方法
	 */
	public void loadData()
	{
		if (mLoadingUI != null)
		{
			// 去网络加载数据--->
			mLoadingUI.loadData();
		}
	}

	/**
	 * 当加载数据时的回调
	 * 
	 * @return
	 */
	protected abstract ResultState onStartLoadData();

	/**
	 * 显示成功的view
	 * 
	 * @return
	 */
	protected abstract View onInitSuccessView();
}

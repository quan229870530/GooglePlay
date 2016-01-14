package org.itheima.googleplay10.fragment;

import org.itheima.googleplay10.base.BaseFragment;

import android.support.v4.util.SparseArrayCompat;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.fragment
 * @类名: FragmentFactory
 * @创建者: 肖琦
 * @创建时间: 2015-8-22 下午4:54:37
 * @描述: 页面的工具类
 * 
 * @svn版本: $Rev: 17 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 10:49:07 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public class FragmentFactory
{
	// 优化--》 hash算法
	// private static Map<Integer, Fragment> mCaches = new
	// LinkedHashMap<Integer, Fragment>();

	// 稀疏array
	// key为Interger
	private static SparseArrayCompat<BaseFragment>	mCaches	= new SparseArrayCompat<BaseFragment>();

	public static BaseFragment getFragment(int position)
	{
		BaseFragment fragment = mCaches.get(position);

		if (fragment != null) { return fragment; }

		switch (position)
		{
			case 0:
				// 首页
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new HomeFragment();
				break;
			case 2:
				fragment = new HomeFragment();
				break;
			case 3:
				fragment = new HomeFragment();
				break;
			case 4:
				fragment = new HomeFragment();
				break;
			case 5:
				fragment = new HomeFragment();
				break;
			case 6:
				fragment = new HomeFragment();
				break;
			default:
				break;
		}

		// 存储
		mCaches.put(position, fragment);

		return fragment;
	}

}

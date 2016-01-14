package org.itheima.googleplay10.activity;

import java.util.ArrayList;
import java.util.List;

import org.itheima.googleplay10.R;
import org.itheima.googleplay10.base.BaseActivity;
import org.itheima.googleplay10.fragment.FragmentFactory;
import org.itheima.googleplay10.fragment.HomeFragment;
import org.itheima.googleplay10.utils.LogUtils;
import org.itheima.googleplay10.utils.UIUtils;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends BaseActivity implements OnPageChangeListener
{

	private ActionBar				mActionBar;
	private DrawerLayout			mDrawerLayout;	// 鎶藉眽
	private ActionBarDrawerToggle	mDrawerToggle;	// 鎶藉眽寮�叧
	private ViewPager				mViewPager;
	private PagerSlidingTabStrip	mTabStrip;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
		mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
		mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.main_tabs);

		// 鍒濆鍖朼ctionbar
		initActionBar();

		// 鍒濆鍖栨暟鎹�		initData();
	}

	private void initActionBar()
	{
		mActionBar = getSupportActionBar();

		// 璁剧疆title
		mActionBar.setTitle("GooglePlay");
		// 璁剧疆鍥炬爣
		mActionBar.setIcon(R.drawable.ic_launcher);

		// 鏄剧ずup鎸夐挳
		mActionBar.setDisplayHomeAsUpEnabled(true);

		// 鍒涘缓寮�叧
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
													R.drawable.ic_drawer_am,
													R.string.drawer_des_open,
													R.string.drawer_des_close);
		// 璁剧疆drawerlayout鐨勭洃鍚�		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// 鍚屾
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				// 打开或关闭抽屉
				boolean selected = mDrawerToggle.onOptionsItemSelected(item);
				if (selected) { return true; }
				break;

			default:
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	// private List<String> mDatas;
	private String[]	mTitles;

	private void initData()
	{

		mTitles = UIUtils.getStringArray(R.array.pagers);

		// mDatas = new ArrayList<String>();
		// for (int i = 0; i < 10; i++)
		// {
		// mDatas.add("椤甸潰-" + i);
		// }

		// 缁檝iewpager璁剧疆鏁版嵁
		mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

		// 缁檝iewpager璁剧疆tabs
		mTabStrip.setViewPager(mViewPager);

		// 璁剧疆鏂囨湰棰滆壊
		mTabStrip.setTextColor(UIUtils.getColor(R.color.tab_text_normal), UIUtils.getColor(R.color.tab_text_selected));

		mTabStrip.setOnPageChangeListener(this);
		
//		mViewPager.setCurrentItem(1);
		mViewPager.setCurrentItem(0);
		
	}

	// FragmentPagerAdapter: 鐢ㄤ簬灏戦噺椤甸潰,fragment瀛樺偍鍦ㄥ唴瀛樹腑
	// FragmentStatePagerAdapter: 鐢ㄤ簬澶ч噺椤甸潰, 涔熷彲浠ュ瓨鍌ㄧ姸鎬�	private class MainPagerAdapter extends FragmentStatePagerAdapter
	private class MainPagerAdapter extends FragmentStatePagerAdapter
	{

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			LogUtils.d("getItem : " + position);
			return FragmentFactory.getFragment(position);
		}

		// 返回的显示的页面数量
		@Override
		public int getCount()
		{
			return mTitles.length;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			return mTitles[position];
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position)
	{
		// 鍘诲姞杞絝ragment鐨勬暟鎹�		FragmentFactory.getFragment(position).loadData();
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}

	// private class MainAdapter extends PagerAdapter
	// {
	//
	// @Override
	// public int getCount()
	// {
	// if (mDatas != null) { return mDatas.size(); }
	// return 0;
	// }
	//
	// @Override
	// public boolean isViewFromObject(View view, Object object)
	// {
	// return view == object;
	// }
	//
	// @Override
	// public Object instantiateItem(ViewGroup container, int position)
	// {
	// String data = mDatas.get(position);
	//
	// TextView tv = new TextView(UIUtils.getContext());
	// tv.setText(data);
	//
	// container.addView(tv);
	//
	// return tv;
	// }
	//
	// @Override
	// public void destroyItem(ViewGroup container, int position, Object object)
	// {
	// container.removeView((View) object);
	// }
	//
	// @Override
	// public CharSequence getPageTitle(int position)
	// {
	// if (mDatas != null) { return mDatas.get(position); }
	// return super.getPageTitle(position);
	// }
	// }
}

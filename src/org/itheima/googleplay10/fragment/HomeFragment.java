package org.itheima.googleplay10.fragment;

import java.util.List;

import org.itheima.googleplay10.R;
import org.itheima.googleplay10.adapter.SuperBaseAdapter;
import org.itheima.googleplay10.base.BaseFragment;
import org.itheima.googleplay10.base.BaseHolder;
import org.itheima.googleplay10.bean.AppInfoBean;
import org.itheima.googleplay10.bean.HomeBean;
import org.itheima.googleplay10.fragment.LoadingUI.ResultState;
import org.itheima.googleplay10.holder.AppItemHolder;
import org.itheima.googleplay10.utils.UIUtils;

import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.fragment
 * @类名: HomeFragment
 * @创建者: 肖琦
 * @创建时间: 2015-8-22 下午4:23:56
 * @描述: 首页
 * 
 * @svn版本: $Rev: 20 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 15:58:38 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public class HomeFragment extends BaseFragment
{

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState)
	// {
	// TextView tv = new TextView(UIUtils.getContext());
	// tv.setText("首页");
	//
	//
	//
	// return tv;
	// }

	// private List<String> mDatas; // 模拟假数据

	private List<AppInfoBean>	mDatas;	// listView对应的数据
	private List<String>		mPictures;	// 图片对应的数据

	// 子线程执行的
	@Override
	protected ResultState onStartLoadData()
	{
		// 1. 随机加载数据
		// ResultState[] states = new ResultState[] { ResultState.EMPTY,
		// ResultState.ERROR, ResultState.SUCCESS };
		//
		// try
		// {
		// Thread.sleep(1500);
		// }
		// catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }
		//
		// Random rdm = new Random();
		//
		// return states[rdm.nextInt(states.length)];

		// 2.模拟数据加载
		// mDatas = new ArrayList<String>();
		// for (int i = 0; i < 60; i++)
		// {
		// mDatas.add("" + i);
		// }
		//
		// try
		// {
		// Thread.sleep(1000);
		// }
		// catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }

		// 3.获取网络数据

		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		HttpUtils utils = new HttpUtils();
		String url = "http://10.0.2.2:8080/GooglePlayServer/home";
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("index", "" + 0);

		try
		{
			ResponseStream stream = utils.sendSync(HttpMethod.GET, url, params);

			// 获得string类型的结果
			String json = stream.readString();

			// 解析json
			Gson gson = new Gson();
			HomeBean bean = gson.fromJson(json, HomeBean.class);

			if (bean == null) { return ResultState.EMPTY; }

			if (bean.list == null || bean.list.size() == 0) { return ResultState.EMPTY; }

			if (bean.picture == null || bean.picture.size() == 0) { return ResultState.EMPTY; }

			// 获得数据
			mDatas = bean.list;
			mPictures = bean.picture;
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return ResultState.ERROR;
		}

		return ResultState.SUCCESS;
	}

	@Override
	protected View onInitSuccessView()
	{
		// TextView tv = new TextView(UIUtils.getContext());
		// tv.setText("首页");
		// return tv;

		// listView
		ListView mListView = new ListView(UIUtils.getContext());
		// 设置listview的背景
		mListView.setBackgroundResource(R.color.bg);

		// 给listView设置数据
		mListView.setAdapter(new HomeAdapter(mDatas));// adapter-->List<数据类型>

		return mListView;
	}

	private class HomeAdapter extends SuperBaseAdapter<AppInfoBean>
	{

		public HomeAdapter(List<AppInfoBean> datas) {
			super(datas);
		}

		@Override
		protected BaseHolder<AppInfoBean> getItemHolder()
		{
			return new AppItemHolder();
		}
	}

}

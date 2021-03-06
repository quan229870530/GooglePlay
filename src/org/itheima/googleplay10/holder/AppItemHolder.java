package org.itheima.googleplay10.holder;

import org.itheima.googleplay10.R;
import org.itheima.googleplay10.base.BaseHolder;
import org.itheima.googleplay10.bean.AppInfoBean;
import org.itheima.googleplay10.utils.Constants;
import org.itheima.googleplay10.utils.UIUtils;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.holder
 * @类名: AppItemHolder
 * @创建者: 肖琦
 * @创建时间: 2015-8-23 下午2:26:42
 * @描述: 首页应用item的holder
 * 
 * @svn版本: $Rev: 20 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 15:58:38 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public class AppItemHolder extends BaseHolder<AppInfoBean>
{
	// private TextView tv1;
	// private TextView tv2;

	@ViewInject(R.id.item_appinfo_tv_title)
	private TextView	mTvTitle;

	@ViewInject(R.id.item_appinfo_tv_size)
	private TextView	mTvSize;

	@ViewInject(R.id.item_appinfo_tv_des)
	private TextView	mTvDes;

	@ViewInject(R.id.item_appinfo_iv_icon)
	private ImageView	mIvIcon;

	@ViewInject(R.id.item_appinfo_rb_stars)
	private RatingBar	mRbStars;

	private BitmapUtils	mBitmapUtils;

	@Override
	protected View initView()
	{
		// View view = View.inflate(UIUtils.getContext(), R.layout.item_tmp,
		// null);
		//
		// tv1 = (TextView) view.findViewById(R.id.tmp_tv_1);
		// tv2 = (TextView) view.findViewById(R.id.tmp_tv_2);
		//
		// return view;

		View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);

		// 注入
		ViewUtils.inject(this, view);

		mBitmapUtils = new BitmapUtils(UIUtils.getContext());

		return view;
	}

	@Override
	protected void refreshUI(AppInfoBean data)
	{
		// tv1.setText("新的title : " + data);
		// tv2.setText("新的内容 : " + data);

		mTvTitle.setText(data.name);
		mTvDes.setText(data.des);
		mTvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));

		mRbStars.setRating(data.stars);

		// 设置默认图标
		mIvIcon.setImageResource(R.drawable.ic_default);

		String uri = Constants.BASE_IMAGE + data.iconUrl;
		mBitmapUtils.display(mIvIcon, uri);

	}

}

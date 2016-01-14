package org.itheima.googleplay10.manager;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.manager
 * @类名: ThreadPoolManager
 * @创建者: 肖琦
 * @创建时间: 2015-8-23 下午5:09:38
 * @描述: 线程池的管理者
 * 
 * @svn版本: $Rev: 21 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 17:15:44 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public class ThreadPoolManager
{

	private static ThreadPoolProxy	mLongPool;					// 耗时操作的池子
	private static Object			mLongLock	= new Object();

	/**
	 * 获得耗时操作的池子
	 * 
	 * @return
	 */
	public static ThreadPoolProxy getLongPool()
	{
		if (mLongPool == null)
		{
			synchronized (mLongLock)
			{
				if (mLongPool == null)
				{
					mLongPool = new ThreadPoolProxy(3, 3, 0L);
				}
			}
		}
		return mLongPool;
	}

}

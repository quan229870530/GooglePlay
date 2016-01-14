package org.itheima.googleplay10.manager;

import java.util.Comparator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.manager
 * @类名: ThreadPoolProxy
 * @创建者: 肖琦
 * @创建时间: 2015-8-23 下午4:22:04
 * @描述: 线程池的类
 * 
 * @svn版本: $Rev: 21 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-23 17:15:44 +0800 (Sun, 23 Aug 2015) $
 * @更新描述: TODO
 */
public class ThreadPoolProxy
{
	private ThreadPoolExecutor	mExecutor;
	private int					mCorePoolSize		= 3;	// 核心线程数
	private int					mMaximumPoolSize	= 5;	// 最大线程数
	private long				mKeepAliveTime		= 5000; // 存活时间

	public ThreadPoolProxy(int coreSize, int maxSize, long keepAlive) {
		this.mCorePoolSize = coreSize;
		this.mMaximumPoolSize = maxSize;
		this.mKeepAliveTime = keepAlive;
	}

	/**
	 * 执行任务
	 * 
	 * @param task
	 */
	public void execute(Runnable task)
	{
		if (task == null) { return; }

		checkPool();

		mExecutor.execute(task);
	}

	/**
	 * 执行任务
	 * 
	 * @param task
	 * @return
	 */
	public Future<?> submit(Runnable task)
	{
		if (task == null) { return null; }

		checkPool();

		return mExecutor.submit(task);
	}

	/**
	 * 移除任务
	 * 
	 * @param task
	 */
	public void remove(Runnable task)
	{
		if (mExecutor != null)
		{
			mExecutor.getQueue().remove(task);
		}
	}

	private void checkPool()
	{
		if (mExecutor == null || mExecutor.isShutdown())
		{

			// new Thread() {
			// public void run() {
			// while(flag) {
			//
			// }
			// };
			// }.start();

			TimeUnit unit = TimeUnit.MILLISECONDS;// 时间单位--> 存活时间
			BlockingQueue<Runnable> workQueue = null;// 任务队列

			// workQueue = new ArrayBlockingQueue<Runnable>(3);// capacity :
			// // 任务队列的大小最大为多少

			workQueue = new LinkedBlockingQueue<Runnable>();// 不固定大小
			// workQueue = new PriorityBlockingQueue<Runnable>(100, new
			// Comparator<MyTask>() {
			//
			// @Override
			// public int compare(MyTask a, MyTask b)
			// {
			// return a.priority - b.priority;
			// }
			// });

			// workQueue = new SynchronousQueue<Runnable>();

			ThreadFactory threadFactory = Executors.defaultThreadFactory();// 线程工厂
			RejectedExecutionHandler handler = null;// 错误捕获器
			// handler = new ThreadPoolExecutor.AbortPolicy();// 抛异常---》调用者
			// handler = new ThreadPoolExecutor.CallerRunsPolicy();//直接在当前线程执行
			// handler = new
			// ThreadPoolExecutor.DiscardOldestPolicy();//取出第一个，将task加到最后
			handler = new ThreadPoolExecutor.DiscardPolicy();// 空实现

			mExecutor = new ThreadPoolExecutor(mCorePoolSize,
												mMaximumPoolSize,
												mKeepAliveTime,
												unit,
												workQueue,
												threadFactory,
												handler);

		}
	}

	// private class MyTask implements Runnable {
	//
	// int priority;
	//
	// public MyTask(int priority) {
	// this.priority = priority;
	// }
	//
	// @Override
	// public void run()
	// {
	// // TODO Auto-generated method stub
	//
	// }
	// }

}

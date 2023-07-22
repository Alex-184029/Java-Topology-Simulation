import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
	
	private int wave,during,interval,cnt;   //设定波长、持续时间、持续时间、辅助计数
	private int block = 0;                  //阻塞统计
	final double lam1=0.0035,lam2=0.008;    //设定参数，需要后续自己调整合理
	private String result;                  //后续完善，加入界面中显示
	private int[] waves;
	private Path path;
	private LinkedBlockingQueue<Task> lbq = new LinkedBlockingQueue<Task>();  //任务队列
		//构造方法
	public TaskQueue(Path path,int wave,int during) {
		this.path = path;
		this.wave = wave;
		this.during = during;
		initData();
		begin();
	}
		//数据初始化
	public void initData() {
		int[] x = new int[path.getLinkNum()];
		for(int i=0;i<x.length;i++) {
			x[i] = wave;
		}
		waves = x;
		cnt = 0;
		result = "\n";
		interval = (int)(lam2/lam1*Math.exp((lam1-lam2)*cnt));   //后续需要不断刷新
	}
		//核心模拟程序开始
	public void begin() {
		while(cnt < during) {
			Task t = new Task(path,cnt,waves);
			if(t.isSucceed()) {
				lbq.add(t);
				decreaseWaves(t.getLinks());
				result += t.toStringStart();
			}else {
				result += t.toStringBlock();
				block++;
			}
			if(cnt%interval==0 && cnt!=0) {
				Task t1 = lbq.poll();
				result += t1.toStringEnd();
				increaseWaves(t1.getLinks());
			}
			interval = (int)(lam2/lam1*Math.exp((lam1-lam2)*cnt));
			cnt++;
		}
		result += "\n运行业务："+during+" 阻塞业务："+block
				+"\n最终阻塞率："+block*1.0/during*100+"%\n";
	}
		//减小波长方法
	public void decreaseWaves(ArrayList<Integer> al) {
		for(int i : al) {
			if(waves[i] > 0) {
				waves[i]--;
			}
		}
	}
		//增大波长方法
	public void increaseWaves(ArrayList<Integer> al) {
		for(int i : al) {
			if(waves[i] < wave) {
				waves[i]++;
			}
		}
	}
		//获取输出结果
	public String getResult() {
		return result;
	}

}

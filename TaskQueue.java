import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
	
	private int wave,during,interval,cnt;   //�趨����������ʱ�䡢����ʱ�䡢��������
	private int block = 0;                  //����ͳ��
	final double lam1=0.0035,lam2=0.008;    //�趨��������Ҫ�����Լ���������
	private String result;                  //�������ƣ������������ʾ
	private int[] waves;
	private Path path;
	private LinkedBlockingQueue<Task> lbq = new LinkedBlockingQueue<Task>();  //�������
		//���췽��
	public TaskQueue(Path path,int wave,int during) {
		this.path = path;
		this.wave = wave;
		this.during = during;
		initData();
		begin();
	}
		//���ݳ�ʼ��
	public void initData() {
		int[] x = new int[path.getLinkNum()];
		for(int i=0;i<x.length;i++) {
			x[i] = wave;
		}
		waves = x;
		cnt = 0;
		result = "\n";
		interval = (int)(lam2/lam1*Math.exp((lam1-lam2)*cnt));   //������Ҫ����ˢ��
	}
		//����ģ�����ʼ
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
		result += "\n����ҵ��"+during+" ����ҵ��"+block
				+"\n���������ʣ�"+block*1.0/during*100+"%\n";
	}
		//��С��������
	public void decreaseWaves(ArrayList<Integer> al) {
		for(int i : al) {
			if(waves[i] > 0) {
				waves[i]--;
			}
		}
	}
		//���󲨳�����
	public void increaseWaves(ArrayList<Integer> al) {
		for(int i : al) {
			if(waves[i] < wave) {
				waves[i]++;
			}
		}
	}
		//��ȡ������
	public String getResult() {
		return result;
	}

}

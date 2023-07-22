import java.util.ArrayList;

public class Path {
	
	private int n_num,l_num,wave;
	private ArrayList<Integer> links = new ArrayList<Integer>();
		//构造方法
	public Path(int n_num,int l_num,int wave) {
		this.n_num = n_num;
		this.l_num = l_num;
		this.wave = wave;
		this.createPath();
	}
		//获取节点个数
	public int getNum() {
		return this.n_num;
	}
		//获取波长个数
	public int getWave() {
		return this.wave;
	}
		//获取链路个数
	public int getLinkNum() {
		return this.l_num;
	}
		//获取链路
	public ArrayList<Integer> getLinks(){
		return this.links;
	}

		//生成连线ArrayList存储（注意需保证l_num合理范围，提前判断）
	public void createPath() {
			//首先保证每个节点都被连入
		int cnt=n_num-1,sum=0;
		while(sum < n_num*(n_num-1)/2) {
			links.add(sum);
			sum += cnt;
			cnt -= 1;
		}
			//剩余连线随机生成
		while(links.size() < l_num) {
			int x = (int)(Math.random()*n_num*(n_num-1)/2);
			if(links.indexOf(x) == -1) {
				links.add(x);
			}
		}
	}

}
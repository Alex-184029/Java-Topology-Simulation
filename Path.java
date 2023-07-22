import java.util.ArrayList;

public class Path {
	
	private int n_num,l_num,wave;
	private ArrayList<Integer> links = new ArrayList<Integer>();
		//���췽��
	public Path(int n_num,int l_num,int wave) {
		this.n_num = n_num;
		this.l_num = l_num;
		this.wave = wave;
		this.createPath();
	}
		//��ȡ�ڵ����
	public int getNum() {
		return this.n_num;
	}
		//��ȡ��������
	public int getWave() {
		return this.wave;
	}
		//��ȡ��·����
	public int getLinkNum() {
		return this.l_num;
	}
		//��ȡ��·
	public ArrayList<Integer> getLinks(){
		return this.links;
	}

		//��������ArrayList�洢��ע���豣֤l_num����Χ����ǰ�жϣ�
	public void createPath() {
			//���ȱ�֤ÿ���ڵ㶼������
		int cnt=n_num-1,sum=0;
		while(sum < n_num*(n_num-1)/2) {
			links.add(sum);
			sum += cnt;
			cnt -= 1;
		}
			//ʣ�������������
		while(links.size() < l_num) {
			int x = (int)(Math.random()*n_num*(n_num-1)/2);
			if(links.indexOf(x) == -1) {
				links.add(x);
			}
		}
	}

}
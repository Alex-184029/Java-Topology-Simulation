import java.util.*;

public class Task {
	
	private int start,end,num;           //��㡢�յ㡢�ڵ��������������
	private int[] waves;                 //������������紫�룩
	private long id;                     //����id
	private Path path;                                                 //·������
	private ArrayList<TaskLink> t = new ArrayList<TaskLink>();         //�����߼���
	private ArrayList<Integer> taskLinks = new ArrayList<Integer>();   //�������߼���
	private boolean sign=false;
		//���췽��
	public Task(Path p,long id,int[] waves) {
		this.id = id;
		this.path = p;
		this.num = path.getNum();
		this.waves = waves;
		haveStartEnd();                //ʼĩ������
		haveT();                       //�����߼�������
		haveTaskLinks();
	}
		//��������
	class TaskLink {
		private int x,y;
		public TaskLink(int x,int y) {
			this.x = x;
			this.y = y;
		}
		public boolean haveElement(int z) {
			if(x==z || y==z) {
				return true;
			}else{
				return false;
			}
		}
	}
		//ʼĩ�����ɣ���粻�ɵ��ã�
	private void haveStartEnd() {
		while(true) {
			int x = (int)(Math.random()*this.num);
			int y = (int)(Math.random()*this.num);
			if(x != y) {
				this.start = x;
				this.end = y;
				break;
			}
		}
	}
		//�����߼������ɣ���粻�ɵ��ã�
	private void haveT() {
		for(int a : path.getLinks()) {
			int cnt = 0;
			for(int i=0;i<num;i++) {
				for(int j=i+1;j<num;j++) {
					if(cnt==a) {       //����ʣ�࣬������
						t.add(new TaskLink(i,j));
					}
					cnt++;
				}
			}
		}
			//ɾ����������Ϊ0����·
		ArrayList<TaskLink> t2 = new ArrayList<TaskLink>();
		for(int i=0;i<waves.length;i++) {
			if(waves[i]==0) {
				t2.add(t.get(i));
			}
		}t.removeAll(t2);
	}
		//�������߷�����˼·��ȫ����������粻�ɵ��ã�
	private void haveTaskLinks() {	
		for(int i=2;i<num+1;i++) {
			sign = false;
			taskLinks.clear();
			taskLinks.add(start);
			haveLinks(i);
			if(taskLinks.size() == i) {
				break;
			}
		}
	}
		//�����������߷�����������x���ڵ㣬�ݹ�ʵ�֣���粻�ɵ��ã�
	private void haveLinks(int x) {
		if(x==2) {
			for(int i=0;i<num;i++) {
				if(i==end && isLinked(taskLinks.get(taskLinks.size()-1),i)) {
					taskLinks.add(i);
					break;
				}
			}
		}else if(x>2) {
			for(int i=0;i<num;i++) {
				if(taskLinks.indexOf(i)==-1 && isLinked(taskLinks.get(taskLinks.size()-1),i)) {
					if(!sign) {
						taskLinks.add(i);
						if(taskLinks.size() == x) {
							if(taskLinks.get(taskLinks.size()-1) == end) {
								sign = true;
								break;
							}else {
								break;
							}
						}
						haveLinks(x);
					}
					
				}
			}if(!sign) {
				taskLinks.remove(taskLinks.size()-1);
			}
		}else {
			System.out.println("�������ò��Ϲ淶��");
		}
	}
		//�ж�������Ƿ������
	boolean isLinked(int x,int y) {
		for(TaskLink i : t) {
			if(i.haveElement(x) && i.haveElement(y)) {
				return true;
			}
		}
		return false;
	}
		//��ȡ���߷���
	public ArrayList<Integer> getLinks(){
		return taskLinks;
	}
		//ҵ��ʼString
	public String toStringStart() {
		String s = id+"��ҵ�����У�����·����"+taskLinks.get(0);
		for(int i=1;i<taskLinks.size();i++) {
			s += " -> "+taskLinks.get(i);
		}s += "\n";
		return s;
	}
		//ҵ����ֹString
	public String toStringEnd() {
		String s = id+"��ҵ����ֹ��ԭʼ·����"+taskLinks.get(0);
		for(int i=1;i<taskLinks.size();i++) {
			s += " -> "+taskLinks.get(i);
		}s += "\n";
		return s;
	}
		//ҵ������String
	public String toStringBlock() {
		return id+"��ҵ��������\n";
	}
		//�ж��Ƿ����ɳɹ�
	public boolean isSucceed() {
		if(taskLinks.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

}

import java.util.*;

public class Task {
	
	private int start,end,num;           //起点、终点、节点个数、波长个数
	private int[] waves;                 //波长个数（外界传入）
	private long id;                     //任务id
	private Path path;                                                 //路径对象
	private ArrayList<TaskLink> t = new ArrayList<TaskLink>();         //辅助边集合
	private ArrayList<Integer> taskLinks = new ArrayList<Integer>();   //生成连线集合
	private boolean sign=false;
		//构造方法
	public Task(Path p,long id,int[] waves) {
		this.id = id;
		this.path = p;
		this.num = path.getNum();
		this.waves = waves;
		haveStartEnd();                //始末点生成
		haveT();                       //辅助边集合生成
		haveTaskLinks();
	}
		//辅助边类
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
		//始末点生成（外界不可调用）
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
		//辅助边集合生成（外界不可调用）
	private void haveT() {
		for(int a : path.getLinks()) {
			int cnt = 0;
			for(int i=0;i<num;i++) {
				for(int j=i+1;j<num;j++) {
					if(cnt==a) {       //波长剩余，则加入边
						t.add(new TaskLink(i,j));
					}
					cnt++;
				}
			}
		}
			//删除波长参数为0的链路
		ArrayList<TaskLink> t2 = new ArrayList<TaskLink>();
		for(int i=0;i<waves.length;i++) {
			if(waves[i]==0) {
				t2.add(t.get(i));
			}
		}t.removeAll(t2);
	}
		//生成连线方法（思路：全部遍历，外界不可调用）
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
		//辅助生成连线方法（连线中x个节点，递归实现，外界不可调用）
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
			System.out.println("函数调用不合规范。");
		}
	}
		//判断两点间是否可连线
	boolean isLinked(int x,int y) {
		for(TaskLink i : t) {
			if(i.haveElement(x) && i.haveElement(y)) {
				return true;
			}
		}
		return false;
	}
		//获取连线方法
	public ArrayList<Integer> getLinks(){
		return taskLinks;
	}
		//业务开始String
	public String toStringStart() {
		String s = id+"号业务运行，运行路径："+taskLinks.get(0);
		for(int i=1;i<taskLinks.size();i++) {
			s += " -> "+taskLinks.get(i);
		}s += "\n";
		return s;
	}
		//业务终止String
	public String toStringEnd() {
		String s = id+"号业务终止，原始路径："+taskLinks.get(0);
		for(int i=1;i<taskLinks.size();i++) {
			s += " -> "+taskLinks.get(i);
		}s += "\n";
		return s;
	}
		//业务阻塞String
	public String toStringBlock() {
		return id+"号业务被阻塞。\n";
	}
		//判断是否生成成功
	public boolean isSucceed() {
		if(taskLinks.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

}

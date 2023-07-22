import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Mission {
	
	private JFrame jf = new JFrame("刘朴华-2019210982");
	private Container con = jf.getContentPane();
	private JTextField jtf10 = new JTextField(3);
	private JTextField jtf11 = new JTextField(3);
	private JTextField jtf12 = new JTextField(3);
	private JTextField jtf13 = new JTextField(4);
	private JLabel jl_icon = new JLabel();
	private JTextArea console = new JTextArea();
	private int n_num=-1,l_num=-1,w_num=-1,d_num=-1;           //节点、链路、波长、持续时长
	private Path path;
	private IconPath icon;
	private TaskQueue tq;
		//构造方法
	public Mission() {
		this.setJf();
		this.addWidget();
	}
		//主窗体配置
	void setJf() {
		jf.setBounds(400,200,520,470);
		con.setBackground(new Color(248,248,255));
		con.setLayout(null);                   //声明不使用布局管理（绝对布局）
		jf.setVisible(true);
		jf.setResizable(false);                //设置用户不可改变大小
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		//添加组件
	void addWidget() {
		JLabel jl0 = new JLabel("大作业：网络业务模拟");
		jl0.setBounds(110, 18, 300, 40);
		jl0.setFont(new Font("黑体",Font.BOLD,27));
		this.con.add(jl0);
		
		JLabel jl10 = new JLabel("节点个数：");
		jl10.setBounds(55, 70, 80, 25);
		jl10.setFont(new Font("黑体",Font.PLAIN,15));
		JLabel jl11 = new JLabel("链路个数：");
		jl11.setBounds(55, 100, 80, 25);
		jl11.setFont(new Font("黑体",Font.PLAIN,15));
		JLabel jl12 = new JLabel("波长个数：");
		jl12.setBounds(55, 130, 80, 25);
		jl12.setFont(new Font("黑体",Font.PLAIN,15));
		JLabel jl13 = new JLabel("持续时间：");
		jl13.setBounds(55, 160, 80, 25);
		jl13.setFont(new Font("黑体",Font.PLAIN,15));
		this.con.add(jl10);
		this.con.add(jl11);
		this.con.add(jl12);
		this.con.add(jl13);
		
		this.con.add(jtf10);
		jtf10.setBounds(140, 70, 80, 25);
		jtf10.setFont(new Font("黑体",Font.PLAIN,15));
		jtf10.setBackground(Color.white);
		jtf10.setBorder(BorderFactory.createEtchedBorder());
		this.con.add(jtf11);
		jtf11.setBounds(140, 100, 80, 25);
		jtf11.setFont(new Font("黑体",Font.PLAIN,15));
		jtf11.setBackground(Color.white);
		jtf11.setBorder(BorderFactory.createEtchedBorder());
		this.con.add(jtf12);
		jtf12.setBounds(140, 130, 80, 25);
		jtf12.setFont(new Font("黑体",Font.PLAIN,15));
		jtf12.setBackground(Color.white);
		jtf12.setBorder(BorderFactory.createEtchedBorder());
		this.con.add(jtf13);
		jtf13.setBounds(140, 160, 80, 25);
		jtf13.setFont(new Font("黑体",Font.PLAIN,15));
		jtf13.setBackground(Color.white);
		jtf13.setBorder(BorderFactory.createEtchedBorder());
		
		JButton jb1 = new JButton("生成拓扑");
		this.con.add(jb1);
		jb1.setBounds(92, 192, 85, 25);
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createTopo(jtf10.getText(),jtf11.getText(),jtf12.getText(),jtf13.getText());
			}
		});
		
		JButton jb2 = new JButton("开始模拟");
		this.con.add(jb2);
		jb2.setBounds(75, 227, 120, 30);
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				beginSimu();
			}
		});
		
		JLabel jl20 = new JLabel("拓扑显示");
		this.con.add(jl20);
		jl20.setFont(new Font("黑体",Font.PLAIN,17));
		jl20.setBounds(280, 65, 120, 20);
		this.con.add(jl_icon);
		jl_icon.setBorder(BorderFactory.createEtchedBorder());
		jl_icon.setBounds(280, 90, 170, 170);
		
		JLabel jl30 = new JLabel("控制台");
		this.con.add(jl30);
		jl30.setFont(new Font("黑体",Font.PLAIN,17));
		jl30.setBounds(55,285,80,25);
		console.setFont(new Font("黑体",Font.PLAIN,13));
		JScrollPane sp = new JScrollPane(console);
		this.con.add(sp);
		sp.setBounds(55, 310, 400, 100);
		sp.setBorder(BorderFactory.createEtchedBorder());
	}
		//拓扑生成方法
	public void createTopo(String x,String y,String z,String w) {
			//输入校验、 调用Path类随机生成、绘制显示
		int ix=0,iy=0,iz=0,iw=0;
		boolean sign = true;
		try {
			ix = Integer.parseInt(x);
			iy = Integer.parseInt(y);
			iz = Integer.parseInt(z);
			iw = Integer.parseInt(w);
		} catch (Exception e) {
			sign = false;
		}
		if(!sign) {
			JDialog jd = new JDialog(jf,"错误提示",true);   //父窗体、标题、是否阻塞（默认否）
			jd.setBounds(610, 350, 100, 150);
			JLabel jl = new JLabel("<html>数据不合规范，<br>请输入整数。");
			jl.setHorizontalAlignment(SwingConstants.CENTER);
			Container con2 = jd.getContentPane();
			con2.add(jl);
			jd.setVisible(true);
			jtf10.setText("");
			jtf11.setText("");
			jtf12.setText("");
			jtf13.setText("");
		}else {
			if(ix<4 || ix>8 || iy<ix-1 || iy>ix*(ix-1)/2 || iz<3 || iz>6 || iw<50 || iw>250) {
				JDialog jd = new JDialog(jf,"错误提示",true);
				jd.setBounds(550, 300, 300, 250);
				JLabel jl = new JLabel("<html>数据超出有效范围，请重新输入。<br>"
						+ "<br>1.节点个数：4~8个。"
						+ "<br>2.链路个数：[n-1,n*(n-1)/2]个。"
						+ "<br>3.波长个数：3~5个。"
						+ "<br>4.持续时间：50~250个业务单位。");
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				Container con2 = jd.getContentPane();
				con2.add(jl);
				jd.setVisible(true);
				jtf10.setText("");
				jtf11.setText("");
				jtf12.setText("");
				jtf13.setText("");
			}else {
				this.n_num = ix;
				this.l_num = iy;
				this.w_num = iz;
				this.d_num = iw;
					//校验完成，开始生成拓扑
				this.path = new Path(n_num,l_num,iz);
				icon = new IconPath(120,120,path);
				jl_icon.setIcon(icon);
					//调试完毕，还需控制台输出
				console.append(">>> 拓扑网络生成成功！\n节点："+this.n_num
						+"  链路："+this.l_num+"  波长："+this.w_num+"\n");
			}
		}
	}
		//开始模拟方法
	public void beginSimu() {
		if(n_num==-1 || l_num==-1 || w_num==-1 || d_num==1) {
			JDialog jd = new JDialog(jf,"错误提示",true);   //父窗体、标题、是否阻塞（默认否）
			jd.setBounds(610, 350, 100, 150);
			JLabel jl = new JLabel("<html>拓扑尚未生成，<br>请先点击“生成拓扑”。");
			jl.setHorizontalAlignment(SwingConstants.CENTER);
			Container con2 = jd.getContentPane();
			con2.add(jl);
			jd.setVisible(true);
			return;
		}
		console.append("\n>>> 请稍等，程序正在运行。。。\n");
		tq = new TaskQueue(path,w_num,d_num);
		console.append(tq.getResult());
		System.out.println("程序运行成功，请在窗口控制台中进行查看。");
	}
		//程序运行入口
	public static void main(String[] args) {
		new Mission();
	}
	
}

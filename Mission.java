import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Mission {
	
	private JFrame jf = new JFrame("���ӻ�-2019210982");
	private Container con = jf.getContentPane();
	private JTextField jtf10 = new JTextField(3);
	private JTextField jtf11 = new JTextField(3);
	private JTextField jtf12 = new JTextField(3);
	private JTextField jtf13 = new JTextField(4);
	private JLabel jl_icon = new JLabel();
	private JTextArea console = new JTextArea();
	private int n_num=-1,l_num=-1,w_num=-1,d_num=-1;           //�ڵ㡢��·������������ʱ��
	private Path path;
	private IconPath icon;
	private TaskQueue tq;
		//���췽��
	public Mission() {
		this.setJf();
		this.addWidget();
	}
		//����������
	void setJf() {
		jf.setBounds(400,200,520,470);
		con.setBackground(new Color(248,248,255));
		con.setLayout(null);                   //������ʹ�ò��ֹ������Բ��֣�
		jf.setVisible(true);
		jf.setResizable(false);                //�����û����ɸı��С
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		//������
	void addWidget() {
		JLabel jl0 = new JLabel("����ҵ������ҵ��ģ��");
		jl0.setBounds(110, 18, 300, 40);
		jl0.setFont(new Font("����",Font.BOLD,27));
		this.con.add(jl0);
		
		JLabel jl10 = new JLabel("�ڵ������");
		jl10.setBounds(55, 70, 80, 25);
		jl10.setFont(new Font("����",Font.PLAIN,15));
		JLabel jl11 = new JLabel("��·������");
		jl11.setBounds(55, 100, 80, 25);
		jl11.setFont(new Font("����",Font.PLAIN,15));
		JLabel jl12 = new JLabel("����������");
		jl12.setBounds(55, 130, 80, 25);
		jl12.setFont(new Font("����",Font.PLAIN,15));
		JLabel jl13 = new JLabel("����ʱ�䣺");
		jl13.setBounds(55, 160, 80, 25);
		jl13.setFont(new Font("����",Font.PLAIN,15));
		this.con.add(jl10);
		this.con.add(jl11);
		this.con.add(jl12);
		this.con.add(jl13);
		
		this.con.add(jtf10);
		jtf10.setBounds(140, 70, 80, 25);
		jtf10.setFont(new Font("����",Font.PLAIN,15));
		jtf10.setBackground(Color.white);
		jtf10.setBorder(BorderFactory.createEtchedBorder());
		this.con.add(jtf11);
		jtf11.setBounds(140, 100, 80, 25);
		jtf11.setFont(new Font("����",Font.PLAIN,15));
		jtf11.setBackground(Color.white);
		jtf11.setBorder(BorderFactory.createEtchedBorder());
		this.con.add(jtf12);
		jtf12.setBounds(140, 130, 80, 25);
		jtf12.setFont(new Font("����",Font.PLAIN,15));
		jtf12.setBackground(Color.white);
		jtf12.setBorder(BorderFactory.createEtchedBorder());
		this.con.add(jtf13);
		jtf13.setBounds(140, 160, 80, 25);
		jtf13.setFont(new Font("����",Font.PLAIN,15));
		jtf13.setBackground(Color.white);
		jtf13.setBorder(BorderFactory.createEtchedBorder());
		
		JButton jb1 = new JButton("��������");
		this.con.add(jb1);
		jb1.setBounds(92, 192, 85, 25);
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createTopo(jtf10.getText(),jtf11.getText(),jtf12.getText(),jtf13.getText());
			}
		});
		
		JButton jb2 = new JButton("��ʼģ��");
		this.con.add(jb2);
		jb2.setBounds(75, 227, 120, 30);
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				beginSimu();
			}
		});
		
		JLabel jl20 = new JLabel("������ʾ");
		this.con.add(jl20);
		jl20.setFont(new Font("����",Font.PLAIN,17));
		jl20.setBounds(280, 65, 120, 20);
		this.con.add(jl_icon);
		jl_icon.setBorder(BorderFactory.createEtchedBorder());
		jl_icon.setBounds(280, 90, 170, 170);
		
		JLabel jl30 = new JLabel("����̨");
		this.con.add(jl30);
		jl30.setFont(new Font("����",Font.PLAIN,17));
		jl30.setBounds(55,285,80,25);
		console.setFont(new Font("����",Font.PLAIN,13));
		JScrollPane sp = new JScrollPane(console);
		this.con.add(sp);
		sp.setBounds(55, 310, 400, 100);
		sp.setBorder(BorderFactory.createEtchedBorder());
	}
		//�������ɷ���
	public void createTopo(String x,String y,String z,String w) {
			//����У�顢 ����Path��������ɡ�������ʾ
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
			JDialog jd = new JDialog(jf,"������ʾ",true);   //�����塢���⡢�Ƿ�������Ĭ�Ϸ�
			jd.setBounds(610, 350, 100, 150);
			JLabel jl = new JLabel("<html>���ݲ��Ϲ淶��<br>������������");
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
				JDialog jd = new JDialog(jf,"������ʾ",true);
				jd.setBounds(550, 300, 300, 250);
				JLabel jl = new JLabel("<html>���ݳ�����Ч��Χ�����������롣<br>"
						+ "<br>1.�ڵ������4~8����"
						+ "<br>2.��·������[n-1,n*(n-1)/2]����"
						+ "<br>3.����������3~5����"
						+ "<br>4.����ʱ�䣺50~250��ҵ��λ��");
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
					//У����ɣ���ʼ��������
				this.path = new Path(n_num,l_num,iz);
				icon = new IconPath(120,120,path);
				jl_icon.setIcon(icon);
					//������ϣ��������̨���
				console.append(">>> �����������ɳɹ���\n�ڵ㣺"+this.n_num
						+"  ��·��"+this.l_num+"  ������"+this.w_num+"\n");
			}
		}
	}
		//��ʼģ�ⷽ��
	public void beginSimu() {
		if(n_num==-1 || l_num==-1 || w_num==-1 || d_num==1) {
			JDialog jd = new JDialog(jf,"������ʾ",true);   //�����塢���⡢�Ƿ�������Ĭ�Ϸ�
			jd.setBounds(610, 350, 100, 150);
			JLabel jl = new JLabel("<html>������δ���ɣ�<br>���ȵ�����������ˡ���");
			jl.setHorizontalAlignment(SwingConstants.CENTER);
			Container con2 = jd.getContentPane();
			con2.add(jl);
			jd.setVisible(true);
			return;
		}
		console.append("\n>>> ���Եȣ������������С�����\n");
		tq = new TaskQueue(path,w_num,d_num);
		console.append(tq.getResult());
		System.out.println("�������гɹ������ڴ��ڿ���̨�н��в鿴��");
	}
		//�����������
	public static void main(String[] args) {
		new Mission();
	}
	
}

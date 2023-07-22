import javax.swing.*;
import java.awt.*;

public class IconPath implements Icon {
	
	private int w,h;
	private Path path;
		//���췽��
	public IconPath(int w,int h,Path path) {
		this.w = w;
		this.h = h;
		this.path = path;
	}
		//��ȡ�����߶�Ӧ���߽��
	public int[] getNode(int link) {
		int i=0,j=0,cnt=0;
		int[] l = {-1,-1};
		for(i=0;i<path.getNum();i++) {
			for(j=i+1;j<path.getNum();j++) {
				if(cnt == link) {
					l[0] = i;
					l[1] = j;
				}
				cnt++;
			}
		}
		if(l[0] == -1 || l[1] == -1) {
			System.out.println("�������");
		}
		return l;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {      //x,y���������ĵ�
			//�����Ǻ��Ļ�������
		if(path.getNum() == 4) {             //���Ͽ�ʼ��˳ʱ�룩
			g.setColor(Color.red);
			int[] lx = {x-w/2,x+w/2,x+w/2,x-w/2};
			int[] ly = {y-h/2,y-h/2,y+h/2,y+h/2};
			for(int i : path.getLinks()) {
				int[] l = this.getNode(i);
				g.drawLine(lx[l[0]]+w*9/14, ly[l[0]]+h/2, lx[l[1]]+w*9/14, ly[l[1]]+h/2);
			}
		}else if(path.getNum() == 5) {       //���Ͽ�ʼ��˳ʱ�룩
			g.setColor(new Color(65,105,225));
			int[] lx = {x-w/2,x,x+w/2,x+w*2/7,x-w*2/7};
			int[] ly = {y-h/7,y-h/2,y-h/7,y+h/2,y+h/2};
			for(int i : path.getLinks()) {
				int[] l = this.getNode(i);
				g.drawLine(lx[l[0]]+w*9/14, ly[l[0]]+h/2, lx[l[1]]+w*9/14, ly[l[1]]+h/2);
			}
		}else if(path.getNum() == 6) {       //��࿪ʼ��˳ʱ�룩
			g.setColor(new Color(106,90,205));
			int[] lx = {x-w/2,x-w*2/7,x+w*2/7,x+w/2,x+w*2/7,x-w*2/7};
			int[] ly = {y,y-h/2,y-h/2,y,y+h/2,y+h/2};
			for(int i : path.getLinks()) {
				int[] l = this.getNode(i);
				g.drawLine(lx[l[0]]+w*9/14, ly[l[0]]+h/2, lx[l[1]]+w*9/14, ly[l[1]]+h/2);
			}
		}else if(path.getNum() == 7) {       //�Ϸ���ʼ��˳ʱ�룩
			g.setColor(new Color(255,97,0));
			int[] lx = {x,x+w/2,x+w/2,x+w/4,x-w/4,x-w/2,x-w/2};
			int[] ly = {y-h/2,y-h*3/8,y+h/8,y+h/2,y+h/2,y+h/8,y-h*3/8};
			for(int i : path.getLinks()) {
				int[] l = this.getNode(i);
				g.drawLine(lx[l[0]]+w*9/14, ly[l[0]]+h/2, lx[l[1]]+w*9/14, ly[l[1]]+h/2);
			}
		}else if(path.getNum() == 8) {       //���Ͽ�ʼ��˳ʱ�룩
			g.setColor(new Color(34,139,34));
			int[] lx = {x-w/2,x-w/4,x+w/4,x+w/2,x+w/2,x+w/4,x-w/4,x-w/2};
			int[] ly = {y-h/4,y-h/2,y-h/2,y-h/4,y+h/4,y+h/2,y+h/2,y+h/4};
			for(int i : path.getLinks()) {
				int[] l = this.getNode(i);
				g.drawLine(lx[l[0]]+w*9/14, ly[l[0]]+h/2, lx[l[1]]+w*9/14, ly[l[1]]+h/2);
			}
		}
	}

	@Override
	public int getIconWidth() {
		return w;
	}

	@Override
	public int getIconHeight() {
		return h;
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame("���ԣ��������˻���");
		Container con = jf.getContentPane();
		jf.setBounds(400,200,600,450);
		con.setBackground(Color.white);
		con.setLayout(null);                   //������ʹ�ò��ֹ������Բ��֣�
		jf.setVisible(true);
		jf.setResizable(false);                //�����û����ɸı��С
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IconPath icon = new IconPath(200,200,new Path(8,10,5));
		JLabel jl = new JLabel(icon);
		jl.setBounds(100, 100, 400, 400);
		
		con.add(jl);
	}

}

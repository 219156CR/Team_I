import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

public class Screen extends Canvas implements ComponentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5203228742370884076L;
	private Graphics bufferGraphics;
	private Image offScreen;
	private Dimension dim;
	private Character ryu = new Character();
	private int countNumber = 0;
	
	public void counting() {
		this.countNumber++;
	}
	
	public int getCount() {
		return this.countNumber;
	}
	public Screen() {
		addComponentListener(this);
		addKeyListener(ryu);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
				counting();
			}
		}, 0, 1);
	}
	
	private void initBuffer() {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.bufferGraphics = this.offScreen.getGraphics();
	}
	
	@Override
	public void paint(Graphics g) {
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		ryu.draw(bufferGraphics, this);
		g.drawImage(this.offScreen, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		initBuffer();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}

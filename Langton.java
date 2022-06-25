import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Langton extends JPanel
{
	private static final int SCREEN_WIDTH = 500;
	private static final int SCREEN_HEIGHT = 500;
	private static final int BOX_DIM = 1;
	private static int[][] grid = new int[SCREEN_WIDTH][SCREEN_HEIGHT];
	
	public static void drawPixel(int x, int y, Graphics2D g2d, Color col, boolean white) 
	{
		g2d.setColor(col);
		g2d.fillRect(x, y, BOX_DIM, BOX_DIM); 
		if(white) 
			grid[x][y] = 1;
		else 
			grid[x][y] = 0;

	}
	
	public static boolean checkPixelWhite(int x, int y)
	{
		return (grid[x][y] == 0);
	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		JFrame frame = new JFrame("Langton's Ant");
		frame.add(new Langton());
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setBackground(Color.black);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Graphics2D g2d = (Graphics2D) frame.getGraphics();
		int antX1 = (SCREEN_WIDTH/2);
		int antY1 = (SCREEN_HEIGHT/2);

		while(true)
		{
			antMovement(antX1, antY1, g2d);
		}	
	}
	
	
	
	public static void antMovement(int antX, int antY, Graphics2D g2d) throws InterruptedException
	{
		Direction antDirection = Direction.N;
		Color col = Color.black;
		while(true)
		{
			try {
				if(antX > SCREEN_WIDTH -1) 
					antX = 0;
				else if(antX < 0) 
					antX = SCREEN_WIDTH - 1;
				if(antY > SCREEN_HEIGHT -1) 
					antY = 0;
				else if(antY < 0) 
					antY = SCREEN_HEIGHT - 1;
				if(checkPixelWhite(antX, antY))
				{	
					drawPixel(antX, antY, g2d, col, true);
					switch(antDirection)
					{
					case N:
						antDirection = Direction.E;
						antY += BOX_DIM;
						col = Color.red;
						break;
	
					case S:
						antDirection = Direction.W;
						antY -= BOX_DIM;
						col = Color.red;
						break;
	
					case E:
						antDirection = Direction.S;
						antX += BOX_DIM;
						col = Color.red;
						break;
	
					case W:
						antDirection = Direction.N;
						antX -= BOX_DIM;
						col = Color.red;
						break;
					}
				}
				
				else
				{
					drawPixel(antX, antY, g2d, col, false);
					switch(antDirection)
					{
					case N:
						antDirection = Direction.W;
						antY -= BOX_DIM;
						//col = Color.blue;
						break;
					case S:
						antDirection = Direction.E;
						antY += BOX_DIM;
						col = Color.blue;
						break;
	
					case E:
						antDirection = Direction.N;
						antX -= BOX_DIM;
						col = Color.blue;
						break;
	
					case W:
						antDirection = Direction.S;
						antX += BOX_DIM;
						col = Color.blue;
						break;
					}
				}
	
			}
			
			catch(ArrayIndexOutOfBoundsException e)
			{
				 System.out.println("x: " +antX);
				 System.out.println("y: " +antY);
				 e.printStackTrace();
			}
			
			Thread.sleep(1);
		}
	}
	
	public enum Direction
	{
		N, S, E, W;
	}
}



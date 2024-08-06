import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

    private int counter = 0;
    private int counter_for_cherry = 0;

    private static final int WIDTH = 750;
	private static final int HEIGHT = 750;

	private int SQUARE_SIZE;
    private int GAME_UNITS;

    private int x[];
	private int y[];

	private boolean isAlive = false;

	private static final int DELAY = 100;
	private Timer timer;
    private Random random;

	private Apple apple;
    private Cherry cherry;

    private Snake snake;

    private char direction = 'R'; 
   

	

    public GamePanel(int val, int speed) {
        random = new Random(); 

        SQUARE_SIZE = val;
        GAME_UNITS = (WIDTH * HEIGHT)/(SQUARE_SIZE * SQUARE_SIZE);

        x = new int[GAME_UNITS];
	    y = new int[GAME_UNITS];
		
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(new MyKeyAdapter());
        this.setFocusable(true);    

		start_game(speed);		
    }
	
	public void start_game(int speed){
    
		putApple();

		putCherry(); 
	
		Timer timer = new Timer(speed, this);
		timer.start();

        snake = new Snake(6, "yellow");

		isAlive = true;

	}

    public void putApple() {

        int x = random.nextInt((int)(WIDTH / SQUARE_SIZE)) * SQUARE_SIZE;
        int y = random.nextInt((int)(HEIGHT / SQUARE_SIZE)) * SQUARE_SIZE;
        String c = "green";      
        
        apple = new Apple(3, c, x, y);
    }

    public void putCherry() {

        counter_for_cherry = 0;

        int x = random.nextInt((int)(WIDTH / SQUARE_SIZE)) * SQUARE_SIZE;
        int y = random.nextInt((int)(HEIGHT / SQUARE_SIZE)) * SQUARE_SIZE;
        String c = "red";

        cherry = new Cherry(5, c, x, y);
    }

    public void deleteCherry(){
        int x = WIDTH + 1;
        int y = HEIGHT + 1;
        String c = "red";

        cherry = new Cherry(5, c, x, y);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g){
        if(isAlive){

            g.setColor(Color.green); 
            g.fillOval(apple.x, apple.y, SQUARE_SIZE, SQUARE_SIZE);
        
            g.setColor(Color.red); 
            g.fillOval(cherry.x, cherry.y, SQUARE_SIZE, SQUARE_SIZE);

            for(int i = 0; i < snake.bodylen; i++){
                g.setColor(Color.yellow);
                g.fillRect(x[i], y[i], SQUARE_SIZE,  SQUARE_SIZE);
            }

            g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+counter, (WIDTH - metrics.stringWidth("Score: "+counter))/2, g.getFont().getSize());
            
        } else {
            endGame(g);
        }
    }

    public void endGame(Graphics g){

        g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: "+counter, (WIDTH - metrics.stringWidth("Score: "+counter))/2, g.getFont().getSize());
        
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (WIDTH - metrics2.stringWidth("Game Over"))/2, HEIGHT/2);

        //timer.stop();


    }

    public void move() {
        for (int i = snake.bodylen; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - SQUARE_SIZE;
                break;
            case 'D':
                y[0] = y[0] + SQUARE_SIZE;
                break;
            case 'L':
                x[0] = x[0] - SQUARE_SIZE;
                break;
            case 'R':
                x[0] = x[0] + SQUARE_SIZE;
                break;
            } 
    }

    public void checkApple(){
        if((x[0] == apple.x) && (y[0] == apple.y)) {
            counter_for_cherry++;
			snake.bodylen+=apple.value;
			counter+=apple.value;
			putApple();
		}
    }

    public void checkCherry(){
        if((x[0] == cherry.x) && (y[0] == cherry.y)) {
			snake.bodylen+=cherry.value;
			counter+=cherry.value;
			deleteCherry();  
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isAlive) {
			move();

            checkApple();     
            checkCherry();
            checkCollisions();
            if(counter_for_cherry > 4){
                putCherry();
            }
            
		}
        repaint(); 
    }

    public void checkCollisions() {
		for(int i = snake.bodylen; i > 0; i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				isAlive = false;
			}
		}
		
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            isAlive = false;
        }

	}

    public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}

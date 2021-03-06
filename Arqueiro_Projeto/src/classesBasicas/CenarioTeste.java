package classesBasicas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import posicao.Ponto;
/**
 * M�todo que constr�i o cen�rio gr�fico e desenha os elementos do jogo
 * @author Babi
 *
 */
public class CenarioTeste extends JFrame implements KeyListener{

	BufferedImage backBuffer;
	int FPS = 30;
	int janelaW = 800;
	int janelaH = 600;
	char teclaPressionada;
	int xBola = 10, yBola = 300;
	
	Arqueiro arq = new Arqueiro();
	Alvo alvo = new Alvo();
	Flecha flecha = new Flecha(FPS);
	

	public Ponto PosicaoArq() {
		Ponto ponto = new Ponto(xBola, yBola);
		arq.setPosicao(ponto);

		return arq.getPosicao();

	}

	public Ponto PosicaoFlecha() {
		
		Ponto ponto = new Ponto(xBola,yBola);
		if(flecha.getPosicao().getCoordenadaY()>30 && flecha.getPosicao().getCoordenadaY()<550){
		this.flecha.setPosicao(ponto);
		}
	
		return this.flecha.getPosicao();
	}



	public Ponto PosicaoAlvo() {
		Ponto ponto = new Ponto(600, 500);
		Ponto pontoLoop = new Ponto(600, 500);

		int coordenadaY;

		ponto = alvo.getPosicao();
		coordenadaY = ponto.getCoordenadaY();
		if (coordenadaY > 30 && coordenadaY < 550) {
			coordenadaY--;
			ponto.setCoordenadaY(coordenadaY);

			alvo.setPosicao(ponto);
		} else {
			coordenadaY++;
			alvo.setPosicao(pontoLoop);
		}

		return alvo.getPosicao();

	}

	public void atirar(){
		int x = this.flecha.getPosicao().getCoordenadaX();
		while(x<500){
			x++;
			flecha.getPosicao().setCoordenadaX(x);
			}
			
	}

	public void desenharGraficos() {
		int x, y;
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		Graphics bbg2 = backBuffer.getGraphics();
		Graphics bbg3 = backBuffer.getGraphics();
		bbg.setColor(Color.GREEN);
		bbg2.setColor(Color.BLUE);
		bbg3.setColor(Color.YELLOW);
		bbg.fillRect(0, 0, janelaW, janelaH);

		bbg.setColor(Color.RED);
		PosicaoArq();
		x = arq.getPosicao().getCoordenadaX();
		y = arq.getPosicao().getCoordenadaY();

		// DESENHA UMA BOLA VERMELHA NA TELA
		bbg.fillOval(x, y, 50, 50);
		// DESENHA UMA BOLA AZUL -ALVO

		PosicaoAlvo();
		x = alvo.getPosicao().getCoordenadaX();
		y = alvo.getPosicao().getCoordenadaY();

		bbg2.fillOval(x, y, 60, 60);

		x = flecha.getPosicao().getCoordenadaX();
		y = flecha.getPosicao().getCoordenadaY();

		bbg3.fillOval(x, y, 30, 30);

		g.drawImage(backBuffer, 0, 0, this);
	}

	public void inicializar() {
		setTitle("Arqueiro");
		setSize(janelaW, janelaH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		backBuffer = new BufferedImage(janelaW, janelaH,
				BufferedImage.TYPE_INT_RGB);

		// Keylistener
		addKeyListener(this);
	}

	public void atualizar() {
		PosicaoAlvo();
		PosicaoFlecha();
		
	}

	public void run() {
		inicializar();
		while (true) {
			atualizar();
			desenharGraficos();
			try {
				Thread.sleep(1000 / FPS);
			} catch (Exception e) {
				System.out.println("Thread interrompida!");
			}
		}
	}

	public void keyPressed(KeyEvent e) {

		teclaPressionada = e.getKeyChar();
		

		if (e.getKeyCode() == e.VK_UP) {
			yBola -= 10;
		}

		if (e.getKeyCode() == e.VK_DOWN) {
			yBola += 10;
		}
		if(e.getKeyCode() == e.VK_SPACE){
			atirar();
			
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

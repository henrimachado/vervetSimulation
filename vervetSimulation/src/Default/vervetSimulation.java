/*
 * SIMULAÇÃO DE COMUNICAÇÃO ENTRE MACACOS VERVETS
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 * @authot Lucas Alexsander Barbosa Cruz
 * @version 1.0
 * @since 25-07-2023
 * */


package Default;

import Entidades.*;

import processing.core.*;


public class vervetSimulation extends PApplet {
	PImage background;
	PImage macaco1;
	PImage macaco2;
	PImage macaco3;
	PImage macaco4;
	PImage cobra;
	PImage tigre;
	PImage aguia;


	

	public void settings() {
		size(1280, 960);

	}

	public void setup() {

		background = loadImage("imgs/backgroundfield.png");
		background.resize(1280, 960);

		macaco1 = loadImage("imgs/macaco.png");
		macaco1.resize((800 / Config.altura), (800 / Config.largura));

		macaco2 = loadImage("imgs/macaco.png");
		macaco2.resize((800 / Config.altura), (800 / Config.largura));

		macaco3 = loadImage("imgs/macaco.png");
		macaco3.resize((800 / Config.altura), (800 / Config.largura));

		macaco4 = loadImage("imgs/macaco.png");
		macaco4.resize((800 / Config.altura), (800 / Config.largura));

		cobra = loadImage("imgs/cobra.png");
		cobra.resize((800 / Config.altura), (800 / Config.largura));

		tigre = loadImage("imgs/tigre.png");
		tigre.resize((800 / Config.altura), (800 / Config.largura));

		aguia = loadImage("imgs/aguia.png");
		aguia.resize((800 / Config.altura), (800 / Config.largura));
		
		

	}

	public void draw() {
		// background(color(26, 94, 99));
		image(background, 0, 0);

		strokeWeight(4);
		// Predador 1 - Cobra
		image(cobra, (Ambiente.getPredadores()[0].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getPredadores()[0].getPosicao()[1] + 1) * (800 / Config.altura));

		// Predador 1 - Tigre
		image(tigre, (Ambiente.getPredadores()[1].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getPredadores()[1].getPosicao()[1] + 1) * (800 / Config.altura));

		// Predador 1 - Águia
		image(aguia, (Ambiente.getPredadores()[2].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getPredadores()[2].getPosicao()[1] + 1) * (800 / Config.altura));

		// Macaco 1
		noStroke();
		fill(color(237, 20, 7, 30));
		this.ellipse((Ambiente.getMacacos()[0].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[0].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioVisao + 1), (800 / Config.altura) * (Config.raioVisao + 1));

		fill(color(34, 46, 150, 30));
		this.ellipse((Ambiente.getMacacos()[0].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[0].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioAudicao + 1), (800 / Config.altura) * (Config.raioAudicao + 1));

		image(macaco1, (Ambiente.getMacacos()[0].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[0].getPosicao()[1] + 1) * (800 / Config.altura));

		// Macaco 2
		fill(color(237, 20, 7, 30));
		this.ellipse((Ambiente.getMacacos()[1].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[1].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioVisao + 1), (800 / Config.altura) * (Config.raioVisao + 1));

		fill(color(34, 46, 150, 30));
		noStroke();
		this.ellipse((Ambiente.getMacacos()[1].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[1].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioAudicao + 1), (800 / Config.altura) * (Config.raioAudicao + 1));

		image(macaco2, (Ambiente.getMacacos()[1].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[1].getPosicao()[1] + 1) * (800 / Config.altura));

		// Macaco 3
		fill(color(237, 20, 7, 30));
		this.ellipse((Ambiente.getMacacos()[2].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[2].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioVisao + 1), (800 / Config.altura) * (Config.raioVisao + 1));

		fill(color(34, 46, 150, 30));
		noStroke();
		this.ellipse((Ambiente.getMacacos()[2].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[2].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioAudicao + 1), (800 / Config.altura) * (Config.raioAudicao + 1));

		image(macaco3, (Ambiente.getMacacos()[2].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[2].getPosicao()[1] + 1) * (800 / Config.altura));

		// Macaco 4
		fill(color(237, 20, 7, 30));
		this.ellipse((Ambiente.getMacacos()[3].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[3].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioVisao + 1), (800 / Config.altura) * (Config.raioVisao + 1));

		fill(color(34, 46, 150, 30));
		noStroke();
		this.ellipse((Ambiente.getMacacos()[3].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[3].getPosicao()[1] + 1) * (800 / Config.altura),
				(800 / Config.largura) * (Config.raioAudicao + 1), (800 / Config.altura) * (Config.raioAudicao + 1));

		image(macaco4, (Ambiente.getMacacos()[3].getPosicao()[0] + 1) * (800 / Config.largura),
				(Ambiente.getMacacos()[3].getPosicao()[1] + 1) * (800 / Config.altura));

		
		stroke(255,255,255);
		strokeWeight(2);
		line(1080, 110, 1080, 630);
		
		textSize(24);
		fill(255,255,255);
		text("LINGUAGEM", 1120, 165);
		
		fill(255,255,255);
		text("Macaco 1", 1120, 230);
		
		String linguagemM1 = String.valueOf(Ambiente.getMacacos()[0].getLinguagem()[0]) + " - " + String.valueOf(Ambiente.getMacacos()[0].getLinguagem()[1])+ " - " + String.valueOf(Ambiente.getMacacos()[0].getLinguagem()[2]);
		
		fill(255,255,255);
		text(linguagemM1, 1120, 260);
		
		
		
		fill(255,255,255);
		text("Macaco 2", 1120, 310);
		
		String linguagemM2 = String.valueOf(Ambiente.getMacacos()[1].getLinguagem()[0]) + " - " + String.valueOf(Ambiente.getMacacos()[1].getLinguagem()[1])+ " - " + String.valueOf(Ambiente.getMacacos()[1].getLinguagem()[2]);
		
		fill(255,255,255);
		text(linguagemM2, 1120, 340);
		
		
		fill(255,255,255);
		text("Macaco 3", 1120, 390);
		
		String linguagemM3 = String.valueOf(Ambiente.getMacacos()[2].getLinguagem()[0]) + " - " + String.valueOf(Ambiente.getMacacos()[2].getLinguagem()[1])+ " - " + String.valueOf(Ambiente.getMacacos()[2].getLinguagem()[2]);
		
		fill(255,255,255);
		text(linguagemM3, 1120, 420);
		
		
		fill(255,255,255);
		text("Macaco 4", 1120, 470);
		
		String linguagemM4 = String.valueOf(Ambiente.getMacacos()[3].getLinguagem()[0]) + " - " + String.valueOf(Ambiente.getMacacos()[3].getLinguagem()[1])+ " - " + String.valueOf(Ambiente.getMacacos()[3].getLinguagem()[2]);
		
		fill(255,255,255);
		text(linguagemM4, 1120, 500);

		String iteracoes = "Qnt. Iterações:\n" + String.valueOf(Sistema.getI());
		
		textSize(18);
		fill(255,255,255);
		text(iteracoes, 1120, 550);
		
	}

	public static void main(String[] args) {
		PApplet.main("Default.vervetSimulation");
		
		Sistema newSistema = new Sistema();
		try {
			newSistema.startSimulacao();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


	}
}
//
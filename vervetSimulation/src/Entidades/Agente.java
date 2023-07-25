package Entidades;

import java.util.Random;

public class Agente {


	public int velocidade, linha, coluna;
	public boolean vagaVazia;
	public int[] posicao = new int[2];

	public Agente() {
		
	}
	
	public Agente(int velocidade) {
		this.velocidade = velocidade;

		Random aleatorio = new Random();
		this.linha = aleatorio.nextInt(Config.altura);
		this.coluna = aleatorio.nextInt(Config.largura);
		this.vagaVazia = Ambiente.getmAmbienteByPosition(linha, coluna) == null;

		while (this.vagaVazia == false) {
			this.linha = aleatorio.nextInt(Config.altura);
			this.coluna = aleatorio.nextInt(Config.largura);
			this.vagaVazia = Ambiente.getmAmbienteByPosition(linha, coluna)==null;
		}
		Ambiente.setmAmbienteByPosition(this, this.linha, this.coluna);
		this.posicao[0] = this.linha;
		this.posicao[1] = this.coluna;
	}

	public void movimento() {
		boolean movValido = false;
		int linhaM = 0;
		int colunaM = 0;

		Random movAleatorio = new Random();

		while (movValido == false) {
			switch (movAleatorio.nextInt(8)) {

			case 0: // Cima
				linhaM = -this.velocidade;
				colunaM = 0;
				break;

			case 1: // Cima-direita
				linhaM = -this.velocidade;
				colunaM = this.velocidade;
				break;

			case 2: // Direita
				linhaM = 0;
				colunaM = this.velocidade;
				break;

			case 3:// Baixo - direita
				linhaM = this.velocidade;
				colunaM = this.velocidade;
				break;

			case 4: // Baixo
				linhaM = this.velocidade;
				colunaM = 0;
				break;

			case 5: // Baixo - esquerda
				linhaM = this.velocidade;
				colunaM = -this.velocidade;
				break;

			case 6: // Esquerda
				linhaM = 0;
				colunaM = -this.velocidade;
				break;

			case 7: // Cima - esquerda
				linhaM = -this.velocidade;
				colunaM = -this.velocidade;
				break;
			}
			int newLine = this.posicao[0] + linhaM;
			int newCol = this.posicao[1] + colunaM;
			
			if ((newLine >=0) && (newLine < Config.altura) && (newCol >= 0) && (newCol < Config.largura) && 
					Ambiente.isVagavazia(newLine, newCol) == true) {

				movValido = true;
			}
		}

		Ambiente.setmAmbienteByPosition(this, this.posicao[0] + linhaM, this.posicao[1] + colunaM);
		Ambiente.setmAmbienteByPosition(null, this.posicao[0], this.posicao[1]);
		this.posicao[0] = this.posicao[0] + linhaM;
		this.posicao[1] = this.posicao[1] + colunaM;

	}

	public int[] getPosicao() {
		return posicao;
	}

	public void setPosicao(int[] posicao) {
		this.posicao = posicao;
	}
	
	public int getId() {
		return 0;
	}
	
	public void ver() {}
	
	public int getMemoriaAlerta() {return 1;}
	

}

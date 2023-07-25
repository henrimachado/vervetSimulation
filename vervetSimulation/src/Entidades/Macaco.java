package Entidades;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DecimalFormat;

public class Macaco extends Agente {

	public String id;
	public double[][] simbolos = new double[10][3];
	public Agente memoriaPredador;
	public int memoriaSimbolo;
	public int memoriaAlerta;
	public int[] linguagem = new int[3];

	public Macaco() {

	}

	public Macaco(int velocidade, String id) {
		super(velocidade);
		this.id = id;
		this.initSimb();
		this.memoriaPredador = null;
		this.memoriaAlerta = -1;
		this.memoriaSimbolo = -1;

	}

	public void initSimb() {
		for (int i = 0; i < Config.qntPredador; i++) {
			Set<Double> numerosGeradosC = new HashSet<>();
			for (int j = 0; j < Config.qntSimbolos; j++) {
				Set<Double> numerosGeradosL = new HashSet<>();
				Random probAleatoria = new Random();
				double prob;

				do {
					prob = Math.round(probAleatoria.nextDouble(1) * 10.0) / 10.0;
				} while (numerosGeradosC.contains(prob) || numerosGeradosL.contains(prob));

				numerosGeradosL.add(prob);
				numerosGeradosC.add(prob);
				this.simbolos[j][i] = prob;
			}
		}
	}

	public void alertar(int idPredador) {
		double aux = this.simbolos[0][idPredador];
		this.setMemoriaAlerta(0);

		for (int l = 0; l < Config.qntSimbolos; l++) {
			if (this.simbolos[l][idPredador] > aux) {
				aux = this.simbolos[l][idPredador];
				this.setMemoriaAlerta(l);
			}
		}
	}

	@Override
	public void ver() {
		for (int i = -Config.raioVisao; i <= Config.raioVisao; i++) {
			for (int j = -Config.raioVisao; j <= Config.raioVisao; j++) {
				int newX = this.posicao[0] + i;
				int newY = this.posicao[1] + j;

				if (newX < 0 || newX > Config.altura - 1 || newY < 0 || newY > Config.largura - 1) {
					continue;
				}

				if (newX >= 0 && newX <= Config.altura && newY >= 0 && newY < Config.largura) {
					if (Ambiente.getmAmbienteByPosition(newX, newY) instanceof Predador) {
						this.setMemoriaPredador(Ambiente.getmAmbienteByPosition(newX, newY));
						this.alertar(this.getMemoriaPredador().getId());
					}
				}

			}
		}
	}

	public void aprender() {
		if (this.getMemoriaPredador() != null && this.getMemoriaSimbolo() != -1) {
			// int simbolPredador = 0;
			double aux = this.simbolos[0][this.memoriaPredador.getId()];

			for (int l = 0; l < Config.qntSimbolos; l++) {
				if (this.simbolos[l][this.memoriaPredador.getId()] > aux) {
					aux = this.simbolos[l][this.memoriaPredador.getId()];
					// simbolPredador = l;
				}
			}
			this.aprenderSimbolosByPosition(this.getMemoriaSimbolo(), this.getMemoriaPredador().getId());

		}

		this.correcaoSimb();
	}

	public void correcaoSimb() {
		int maiorValor0 = this.maiorValorCol(0);
		int maiorValor1 = this.maiorValorCol(1);
		int maiorValor2 = this.maiorValorCol(2);

		if (maiorValor0 == maiorValor1) {
			this.corSimbolosByPosition(maiorValor0, 1);
		}
		if (maiorValor0 == maiorValor2) {
			this.corSimbolosByPosition(maiorValor0, 2);
		}
		if (maiorValor1 == maiorValor2) {
			this.corSimbolosByPosition(maiorValor1, 2);
		}
	}

	public int maiorValorCol(int idPredador) {
		int simbolPredador = 0;
		double aux = this.simbolos[0][idPredador];

		for (int l = 0; l < Config.qntSimbolos; l++) {
			if (this.simbolos[l][idPredador] > aux) {
				aux = this.simbolos[l][idPredador];
				simbolPredador = l;
			}
		}
		return simbolPredador;
	}

	public void ouvir() {
		for (int i = -Config.raioAudicao; i <= Config.raioAudicao; i++) {
			for (int j = -Config.raioAudicao; j <= Config.raioAudicao; j++) {
				int newX = this.posicao[0] + i;
				int newY = this.posicao[1] + j;

				if (newX < 0 || newX > Config.altura - 1 || newY < 0 || newY > Config.largura - 1) {
					continue;
				}

				if (newX >= 0 && newX < Config.altura && newY >= 0 && newY < Config.largura) {
					if (Ambiente.getmAmbienteByPosition(newX, newY) instanceof Macaco
							&& Ambiente.getmAmbienteByPosition(newX, newY) != this) {
						Ambiente.getmAmbienteByPosition(newX, newY).ver();

						if (Ambiente.getmAmbienteByPosition(newX, newY).getMemoriaAlerta() != -1) {
							this.setMemoriaSimbolo(Ambiente.getmAmbienteByPosition(newX, newY).getMemoriaAlerta());
						}

					}
				}
			}
		}
	}

	public void linguagemM() {
		int[] linguagemAux = new int[3];

		for (int j = 0; j < Config.qntPredador; j++) {
			double aux = 0.0;
			for (int l = 0; l < Config.qntSimbolos; l++) {
				if (this.simbolos[l][j] > aux) {
					aux = this.simbolos[l][j];
					linguagemAux[j] = l;
				}
			}
		}

		this.setLinguagem(linguagemAux);
	}

	public void cicloMacaco() {
		this.ver();
		this.aprender();
		this.ouvir();
		this.linguagemM();
		this.guardarResultados();
	}

	public Agente getMemoriaPredador() {
		return this.memoriaPredador;
	}

	public void setMemoriaPredador(Agente memoriaPredador) {
		this.memoriaPredador = memoriaPredador;
	}

	public int getMemoriaSimbolo() {
		return this.memoriaSimbolo;
	}

	public void setMemoriaSimbolo(int memoriaSimbolo) {
		this.memoriaSimbolo = memoriaSimbolo;
	}

	@Override
	public int getMemoriaAlerta() {
		return this.memoriaAlerta;
	}

	public void setMemoriaAlerta(int memoriaAlerta) {
		this.memoriaAlerta = memoriaAlerta;
	}

	public int[] getLinguagem() {
		return this.linguagem;
	}

	public void setLinguagem(int[] linguagem) {
		this.linguagem = linguagem;
	}

	public void aprenderSimbolosByPosition(int linha, int coluna) {
		this.simbolos[linha][coluna] = this.simbolos[linha][coluna] + Config.taxaAprendizado;
	}

	public void corSimbolosByPosition(int linha, int coluna) {
		this.simbolos[linha][coluna] -= Config.taxaAprendizado;
	}

	public String toStringLiguagem() {
		String lang = "[ ";
		for (int i = 0; i < 3; i++) {
			lang += String.valueOf(this.getLinguagem()[i]) + " ";
		}
		;
		lang += "]";

		return lang;
	}

	public String getIdMacaco() {
		return this.id;
	}

	public void guardarResultados() {

		String arquivoCobra = "arquivo_" + this.getIdMacaco() + "_Cobra.txt";
		String arquivoTigre = "arquivo_" + this.getIdMacaco() + "_Tigre.txt";
		String arquivoAguia = "arquivo_" + this.getIdMacaco() + "_Aguia.txt";
		String[] arquivos = { arquivoCobra, arquivoTigre, arquivoAguia };
		DecimalFormat df = new DecimalFormat("0.0");

		int colPred = 0;
		for (String arquivo : arquivos) {
			try {

				File nArquivo = new File(arquivo);

				if (nArquivo.exists() == false) {
					nArquivo.createNewFile();
				}

				FileWriter fileWriter = new FileWriter(arquivo, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				String frase = ""; 

				for (int i = 0; i < Config.qntSimbolos; i++) {

					if (i == Config.altura - 1) {
						frase += df.format(this.simbolos[i][colPred]);
						continue;
					}

					frase += df.format(this.simbolos[i][colPred]) + ";";
				}

				bufferedWriter.write(frase);
				bufferedWriter.newLine();
				bufferedWriter.close();

			} catch (IOException e) {

			}
			colPred++;
		}

	}

	public void guardarSimbolos(String statusPrograma) {
		
		String arquivo = "simbolos_" + this.getIdMacaco() + "_" + statusPrograma + ".txt"; 
		DecimalFormat df = new DecimalFormat("0.0");
		
		try {
			File nArquivo = new File(arquivo);

			if (nArquivo.exists() == false) {
				nArquivo.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			
			for(int linha =0; linha < Config.qntSimbolos; linha++) {
				String frase = "";
				for(int coluna=0; coluna < Config.qntPredador; coluna++) {
					if (coluna == Config.qntPredador -1) {
						frase += df.format(this.simbolos[linha][coluna]);
						continue;
					}
					frase += df.format(this.simbolos[linha][coluna]) + ";";
				}
				bufferedWriter.write(frase);
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
			fileWriter.close();

		} catch (IOException e) {
		}
		
		
	}
	
	
}

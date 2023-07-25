package Entidades;

import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Sistema {

	public static int i = 0;
	
	
	public static int getI() {
		return i;
	}

	public static void setI() {
		Sistema.i += 1;
	}

	public Sistema() {

	}

	public void initSimulacao() {
		Ambiente ambiente = new Ambiente();

		// Predadores
		Predador Cobra = new Predador(1, 0);
		Predador Tigre = new Predador(2, 1);
		Predador Aguia = new Predador(3, 2);

		// Macacos
		Macaco Macaco1 = new Macaco(1, "M1");
		Macaco Macaco2 = new Macaco(1, "M2");
		Macaco Macaco3 = new Macaco(1, "M3");
		Macaco Macaco4 = new Macaco(1, "M4");

		Ambiente.setMacacos(Macaco1, Macaco2, Macaco3, Macaco4);
		Ambiente.setPredadores(Cobra, Tigre, Aguia);

		this.initArquivos();

		for (Macaco macaco : Ambiente.Macacos) {
			macaco.guardarSimbolos("inicio");
		}

	}

	public void movimentarAgentes() {

		for (Predador predador : Ambiente.Predadores) {
			predador.movimento();
		}

		for (Macaco macaco : Ambiente.Macacos) {
			macaco.movimento();
			macaco.cicloMacaco();
		}
	}

	public boolean convergencia() {
		return (Arrays.equals(Ambiente.Macacos[0].getLinguagem(), Ambiente.Macacos[1].getLinguagem())
				&& Arrays.equals(Ambiente.Macacos[0].getLinguagem(), Ambiente.Macacos[2].getLinguagem())
				&& Arrays.equals(Ambiente.Macacos[0].getLinguagem(), Ambiente.Macacos[3].getLinguagem()));
	}

	public void resultado() {
		for (Macaco macaco : Ambiente.Macacos) {
			System.out.println(macaco.toStringLiguagem());
		}
	}

	
	public void startSimulacao() throws InterruptedException {

		try {
			this.initSimulacao();
			int i = 0;
			do {
				Sistema.setI();
				i++;
				//System.out.println("Iteração: " + i);
				this.movimentarAgentes();
				//this.resultado();
				//System.out.println("\n");
				Thread.sleep(50);

			} while (this.convergencia() == false);
			//System.out.println("Resultado final");
			//this.resultado();
			for (Macaco macaco : Ambiente.Macacos) {
				macaco.guardarSimbolos("final");
			}

		} catch (InterruptedException e) {

		}
	}
	 

	/*
	public void startSimulacao() {
				

		this.initSimulacao();
		int i = 0;
		do {
			i++;
			System.out.println("Iteração: " + i);
			this.movimentarAgentes();
			this.resultado();
			System.out.println("\n");

		} while (this.convergencia() == false);
		System.out.println("Resultado final");
		this.resultado();
		for (Macaco macaco : Ambiente.Macacos) {
			macaco.guardarSimbolos("final");
		}
	}
	*/
	

	public void initArquivos() {

		for (Macaco macaco : Ambiente.Macacos) {
			String arquivoCobra = "arquivo_" + macaco.getIdMacaco() + "_Cobra.txt";
			String arquivoTigre = "arquivo_" + macaco.getIdMacaco() + "_Tigre.txt";
			String arquivoAguia = "arquivo_" + macaco.getIdMacaco() + "_Aguia.txt";
			String[] arquivos = { arquivoCobra, arquivoTigre, arquivoAguia };

			for (String arquivo : arquivos) {
				String header = "a;b;c;d;e;f;g;h;i;j";
				try {
					File nArquivo = new File(arquivo);

					if (nArquivo.exists() == false) {
						nArquivo.createNewFile();
					}

					FileWriter fileWriter = new FileWriter(arquivo, false);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(header);
					bufferedWriter.newLine();
					bufferedWriter.close();
					fileWriter.close();

				} catch (IOException e) {
				}
			}

		}

	}

}

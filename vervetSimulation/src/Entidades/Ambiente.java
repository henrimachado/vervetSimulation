package Entidades;

public class Ambiente {

	static public Agente[][] mAmbiente = new Agente[Config.altura][Config.largura];
	static public Macaco[] Macacos = new Macaco[Config.qntMacaco];
	static public Predador[] Predadores = new Predador[Config.qntPredador];

	public Ambiente() {

		for (int i = 0; i < Config.largura; i++) {
			for (int j = 0; j < Config.altura; j++) {
				mAmbiente[i][j] = null;
			}
		}

	}

	public static Agente[][] getmAmbiente() {
		return mAmbiente;

	}

	public static Agente getmAmbienteByPosition(int linha, int coluna) {
		return mAmbiente[linha][coluna];
	}

	public static boolean isVagavazia(int linha, int coluna) {
		if (mAmbiente[linha][coluna] != null) {
			return false;
		}
		return true;
	}

	public static void setmAmbienteByPosition(Agente agente, int linha, int coluna) {
		mAmbiente[linha][coluna] = agente;
	}

	public static Macaco[] getMacacos() {
		return Macacos;
	}

	public static void setMacacos(Macaco macaco1, Macaco macaco2, Macaco macaco3, Macaco macaco4) {
		Macacos[0] = macaco1;
		Macacos[1] = macaco2;
		Macacos[2] = macaco3;
		Macacos[3] = macaco4;
	}

	public static Predador[] getPredadores() {
		return Predadores;
	}

	public static void setPredadores(Predador predador1, Predador predador2, Predador predador3) {
		Predadores[0] = predador1;
		Predadores[1] = predador2;
		Predadores[2] = predador3;
	}

}

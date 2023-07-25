package Entidades;

public class Predador extends Agente{

	
	public int id;
	
	public Predador() {

	}
	
	public Predador(int velocidade, int id) {
		super(velocidade);
		this.id =id;
	}

	
	@Override
	public int getId() {
		return this.id;
	}


	
	
}

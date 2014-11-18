import java.io.*;

public class Simbolo{
	private String nome_;
	private String tipo_;
	private String classe_;
	private int offset_;

	public Simbolo(String nome, String tipo, String classe){
		this.nome_ = nome;
		this.tipo_ = tipo;
		this.classe_ = classe;
		this.offset_ = 4;
	}

	public void printSimbolo(){
		System.out.println("Nome: "+this.nome_);
		System.out.println("Tipo: "+this.tipo_);
//		System.out.println("Offset: "+this.offset_);
		System.out.println("Classe: "+this.classe_);
	}

	public String getNome(){
		return this.nome_;
	}

	public String getTipo(){
		return this.tipo_;
	}
	public int getOffset(){
		return this.offset_;
	}
	public String getClasse(){
		return this.classe_;
	}

}

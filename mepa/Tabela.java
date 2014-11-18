import java.util.*;
import java.io.*;

public class Tabela {
	
	private final static int NMAX = 10;
	private Hashtable[] tabelas = new Hashtable[NMAX];
	private int nivel;

	public Tabela() {
		nivel = 0;
		for(int i = 0;i<NMAX;i++)
			tabelas[i] = new Hashtable();
	}

	public void sobeNivel(){
		nivel++;
	}

	public void desceNivel(){
		tabelas[nivel].clear();
		nivel--;
	}

	public int getNivel(){
		return nivel;
	}
	public void add(Simbolo s) throws ExisteException {
		if(tabelas[nivel].get(s.getNome())==null)
			tabelas[nivel].put(s.getNome(),s);
		else
			throw new ExisteException("A variavel \""+s.getNome()+"\" ja foi declarada neste escopo!!");
	}

	public Simbolo lookup(String s) {
		
		for(int i=nivel; i>=0 ;i--)
			if(tabelas[i].get(s)!=null)
				return (Simbolo)(tabelas[i].get(s));
		
		return null;
	}

	public void printTabela(){
		if(tabelas[nivel].size() != 0){
			System.out.println("***********");
			System.out.println("|Nivel "+nivel+"  |");
			System.out.println("***********\n");
			Enumeration e = tabelas[nivel].elements();
			while(e.hasMoreElements()){
				((Simbolo)e.nextElement()).printSimbolo();
				System.out.println();
			}
		}
	}
}

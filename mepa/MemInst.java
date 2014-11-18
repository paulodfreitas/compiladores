import java.util.*;
import java.io.*;

public class MemInst {


	private final static int I_LENGTH = 100;
	private final static int L_LENGTH = 100;
	private final static int NARGS = 4; 


	private class Label{
		private String label_;
		private int posicao_;

		public Label(String s, int p){
			this.label_ = s;
			this.posicao_ = p;
		}
	}


	private Instrucao[] instrucoes = new Instrucao[I_LENGTH];
	private Label[] labels = new Label[L_LENGTH];

	public MemInst(String path){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("foo.out")));
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;
			int i = 0;
			int j = 0;
			// Salva todos os labels na tabela e escreve um arq intermediario
			// sem os labels
			while ((str = in.readLine()) != null) { 
				if(Instrucao.isLabel(str)){
//					System.out.println("Achou o label "+str.split(" ")[0]);
					labels[j] = new Label(str.split(" ")[0],i);
					j++;
					for (int k=1; k<str.split(" ").length; k++){
						out.print(str.split(" ")[k]+" ");
					}
					out.println();
				}
				else
					out.println(str);
				i++;
			}
			out.close();
			in.close();
//			this.imprimeLabels();

			in = new BufferedReader(new FileReader("foo.out"));
			i = 0;
			while ((str = in.readLine()) != null) { 
				str = replaceLabel(str);
				instrucoes[i] = new Instrucao(str); 
				i++;
			} 
		in.close(); 
		}
		catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
//			System.out.println("Erro na leitura do arquivo!\n"+e.getMessage());
		}

	}	
	
	// Se houver label na instrucao, substitui pela sua posicao no programa
	public String replaceLabel(String str){
		try{
			for (int i=0; i< L_LENGTH; i++){
				if(str.split(" ")[1].equals(labels[i].label_)){
					String result;
					result = str.split(" ")[0]+" "+labels[i].posicao_;
					try{
						result += " "+str.split(" ")[2];
					}
					catch (Exception e){
						return result;
					}
					try{
						result += " "+str.split(" ")[4];
					}
					catch (Exception e){
						return result;
					}
				}
			}
		}
		catch (Exception e) {
			return str;
		}
		return str;
	}

	public void imprimeLabels(){
		try{	
			System.out.println("LABELS:");
			for (int i=0; i < labels.length; i++){
				System.out.println(this.labels[i].label_+" "+this.labels[i].posicao_);
			}
		}
		catch (NullPointerException e){}
		System.out.println();
	}

	public Instrucao getInstrucao(int pc){
		return this.instrucoes[pc];
	}
}

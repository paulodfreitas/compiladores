import java.util.*;
import java.io.*;

public class MEPA {
	
	private final static int D_LENGTH = 100;
	private final static int M_LENGTH = 100;

	private int[] display = new int[D_LENGTH];
	private int sp;
	private int[] pilha = new int[M_LENGTH];
	private MemInst meminst;
	private int pc;

	public MEPA(String path) {
		meminst = new MemInst(path);
	}

	public void exec(){
		pc = 0;
		System.out.println("\n********* SAIDA *********\n");
		while(meminst.getInstrucao(pc).getId() != 49 ){
//			System.out.println("Vai executar: "+meminst.getInstrucao(pc).getNome());
			switch(meminst.getInstrucao(pc).getId()){
				case  0: AMEM(meminst.getInstrucao(pc).getArgs(1));break;
				case  1: ARMI(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break; 
				case  2: ARMP(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  3: ARMZ(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  4: CHPP(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2),meminst.getInstrucao(pc).getArgs(3));break;
				case  5: CHPR(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
//				case  6: CMAF();break;
				case  7: CMAG();break;
//				case  8: CMDF();break;
				case  9: CMDG();break;
//				case  10: CMEF();break; 
				case  11: CMEG();break;
//				case  12: CMIF();break;
				case  13: CMIG();break;
				case  14: CMMA();break;
//				case  15: CMMF();break;
				case  16: CMME();break;
//				case  17: CMNF();break;
				case  18: CONJ();break;
				case  19: CRCT(meminst.getInstrucao(pc).getArgs(1));break;
//				case  20: CRCF();break;
				case  21: CREG(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  22: CREN(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break; 
				case  23: CRVI(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  24: CRVL(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  25: CRVP(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  26: DISJ();break; 
				case  27: DIVF();break;
				case  28: DIVI();break;
				case  29: DMEM(meminst.getInstrucao(pc).getArgs(1));break;
				case  30: DSVF(meminst.getInstrucao(pc).getArgs(1));break;
				case  31: DSVS(meminst.getInstrucao(pc).getArgs(1));break;
				case  32: ENTR(meminst.getInstrucao(pc).getArgs(1));break;
				case  35: IMPR();break;
				case  36: INPP();break;
//				case  37: INVF();break;
				case  38: INVR();break;
				case  39: LEIT();break;
//				case  40: MULF();break;
				case  41: MULT();break;
				case  43: NEGA();break;
				case  44: RTPR(meminst.getInstrucao(pc).getArgs(1),meminst.getInstrucao(pc).getArgs(2));break;
				case  45: SOMA();break;
//				case  46: SOMF();break;
				case  47: SUBT();break;
//				case  48: SUBF();break;
				case  49: PARA();break;

			}
//			dumpPilha();
//			dumpDisplay();
			pc++;
		}			
	}

	private void INPP(){
		sp = -1;
		display[0] = 0;
	}

	private void PARA(){
			
	}

	private void SOMA(){
		pilha[sp - 1] = pilha[sp - 1] + pilha[sp];
		sp = sp - 1;
	}

	private void SUBT(){
		pilha[sp - 1] = pilha[sp - 1] - pilha[sp];
		sp = sp - 1;
	}

	private void MULT(){
		pilha[sp - 1] = pilha[sp - 1] * pilha[sp];
		sp = sp - 1;
	}
	
	private void DIVI(){
		pilha[sp - 1] = pilha[sp - 1] / pilha[sp];
		sp = sp - 1;
	}

	private void DIVF(){
		pilha[sp - 1] = pilha[sp - 1] / pilha[sp];
		sp = sp - 1;
	}

	private void INVR(){
		pilha[sp] = -pilha[sp];
	}

	private void CONJ(){
		pilha[sp - 1] = pilha[sp - 1] & pilha[sp];
		sp = sp - 1;	
	}

	private void DISJ(){
		pilha[sp - 1] = pilha[sp - 1] | pilha[sp];
		sp = sp - 1;	
	}

	private void NEGA(){
		pilha[sp] = ~(pilha[sp]);
	}

	private void CMMA(){
		pilha[sp - 1] = (pilha[sp - 1] > pilha[sp])?1:0;
		sp = sp - 1;
	}

	private void CMME(){
		pilha[sp - 1] = (pilha[sp - 1] < pilha[sp])?1:0;
		sp = sp - 1;
	}

	private void CMIG(){
		pilha[sp - 1] = (pilha[sp - 1] == pilha[sp])?1:0;
		sp = sp - 1;
	}

	private void CMDG(){
		pilha[sp - 1] = (pilha[sp - 1] != pilha[sp])?1:0;
		sp = sp - 1;
	}

	private void CMAG(){
		pilha[sp - 1] = (pilha[sp - 1] >= pilha[sp])?1:0;
		sp = sp - 1;
	}

	private void CMEG(){
		pilha[sp - 1] = (pilha[sp - 1] <= pilha[sp])?1:0;
		sp = sp - 1;
	}

	private void DSVS(int p){
		pc = p-1;
	}

	private void DSVF(int p){
		if(pilha[sp] == 0) 
			pc = p - 1;
		sp = sp - 1;
	}

	private void CHPR(int p, int k){
		pilha[sp+1] = pc+1; //endereco de rotrno
		pilha[sp+2] = display[k]; //link dinamico
		pilha[sp+3] = k; //nivel da chamada
		sp = sp +3;
		pc = p-1;
	}

	private void ENTR(int k){
		sp = sp +1;
		pilha[sp] = display[k-1]; //link estatico
		display[k] = sp + 1;
	}

	private void RTPR(int n, int k){
		sp = display[k] - 1;
		k = pilha[sp-1]; 
		
		display[k] = pilha[sp-2];
		pc = pilha[sp-3] - 1;
		sp = sp - (n+4);
		
		while(k >= 2){
		      display[k-1] = pilha[display[k]-1];
		      k = k - 1;
		}
	}

	private void CREG(int p, int k){
		pilha[sp+1] = p; //endereco de rotrno
		pilha[sp+2] = display[k]; //link dinamico
		pilha[sp+3] = k; //nivel da chamada
		sp = sp +3;
	}

	private void CHPP(int m, int n, int k){
		int l;
		pilha[sp+1] = pc+1; //endereco de rotrno
		pilha[sp+2] = display[k]; //link dinamico
		pilha[sp+3] = k; //nivel da chamada
		sp = sp +3;

		pc = pilha[display[m]+n] - 1;
		l = pilha[display[m]+n+2];
		display[l] = pilha[display[m]+n+1];
		while(l >= 2){
			display[l-1] = pilha[display[l]-1]; 
	        l = l - 1;
		}
	}

	private void AMEM(int n){
		sp = sp + n;
	}

	private void ARMZ(int m, int n){
		pilha[display[m]+n] = pilha[sp];
		sp = sp - 1;
	}

	private void CRVL(int m, int n){
		sp = sp + 1;
		pilha[sp] = pilha[display[m] + n];
	}

	private void CRCT(int k){
		sp = sp + 1;
		pilha[sp] = k;
	}

	private void ARMP(int n, int k){
		pilha[pilha[display[n] + k + pilha[sp]]] = pilha[sp - 1];
		sp = sp - 2;
	}

	private void DMEM(int n){
		sp = sp - n;
	}

	private void CRVI(int n, int k){
		sp = sp + 1;
		pilha[sp] = pilha[pilha[display[n] + k]];
	}

	private void CREN(int n, int k){
		sp = sp + 1;
		pilha[sp] = display[n] + k;
	}

	private void ARMI(int n, int k){
		pilha[pilha[display[n] + k]] = pilha[sp];
		sp = sp - 1;
	}

	private void CRVP(int n, int k){
		pilha[sp] = pilha[pilha[display[n] + k + pilha[sp]]];
	}

	private void LEIT(){
		try{
			sp = sp + 1;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Digite um inteiro:");
			pilha[sp] = Integer.parseInt(in.readLine());
		}
		catch (IOException e) {
			System.out.println("Erro na leitura da entrada!\n"+e.getMessage());
		}

	}

	private void IMPR(){
		System.out.println(pilha[sp]);
		sp = sp - 1;
	}



	public void dumpPilha(){
		System.out.println("\n********* DUMP DA PILHA *********\n");
		System.out.println("SP = "+sp);
		for(int i=0;i<10;i++){
			System.out.println("Pilha "+i+": "+pilha[i]);
		}
	}

	public void dumpDisplay(){
		System.out.println("\n********* DUMP DO DISPLAY *********\n");
		for(int i=0;i<10;i++){
			System.out.println("Display "+i+": "+display[i]);
		}
	}
	
	public static void main(String[] args) {

		try {
			MEPA interpretador = new MEPA(args[0]);
			interpretador.exec();
			if (args[0].equals("dumpPilha")){
				interpretador.dumpPilha();
			}

			if (args[0].equals("dumpDisplay")){
				interpretador.dumpDisplay();
			}

			if (args[0].equals("dumpAll")){
				interpretador.dumpPilha();
				interpretador.dumpDisplay();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

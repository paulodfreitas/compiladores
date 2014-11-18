public class Instrucao{
	private String nome_;
	private int id_;
	private int[] args_ = new int[NARGS];

	private final static int NARGS = 3;

	public static boolean isLabel(String s){ 
		String tokens[] = new String[NARGS];
		tokens = s.split(" ");
		s = tokens[0];

		if(s.equals("AMEM")) return false;
		else if(s.equals("ARMI")) return false;
		else if(s.equals("ARMP")) return false;
		else if(s.equals("ARMZ")) return false;
		else if(s.equals("CHPP")) return false;
		else if(s.equals("CHPR")) return false;
		else if(s.equals("CMAF")) return false;
		else if(s.equals("CMAG")) return false;
		else if(s.equals("CMDF")) return false;
		else if(s.equals("CMDG")) return false;
		else if(s.equals("CMEF")) return false;
		else if(s.equals("CMEG")) return false;
		else if(s.equals("CMIF")) return false;
		else if(s.equals("CMIG")) return false;
		else if(s.equals("CMMA")) return false;
		else if(s.equals("CMMF")) return false;
		else if(s.equals("CMME")) return false;
		else if(s.equals("CMNF")) return false;
		else if(s.equals("CONJ")) return false;
		else if(s.equals("CRCT")) return false;
		else if(s.equals("CRCF")) return false;
		else if(s.equals("CREG")) return false;
		else if(s.equals("CREN")) return false;
		else if(s.equals("CRVI")) return false;
		else if(s.equals("CRVL")) return false;
		else if(s.equals("CRVP")) return false;
		else if(s.equals("DISJ")) return false;
		else if(s.equals("DIVF")) return false;
		else if(s.equals("DIVI")) return false;
		else if(s.equals("DMEM")) return false;
		else if(s.equals("DSVF")) return false;
		else if(s.equals("DSVS")) return false;
		else if(s.equals("ENTR")) return false;
		//33 e 34?
		else if(s.equals("IMPR")) return false;
		else if(s.equals("INPP")) return false;
		else if(s.equals("INVF")) return false;
		else if(s.equals("INVR")) return false;
		else if(s.equals("LEIT")) return false;
		else if(s.equals("MULF")) return false;
		else if(s.equals("MULT")) return false;
		//42?
		else if(s.equals("NEGA")) return false;
		else if(s.equals("RTPR")) return false;
		else if(s.equals("SOMA")) return false;
		else if(s.equals("SOMF")) return false;
		else if(s.equals("SUBT")) return false;
		else if(s.equals("SUBF")) return false;
		else if(s.equals("PARA")) return false;
		else return true;		
	}

	public Instrucao(String s){
		String tokens[] = new String[NARGS];
		tokens = s.split(" ");
		this.nome_ = tokens[0];
		for(int i=0;i<tokens.length-1;i++)
			this.args_[i] = Integer.parseInt(tokens[i+1]);

		if(this.nome_.equals("AMEN")) this.id_=0;
		else if(this.nome_.equals("ARMI")) this.id_=1;
		else if(this.nome_.equals("ARMP")) this.id_=2;
		else if(this.nome_.equals("ARMZ")) this.id_=3;
		else if(this.nome_.equals("CHPP")) this.id_=4;
		else if(this.nome_.equals("CHPR")) this.id_=5;
		else if(this.nome_.equals("CMAF")) this.id_=6;
		else if(this.nome_.equals("CMAG")) this.id_=7;
		else if(this.nome_.equals("CMDF")) this.id_=8;
		else if(this.nome_.equals("CMDG")) this.id_=9;
		else if(this.nome_.equals("CMEF")) this.id_=10;
		else if(this.nome_.equals("CMEG")) this.id_=11;
		else if(this.nome_.equals("CMIF")) this.id_=12;
		else if(this.nome_.equals("CMIG")) this.id_=13;
		else if(this.nome_.equals("CMMA")) this.id_=14;
		else if(this.nome_.equals("CMMF")) this.id_=15;
		else if(this.nome_.equals("CMME")) this.id_=16;
		else if(this.nome_.equals("CMNF")) this.id_=17;
		else if(this.nome_.equals("CONJ")) this.id_=18;
		else if(this.nome_.equals("CRCT")) this.id_=19;
		else if(this.nome_.equals("CRCF")) this.id_=20;
		else if(this.nome_.equals("CREG")) this.id_=21;
		else if(this.nome_.equals("CREN")) this.id_=22;
		else if(this.nome_.equals("CRVI")) this.id_=23;
		else if(this.nome_.equals("CRVL")) this.id_=24;
		else if(this.nome_.equals("CRVP")) this.id_=25;
		else if(this.nome_.equals("DISJ")) this.id_=26;
		else if(this.nome_.equals("DIVF")) this.id_=27;
		else if(this.nome_.equals("DIVI")) this.id_=28;
		else if(this.nome_.equals("DMEM")) this.id_=29;
		else if(this.nome_.equals("DSVF")) this.id_=30;
		else if(this.nome_.equals("DSVS")) this.id_=31;
		else if(this.nome_.equals("ENTR")) this.id_=32;
		//33 e 34?
		else if(this.nome_.equals("IMPR")) this.id_=35;
		else if(this.nome_.equals("INPP")) this.id_=36;
		else if(this.nome_.equals("INVF")) this.id_=37;
		else if(this.nome_.equals("INVR")) this.id_=38;
		else if(this.nome_.equals("LEIT")) this.id_=39;
		else if(this.nome_.equals("MULF")) this.id_=40;
		else if(this.nome_.equals("MULT")) this.id_=41;
		//42?
		else if(this.nome_.equals("NEGA")) this.id_=43;
		else if(this.nome_.equals("RTPR")) this.id_=44;
		else if(this.nome_.equals("SOMA")) this.id_=45;
		else if(this.nome_.equals("SOMF")) this.id_=46;
		else if(this.nome_.equals("SUBT")) this.id_=47;
		else if(this.nome_.equals("SUBF")) this.id_=48;
		else if(this.nome_.equals("PARA")) this.id_=49;
	}
 
	public String getNome(){
		return this.nome_;
	}

	public int getArgs(int n){
		return this.args_[n-1];
	}

	public int getId(){
		return this.id_;
	}
}

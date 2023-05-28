
public class Instruction extends Memory_Element {
	
    private String InstName ;
    private String inst;
     
     public String getInstName() {
		return InstName;
	}

	public void setInstName(String instName) {
		InstName = instName;
	}

	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}

	public Instruction(String inst ) {
    	 this.inst=inst;
    	 String instruction[]= inst.split(" ");
    	 this.InstName = instruction[0] ;
     }
     
     @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return inst;
    }

}


public class Instruction extends Memory_Element {
	
     String InstName ;
     String inst;
     
     public Instruction(String inst ) {
    	 String instruction[]= inst.split(" ");
    	 this.InstName = instruction[0] ;
     }

}

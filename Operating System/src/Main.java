
public class Main {
	
	public static void main(String[] args) {
		
		
		Kernel kernel = new Kernel();
		Interpreter interpreter = new Interpreter();
		
		
        interpreter.codeParser("src/Program_1.txt");
        interpreter.codeParser("src/Program_2.txt");
        interpreter.codeParser("src/Program_3.txt");
        
        //Print Memor
        for(int i=0; i<kernel.getMEM().length;i++)
        {
        	if (kernel.getMEM()[i] == null)
        	{
        		System.out.println("NULL");
        	}
        	if (kernel.getMEM()[i] !=null)
        	{
        System.out.println(kernel.getMEM()[i].toString());
        	}
        	
        }
       
        }
//        codeParser("./Program_2.txt");
//        codeParser("./Program_3.txt");

	}

}

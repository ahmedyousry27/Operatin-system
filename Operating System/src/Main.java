import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		
		Kernel kernel = new Kernel();
		Interpreter interpreter = new Interpreter();
		Scheduler scheduler = new Scheduler();
		
       interpreter.codeParser("src/Program_5.txt");
       interpreter.codeParser("src/Program_3.txt");
       interpreter.codeParser("src/Program_6.txt");
       interpreter.codeParser("src/Program_1.txt");
       interpreter.codeParser("src/Program_2.txt");
       interpreter.codeParser("src/Program_3.txt");
     
       System.out.println("MEMORY");
       System.out.println();
       for(int i=0; i<kernel.getMEM().length;i++)
       {
       	if (kernel.getMEM()[i] == null)
       	{
       		System.out.println(i+" null");
       	}
       	if (kernel.getMEM()[i] !=null)
       	{
       System.out.println(i+" "+kernel.getMEM()[i].toString());
       	}
       	
       }
       
       

        
     
        scheduler.start();
        //Print Memory
   	    System.out.println("MEMORY");
   	    System.out.println();
        for(int i=0; i<kernel.getMEM().length;i++)
        {
        	
        	if (kernel.getMEM()[i] == null)
        	{
        		System.out.println(i+" NULL");
        	}
        	if (kernel.getMEM()[i] !=null)
        	{
        System.out.println(i+" "+kernel.getMEM()[i].toString());
        	}
        	
        }
        
        
        
       
        }

	}



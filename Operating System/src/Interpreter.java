import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Interpreter {

	
	
	
	
	public static void  codeParser (String filelocation) throws IOException{		  
          
		   Queue<Instruction> queue= new LinkedList<>();
		   int numofInstruction=0;
		   
		   File file = new File(filelocation);
		   int PC_begin =Kernel.getPc();
           Kernel.setPc(Kernel.getPc()+4);		 
           Scanner scanner;
           boolean disk =false;
	        try {
	            scanner = new Scanner(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return ; 
	        }
	        while (scanner.hasNextLine())
	        {   Instruction inst = new Instruction(scanner.nextLine());
	        	   
	        	queue.add(inst);
	        	numofInstruction++;

	        	
	        	
	        }
        	if (numofInstruction+4 <= (Kernel.getMEM().length-Kernel.getPc()))
        	{
        		disk=true;
        		while (!queue.isEmpty())
        		{
        		Kernel.getMEM()[Kernel.getPc()]=queue.remove();
        		Kernel.setPc(Kernel.getPc()+1);
        		}
        	}
        	else 
        	{
        		try(BufferedWriter writer= new BufferedWriter (new FileWriter("src/Disk.txt"));)
        		{
        			
        			writer.write("null\n");	
        			writer.write("null\n");	
        			writer.write("null\n");	
        			
        			System.out.println("Disk");
        			System.out.println();
        			System.out.println("null");
        			System.out.println("null");
        			System.out.println("null");
        			while (!queue.isEmpty())
            		{
                    Instruction temp=  queue.remove();
                    
               		writer.write(""+temp+"\n");
               		System.out.println(temp);
            		}
        		}
        		catch (IOException e)
        		{
        			e.printStackTrace();
        		}
        		
        	}
        	
        	PCB PCB;
        	   if (disk)
        	   {
			    PCB=new PCB(Kernel.getProcessID(),"Ready",(PC_begin+4)+"-"+(Kernel.getPc()-1));
				 Kernel.getMEM()[PC_begin]=PCB;
        	   }
        	   else 
        	   {
    			  PCB=new PCB(Kernel.getProcessID(),"Ready","");

        	   }
			   Kernel.setProcessID(Kernel.getProcessID()+1);
			   Process p= new Process(PCB);			  
			   Kernel.getReady().add(p);


			   
          

	}
	
	
}

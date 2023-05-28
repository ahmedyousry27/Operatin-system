import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Scheduler {
	
	


	public void start() {
		
	
	  while (!Kernel.getReady().isEmpty())
      	
      {
      	int scheduleCounter=2;
      	if (Kernel.getRunningProcess()== null)
      		
      	{
      		Kernel.setRunningProcess(Kernel.getReady().remove());
      		
      	}
      int ID =Kernel.getRunningProcess().pcb.getProcessID();
      for (int i=0;i<(Kernel.getPc()-1);i++)
      {
      	
      	if (Kernel.getMEM()[i] instanceof PCB)
      	{
      		PCB pcb= (PCB) Kernel.getMEM()[i];
      		if (pcb.getProcessID()== ID)
      		{
      			int begin= Integer.parseInt(""+pcb.getMemoryBoundaries().charAt(0));
      			int end= Integer.parseInt(""+pcb.getMemoryBoundaries().charAt(2));
                execute(scheduleCounter, begin, end,pcb);
      		}
      	}
      }
      	
}
	}
	
	public static void execute (int timer , int begin , int end, PCB pcb)
	{
		
		while (timer >0)
		{
			Scanner sc =  new Scanner(System.in);
			Scanner scanner;
			String instruction [] = ((Instruction)Kernel.getMEM()[pcb.getProgramCounter()]).getInst().split(" ");
			String x;
			String y;
			String z;
			switch (instruction[0])
			{
			case "assign":
				if (instruction [2]=="Input")
			   {
				  System.out.println("Please enter a value");
	              x = sc.nextLine();
	              Kernel.getMEM()[begin-3]=new Memory_Variable(instruction[1],x);
			   }
				else if (instruction [2]=="readFile")
				{
					int ReadDestination=0;
					for (int i=begin-3;i<begin;i++)
					{
						if(((Memory_Variable)Kernel.getMEM()[i]).getName()==instruction[3])
						{
							ReadDestination=i;
							break;
						}
						//think again
						else if(Kernel.getMEM()[i]==null) 
						{
							ReadDestination=i;
							break;
						}
					}
					 scanner = new Scanner(((Memory_Variable)Kernel.getMEM()[ReadDestination]).getValue());
				        String ReadDataFromFile= scanner.nextLine();
						for (int i=begin-3;i<begin;i++)
						{
							if(((Memory_Variable)Kernel.getMEM()[i]).getName()==instruction[1])
							{
								Kernel.getMEM()[i]=new Memory_Variable(instruction[2], ReadDataFromFile);
							}
							else if (Kernel.getMEM()[i]==null)
							{
								Kernel.getMEM()[i]=new Memory_Variable(instruction[2], ReadDataFromFile);

							}
						
						}
					
				}
				else 
				{
					for (int i=begin-3;i<begin;i++)
					{
						if(((Memory_Variable)Kernel.getMEM()[i]).getName()==instruction[1])
						{
							Kernel.getMEM()[i]=new Memory_Variable(instruction[1], instruction[2]);
						}
						else if (Kernel.getMEM()[i]==null)
						{
							Kernel.getMEM()[i]=new Memory_Variable(instruction[1], instruction[2]);

						}
					
					}
				}

				break;
			case "printFrom":
				int a=0;
				int b=0;
				for (int i=begin-3;i<begin;i++)
				{
					if(((Memory_Variable)Kernel.getMEM()[i]).getName()==instruction[1])
					{
						 a = Integer.parseInt(((Memory_Variable)Kernel.getMEM()[i]).getValue());	
						 }
				
					else if (((Memory_Variable)Kernel.getMEM()[i]).getName()==instruction[2])
					{
						 b = Integer.parseInt(((Memory_Variable)Kernel.getMEM()[i]).getValue());
						 }
				    }
		    	
		         	
				
					
				
				for (int i=a;i<=b;i++)
				{
					System.out.print(i+" ");
				}
				break;
			case "writeFile":
				String File_name="";
				for (int i=begin-3;i<begin;i++)
				{
					if(((Memory_Variable)Kernel.getMEM()[i]).getName()==instruction[1])
					{
						File_name  = ((Memory_Variable)Kernel.getMEM()[i]).getValue();	
						 }
				
				    }
				 try (BufferedWriter writer = new BufferedWriter(new FileWriter(File_name))) {
                      writer.write(instruction[2]);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				 break;
			case "print":	
				System.out.println(instruction[1]);
				
			}
		}
	}
}
	
				
			
			
	

	
		
			
			



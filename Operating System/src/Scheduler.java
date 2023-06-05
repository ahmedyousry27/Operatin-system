import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

public class Scheduler {
	
	public Scheduler ()
	{
		
	}


	public void start() throws IOException {
		
	Process Last_Used=null;
	  while (!Kernel.getReady().isEmpty()|| Kernel.getRunningProcess() !=null)
      	
      {
		  System.out.println("Ready Queue: "+Kernel.getReady());
      	int scheduleCounter=2;
      	if (Kernel.getRunningProcess()== null)
      		
      	{
      		Kernel.setRunningProcess(Kernel.getReady().remove());
      	}
      int ID =Kernel.getRunningProcess().pcb.getProcessID();
      boolean found_InMemory=false;
      for (int i=0;i<(Kernel.getPc()-1);i++)
      {
      	
      	if (Kernel.getMEM()[i] instanceof PCB)
      	{
      		PCB pcb= (PCB) Kernel.getMEM()[i];
      		if (pcb.getProcessID()== ID)
      		{
      			found_InMemory=true;
      			int begin= Integer.parseInt((pcb.getMemoryBoundaries().split("-"))[0]);
      			int end= Integer.parseInt((pcb.getMemoryBoundaries().split("-"))[1]);
                System.out.println("Current Process: "+Kernel.getRunningProcess());
      			execute(scheduleCounter, (begin+pcb.getProgramCounter()), end,pcb);
                if (pcb.getProcessState()!="Finished")
                {
                Kernel.getReady().add(Kernel.getRunningProcess());
                Last_Used=Kernel.getRunningProcess();
                }
                else 
                {
                	Kernel.getFinished().add(Kernel.getRunningProcess());
                	System.out.println("Finished Instruction: "+Kernel.getFinished());
                }
                Kernel.setRunningProcess(null);
                break;
      		}
      	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
      }
      // Disk and Memory Swap
      if (!found_InMemory)
      {
    	  System.out.println("Put into disk:"+Last_Used.pcb.getProcessID());
    	  System.out.println("Put into Memory:"+Kernel.getRunningProcess().pcb.getProcessID());
    	  int begin=Integer.parseInt((Last_Used.pcb.getMemoryBoundaries().split("-"))[0]);
    	  int end=Integer.parseInt((Last_Used.pcb.getMemoryBoundaries().split("-"))[1]);
    	  
    	  Memory_Element [] temp = new Memory_Element[end-begin+4+1];
    	  int j=begin-4;
    	  for (int i=0;i<temp.length;i++)
    	  {
    		  temp[i]=Kernel.getMEM()[j++];
    	  }
    	  j=begin-3;
    	  Kernel.getRunningProcess().pcb.setMemoryBoundaries(begin+"-"+end);
		  Kernel.getMEM()[begin-4]=Kernel.getRunningProcess().pcb;
		  int variable =3;
    	  try(BufferedReader reader =  new BufferedReader(new FileReader("src/Disk.txt")))
    	    {
    		  String Line;
    		 
    		  while ((Line=reader.readLine())!=null)
    		  {
    			  if (variable>0)
    			  {
    				  if (Line .equals("null"))
    				  {
    					  Kernel.getMEM()[j]=null;
    				  }
    				  else 
    				  { 
    					  
    			        Kernel.getMEM()[j]= new Memory_Variable(Line.split("=")[0],Line.split("=")[1]);
    				  }
    				  variable--;
    			  }
    			  else 
    			  {
    				  Kernel.getMEM()[j]=new Instruction(Line);
    			  }
    			  j++;
    		  }
    				  
       	    }
    	  
    	  catch (IOException e)
    	  {
    		   e.printStackTrace();
    	  }
    	  
    	  try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Disk.txt")))
    		{
    		  System.out.println("DISK");
    		  System.out.println();
    		  
    				  for (int i=1;i<temp.length;i++)
    				  {
    					  writer.write(temp[i]+"\n");
    					  System.out.println(temp[i]);
    				  }
    	    }	  
          catch (IOException e)
          {
        	  e.printStackTrace();
          }
    	  
			 System.out.println("MEMORY");
		       System.out.println();
		       for(int i=0; i<Kernel.getMEM().length;i++)
		       {
		       	if (Kernel.getMEM()[i] == null)
		       	{
		       		System.out.println(i+" null");
		       	}
		       	if (Kernel.getMEM()[i] !=null)
		       	{
		       System.out.println(i+" "+Kernel.getMEM()[i].toString());
		       	}
		       	
		       }
    	  
      }
      	
}
	}
	
	public static void execute (int timer , int begin , int end, PCB pcb) throws FileNotFoundException
	{
		
		while (timer >0 && begin<=end)
		{
			Scanner sc =  new Scanner(System.in);
			Scanner scanner;
			String instruction [] = ((Instruction)Kernel.getMEM()[begin]).getInst().split(" ");
			String x;
			String y;
			String z;
			System.out.println("Current Instruction:"+((Instruction)Kernel.getMEM()[begin]).getInst());
			switch (instruction[0])
			{
			case "assign":
				if (instruction [2].equals("input"))
			   {
				  System.out.println("Please enter a value");
	              x = sc.nextLine();
	              Kernel.getMEM()[begin-3]=new Memory_Variable(instruction[1],x);
			   }
				else if (instruction [2].equals("readFile"))
				{
					int ReadDestination=0;
					for (int i=begin-pcb.getProgramCounter()-3;i<begin-pcb.getProgramCounter();i++)
					{
						if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[3]))
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
					   File file = new File(((Memory_Variable)Kernel.getMEM()[ReadDestination]).getValue());

					 scanner = new Scanner(file);
				        String ReadDataFromFile= scanner.nextLine();
						for (int i=begin-pcb.getProgramCounter()-3;i<begin-pcb.getProgramCounter();i++)
						{

						     if (Kernel.getMEM()[i]==null)
							{
								Kernel.getMEM()[i]=new Memory_Variable(instruction[1], ReadDataFromFile);
								break;

							}
							else if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[1]))
							{
								Kernel.getMEM()[i]=new Memory_Variable(instruction[1], ReadDataFromFile);
								break;
							}
						
						     
						}
						
					
				}
				else 
				{
					for (int i=begin-pcb.getProgramCounter()-3;i<begin-pcb.getProgramCounter();i++)
					{

						 if (Kernel.getMEM()[i]==null)
						{
							Kernel.getMEM()[i]=new Memory_Variable(instruction[1], instruction[2]);

						}
						 else if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[1]))
						{
							Kernel.getMEM()[i]=new Memory_Variable(instruction[1], instruction[2]);
						}
					
					}
				}

				break;
			case "printFromTo":
				int a=0;
				int b=0;
				int variable_begin = Integer.parseInt(""+pcb.getMemoryBoundaries().split("-")[0]);
				for (int i=variable_begin-3;i<variable_begin-1;i++)
				{
					if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[1]))
					{
						 a = Integer.parseInt(((Memory_Variable)Kernel.getMEM()[i]).getValue());	
						 }
				
					else if (((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[2]))
					{
						 b = Integer.parseInt(((Memory_Variable)Kernel.getMEM()[i]).getValue());
						 }
				    }
		    	
		         	
				
					
				
				for (int i=a;i<=b;i++)
				{
					System.out.print(i+" ");
				}
				System.out.println();
				break;
			case "writeFile":
				String File_name="";
				for (int i=begin-pcb.getProgramCounter()-3;i<begin-pcb.getProgramCounter();i++)
				{
//					if (Kernel.getMEM()[i]==null)
//					{
//						
//					}
					if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[1]))
					{
						File_name  = ((Memory_Variable)Kernel.getMEM()[i]).getValue();	
						break;
						 }
				
				    }
				 try (BufferedWriter writer = new BufferedWriter(new FileWriter(File_name))) {
					 String writeValue="";
						for (int i=begin-pcb.getProgramCounter()-3;i<begin-pcb.getProgramCounter();i++)
						{

							  if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[2]))
							{
								 writeValue=((Memory_Variable)Kernel.getMEM()[i]).getValue();
								break;
							}
						
						}
					 
					 writer.write(writeValue);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				 break;
			case "print":	
				boolean flag =false ;
				for (int i=begin-pcb.getProgramCounter()-3;i<begin-pcb.getProgramCounter();i++)
				{

					  if(((Memory_Variable)Kernel.getMEM()[i]).getName().equals(instruction[1]))
					{
						 System.out.println(((Memory_Variable)Kernel.getMEM()[i]).getValue());
						flag=true; break;
					}
				
				}
				if (!flag)
				{
					System.out.println("Null");
				}
				//System.out.println(instruction[1]);
				
			}
			
			 System.out.println("MEMORY");
		       System.out.println();
		       for(int i=0; i<Kernel.getMEM().length;i++)
		       {
		       	if (Kernel.getMEM()[i] == null)
		       	{
		       		System.out.println(i+" null");
		       	}
		       	if (Kernel.getMEM()[i] !=null)
		       	{
		       System.out.println(i+" "+Kernel.getMEM()[i].toString());
		       	}
		       	
		       }
			timer--;
			begin++;
			pcb.setProgramCounter(pcb.getProgramCounter()+1);
			if (begin>end )
			{
				pcb.setProcessState("Finished");
			}
		}
	}
	public static void PrintQueue (Queue queue)
	{
          while (!queue.isEmpty())
          {
        	  System.out.println(queue.remove());
          }
	}
	
}
	
				
			
			
	

	
		
			
			



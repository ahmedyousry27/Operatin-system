import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Kernel {

	private static int  ProcessID=0;
	private static Object [] MEM= new Object [40]; 
	private  static int pc=0;
	private static Queue <Process> Ready= new LinkedList<>();
	private static Queue <Process> Blocked= new LinkedList<>();
	private static Queue <Process> Finished= new LinkedList<>();
    private static Process RunningProcess= null;
    
	
	public static void  codeParser (String filelocation)
	{		  

		   Queue<String> queue= new LinkedList<>();
		   int numofInstruction=0;
		   File file = new File(filelocation);
		   int PC_begin =pc;
		   pc+=4;
	        Scanner scanner;
        
	        try {
	            scanner = new Scanner(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return ; // Handle the exception or terminate the program
	        }
	        while (scanner.hasNextLine())
	        {
	        	String Line = scanner.nextLine();
	        	queue.add(Line);
	        	numofInstruction++;

	        	
	        	
	        }
        	if (numofInstruction+4 <= (MEM.length-pc))
        	{
        		while (!queue.isEmpty())
        		{
        		MEM[pc++]=queue.remove();
        		}
        	}
        	else 
        	{
        		// block process
        	}
        	
	        
			   PCB PCB1=new PCB(ProcessID++,"Ready",(PC_begin+4)+"-"+(pc-1));
			   Process p= new Process(PCB1);			  
			   Ready.add(p);
			   MEM[PC_begin]=PCB1;

			   
          

	}
	public static void decode (String i) throws FileNotFoundException
	{
		Scanner sc =  new Scanner(System.in);
		Scanner scanner;
		String instruction [] = i.split(" ");
		int x=0;
		switch (instruction[0])
		{
		case "assign":
			if (instruction [2]=="Input")
		   {
			  System.out.println("Please enter a value");
               x = sc.nextInt();			
		   }
			else if (instruction [2]=="readFile")
			{
				 scanner = new Scanner(instruction[3]);
			        while (scanner.hasNextLine())
			        {
			          // read file
			        }
				
			}
			else
			{
				x=Integer.parseInt(instruction[2]);
			}
			break;
		case "printFrom":
			int a = Integer.parseInt(instruction[1]);
			int b = Integer.parseInt(instruction[2]);
			for (int i1=a;i1<=b;i1++)
			{
				System.out.print(i+" ");
			}
			
		
		}
		
		
	}
	public static void execute (int begin,int end)
	{
		
		int timer =2;
		while (timer-->0)
		{
			String i = (String) MEM[0];
		//	decode(i);
			
					
		}
				
	}
	public static void main(String[] args) {
        codeParser("src/Program_1.txt");
        codeParser("src/Program_2.txt");
        codeParser("src/Program_3.txt");
        for(int i=0; i<(pc-1);i++)
        {
        	if (MEM[i] !=null)
        	{
        System.out.println(MEM[i].toString());
        	}
        	
        }
        
        while (!Ready.isEmpty())
        	
        {
        	int scheduleCounter=2;
        	if (RunningProcess== null)
        	{
        		RunningProcess=Ready.remove();
        		
        	}
        int ID =RunningProcess.PCB1.processID;
        for (int i=0;i<(pc-1);i++)
        {
        	
        	if (MEM[i] instanceof PCB)
        	{
        		PCB p1=(PCB) MEM[i];
        		if (p1.processID== ID)
        		{
        			int begin= Integer.parseInt(""+p1.MemoryBoundaries.charAt(0));
        			int end= Integer.parseInt(""+p1.MemoryBoundaries.charAt(2));
                  //  execute();
        		}
        	}
        }
        	
        }
//        codeParser("./Program_2.txt");
//        codeParser("./Program_3.txt");

	}

}

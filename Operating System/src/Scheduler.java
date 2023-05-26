
public class Scheduler {
	
	


	public void start() {
		
	
	  while (!Kernel.getReady().isEmpty())
      	
      {
      	int scheduleCounter=2;
      	if (Kernel.getRunningProcess()== null)
      		
      	{
      		Kernel.setRunningProcess(Kernel.getReady().remove());
      		
      	}
      int ID =Kernel.getRunningProcess().pcb.processID;
      for (int i=0;i<(Kernel.getPc()-1);i++)
      {
      	
      	if (Kernel.getMEM()[i] instanceof PCB)
      	{
      		PCB p1= (PCB) Kernel.getMEM()[i];
      		if (p1.processID== ID)
      		{
      			int begin= Integer.parseInt(""+p1.MemoryBoundaries.charAt(0));
      			int end= Integer.parseInt(""+p1.MemoryBoundaries.charAt(2));
                //  execute();
      		}
      	}
      }
      	
}
	}
}

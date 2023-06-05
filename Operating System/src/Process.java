public class Process {
	PCB pcb;
	
	public Process(PCB pcb)
	{
		this.pcb=pcb;
	}

	@Override
	public String toString() {
		return "Process "+this.pcb.getProcessID()+"";
	}
}

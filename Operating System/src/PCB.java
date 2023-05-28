public class PCB extends Memory_Element {

	private int processID;
	private String processState;
	private int ProgramCounter;
	private String MemoryBoundaries;
	
	public int getProcessID() {
		return processID;
	}
	public void setProcessID(int processID) {
		this.processID = processID;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public int getProgramCounter() {
		return ProgramCounter;
	}
	public void setProgramCounter(int programCounter) {
		ProgramCounter = programCounter;
	}
	public String getMemoryBoundaries() {
		return MemoryBoundaries;
	}
	public void setMemoryBoundaries(String memoryBoundaries) {
		MemoryBoundaries = memoryBoundaries;
	}
	public PCB(int processID,String processState,String MemoryBoundaries)
	{  
		this.processID=processID;
		this.processState=processState;
		this.ProgramCounter=0;
		this.MemoryBoundaries=MemoryBoundaries;
		
	}
    public String toString() {
        return "[processID=" + processID + ", processState = " + processState + ", ProgramCounter "+ ProgramCounter+ ", MemoryBoundaries "+ MemoryBoundaries +"]";
    }
}
	

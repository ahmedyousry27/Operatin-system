public class PCB {

	int processID;
	String processState;
	int ProgramCounter;
	String MemoryBoundaries;
	
	public PCB(int processID,String processState,String MemoryBoundaries)
	{
		this.processID=processID;
		this.processState=processState;
		this.ProgramCounter=0;
		this.MemoryBoundaries=MemoryBoundaries;
		
	}
    public String toString() {
        return "[processID=" + processID + ", processState = " + processState + ", ProgramCounter"+ ProgramCounter+ "MemoryBoundaries"+ MemoryBoundaries +"]";
    }
}
	

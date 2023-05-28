
public class Memory_Variable extends Memory_Element {
private String name ;	
private String value;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public Memory_Variable (String name , String value)
{
this.name=name;	
this.value=value;	
}

}

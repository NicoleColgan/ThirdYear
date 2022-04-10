
public class Employee {
	private int id;
	private String name;
	private Department dept;
	
	public Employee(int id, String name) {
		this.id=id;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setDepartment(Department d) {
		dept=d;
	}
	public Department getDept() {
		return dept;
	}
	@Override 
	public String toString() {
		return "Employee id: "+this.id+"\tEmployee name: "+this.name;
	}

}


public class Department {
	private String name;
	private int id;
	
	public Department(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Deparment id: "+this.id+"\tDepartment name: "+this.name;
	}
}

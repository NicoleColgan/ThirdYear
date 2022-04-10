package com.mysql_connection;

public class Employee {
	private int id, number;
	private String name,email,desig;
	private float salary;
	public Employee(int id, String name, String email, String desig, float salary, int number) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setDesig(desig);
		this.setSalary(salary);
		this.setNumber(number);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "id: "+id+" name: "+name+" email: "+email+" desig: "+desig+" salary: "+salary+" number: "+number;
	}

}

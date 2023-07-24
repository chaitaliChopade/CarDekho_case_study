package com.jspider.cardekho_case_study.entity;

public class Car {
	private int car_id;
	private String name;
    private String brand;
	private String fuel_type;
	private double price;
	
	  public Car(int id, String name, String brand, String fuel_type, double price) {
	        this.car_id = id;
	        this.name = name;
	        this.brand = brand;
	        this.fuel_type = fuel_type;
	        this.price = price;
	    }
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFuel_type() {
		return fuel_type;
	}
	@Override
	public String toString() {
		return "Car [car_id=" + car_id + ", name=" + name + ", brand=" + brand + ", fuel_type=" + fuel_type + ", price="
				+ price + "]";
	}
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}
	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}

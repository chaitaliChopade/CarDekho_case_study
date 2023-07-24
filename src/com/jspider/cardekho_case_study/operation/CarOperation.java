package com.jspider.cardekho_case_study.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jspider.cardekho_case_study.entity.Car;

public class CarOperation {
	   private static List<Car> cars = new ArrayList<>();
	   static Scanner sc=new Scanner(System.in);

	public static void addCarDetails()
    {
		
    	System.out.println("How many car you want to Add? ");
    	int num=sc.nextInt();
    	for (int i = 0; i < num; i++) {
    		System.out.println("Enter Car_id");
    		int id=sc.nextInt();
    		System.out.println("Enter car Name");
    		String name=sc.next();
			System.out.println("Enter car Brand");
			String brand=sc.next();
			System.out.println("Enter Fuel type");
			String fuel=sc.next();
			System.out.println("Enter car Price");
			double price=sc.nextDouble();
			
			System.out.println("Car details added Succesfully");
			Car car = new Car(id, name, brand, fuel, price);
            cars.add(car);
			
			
		}
    }
	public static void searchCarByName() {
		if (Car.isEmpty()) {
			System.out.println("No car details Founded");
		}
        System.out.print("Enter the car name to search: ");
        sc.nextLine();
        String n = sc.nextLine();
       

        boolean f = false;
          for (Car car : cars) {
          
			if (car.getName().equals(n)) {
                System.out.println(car);
                f = true;
            }

            if (!f) {
                System.out.println("No cars found with the given name.");
            }
            break;
        
        }
	}
	
	public static void searchcarByBrand()
	{
		if (Car.isEmpty()) {
			System.out.println("No car details Founded");
		}
		System.out.println("Enter the car Brand to search: ");
		String Brand=sc.nextLine();
		boolean b=false;
		for (Car car : cars) {
			if(car.getBrand().equalsIgnoreCase(Brand))
           System.out.println(car);
			b=true;
			
		}
		   if (b) {
               System.out.println("No cars found with the given Brand.");
           }
	}

		public static void editCarDetails()
		{
			 System.out.println("Enter the car id");
			    int id = sc.nextInt();
			    boolean carFound = false;

			    for (Car car : cars) {
			        if (car.getCar_id() == id) {
			            System.out.println("Enter new car name");
			            String name = sc.next();
			            car.setName(name);

			            System.out.println("Enter new car brand");
			            String brand = sc.next();
			            car.setBrand(brand);

			            System.out.println("Enter new car Fuel Type");
			            String fuel_type = sc.next();
			            car.setFuel_type(fuel_type);

			            System.out.println("Enter new car price");
			            double price = sc.nextDouble();
			            car.setPrice(price);

			          
			            System.out.println("Car details added Succesfully");
					
						Car car1 = new Car(id, name, brand, fuel_type, price);
			            cars.add(car1);
			            carFound = true;
			            break; 
			        }
			    }

			    if (!carFound) {
			        System.out.println("Car not found");
			    }
		}
	}
		
	


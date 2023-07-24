package com.jspider.cardekho_case_study.main;

import java.util.Scanner;

import com.jspider.cardekho_case_study.operation.CarOperation;

public class cardekhomenu  {
	private CarOperation carOperation = new CarOperation(); 
	private static boolean loop=true;
	public static void main(String[] args) {
		while (loop) {
			cardekhomenu();
		}
	}
	 public static void cardekhomenu()
	{
		System.out.println("=========Menu========="
				           +"\n 1. Enter car details"
				           +"\n 2. Search Car details"
				           +"\n 3.Edit car details"
				           +"\n 4.Exit");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the choice");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
			CarOperation.addCarDetails();
			   
			break;
		case 2:
			System.out.println("======Search Car======="
					         +"\n 1.Search car by Name"
					         +"\n 2.Search car by Brand"
					         +"\n 3.Search car by Fuel Type");
			        int searchchoice=sc.nextInt();
			      switch (searchchoice)
			      {
			      case 1:
				  CarOperation.searchCarByName();
				  break;
			      case 2:
                  CarOperation.searchcarByBrand();
                  break;
			      default:
				  System.out.println("Invalid choice. Please try again");
			 	 break;
			     }break;
		case 3:
			CarOperation.editCarDetails();
			break;
		case 4:
			System.out.println("Thank you");
			loop=false;
			break;
		default:
			System.out.println("Invalid choice. Please try again");
			break;
		}
		
		
	}
	
	
}

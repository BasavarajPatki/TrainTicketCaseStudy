package caseStudy;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class TicketApplication {

	public static void main(String[] args) throws IOException {
		System.out.println("===========Test Application=========");


		TrainDao trainDao=new TrainDao();
		Scanner scanner=new Scanner(System.in);
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy/MM/dd");


		System.out.println("Enter the Train number : ");


		Train train=trainDao.findTrain(scanner.nextInt());
		if(train==null)
		{
			System.out.println("Entery a valid Train number");
			System.exit(0);
		}
		
		LocalDate today=LocalDate.now();
		
		System.out.println("Enter the Travel date : ");
		String traveldate=scanner.next();

		LocalDate date=LocalDate.parse(traveldate,df);
		if(date.compareTo(today)<0) {
			System.out.println("Not a Valid date (This date is before todays date)");
			System.exit(0);
		}
		
		Ticket t=new Ticket(date,train);
		System.out.println("Enter the number of Passengers : ");
		int num=scanner.nextInt();
		for(int i=1;i<=num;i++)
		{	

			System.out.println("Passenger number "+i+" : ");
			System.out.println("Name : ");
			String name=scanner.next();
			System.out.println("Age : ");
			int age=scanner.nextInt();
			System.out.println("Gender : ");
			char gender=scanner.next().charAt(0);
			t.addPassenger(name, age, gender);
		}
		t.writeTicket();


	}

}

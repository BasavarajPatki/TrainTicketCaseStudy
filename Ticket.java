package caseStudy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeMap;



import java.util.Map;

public class Ticket {
	private int counter=100;
	private String pnr;
	private LocalDate travelDate;
	private Train train;
	private TreeMap<Passenger,Integer> passengers=new TreeMap<Passenger,Integer>();
	
	
	
	//setters and getters
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public TreeMap<Passenger, Integer> getPassengers() {
		return passengers;
	}
	public void setPassengers(TreeMap<Passenger, Integer> passengers) {
		this.passengers = passengers;
	}
	
	
	//Constructor
	public Ticket(LocalDate date, Train train) {
		super();
		this.travelDate = date;
		this.train = train;
	}
	
	//Generate PNR

	String generatePNR() {

		pnr=(String)(train.getSource().charAt(0)+train.getDestination().charAt(0)+"_"+travelDate+"_"+(++counter));
		return pnr;
	}
	
	
	//calculate Passenger Fare
	
	double calcPassengerFare(Passenger p) {
		if(p.getAge()<=12)
		{
			return 0.5*train.getTicketPrice();
		}
		else
			if(p.getAge()>=60)
			{
				return 0.6*train.getTicketPrice();
			}
			else
				if(p.getGender()=='F' || p.getGender()=='f')
				{
					return train.getTicketPrice()-(0.25*train.getTicketPrice());
				}

		return train.getTicketPrice();

	}
	
	//Add Passenger
	
	void addPassenger(String name,int age,char gender) {
		Passenger p=new Passenger(name, age, gender);
		passengers.put(p, (int) calcPassengerFare(p));
	}
	
	//Calculate total Fare
	
	double calculateTotalTicketPrice() throws IOException{
		double totalPrice = 0;
		Iterator<Integer> iterator = passengers.values().iterator();


		while(iterator.hasNext()){
			totalPrice+=iterator.next();
			
		}

		return totalPrice;

	}
	
	//write Ticket
	void writeTicket() throws IOException {
		FileWriter fileWriter= new FileWriter(generatePNR()+".txt",true);

		BufferedWriter bw=new BufferedWriter(fileWriter);
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.write("PNR\t\t\t: "+generatePNR().toString());
		bw.newLine();
		bw.write("Train no\t\t: "+train.getTrainNo());
		bw.newLine();
		bw.write("Train Name\t\t: "+train.getTrainName());
		bw.newLine();
		bw.write("From\t\t\t: "+train.getSource());
		bw.newLine();
		bw.write("To\t\t\t: "+train.getDestination());
		bw.newLine();;
		bw.write("Travel Date\t\t: "+travelDate);
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.write("Passengers :");
		bw.newLine();
		bw.write("Name\t\tAge\t\tGender\t\tFare");
		
		Iterator<Map.Entry<Passenger, Integer>> iterator = passengers.entrySet().iterator();
		 
		Map.Entry<Passenger, Integer> entry = null;
		 
		while(iterator.hasNext()){
		    
		    entry = iterator.next();
		    System.out.println( entry.getKey() + "=>" + entry.getValue() );
		}
		
		
		for(Map.Entry<Passenger,Integer> m:passengers.entrySet())
		{
			bw.newLine();
			bw.write(m.getKey().getName()+"\t\t"+m.getKey().getAge()+"\t\t"+m.getKey().getGender()+"\t\t"+m.getValue());
		}
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.write("Total Price : "+calculateTotalTicketPrice());
		bw.flush();
		bw.close();

}
	
	
}

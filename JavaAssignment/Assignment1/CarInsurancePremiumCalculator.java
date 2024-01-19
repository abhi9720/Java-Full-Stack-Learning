
import java.util.*;

public class CarInsurancePremiumCalculator {

	public static void main(String[] args) {
		Scanner scn =  new Scanner(System.in);
        ArrayList<Car> carData =  new ArrayList<>();// to store all cars
        System.out.println("Enter ClI Input \neg: -model \"Swift\" -type Hatchback -price 500000 -insuranceType Premium");
		boolean continueInput = true;
		while (continueInput) {
			Car newCar = getCarDetails( scn);
            carData.add(newCar);

			System.out.println("Do you want to enter details of any other car (y/n): ");
			String moreInput = scn.nextLine();
			continueInput = moreInput.equalsIgnoreCase("y");
		}

        System.out.println("\nDetails Of car: ");
        for(Car c : carData){
            c.printDetails();
        }

	}

	private static Car getCarDetails(Scanner scn) {
		// Accept input for car details

		String carModel = null;
		String carType = null;
		double carCostPrice = 0.0;
		String insuranceType = null;
		System.out.println("Enter details of car:\n");

		String nextInput[] = scn.nextLine().split("-");
		for (int i = 1; i < nextInput.length; i++) {
            String[] entry = nextInput[i].split("\\s+", 2);// split on first space 
			String key =  entry[0];
			String value =  entry[1].trim();


			if (key.equals("model")) {
				carModel = value;
			} else if (key.equals("type")) {
				carType = value;
			} else if (key.equals("price")) {
				carCostPrice = Double.parseDouble(value);
			} else if (key.equals("insuranceType")) {
                if(value.equalsIgnoreCase("Basic") ||value.equalsIgnoreCase("Premium")){
				    insuranceType = value;
                }
                else{
                    throw new IllegalArgumentException("Invalid Insurance type entered! Insurance can be only : Basic,Premium");
                }
			}
		}

		// Create car object and return
		return new Car(carModel, carType, carCostPrice, insuranceType);
	}

}

class Car {
	private String model;
	private String type;
	private double costPrice;
	private String insuranceType;
	private double totalInsurance;

	public Car(String model, String type, double costPrice, String insuranceType) {
		this.model = model;
		this.type = type;
		this.costPrice = costPrice;
		this.insuranceType = insuranceType;
		this.totalInsurance = calculateInsurancePremium();
	}

	private double calculateInsurancePremium() {
		// Calculate insurance premium based on car type
		double premium = 0.0;

		switch (type) {
            case "Hatchback":
                premium = costPrice * 0.05;
                break;
            case "Sedan":
                premium = costPrice * 0.08;
                break;
            case "SUV":
                premium = costPrice * 0.10;
                break;
            default:
			    throw new IllegalArgumentException("Invalid car type entered! Car can have type only:Hatchback, Sedan, SUV");
		}

		// Apply 20% increase if insurance type is Premium
		if (insuranceType.equalsIgnoreCase("Premium")) {
			premium += premium * 0.20;
		}

		return premium;
	}
    

	@Override
	public String toString() {
		return "Car Model: " + model + "\n" + "Car Type: " + type + "\n" + "Car Cost Price: Rs. " + costPrice + "\n"
				+ "Total insurance : Rs. " + totalInsurance + "\n" + "Insurance Type: " + insuranceType;
	}

    public void printDetails(){
        System.out.println("--------------------------------------------");
        System.out.println(this.toString());
    }
}
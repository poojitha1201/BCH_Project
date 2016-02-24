package com.inventory;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.model.Vehicle;

/**
 * 
 * @author Poojitha_Alla
 *
 */
@Component("vehicleInventory")
public class VehicleInventory {
	
public List<Vehicle> getVehicles(){
	List<String> lines = readDb();
	List<Vehicle> vehicles = new ArrayList<>();
		for(String line:lines){
			String data[] = line.split(",");
			Vehicle vehicle = new Vehicle(data[0],data[1],data[2],data[3],data[4],Double.valueOf(data[5].trim()), 
					Integer.valueOf(data[6].trim()));
			System.out.println(vehicle.toString());
			vehicles.add(vehicle);
		}
		return vehicles;
	}

private  List<String> readDb(){
	String fileName = "VehicleInventoryData.txt";
	List<String> lines = new ArrayList<>();
	ClassLoader classLoader = VehicleInventory.class.getClassLoader();
	File file = new File(classLoader.getResource(fileName).getFile());
	try(Scanner scanner = new Scanner(file)){
		while(scanner.hasNextLine()){
			lines.add(scanner.nextLine().trim());
		}
		scanner.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return lines;
}

}

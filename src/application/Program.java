package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> listInformations = new ArrayList<>();

		System.out.print("Enter with a folder path: ");
		String strPath = sc.nextLine();
		
		File sourceFile = new File(strPath);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))){
			
			String line = br.readLine();
			
			while (line != null) {
				String[] informations = line.split(",");
				String name = informations[0];
				Double price = Double.parseDouble(informations[1]);
				Integer qtt = Integer.parseInt(informations[2]);
				
				listInformations.add(new Product(name, price, qtt));
			    line = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
			      for (Product item : listInformations) {
			    	  bw.write(item.getName() + ", " + String.format("%.2f", item.totalPrice()));
			    	  bw.newLine();
			      }
			      
			      System.out.println(targetFileStr + " was created: " + success);
			}
			catch(IOException e){
				System.out.println("Error writing file: " + e.getMessage());
			}
			
		}
		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		sc.close();
		
	}
	
}
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
		
		List<String> productsList = new ArrayList<>();
		
		System.out.print("Enter a folder path: ");
		String sourceFileStr = sc.nextLine();
		
		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";
				
		try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
		    String line = br.readLine();
		    while (br.readLine() != null) {
				String[]  informationsProduct = line.split(",");
				Product product = converter(informationsProduct);
				productsList.add(product.getName() + product.getPrice());
				line = br.readLine();
		    }			    
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
			    for (String infor : productsList) {
			    	bw.write(infor);
			    	bw.newLine();
			    }
			    
			    System.out.println(targetFileStr + " Was created: " + success);
			    
			}
			catch(IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}
		}
		catch(IOException e){
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		sc.close();

	}

	public static Product converter(String[] informationsProduct) {
	    String name = informationsProduct[0];
	    Double price = Double.parseDouble(informationsProduct[1]);
	    int qtt = Integer.parseInt(informationsProduct[2]);
	    
	    return new Product(name, price, qtt);
	}
	
}
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		
		List<Employee> employees = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			
			while (line != null) {
				
				String[] vect = line.split(",");
				employees.add(new Employee(vect[0], vect[1], Double.parseDouble(vect[2])));
				line = br.readLine();
			}
			
			System.out.println("Enter salary: ");
			double salary = sc.nextDouble();
			
			//Comparator<String> comp = (p1, p2) -> p1.toUpperCase().compareTo(p2.toUpperCase());
			
			List<String> emails = employees.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Emails: ");
			emails.stream().forEach(System.out::println);
			
			double sum = employees.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary: " + String.format("%.2f", sum));
				
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}

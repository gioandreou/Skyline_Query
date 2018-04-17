package algorithmoi2018test;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	
	public static void main(String[] args) {
		
		try {
		
		System.out.print("Enter the file name with extension : ");

        Scanner input = new Scanner(System.in);

        File file = new File(input.nextLine());

        input = new Scanner(file);
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> list = new ArrayList<Point>();

		input.nextLine();
		while (input.hasNext()){ 
			    int x = Integer.parseInt(input.next());
			    int y = Integer.parseInt(input.next());
			    points.add(new Point(x,y));
		}input.close();
		
		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++)
				{
					if( points.get(i).getX()<=points.get(j).getX() && 
							  points.get(i).getX()<=points.get(j).getX() && 
							  ( points.get(i).getX()!=points.get(j).getX() ||
							  	points.get(i).getX()!=points.get(j).getX() 
							  )
					 ){
						list.add(points.get(i));}
					}
				}
			
		
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).getX()+" "+points.get(i).getY());
			}
		System.out.println("++++++++++++");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getX()+" "+list.get(i).getY());
			}
	}
		
		
		
		catch (FileNotFoundException e) {
		System.out.println("Directory not found");
		System.out.println("//");
        e.printStackTrace();
        }
	}
}

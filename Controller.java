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
			    list.add(new Point(x,y));
		}input.close();
		
		for(int i=0; i<list.size(); i++){
			double domx = list.get(i).getX();
			double domy = list.get(i).getY();
			boolean domination=false;
			for(int j=0; j<list.size(); j++)
				{	
					if( (i!=j) && 
							domx<=list.get(j).getX() && 
							  domy<=list.get(j).getY() && 
							  ( domx!=list.get(j).getX() ||
							  	domy!=list.get(j).getY() 
							  )
					 ){	System.out.println("check number:"+i+"."+j);
						System.out.println(domx+" - "+ domy +"  dominates  "+list.get(j).getX()+"-"+list.get(j).getY() );
						System.out.println();
						list.remove(list.get(j));
						
					 
					 }
					 
				}
		}
		//Printing ----
		
		System.out.println("++++++++++++");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getX()+" "+list.get(i).getY());
			}
		System.out.println("++++++++++++");
			
	
	}
		
		
		
		catch (FileNotFoundException e) {
		System.out.println("Directory not found");
		System.out.println("//");
        e.printStackTrace();
        }
	}
}

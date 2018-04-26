import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
/**
 * This program implements a Divide and Conquer Algorithm to compute Skyline Queries 
 * with a list of Point given a .txt file with certain 2D points
 * 
 * This is the main class of the program where all the operations are made.
 * 
 * @author Giorgos Andreou AEM: 2334
 * 
 *
 */

public class Main {
	
		/**
		 * main method
		 * @param args
		 */
	public static void main(String[] args) {
		
		try {
			long startTime = System.nanoTime(); //starting a timer to count secs of running program
			/**
			 * @input A file with .txt extension
			 * Files must be in the src folder of the project in order to be recognized by the program
			 * Scanner is constructed and file is read
			 */
			System.out.print("Enter the file name with extension : ");
	        Scanner input = new Scanner(System.in);
	        File file = new File(input.nextLine());
	        input = new Scanner(file); 
	        
	        /**
	         * 2 arrayList Point Object Type to store the numbers of the file
	         * I used the ArrayList<Point> because it has built-in methods to seperate the x and y attribute 
	         * and it is better to handle rathen than a typical List or Array
	         */
	        ArrayList<Point> Final = new ArrayList<Point>();
	        ArrayList<Point> coordinateList = new ArrayList<Point>();
	        /**
	         * input.nextLine(); to ignore the first line of the file where the number of points is indicated
	         * and read directly the first point.
	         * 
	         * 2 variable x , y to store temporally the attributes that are read and store them right after 
	         * in the Coordinate List
	         */
			input.nextLine();
			while (input.hasNext()){ 
				    int x = Integer.parseInt(input.next());		//parses attributes 
				    int y = Integer.parseInt(input.next());
				    
				    coordinateList.add(new Point(x,y));		//stores attributes in Array
			}
			Collections.sort(coordinateList, comp);		//sorts the CoordinateList by using a custom comparator
			input.close();		// scanner is closed
			
			
			/**
			 * output text with separation 
			 */
			System.out.println("+++++///+++");
			System.out.println("Skyline is:");
			System.out.println("+++++///+++");
			
			/*
			 * After using the recursive SkylineDC method, the returned array is stored into the Final A
			 * ArrayList in order to be printed
			 */
			Final= SkylineDC(coordinateList);		
			
			
			/**
			 * Skyline Queries are printed through a for iteration 
			 */
			for (int i = 0; i < Final.size(); i++) {
				System.out.println(Final.get(i).getX()+" "+Final.get(i).getY());
				}
			System.out.println("++++++++++++");
			
			//timer is stopped and running time calculated 
			final long duration = System.nanoTime() - startTime;
			System.out.println(duration/1000000000);
				
			
		}	// A FileNotFoundException if the file is not located 
		catch (FileNotFoundException e) {
			System.out.println("Directory not found");
			System.out.println("//");
	        e.printStackTrace();
	        }
	}
	//Methods
	
	/**
	 * This is the recursive method to compute the SkyLine.
	 * an ArrayList type <point> is the parameter
	 * @param coordinates
	 * @return	the final arraylist with the points of the skyline queries
	 */
	public static ArrayList<Point> SkylineDC(ArrayList<Point> coordinates){
		/**
		 * base case if the input is 1 or 0 return it back 
		 */
		if (coordinates.size()<=1){
	        return coordinates;
	    }
		
		/**
		 * 4 temporary arraylist to store the points each time that the set is divided in half 
		 * LeftSide and RightSide are to store the points in the sides of the set
		 * LeftSkyline and RightSkyline are to store the skyline points on each side after these points 
		 * have been compaired and these that are not conquered are selected
		 */
		ArrayList<Point> LeftSide=new ArrayList<Point>();
	    ArrayList<Point> RightSide=new ArrayList<Point>();
	    ArrayList<Point> LeftSkyline=new ArrayList<>();
	    ArrayList<Point> RightSkyline=new ArrayList<>();
	    
	    /*
	     * Divide the set at half each time
	     */
	    for (int i=0;i<coordinates.size()/2;i++){
	    	LeftSide.add(coordinates.get(i));
	    }
	    for (int i=coordinates.size()/2;i<coordinates.size();i++){
	    	RightSide.add(coordinates.get(i));
	    }
		/**
		 * recursive call of SkylineDC method 
		 */
	    LeftSkyline=SkylineDC(LeftSide);
	    RightSkyline=SkylineDC(RightSide);
	   
	    /**
	     * set a very big minimum attribute so certainly there would be none greater than this
	     * and search each time for a point with a smaller Y attribute.
	     * Since the set is sorted ascending on their x values, we just need the Y's to check.
	     * 
	     * We find the a point with a certain minimum Y attribute on the lefSkyline set and we check 
	     * if there are points on the right side (meaning greater x) with greater Y than the minimum, erase them 
	     * from the rightSkyline set because they are certainly conquered
	     */
	    int minimum=12345;
	    for (int i=0;i<LeftSkyline.size();i++){
	        if (LeftSkyline.get(i).y<minimum){
	        	minimum=LeftSkyline.get(i).y;
	        }
	    }
	    for (int i=RightSkyline.size() -1; i >=0; i--){
	        if (RightSkyline.get(i).y>=minimum){
	        	RightSkyline.remove(RightSkyline.get(i));
	        }
	    }
	    
		/**
		 * After the comparison is done each time we add the sets and return them back 
		 */
	    LeftSkyline.addAll(RightSkyline);
	    return LeftSkyline;
	}
	
	
	/**
	 * This is a custom Comparator for sorting that i have built 
	 * By using this, the set is sorted beforehand and is better for us to manipulate with the set in the
	 * SkylineDC method
	 * 
	 * This comparison function will return positive integer as the x attribute of the first Point is greater
	 * than the second
	 */
	static Comparator<Point> comp = new Comparator<Point>()
	{
	    @Override
	    public int compare(Point o1, Point o2)
	    {
	        return new Integer(o1.x).compareTo(o2.x);
	    }
	};
	

	
}
	
	
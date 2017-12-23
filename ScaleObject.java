import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ScaleObject {

	public static void main(String[] args) {

		try {
			Scanner reader = new Scanner(System.in); 
			System.out.println("Sample Values to Scale:"+"\r\n"+" '1' -> No Scaling"+"\r\n"+" '2' -> Scaled Up Twice"+"\r\n"+" '0.5'-> Scaled Down to Half"+"\r\n"+" '0' -> 2D Model");
			System.out.println("Enter the obj file Name (Sample: dodecahedron.obj):");
			String filename = reader.nextLine();
			System.out.println("Enter the Value to scale x-axis: ");
			Double x = reader.nextDouble(); 
			System.out.println("Enter the Value to scale y-axis: ");
			Double y = reader.nextDouble();
			System.out.println("Enter the Value to scale z-axis: ");
			Double z = reader.nextDouble();
	
			reader.close(); 
			scaleObject(filename, x, y, z );

		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}	
			public static void scaleObject(String InputFileName, Double xScale, Double yScale, Double zScale) throws IOException {
				final String dir = System.getProperty("user.dir");
				InputFileName= dir+File.separator+InputFileName;
				BufferedReader inputStream = new BufferedReader(new FileReader(InputFileName));
			    		// /Users/Natesan/Downloads/cube.obj
			    String[] outputfilesplit = InputFileName.split(".obj");
			    String outputFileName = outputfilesplit[0]+"Scaled.obj";
			     File outputFile = new File(outputFileName);
			       
			        if (!outputFile.exists()) {
			        	outputFile.createNewFile();
			        }
			    FileWriter filewriter = new FileWriter(outputFile.getAbsoluteFile());
			    BufferedWriter outputStream= new BufferedWriter(filewriter);
			    String line;
			    while ((line = inputStream.readLine()) != null) {
			    	if (line.contains("v")) {
						 String[] splitvertices = line.split("\\s+");
			            	String vertex = splitvertices[0];
			            	if (vertex.equals("v")) {
			            	String x = splitvertices[1];
			            	String y = splitvertices[2];
			            	String z = splitvertices[3];
			            	double xValue = Double.parseDouble(x);
			            	double yValue = Double.parseDouble(y);
			            	double zValue = Double.parseDouble(z);
			            	xValue = xValue * xScale;
			            	yValue = yValue * yScale;
			            	zValue = zValue * zScale;
			            	x = String.valueOf(xValue);
			            	y = String.valueOf(yValue);
			            	z = String.valueOf(zValue);
			            	line= vertex+" "+x+" "+y+" "+z;			            	
			            	} 	
			            	else{
			            		continue;
			            	}
			    	}
			    	outputStream.write(line+"\r\n");
			    }
			    outputStream.flush();
			    outputStream.close();
			    inputStream.close();

			System.out.println("Output File: "+outputFileName);
			
			}
			
			
}		
			
			

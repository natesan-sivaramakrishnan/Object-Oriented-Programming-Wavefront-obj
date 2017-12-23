import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Panel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ScaleObjectGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScaleObjectGUI window = new ScaleObjectGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScaleObjectGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setBounds(100, 100, 869, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CS 635 Computer Programming Language - Programming Project");
		lblNewLabel.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblNewLabel.setBounds(15, 16, 766, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Submitted by: Natesan Sivaramakrishnan (ns632@njit.edu)");
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(15, 454, 702, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));

		textField.setBounds(334, 134, 287, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea txtrSampleValuesTo = new JTextArea();
		txtrSampleValuesTo.setFont(new Font("Sitka Small", Font.PLAIN, 25));
		txtrSampleValuesTo.setText("Sample Values to Scale:\r\n '1' -> No Scaling\r\n '2' -> Scaled Up Twice\r\n '0.5'-> Scaled Down to Half\r\n '0' -> 2D Model");
		txtrSampleValuesTo.setBounds(461, 192, 371, 164);
		frame.getContentPane().add(txtrSampleValuesTo);
		
		JTextPane txtpnN = new JTextPane();
		txtpnN.setFont(new Font("Sitka Small", Font.BOLD, 20));
		txtpnN.setText("Object Scaling");
		txtpnN.setEditable(false);
		txtpnN.setBounds(48, 50, 211, 38);
		frame.getContentPane().add(txtpnN);
		
		JTextArea txtrEnterTheObj = new JTextArea();
		txtrEnterTheObj.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		txtrEnterTheObj.setBackground(new Color(245, 222, 179));
		txtrEnterTheObj.setText("Enter the obj file Name\t\t:\r\n(Sample: dodecahedron.obj)\r\n\r\nEnter the Value to scale x-axis\t-> \r\n\r\nEnter the Value to scale y-axis\t->\r\n\r\nEnter the Value to scale z-axis\t->");
		txtrEnterTheObj.setBounds(15, 133, 317, 223);
		frame.getContentPane().add(txtrEnterTheObj);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		textField_1.setBounds(334, 213, 108, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(334, 260, 108, 26);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(334, 314, 108, 26);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBounds(361, 386, 471, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnScale = new JButton("Scale");
		btnScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/////
				try {
				String inputFile = textField.getText();
				String xScaleStr = textField_1.getText();
				String yScaleStr = textField_2.getText();
				String zScaleStr = textField_3.getText();
				double xScale = Double.parseDouble(xScaleStr);
            	double yScale = Double.parseDouble(yScaleStr);
            	double zScale = Double.parseDouble(zScaleStr);
				final String dir = System.getProperty("user.dir");
				String InputFileName= dir+File.separator+inputFile;
				BufferedReader inputStream = new BufferedReader(new FileReader(InputFileName));
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

			    
			    String[] filesplit = inputFile.split(".obj");
			    String opFileName = filesplit[0]+"Scaled.obj";
			    lblNewLabel_2.setText("Output File: "+opFileName);
			    lblNewLabel_2.setVisible(true);
			
			
		
			}
			catch (IOException err) {
				err.printStackTrace();
			}
				
			}	
				////
				
				
			});
		btnScale.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnScale.setBounds(193, 376, 115, 29);
		frame.getContentPane().add(btnScale);
		
	
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File f = fc.getSelectedFile();
				String filename = f.getName();
				textField.setText(filename);
				
			}
		});
		btnNewButton.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnNewButton.setBounds(654, 131, 115, 29);
		frame.getContentPane().add(btnNewButton);
	}
}

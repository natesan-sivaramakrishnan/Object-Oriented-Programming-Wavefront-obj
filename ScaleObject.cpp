#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <stdlib.h>     



using namespace std;

int main() {

	string x, y, z;
	string inputFileName;
	string outputFileName;
	double xScale, yScale, zScale;
	double xValue, yValue, zValue;

	cout << "Sample Values to Scale:" << endl
		<< "'1' -> No Scaling" << endl
		<< "'2' -> Scaled Up Twice" << endl
		<< "'0.5'-> Scaled Down to Half" << endl
		<< "'0' -> 2D Model" << endl;
	cout << "Enter the obj file Name (Sample: dodecahedron.obj):";
	cin >> inputFileName;
	cout << "Enter the Value to scale x-axis:";
	cin >> xScale;
	cout << "Enter the Value to scale y-axis:";
	cin >> yScale;
	cout << "Enter the Value to scale z-axis:";
	cin >> zScale;
	cout << "Output File: Scaled.obj" << endl;


	ifstream inputFile;
	ofstream outputFile;	
	
	inputFile.open(inputFileName.c_str());
	outputFileName = "Scaled.obj";
	outputFile.open(outputFileName.c_str());

	
	if (inputFile.fail()) {
		cerr << " Input File Error" << endl;
		exit(1);
	}

	string line;

	while (!inputFile.eof()) {
		std::getline(inputFile, line);
		if (line.find("v") != std::string::npos) {
			std::string vertex = line.substr(0, 2);
			if (vertex.compare("v ") == 0){
				string splitvertices[4];
				int i = 0;
				stringstream ssin(line);
				while (ssin.good() && i < 4){
					ssin >> splitvertices[i];
					++i;
				}
				x = splitvertices[1];
				y = splitvertices[2];
				z = splitvertices[3];
				xValue = atof(x.c_str());
				yValue = atof(y.c_str());
				zValue = atof(z.c_str());
				xValue = xValue * xScale;
				yValue = yValue * yScale;
				zValue = zValue * zScale;
				std::ostringstream strX;
				strX << std::fixed;
				strX << xValue;
				x = strX.str();
				std::ostringstream strY;
				strY << std::fixed;
				strY << yValue;
				y = strY.str();
				std::ostringstream strZ;
				strZ << std::fixed;
				strZ << zValue;
				z = strZ.str();

				line = vertex + " " + x + " " + y + " " + z;
			}
			else {
				continue;
			}

		}
		outputFile << line << endl;
	}

	inputFile.close();
	outputFile.close();
	return 0;
}


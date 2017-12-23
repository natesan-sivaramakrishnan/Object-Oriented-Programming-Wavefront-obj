'''
Created on 09-Dec-2017

@author: Natesan
'''
import os
print("Sample Values to Scale:"+"\r\n"+" '1' -> No Scaling"+"\r\n"+" '2' -> Scaled Up Twice"+"\r\n"+" '0.5'-> Scaled Down to Half"+"\r\n"+" '0' -> 2D Model")
filename = raw_input("Enter the obj file Name (Sample: dodecahedron.obj): ")
xScale = input("Enter the Value to scale x-axis: ")
yScale = input("Enter the Value to scale y-axis: ")
zScale = input("Enter the Value to scale z-axis: ")
cwd = os.getcwd()
#inputfilename = cwd + "/" + filename
inputfilename = filename
inputfile = open(inputfilename)
words = filename.split('/')
outputFileName = "Scaled" + words[-1]
print("Output File: "+outputFileName)
outputfile = open(outputFileName, 'w+')
for line in inputfile:
    if "v " in line:
        val = line.split()
        vertex = val[0]
        x = val[1]
        y = val[2]
        z = val[3] 
        xValue = float(xScale) * float(x)
        yValue = float(yScale) * float(y)
        zValue = float(zScale) * float(z)
        outputfile.write(vertex + " "+ str(xValue) + " " + str(yValue) + " " +str(zValue) + "\r\n")
    elif "vn" in line:
        continue
    else:
        outputfile.write(line +"\r\n")
outputfile.close()
inputfile.close()

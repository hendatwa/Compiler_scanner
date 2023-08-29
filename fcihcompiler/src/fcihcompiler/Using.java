/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fcihcompiler;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Using {
String code;
public Using (){
}
public boolean CheckIfUsing(String code){
this.code =code;
if(code.charAt(1)=='U'&&code.charAt(2)=='s'&&code.charAt(3)=='i'&&code.charAt(4)=='n'&&code.charAt(5)=='g'){
return true;
}
return false;
}
public String FileNameReturner(){
String fileName="";
if(CheckIfUsing(code)){
for (int i = 6; i < code.length(); i++) {
fileName += code.charAt(i);
}
}
return fileName;
}
public String Use(){
Stack stack=new Stack();
FileReader reader = null; // That is the pointer that reads the file.
int breakRestOfStack = 0; // in order not to print the file more than once if the number of files is less than the stack size.
int noMoreFilesFlag = 0; // Once we read a file that has a no "using" part, this flag will be 1 so it stops the outter loop.
String myString = new String(); // Creates a string.
String nextFileName = null;
String fileName = ""; // fileName is the string that holds each file we access.
// Getting the input string from text field to put it into fileName.
String Data="";
while (noMoreFilesFlag == 0 && breakRestOfStack == 0) { // it will always iterate while the file has a using at his beginning and to make sure not to repeat printing the same string in the stack.
try {
reader = new FileReader(fileName);
} catch (FileNotFoundException ex) { // to handle that case if the fileName inside any file is not found.



while (!stack.isStackEmpty()) { // to priint the whole stack even if the file was not found.
//System.out.println(stack.popFromStack());
Data+=stack.popFromStack()+"\n";
}
}
int data = 0;
try {
data = reader.read(); // to read a file each iterate.
} catch (IOException ex) {
Logger.getLogger(Using.class.getName()).log(Level.SEVERE, null, ex);
}
while (data != -1) { // it will loop till the end of the file
myString = myString + (char) data; // adds file characters into a string
try {
data = reader.read();
} catch (IOException ex) {
Logger.getLogger(Using.class.getName()).log(Level.SEVERE, null, ex);
}
}
if (!stack.isStackFull()) { // Check if the stack is not full then push another element into it.
stack.pushInStack(myString);
} else { // if the stack is full, we'll change a flag, in order to stop the loop
noMoreFilesFlag = 1; // once the flag = 1 means there are no more files to access
}
/* --------------------------------------------------------------------------------------------------*/
// Getting the next file name if available to put it inside a string called fileName
File file = new File(fileName);
try {
Scanner scanner = new Scanner(file);
while (scanner.hasNextLine()) {
String line = scanner.nextLine(); // takes a line in the file
if ((line.charAt(0) == 'u') && (line.charAt(1) == 's') && (line.charAt(2) == 'i') && (line.charAt(3) == 'n') && (line.charAt(4) == 'g')) { // Checks if the first word at the file is "using" or not
fileName = ""; // clears the string file every iteration in order to get rid of overflow.
// we loop after the word "using" till the end of the line to reach the name of the next file and put it into the fileName string.
for (int i = 6; i < line.length(); i++) {
fileName = fileName + line.charAt(i);
}
} else { // if we did not find "using" in the beginning of a specific file, we would not find anymore files to read so we will end the outter loop.
breakRestOfStack = 1;
}
break; // in order not to check ONLY the first one line inside the file so we do not to repeat the scanner.hasNextLine() loop more than once.
}

} catch (FileNotFoundException e) {
    System.out.println("knjdfsk");
//handle this
} catch (IOException ex) {
Logger.getLogger(Using.class.getName()).log(Level.SEVERE, null, ex);
}
myString = ""; // To clear the string we add the characters at every iterate to prevent the overflow.
}
/* ---------------------------------------------------------------------------------------------*/
// pops and appends all the files at the end to the text area, it will keep iterating till the stack becomes empty.
while (!stack.isStackEmpty()) {
Data+=stack.popFromStack()+"\n";
System.out.print(stack.popFromStack()+"\n");

}
return Data;
}
}
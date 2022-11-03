import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String state = "startSentence";
        File file = new File("input.txt");
        try (FileReader fr = new FileReader(file)) {
            int character;
            char c;
            while ((character = fr.read()) != -1) {
                System.out.print(state + " -> " + (char) character + " -> ");
                c = (char) character;
                switch (state) {

                    case "startSentence":
                        if ((c == 't') || (c == 'T')) {
                            Main.write('T');
                            state = "inT";
                        }
                        else if ((c == 's') || (c == 'S')) {
                            Main.write('S');
                            state = "inS";
                        }
                        else if (Character.isLowerCase(c)) {
                            char c_ = Character.toUpperCase(c);
                            Main.write(c_);
                            state = "inSentence";
                        }
                        else if (Character.isUpperCase(c)) {
                            Main.write(c);
                            state = "inSentence";
                        }
                        break;

                    case "inSentence":
                        if (c == ' ') {
                            Main.write(c);
                            state = "inSpace";
                        }
                        else if (c == '\n') {
                            break;
                        }
                        else if ((c == 't') || (c == 'T')) {
                            Main.write(c);
                            state = "inT";
                        }
                        else if ((c == 's') || (c == 'S')) {
                            Main.write(c);
                            state = "inS";
                        }
                        else if (Character.isLowerCase(c)) {
                            Main.write(c);
                            state = "inSentence";
                        }
                        else if (Character.isUpperCase(c)) {
                            char c_ = Character.toLowerCase(c);
                            Main.write(c_);
                            state = "inSentence";
                        }
                        else if ((c == '.') || (c == '?') || (c == '!')) {
                            Main.write(c);
                            Main.write(' ');
                            state = "startSentence";
                        }
                        else if (c == ',') {
                            Main.write(c);
                            Main.write(' ');
                        }
                        break;
   
                    case "inSpace":
                        if (c == '\n') {
                          break;
                        }
                        else if (c == 'i') {
                            state = "inI";
                        }
                        else if (Character.isUpperCase(c)) {
                            char c_ = Character.toLowerCase(c);
                            Main.write(c_);
                            state = "inSentence";
                        }
                        else if (Character.isLowerCase(c)) {
                          Main.write(c);
                          state = "inSentence";
                        }
                        else if ((c == '.') || (c == '?') || (c == '!')) {
                          Main.write(c);
                          Main.write(' ');
                          state = "startSentence";
                        }
                        else if (c == ',') {
                          Main.write(c);
                          Main.write(' ');
                          state = "inSentence";
                        }
                          break;

                    case "inI":
                        if (c == ' ') {
                            Main.write('I');
                            Main.write(' ');
                            state = "inSpace";
                        }
                        else if ((Character.isUpperCase(c)) || (Character.isLowerCase(c)))  {
                            Main.write('i');
                            Main.write('c');
                            state = "inSentence";
                        }
                        else if ((c == '.') || (c == '?') || (c == '!')) {
                            Main.write('I');
                            Main.write(c);
                            state = "startSentence";
                        }
                        else if (c == ',') {
                            Main.write('I');
                            Main.write(c);
                            state = "inSentence";
                        }
                        break;

                    case "inT":
                        if ((c == 't') || (c == 'T')) {
                            Main.write(c);
                            state = "inTT";
                        }
                        else {
                            state = "inSentence"; 
                        }
                        break;
                    //Incomplete
                    case "inTT":
                        if ((c == 't') || (c == 'T')) {
                            break;
                        }
                        else if ((c == '.') || (c == '?') || (c == '!')) {
                          Main.write(c);
                          Main.write(' ');
                          state = "startSentence";
                        //punc safe
                        }
                        else {
                          Main.write(c);
                          Main.write(' ');
                          state = "inSentence";
                        }
                        break;


                    case "inS":
                        if ((c == 's') || (c == 'S')) {
                            Main.write(c);
                            state = "inSS";
                        }
                        else {
                            state = "inSentence"; 
                        }
                        break;
                        
                        


                    case "inSS":
                        if ((c == 's') || (c == 'S')) {
                            break;
                        }
                        else if ((c == '.') || (c == '?') || (c == '!')) {
                          Main.write(c);
                          Main.write(' ');
                          state = "startSentence";
                        }
                        else {
                          Main.write(c);
                          Main.write(' ');
                          state = "inSentence";
                          //punc safe
                        }
                        break;

                    //case "ee":
                        //if (c == ' ') {
                            // logic
                        //}
                        //else if (c == ' ') {
                            // logic
                        //}
                        //else {
                            //
                        //}
                        //break;
                        
                        
                        
                        
                    
                    default: //state = "Invalid";
                        break;

                }
                System.out.println(state);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(char c) {
        try {
            System.out.print(c + " -> ");
            FileWriter myWriter = new FileWriter("output.txt", true);
            myWriter.write(c);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}//class
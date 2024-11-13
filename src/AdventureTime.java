import java.io.*;
import java.util.Scanner;

public class AdventureTime {

    /** This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println(challengeOne("inputOneTwo.txt"));
        System.out.println(challengeTwo("inputOneTwo.txt"));
        System.out.println(challengeThree("inputThreeFour.txt"));
        System.out.println(challengeFour("inputThreeFour.txt"));
    }

    /** TODO 1
     *
     * Challenge 1
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] depths = readFile(fileName);
        int count = 0;
        for(int i = 0; i < depths.length; i++){
            if(i != depths.length -1 ){
                if(depths[i+1] > depths[i]){
                    count ++;
                }
            } else {
                break;
            }
        }
        return count;
    }

    /** TODO 2
     *
     * Challenge 2
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */

    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] depths = readFile(fileName);
        int count = 0;

        for(int i = 0; i < depths.length - 2; i++){
            int currentSum = depths[i] + depths[i+1] + depths[1+2];
            if(i + 3 < depths.length) {
                int nextSum = depths[i + 1] + depths[i + 2] + depths[i + 3];
                if (nextSum > currentSum) {
                    count++;
                }
            }
        }

        return count;
    }

    /** TODO 3
     *
     * Challenge 3
     *
     * @param fileName
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */

    // I added a new method called readStringFile among other methods below, which functions like readFile but for Strings
    public static int challengeThree(String fileName) throws FileNotFoundException {
        String[] instructions = readStringFile(fileName);
        int horizontal = 0;
        int depth = 0;
        for(int i = 0; i < instructions.length; i++){
            String currentInstruction = instructions[i];
            String[] splitInstructions = currentInstruction.split(" ");

            //Check each command one by one

            //1. forward
            if(splitInstructions[0].equals("forward")){
                //increase horizontal position by the specified value
                horizontal += Integer.parseInt(splitInstructions[1]);  //converts String to an int
            }
            //2. down
            else if(splitInstructions[0].equals("down ")){
                depth += Integer.parseInt(splitInstructions[1]);
            }
            //3. up --> if the command is neither "forward" nor "down", must be "up". So just use else to complete the condition
            else{
                depth -= Integer.parseInt(splitInstructions[1]);
            }

        }

        return horizontal * depth;
    }

    /** TODO 4
     *
     * Challenge 4
     *
     * @param filename
     * @return Answer to Challenge 4
     * @throws FileNotFoundException
     */
    public static int challengeFour(String filename) throws FileNotFoundException {
        String[] instructions = readStringFile(filename);

        //initializing variables for horizontal position, depth, and aim to zero
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        //Loop through each instruction in the instructions array, derived from the file
        for(int i = 0; i < instructions.length; i++){
            String currentInstruction = instructions[i];
            String[] splitInstructions = currentInstruction.split(" ");

            //Extract the direction from the split instruction
            String direction = splitInstructions[0];

            int value = Integer.parseInt(splitInstructions[1]);     //convert the second part of the instruction from String to int

            if(direction == "forward"){
                horizontal += value;
                depth += aim * value;
            }

            else if(direction == "down"){
                aim += value;
            }

            else{   // this is up
                aim -= value;
            }
        }

        //debugging
        System.out.println(horizontal);
        System.out.println(depth);

        return horizontal * depth;
    }

    /** This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /** This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    // I added a method to read the values in inputFilename into an array of String, similar to method readFile but for Strings
    private static String[] readStringFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        String[] data = new String[numberOfLinesInFile];
        int index = 0;
        while(scanner.hasNextLine()) {
            data[index++] = scanner.nextLine();
        }
        scanner.close();
        return data;
    }

    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

}
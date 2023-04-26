import java.util.*;
import java.io.*;


public class CSVFixer {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the path of the csv file to correct (with forward slashes): ");
        String csvFilePath = input.nextLine();
        System.out.println("\n");

        FileInputStream fstream = new FileInputStream(csvFilePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        HashMap<String, String> headerToID = new HashMap<String, String>();
        String strLine = br.readLine();
        String id = "";

        System.out.print("Enter the path of the new csv file to create: ");
        String newFileName = input.nextLine();
        System.out.println("\n");
        File newFile = new File(newFileName);
        FileWriter writer = new FileWriter(newFile);
        newFile.createNewFile();

        System.out.print("Which setting? 0 for JUST HEADERS or 1 for HEADERS to ID: ");
        String setting = input.nextLine();
        System.out.println("\n");

        String header = "";

        while (strLine != null){
            String[] attributes = strLine.split(",", 0);
            if (attributes.length != 0) {
                if (attributes.length == 1) {
                    header = attributes[0];
                    if (setting.equals("1")) {
                        if (headerToID.containsKey(header)) {
                            id = headerToID.get(header);
                        } else {
                            System.out.print("ID NOT FOUND. ENTER ID FOR \"" + header + "\": ");
                            headerToID.put(header,input.nextLine());
                            System.out.println("\n");
                            id = headerToID.get(header);
                        }
                    }
                } else {
                    if (setting.equals("1")) {
                        writer.write(id + "," + strLine + "\n");
                    } else if (setting.equals("0")) {
                        writer.write(header + "," + strLine + "\n");
                    }
                }
            }
            strLine = br.readLine();
        }
        br.close();
        input.close();
        writer.close();
    }
}
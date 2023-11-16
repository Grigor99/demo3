package com.example.demo.controller;



import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvValidationException;

//
// class CsvToHashMap {
//    public static void main(String[] args) {
//        String csvFilePath = "/Users/gmartirosyan/tmp/results/TEMPRESULTS_202311141651.csv"; // Replace with the actual path to your CSV file
//        Map<Integer, Integer> hashMap = new HashMap<>();
//
//        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
//            String[] nextLine;
//            int rowNumber = 2; // Assuming the data starts from the 2nd row
//
//            while ((nextLine = reader.readNext()) != null) {
//                // Assuming the values are integers. Adjust accordingly for other data types.
//                int value = Integer.parseInt(nextLine[0]);
//                hashMap.put(rowNumber++, value);
//            }
//
//            // Print the HashMap for verification
//            hashMap.forEach((key, value) -> System.out.println("Row " + key + ": " + value));
//
//            System.out.println(hashMap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CsvValidationException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


class CsvToHashMap {
    static Map<Integer, Integer> hashMapDb = new HashMap<>();
    static Map<Integer, Integer> hashMapEX = new HashMap<>();

    public static void main(String[] args) {
        String csvFilePath = "/Users/gmartirosyan/tmp/results/TEMPRESULTS_202311141651.csv"; // Replace with the actual path to your CSV file

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            int rowNumber = 1; // Start from the 1st row

            while ((nextLine = reader.readNext()) != null) {
                // Assuming the values in column A are separated by semicolons
                String[] values = nextLine[0].split(";");
                for (String value : values) {
                    try {
                        // Parse each value to an integer
                        int intValue = Integer.parseInt(value.trim());
                        hashMapDb.put(rowNumber++, intValue);
                    } catch (NumberFormatException e) {
                        // Handle the case where a value cannot be parsed to an integer
                        System.err.println("Error parsing value: " + value);
                    }
                }
            }

            // Print the HashMap for verification
//            hashMapDb.forEach((key, value) -> System.out.println("Row " + key + ": " + value));
            System.out.println(hashMapDb.size());

            System.out.println("end");


            String csvFilePath2 = "//Users/gmartirosyan/Downloads/rootNodeIdsWithProblemsRange.csv"; // Replace with the actual path to your CSV file

            try (CSVReader reader2 = new CSVReader(new FileReader(csvFilePath2))) {
                String[] nextLine2;
                int rowNumber2 = 1; // Start from the 1st row

                while ((nextLine2 = reader2.readNext()) != null) {
                    // Assuming the values in column A are separated by semicolons
                    String[] values = nextLine2[0].split(";");
                    for (String value : values) {
                        try {
                            // Parse each value to an integer
                            int intValue = Integer.parseInt(value.trim());
                            hashMapEX.put(rowNumber2++, intValue);
                        } catch (NumberFormatException e) {
                            // Handle the case where a value cannot be parsed to an integer
                            System.err.println("Error parsing value: " + value);
                        }
                    }
                }

                // Print the HashMap for verification
//                hashMapEX.forEach((key, value) -> System.out.println("Row " + key + ": " + value));
                System.out.println(hashMapEX.size());

                System.out.println("merging ***********************************************");
                hashMapEX.putAll(hashMapDb);
                System.out.println(hashMapEX.size());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
class Main{
    public static void main(String[] args) {
        Map<Integer, Integer> hashMap1 = new HashMap<>();
        Map<Integer, Integer> hashMap2 = new HashMap<>();

        // Add some key-value pairs to the first HashMap
        hashMap1.put(1, 2);
        hashMap1.put(2, 3);
        hashMap1.put(3, 5);

        // Add the same key-value pairs to the second HashMap
        hashMap2.put(1, 2);
        hashMap2.put(2, 3);
        hashMap2.put(3, 4);

        hashMap2.putAll(hashMap1);

        System.out.println(hashMap2);
    }
}


 class LocalTest {

     public static void main(String[] args) throws IOException {
         // Using a character not supported in ISO-8859-1
         String imdsRaw = "Wälzkörper / Rolling element NRB 1,35x3,8 ";

         File file = File.createTempFile("cms", ".dat");
         try {
             Files.createDirectories(file.getParentFile().toPath());
         } catch (IOException e) {
             e.printStackTrace();
         }
         try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(),StandardCharsets.UTF_8)) {
             writer.append(imdsRaw);
         } catch (IOException e) {
             e.printStackTrace();
         }
         System.out.println(file);
         Path filePath = Path.of("/var/folders/5q/dgrk61pj5j96xqjngwvl1wfh0000gn/T/cms2097125271578647288.dat");
         System.out.println(new String(Files.readAllBytes(filePath), ImdsCharset.DEFAULT));

//         try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 System.out.println(line);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
     }
}
 final class ImdsCharset {
    public static final Charset DEFAULT = StandardCharsets.ISO_8859_1;

    private ImdsCharset() {
    }
}

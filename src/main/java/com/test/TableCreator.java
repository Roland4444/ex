package com.test;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class TableCreator {

   public static void createTable(int lines) throws IOException {
       BufferedWriter bufferedWriter;
       BufferedReader bufferedReader = loadTemplate();
       int count = 1;
       int maxCell = 2 * lines + 1;
       File fileOutput  = ResourceUtils.getFile("classpath:templates/table.html");
       String lineTDYellow = "<td bgcolor=\"yellow\">%s</td>\n";
       String lineTDWhite = "<td bgcolor=\"white\">%s</td>\n";

       try {
           bufferedWriter = new BufferedWriter(new FileWriter(fileOutput));
           if (bufferedReader != null)
               while (bufferedReader.ready())
                   bufferedWriter.write(bufferedReader.read());
           else
               bufferedWriter.write("<html>\n<head>\n</head>\n<body>Error load template\n<table>");

           for (int i = 0; i < lines; i++) {
               bufferedWriter.write("<tr>\n");
               int cellCount = 1;

               for (int j = 2; j < maxCell; j++) {
                   if (j >= maxCell / 2 + 1 - i && (cellCount <= 2 * i + 1 || cellCount == 1)) {
                       if (primeNumber(count)) bufferedWriter.write(String.format(lineTDYellow,count));
                       else bufferedWriter.write(String.format(lineTDWhite,count));
                       count++;
                       cellCount++;
                   } else bufferedWriter.write("<td></td>");
               }
               bufferedWriter.write("</tr>\n");
           }

           bufferedWriter.write("</table>\n</body>\n</html>");
           bufferedWriter.flush();
           bufferedWriter.close();
       } catch (FileNotFoundException e) {
           System.out.println("Fail write Html file");
       }
   }

   private static BufferedReader loadTemplate() {
      BufferedReader bufferedReader;
       try {
           File file  = ResourceUtils.getFile("classpath:static/template.html");
           bufferedReader = new BufferedReader(new FileReader(file));
       } catch (FileNotFoundException e) {
           bufferedReader = null;
       }
        return bufferedReader;
   }

    private static boolean primeNumber(int i) {

        int sqrt = (int) Math.floor(Math.sqrt(i));
        for(int j = 2; j <= sqrt; j++) {
        if(i % j == 0)
            return false;
        }
        return i != 1;
    }
}

package com.RyanCh29.borderlandsvault.CSV;

import android.content.Context;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVManipulator {

    public List<String[]> CSVRead(Context context, String file) {
        List<String[]> result = null;
        try {
            InputStream inputStream = context.getAssets().open(file);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVReader reader = new CSVReader(buffReader);

//            File csvFile = new File(Environment.getExternalStorageDirectory() + file);
//            CSVReader reader = new CSVReader(new FileReader(csvFile.getAbsolutePath()));
            result = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
//                System.out.println(nextLine[0]);
                result.add(nextLine);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

}

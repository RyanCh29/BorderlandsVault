package com.RyanCh29.borderlandsvault.CSV;

import android.content.Context;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVManipulator {

    public List<String[]> CSVReadAsset(Context context, String file) {
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
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
    public List<String[]> CSVReadFile(Context context, String file) {
        List<String[]> result = null;
        try {
            InputStream inputStream = new FileInputStream(context.getFilesDir()+"/"+file);
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
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public void CSVWrite(Context context, String fileName, List<String[]> data) {
        try {
            //check if file exists, if not create it before writing
//            System.out.println(context.getFilesDir()+"/Borderlands_User_CSV_gear.csv");
            File file = new File(context.getFilesDir()+"/Borderlands_User_CSV_gear.csv");

            if(!file.exists()) {
//                create file
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            CSVWriter writer = new CSVWriter(buffWriter);
            Toast.makeText(context, "Saved to " + context.getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();

            for (String[] str: data) {
                writer.writeNext(str);

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

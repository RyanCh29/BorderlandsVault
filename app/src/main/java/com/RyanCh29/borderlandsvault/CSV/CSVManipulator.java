package com.RyanCh29.borderlandsvault.CSV;

import android.content.Context;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            result = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                result.add(nextLine);

            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<String[]> CSVReadFile(Context context, String fileName) {
        List<String[]> result = null;
        try {
            //check if file exists, if not create it before reading
            File file = new File(context.getFilesDir()+"/" + fileName);
            if(!file.exists()) {
                //create file
                file.createNewFile();
                result = new ArrayList<>();
                return result;
            }

            InputStream inputStream = new FileInputStream(file);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVReader reader = new CSVReader(buffReader);

            result = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
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
            File file = new File(context.getFilesDir()+"/" + fileName);

            if(!file.exists()) {
                //create file
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            CSVWriter writer = new CSVWriter(buffWriter);
//            Toast.makeText(context, "Saved to " + context.getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();

            for (String[] str: data) {
                writer.writeNext(str);

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CSVAppend(Context context, String fileName, String[] data) {
        try {
            //check if file exists, if not create it before writing
//            System.out.println(context.getFilesDir()+"/Borderlands_User_CSV_gear.csv");
            File file = new File(context.getFilesDir()+"/" + fileName);

            if(!file.exists()) {
                //create file
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file, true);
            BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            CSVWriter writer = new CSVWriter(buffWriter);

            writer.writeNext(data);


            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

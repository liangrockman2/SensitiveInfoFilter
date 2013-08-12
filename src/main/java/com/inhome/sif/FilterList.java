package com.inhome.sif;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-8
 * Time: 下午8:09
 * To change this template use File | Settings | File Templates.
 */
class FilterList {
    /*InputStream is = new FileInputStream("");
    byte[] buffer = new byte[100];
    int length = 0;
    while ((length = is.read(buffer, 0, 100)) != -1) {
        String str = new String(buffer, 0, length);   //将字节数组转换成字符串
        System.out.print(str);
    }
    is.close();
                  */
    List<String> list = new ArrayList<String>();

    public FilterList(){

        String filePath = FilterList.class.getClassLoader().getResource("filterList.txt").getFile();
        File file = new File( filePath );

        if( file.exists() ){
            try {
                InputStream inputStream = null;
                InputStreamReader isr = null;
                BufferedReader bufferedReader = null ;

                try {
                    inputStream = new FileInputStream(file);
                    isr = new InputStreamReader(inputStream, "UTF-8");
                    bufferedReader = new BufferedReader(isr);

                    String line = bufferedReader.readLine();
                    while(line != null){
                        this.list.add(line);
                        line = bufferedReader.readLine();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } finally {
                    if(bufferedReader != null) bufferedReader.close();
                    if(isr != null) isr.close();
                    if(inputStream != null) inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        /*
        InputStream is = null;
        try {
            is = new FileInputStream("D:/ServletDemo.txt");
            byte[] buffer = new byte[100];
            int length = 0;
            while ((length = is.read(buffer, 0, 100)) != -1) {
                String str = new String(buffer, 0, length);   //将字节数组转换成字符串
                System.out.print(str);
            }
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        */
    }
}

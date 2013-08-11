package com.inhome.sif;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        list.add("a=b");

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

    }
}

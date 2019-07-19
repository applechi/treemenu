package com.apple;

import java.io.File;

/**
* @program: treemenu
*
* @description: 递归输出文件夹下面的所有文件
*
* @author: Apple
*
* @create: 2019-07-19 15:10
**/
final class FileRecuriveTest {
    public static void main(String[] args) {

        File file = new File("F:\\aabbcc");
        RecuriveFile(file.listFiles());
        //System.out.println("isDirectory:" + file.isDirectory());// false



    }

    public static void RecuriveFile( File[] files){
        File[] nextFileList = files;
        for (int i = 0; i < nextFileList.length; i++) {
            File tempFile=nextFileList[i];
            if (tempFile.isDirectory()){
                //System.out.println("---Apple Boy---"+tempFile.getName());
                RecuriveFile(tempFile.listFiles());
            }else {
                System.out.println("---Apple Boy---"+tempFile.getPath());
            }
        }



    }
}

package com.example.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class streamUtils {

	public static String readFromStream(InputStream in){
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		String msg="";
		int len=0;
		byte[] buffer=new byte[1024];
		try {
			while((len=in.read(buffer))!=-1){
				byteArrayOutputStream.write(buffer,0,len);
			}
			msg=byteArrayOutputStream.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				byteArrayOutputStream.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return msg;
	}
}

package com.example.gallery3d.utils;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.R.integer;
import android.content.res.Resources;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;

public class imageUtils {

	private static Hashtable<String, SoftReference<Bitmap>> mCacheHashTable=
			new Hashtable<String, SoftReference<Bitmap>>();
	public static Bitmap getImageBitmap(Resources resources,int imageId){
		SoftReference<Bitmap> reference=mCacheHashTable.get(""+imageId);
		if(reference!=null){
			Bitmap bitmap=reference.get();
			if(bitmap!=null){
				return bitmap;
			}
		}
		
		Bitmap rBitmap =getInvertImage(resources, imageId);
		mCacheHashTable.put(""+imageId, new SoftReference<Bitmap>(rBitmap));
		return rBitmap;
	}
	public static Bitmap getInvertImage(Resources resources, int imageId){
		Bitmap resourcesbitmap=BitmapFactory.decodeResource(resources, imageId);
		
		Matrix matrix=new Matrix();//图形矩阵
		matrix.setScale(1f,-1f);//反转
		Bitmap invertbitmap=Bitmap.createBitmap(resourcesbitmap, 0, resourcesbitmap.getHeight()/2, resourcesbitmap.getWidth(),
				resourcesbitmap.getHeight()/2, matrix, false);
		
		Bitmap resultbitMap=Bitmap.createBitmap(resourcesbitmap.getWidth(),(int)(resourcesbitmap.getHeight()*3/2+5), Config.ARGB_8888);
		
		Canvas canvas=new Canvas(resultbitMap);
		canvas.drawBitmap(resourcesbitmap, 0, 0, null);
		canvas.drawBitmap(invertbitmap, 0, resourcesbitmap.getHeight()+5,null);
		
		Paint paint=new Paint();
		
		LinearGradient linearGradient=new LinearGradient(0, resourcesbitmap.getHeight()+5, 0, resultbitMap.getHeight(),
				0X70ffffff, 0X00ffffff, TileMode.CLAMP);//TileMode.CLAMP以最后的透明继续向下
		paint.setShader(linearGradient);
		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
		canvas.drawRect(0, resourcesbitmap.getHeight()+5, resourcesbitmap.getWidth(), resultbitMap.getHeight(), paint);
		return resultbitMap;
	}
}

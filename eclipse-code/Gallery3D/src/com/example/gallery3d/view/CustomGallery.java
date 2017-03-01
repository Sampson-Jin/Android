package com.example.gallery3d.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class CustomGallery extends Gallery {

	private int GalleryCenterPoint=0;
	private Camera camera;
	public CustomGallery(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setStaticTransformationsEnabled(true);
		camera=new Camera();
	}

	public CustomGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		setStaticTransformationsEnabled(true);
		camera=new Camera();
	}

	public CustomGallery(Context context) {
		super(context);
		setStaticTransformationsEnabled(true);
		camera=new Camera();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		GalleryCenterPoint=getGalleryCenterPoint();
		super.onSizeChanged(w, h, oldw, oldh);
	}
	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		int ViewCenterpoint=getViewCenterPoint(child);
		int rotateAngle=0;//默认旋转角度
		if(ViewCenterpoint!=GalleryCenterPoint){
			int dff=GalleryCenterPoint-ViewCenterpoint;
			float scale=(float)dff/(float)child.getWidth();
			rotateAngle=(int)(scale*50);
			if(Math.abs(rotateAngle)>50){
				rotateAngle=rotateAngle>0?50:-50;
			}
		}
		//把上一个变换效果清空
		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX);//设置变换方式为矩阵
		startTransformation((ImageView)(child), rotateAngle, t);
		return true;
	}
	private void startTransformation(ImageView imageView,int rotateAngle,Transformation transformation){
		camera.save();//保存
		//放大
		int absRotateAngle=Math.abs(rotateAngle);
		camera.translate(0, 0, 100f);//给摄像机定位
		int zoom=-250+(absRotateAngle*2);
		camera.translate(0, 0, zoom);
		
		//透明度
		int alpha=(int)(255-(absRotateAngle*2.5));
		imageView.setAlpha(alpha);
		
		//旋转
		camera.rotateY(rotateAngle);
		
		Matrix matrix=transformation.getMatrix();
		camera.getMatrix(matrix);
		
		//矩阵前乘
		matrix.preTranslate(-imageView.getWidth()/2, -imageView.getHeight()/2);
		//矩阵后乘
		matrix.postTranslate(imageView.getWidth()/2, imageView.getHeight()/2);
		camera.restore();//恢复之前保存的状态
	}
	private int getGalleryCenterPoint(){
		return this.getWidth()/2;
	}
	private int getViewCenterPoint(View view){
		
		return view.getWidth()/2+view.getLeft();
	}
	
}

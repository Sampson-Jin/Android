#include<stdio.h>
#include<stdlib.h>
#include"com_example_hellondk_MainActivity.h"

#include <android/log.h>
#define TAG "nate"
#define LOGV(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

JNIEXPORT jstring JNICALL Java_com_example_hellondk_MainActivity_GetStringFromC
  (JNIEnv * env, jclass jclass){
	LOGV("金永洋");
	return (*env)->NewStringUTF(env,"你好,金永洋");
}

JNIEXPORT void JNICALL Java_com_example_hellondk_MainActivity_updateFile
  (JNIEnv *env, jclass jclazz, jstring path){
	const char* file_path=(*env)->GetStringUTFChars(env,path,NULL);
	if(file_path!=NULL){
		LOGV("from c file_path %s", file_path);
	}
	FILE* file=fopen(file_path,"a+");
	if(file!=NULL){
		LOGV("from c open file success");
	}
	char date[]="I AM A BOY";
	int count=fwrite(date,strlen(date),1,file);
	if(count>0){
		LOGV("from c write file success");
	}
	if(file!=NULL){
		fclose(file);
	}
	(*env)->ReleaseStringUTFChars(env,path,file_path);
}

JNIEXPORT jintArray JNICALL Java_com_example_hellondk_MainActivity_updateIntArray
  (JNIEnv *env, jclass jcalzz, jintArray array){

//	jint nativeArray[5];
//
//	(*env) ->GetIntArrayRegion(env,array,0,5,nativeArray);
//	int j;
//	for(j=0;j<5;j++){
//		nativeArray[j]+=5;
//		LOGV("from c int %d",nativeArray[j]);
//	}
//	(*env) ->SetIntArrayRegion(env,array,0,5,nativeArray);

	jint * data=(*env)->GetIntArrayElements(env,array,NULL);
	jsize len=(*env)->GetArrayLength(env,array);
	int j;
	for(j=0;j<len;j++){
		data[j]+=3;
		LOGV("from c int %d",data[j]);
	}
	(*env)->ReleaseIntArrayElements(env,array,data,0);
	return array;
}





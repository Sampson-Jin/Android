#include <jni.h>
#include <string.h>
#include <android/log.h>
#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

//char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
//{
//	 char*   rtn   =   NULL;
//	 jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
//	 jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
//	 jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
//	 jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
//	 jsize   alen   =   (*env)->GetArrayLength(env,barr);
//	 jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
//	 if(alen   >   0)
//	 {
//	  rtn   =   (char*)malloc(alen+1);         //"\0"
//	  memcpy(rtn,ba,alen);
//	  rtn[alen]=0;
//	 }
//	 (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //
//	 return rtn;
//}

JNIEXPORT void JNICALL Java_com_example_cdyjava_MainActivity_setCToJava
  (JNIEnv* env, jobject obj){
	//char* cstr=Jstring2CStr(env,jstr);
	jclass jclazz= (*env)->FindClass(env,"com/example/cdyjava/MainActivity");
	jmethodID methodID= (*env)->GetMethodID(env,jclazz,"cDiaoJavaShow","(Ljava/lang/String;)V");
	(*env)->CallVoidMethod(env,obj,methodID,(*env)->NewStringUTF(env,"C调用java显示"));
}





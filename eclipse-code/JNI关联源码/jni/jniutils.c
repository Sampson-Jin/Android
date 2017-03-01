#include <jni.h>
#include <string.h>

char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
{
	 char*   rtn   =   NULL;
	 jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
	 jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
	 jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
	 jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
	 jsize   alen   =   (*env)->GetArrayLength(env,barr);
	 jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
	 if(alen   >   0)
	 {
	  rtn   =   (char*)malloc(alen+1);         //"\0"
	  memcpy(rtn,ba,alen);
	  rtn[alen]=0;
	 }
	 (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //
	 return rtn;
}

JNIEXPORT jstring JNICALL Java_com_example_jniyuan_MainActivity_setMi
  (JNIEnv *env, jobject jclzz, jstring jstr,jint len){
	char* cstr=Jstring2CStr(env,jstr);
	int i=0;
	for(i;i<len;i++){
		*(cstr+i)+=1;
	}
	return (*env)->NewStringUTF(env,cstr);
}


JNIEXPORT jint JNICALL Java_com_example_jniyuan_MainActivity_add
  (JNIEnv *env, jobject jclazz, jint a, jint b){
	return a+b;
}

JNIEXPORT void JNICALL Java_com_example_jniyuan_MainActivity_asd
  (JNIEnv *env, jobject jclazz, jintArray arr){
//	jint* GetIntArrayElements(jintArray array, jboolean* isCopy)
	int len=(*env) ->GetArrayLength(env,arr);
	int* carrp =(*env)->GetIntArrayElements(env ,arr,0);
	int i=0;
	for(i;i<len;i++){
		*(carrp+i)+=10;
	}
}

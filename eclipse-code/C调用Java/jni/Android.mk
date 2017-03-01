LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := cdyJava
LOCAL_SRC_FILES := cdyJava.c

include $(BUILD_SHARED_LIBRARY)

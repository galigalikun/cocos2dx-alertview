LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE    := cocos_alertview_plugin_static

LOCAL_MODULE_FILENAME := libalertviewplugin

LOCAL_SRC_FILES := \
	AlertView.cpp


LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)
LOCAL_EXPORT_C_INCLUDES += $(LOCAL_PATH)/../

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../../cocos2d/cocos/2d \
$(LOCAL_PATH)/../


LOCAL_LDLIBS := -landroid
LOCAL_LDLIBS += -llog

include $(BUILD_STATIC_LIBRARY)


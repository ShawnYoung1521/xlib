LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_USE_AAPT2 := true

LOCAL_MODULE := XYLib

src_dirs :=  library/src/main/java
res_dirs :=  library/src/main/res

LOCAL_PROGUARD_FLAG_FILES := library/proguard-rules.pro

LOCAL_SRC_FILES := $(call all-java-files-under, $(src_dirs))
LOCAL_RESOURCE_DIR := $(addprefix $(LOCAL_PATH)/, $(res_dirs))
LOCAL_SRC_FILES += $(call all-Iaidl-files-under)

LOCAL_MANIFEST_FILE := library/src/main/AndroidManifest.xml

LOCAL_JAVA_LIBRARIES := \
    android-support-annotations

LOCAL_SHARED_ANDROID_LIBRARIES := \
    android-support-annotations \
    android-support-v4 \
    android-support-v7-appcompat

LOCAL_PRIVATE_PLATFORM_APIS :=false
LOCAL_JAR_EXCLUDE_FILES := none
LOCAL_JACK_ENABLED := full

include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)

include $(BUILD_MULTI_PREBUILT)

# For the test package.
include $(call all-makefiles-under, $(LOCAL_PATH))

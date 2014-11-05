#include "AlertView.h"
#include "jni/JniHelper.h"


extern "C"
{

JNIEXPORT void JNICALL Java_org_cocos2dx_lib_Cocos2dxAlertView_onNegative(JNIEnv *env, jobject thiz) {
    auto resultCode = cocos2d::plugin::AlertView::EventType::NEGATIVE;
    cocos2d::Director::getInstance()->getEventDispatcher()->dispatchCustomEvent(cocos2d::plugin::AlertView::NOTIFICATION(), &resultCode); // cocos2d::plugin::AlertView::EventType::NEGATIVE);
}
JNIEXPORT void JNICALL Java_org_cocos2dx_lib_Cocos2dxAlertView_onNeutral(JNIEnv *env, jobject thiz) {
    auto resultCode = cocos2d::plugin::AlertView::EventType::NEUTRAL;
    cocos2d::Director::getInstance()->getEventDispatcher()->dispatchCustomEvent(cocos2d::plugin::AlertView::NOTIFICATION(), &resultCode); // cocos2d::plugin::AlertView::EventType::NEGATIVE);
}
JNIEXPORT void JNICALL Java_org_cocos2dx_lib_Cocos2dxAlertView_onPositive(JNIEnv *env, jobject thiz) {
    auto resultCode = cocos2d::plugin::AlertView::EventType::POSITIVE;
    cocos2d::Director::getInstance()->getEventDispatcher()->dispatchCustomEvent(cocos2d::plugin::AlertView::NOTIFICATION(), &resultCode); // cocos2d::plugin::AlertView::EventType::NEGATIVE);
}


}


namespace cocos2d { namespace plugin {

    AlertView::AlertView()
    {
    }

    AlertView::~AlertView() {
    }

    void AlertView::show() {
        cocos2d::JniMethodInfo t;
        if(cocos2d::JniHelper::getStaticMethodInfo(t, CLASS_NAME().c_str(), "show", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
            jstring title = t.env->NewStringUTF(this->getTitle().c_str());
            jstring message = t.env->NewStringUTF(this->getMessage().c_str());
            jstring negative = t.env->NewStringUTF(this->getNegative().c_str());
            jstring positive = t.env->NewStringUTF(this->getPositive().c_str());
            jstring neutral = t.env->NewStringUTF(this->getNeutral().c_str());
            t.env->CallStaticVoidMethod(t.classID, t.methodID, title, message, negative, positive, neutral);

            t.env->DeleteLocalRef(title);
            t.env->DeleteLocalRef(message);
            t.env->DeleteLocalRef(negative);
            t.env->DeleteLocalRef(positive);
            t.env->DeleteLocalRef(neutral);
            t.env->DeleteLocalRef(t.classID);
        }
    }


}}

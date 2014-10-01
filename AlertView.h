#ifndef __ALERT_VIEW_H__
#define __ALERT_VIEW_H__

#include "cocos2d.h"

namespace cocos2d { namespace plugin {

class AlertView : public cocos2d::Ref {
public:
    enum class EventType : int {
        NEGATIVE = 0,
        POSITIVE,
        NEUTRAL,
    };
    static constexpr char* CLASS_NAME = "org/cocos2dx/lib/Cocos2dxAlertView";
    static constexpr char* NOTIFICATION = "AlertViewNotification";

public:
    CREATE_FUNC(AlertView);
    CC_SYNTHESIZE(std::string, title, Title);
    CC_SYNTHESIZE(std::string, message, Message);
    CC_SYNTHESIZE(std::string, negative, Negative);
    CC_SYNTHESIZE(std::string, positive, Positive);
    CC_SYNTHESIZE(std::string, neutral, Neutral);
public:
    static AlertView* create(std::string title, std::string message) {
        auto obj = create();
        obj->setTitle(title);
        obj->setMessage(message);

        return obj;
    }

    AlertView();
    virtual ~AlertView();

    virtual bool init() {
        return true;
    }

    virtual void show();
};

}}

#endif

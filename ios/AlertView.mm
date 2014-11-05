#import "AlertView.h"

enum AlertViewEventType : int {
    UNKNOWN  = 1 << 0,
    POSITIVE = 1 << 1,
    NEUTRAL = 1 << 2,
    NEGATIVE  = 1 << 3,
};



@interface AlertViewIOS : NSObject
{
}

@property(nonatomic, retain)UIAlertView* alert;
@property(nonatomic) int eventType;

@end

@implementation AlertViewIOS


- (id)initWithTitle:(const std::string&) title: (const std::string&) message {
    self = [super init];
    do {
        if(self == nil) break;
        self.alert = [[UIAlertView alloc] init];
        self.eventType = AlertViewEventType::UNKNOWN;

        [self.alert setTitle:[NSString stringWithUTF8String:title.c_str()]];
        [self.alert setMessage:[NSString stringWithUTF8String:message.c_str()]];
        [self.alert setDelegate:self];
        
        return self;
    } while (0);
    
    return nil;
    
}

- (void)setNegative:(const std::string&) negative {
    if(negative.length() > 0) {
        self.eventType |= AlertViewEventType::NEGATIVE;
        [self.alert addButtonWithTitle:[NSString stringWithUTF8String:negative.c_str()]];
    }
}
- (void)setPositive:(const std::string&) positive {
    if(positive.length() > 0) {
        self.eventType |= AlertViewEventType::POSITIVE;
        [self.alert addButtonWithTitle:[NSString stringWithUTF8String:positive.c_str()]];
    }
}
- (void)setNeutral:(const std::string&) neutral {
    if(neutral.length() > 0) {
        self.eventType |= AlertViewEventType::NEUTRAL;
        [self.alert addButtonWithTitle:[NSString stringWithUTF8String:neutral.c_str()]];
    }
}


- (void)show {

    [self.alert show];
}


-(void)alertView:(UIAlertView*)alertView
clickedButtonAtIndex:(NSInteger)buttonIndex {
    //
    auto resultCode = cocos2d::plugin::AlertView::EventType::UNKOWN;

    if(buttonIndex == 0) {
        if((self.eventType & AlertViewEventType::POSITIVE) == AlertViewEventType::POSITIVE) {
            resultCode = cocos2d::plugin::AlertView::EventType::POSITIVE;
        } else if((self.eventType & AlertViewEventType::NEUTRAL) == AlertViewEventType::NEUTRAL) {
            resultCode = cocos2d::plugin::AlertView::EventType::NEUTRAL;
        } else if((self.eventType & AlertViewEventType::NEGATIVE) == AlertViewEventType::NEGATIVE) {
            resultCode = cocos2d::plugin::AlertView::EventType::NEGATIVE;
        }
            
    } else if(buttonIndex == 1) {
        if((self.eventType & AlertViewEventType::NEUTRAL) == AlertViewEventType::NEUTRAL) {
            resultCode = cocos2d::plugin::AlertView::EventType::NEUTRAL;
        } else if((self.eventType & AlertViewEventType::NEGATIVE) == AlertViewEventType::NEGATIVE) {
            resultCode = cocos2d::plugin::AlertView::EventType::NEGATIVE;
        }
    } else if(buttonIndex == 2) {
        if((self.eventType & AlertViewEventType::NEGATIVE) == AlertViewEventType::NEGATIVE) {
            resultCode = cocos2d::plugin::AlertView::EventType::NEGATIVE;
        }
    }
    cocos2d::Director::getInstance()->getEventDispatcher()->dispatchCustomEvent(cocos2d::plugin::AlertView::NOTIFICATION(),&resultCode);

}


@end


namespace cocos2d { namespace plugin {
    
    AlertView::AlertView()
    {
    }
    
    AlertView::~AlertView() {
    }
    
    void AlertView::show() {
        id alert = [[AlertViewIOS alloc] initWithTitle:this->getTitle() :this->getMessage()];
        [alert setPositive:this->getPositive()];
        [alert setNeutral:this->getNeutral()];
        [alert setNegative:this->getNegative()];
        [alert show]; // :[NSString stringWithUTF8String:this->getTitle().c_str()] :[NSString stringWithUTF8String:this->getMessage().c_str()] :[NSString stringWithUTF8String:this->getNegative().c_str()] :[NSString stringWithUTF8String:this->getPositive().c_str()] :[NSString stringWithUTF8String:this->getNeutral().c_str()]];
    }
    
    
}}

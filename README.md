# MyBinderUtils
        intent传值自动注入注解框架。
        
        
        集成方法:
        
          首先在app项目module的gradle中添加:
            dependencies{
            
                implementation 'com.github.SteinsGateZero.MyBinderUtils:CoolBinder:v0.0.2'
            
                annotationProcessor 'com.github.SteinsGateZero.MyBinderUtils:CoolBinderProcessor:v0.0.2'
            
            }
            
          之后在project的gradle中添加:
           allprojects {
                repositories {
                
                maven { url 'https://jitpack.io' }
            
                }
           }
            
            
       使用方法:
          在Acitivity中绑定相应对象如下:
            @IntentKey("key1")
            String name;
            @IntentKey("key2")
            int name2;
            @IntentKey("keyinfo")
            TestInfo info;
            @IntentKey("keyinfo2")
            TestInfo2 info2;
            之后在oncreate方法中添加注入:IntentBinder.inject(this);
            
          在fragment中绑定相应对象如下:
            //注意intentkey显示的声明为TYPE_FRAGMENT类型
            @IntentKey(value = "keyinfo3", intentType = IntentKey.TYPE_FRAGMENT)
            TestInfo info;
            之后在相应方法中添加注入:IntentBinder.inject(this);

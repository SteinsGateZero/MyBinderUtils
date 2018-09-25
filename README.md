# MyBinderUtils
        intent传值自动注入注解框架。
        
        
        集成方法:
        
          首先在app项目module的gradle中添加:
            dependencies{
            
                implementation 'com.github.SteinsGateZero.MyBinderUtils:CoolBinder:v0.0.1'
            
                annotationProcessor 'com.github.SteinsGateZero.MyBinderUtils:CoolBinderProcessor:v.0.0.1'
            
            }
            
          之后在project的gradle中添加:
            repositories {
            
                maven { url 'https://jitpack.io' }
  
            }
            
            
       使用方法:
          在intent接收处添加如下注解即可
            @IntentKey("key1")
            String name;
            @IntentKey("key2")
            int name2;
            @IntentKey("keyinfo")
            TestInfo info;
            @IntentKey("keyinfo2")
            TestInfo2 info2;
            

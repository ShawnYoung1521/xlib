![Log](https://github.com/itemuse/XLib/blob/main/png/xylibrary.png)

## 引用方式
### 一
```
allprojects {
      repositories {
      ...
      maven { url 'https://jitpack.io' }
      }
}
```
### 二
```
dependencies {
      ...
      implementation 'com.github.itemuse:XLib:latest.release'
}
```

### 初始化 Application中init
```
import cn.xy.library.XApp;
      ...
@Override
public void onCreate() {
      super.onCreate();
      XApp.init(this);
}
```

### HRCouncil: 
[Blankj](http://blankj.com)

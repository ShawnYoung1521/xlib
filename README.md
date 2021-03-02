![Log](https://github.com/itemuse/XLib/blob/master/png/xylibrary.png)
       
## 引用方式
### TAG版本
[![](https://jitpack.io/v/itemuse/XLib.svg)](https://jitpack.io/#itemuse/XLib)

### 一.将JitPack存储库添加到您的构建文件中
将其添加到存储库末尾的root build.gradle中：
```
allprojects {
      repositories {
      ...
      maven { url 'https://jitpack.io' }
      }
}
```
### 二.添加依赖项
```
dependencies {
      ...
      implementation 'com.github.itemuse:xlib:Tag'
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

### Predecessors trees: 
[Blankj](http://blankj.com)

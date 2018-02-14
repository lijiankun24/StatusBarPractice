# StatusBarPractice
状态栏及导航栏相关知识小结

在开发应用的过程中，会遇见一些状态栏和导航栏相关的问题，在此记录一下。本文主要目的是记录我在开发中遇到的状态栏和导航栏相关的问题，以便日后查看方便，如果可以帮到你，真是再好不过了。

本文主要分为以下几个部分：

<div align=center>
      <img src="/app_screenshot/catalog.png" width="664" height="361"/>
</div>

<!-- more -->
## 1. 基本概念
首先需要明白几个概念：
* 状态栏（StatusBar）：指屏幕最顶端，显示时间、电量、推送图标那一栏，每个手机都有状态栏。
* 标题栏（TitleBar）：指状态栏下，显示“返回键”、“标题文字”那一栏，根据需求而定是否有标题栏，标题栏可以使用 `Toolbar` 或者 `ActionBar` 控件实现
* 导航栏（NavigationBar)：指屏幕最下端，有“返回键”、“Home键”、”菜单键“那一栏。导航栏是虚拟的，不是每个手机都有的。
状态栏、标题栏、导航栏可以参照下图：
<div align=center>
      <img src="/app_screenshot/statusbar&navigationbar.png" width="240" height="427"/>
</div>

* 沉浸式模式（Immersive Mode）：沉浸式就是要给用户提供完全沉浸的体验，使用户有一种置身于虚拟世界之中的感觉。  ----  [Android状态栏微技巧，带你真正理解沉浸式模式](http://blog.csdn.net/guolin_blog/article/details/51763825)

  沉浸式模式本质上就是把状态栏、导航栏隐藏，将应用界面全屏化。

  最常见的沉浸式模式应用在游戏和视频类应用中，比如爱奇艺的全屏播放，如下图所示：
  <div align=center>
        <img src="/app_screenshot/aiqiyi.png" width="427" height="240"/>
  </div>

* 透明状态栏：如下图所示，透明状态栏就是让应用界面背景利用系统状态栏空间，让应用界面背景和系统状态栏融为一体。

<div align=center>
      <img src="/app_screenshot/transparentstatusbar.png" width="240" height="427"/>
</div>

> 需要注意的是，并**没有沉浸式状态栏**这一概念，只有**沉浸式模式**和**透明状态栏**的概念。

## 2. 实践效果
### 2.1 淡化状态栏和导航栏
在 **Android 4.0（API level 14）及之后** 的版本中，可以实现使状态栏和导航栏淡化的效果（Dimming the System Bars）

效果：
<div align=center>
    <img src="/app_screenshot/dimming.gif" width="300" height="400"/>
</div>

> 注意观察状态栏和导航栏的变化。

代码：
``` java
/**
 * 淡化状态栏和导航栏
 */
private fun dimmingStatusBar() {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
        // This example uses decor view, but you can use any visible view.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
    }
}
```

说明：
* 此 `SYSTEM_UI_FLAG_LOW_PROFILE` 标志并不会重新分配界面中 UI 的大小，只是 StatusBar 和 NavigationBar 的相关图标会被弱化
* 一旦用户触摸 StatusBar 和 NavigationBar 相关区域，系统便清除掉了 `SYSTEM_UI_FLAG_LOW_PROFILE` 标志
* 此 `SYSTEM_UI_FLAG_LOW_PROFILE` 标志只可以在 **Android 4.0（API level 14）** 及之后的版本中使用
* 可以使用代码手动清除 `SYSTEM_UI_FLAG_LOW_PROFILE` 标志，代码如下所示：
``` java
private fun clearSystemUIFlag() {
    window.decorView.systemUiVisibility = 0
}
```

### 2.2 隐藏状态栏
隐藏状态栏分为两种情况：
* 在 Android 4.0(API level 14)  及之下的版本中隐藏状态栏
* 在 Android 4.0(API level 14)  之上的版本中隐藏状态栏

#### 2.2.1 在 Android 4.0 及之下
在 Android 4.0 及之下的版本中，可以通过设置 `WindowManager` 的 flag 实现隐藏状态栏的效果，设置 `WindowManager` 的 flag 有两种方式：

代码：
* 通过编写代码设置 `WindowManager` 的 flag
``` java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);
    }
    ...
}
```

* 通过设置 `AndroidManifest.xml` 中 Activity 的 `theme` 设置  `WindowManager` 的 flag

  ```xml  
  <!-- style.xml -->
  <resources>

    <!-- Base application theme. -->
    <style name="BaseAppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

    <style name="FullScreenTheme" parent="BaseAppTheme">
        <item name="android:windowFullscreen">true</item>
    </style>

    ...

  </resources>
  ```

  ```xml
  <!-- AndroidManifest.xml -->
  <?xml version="1.0" encoding="utf-8"?>
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.lijiankun24.statusbarpractice">

    <application
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/BaseAppTheme"
        ... >

        <activity
            android:name=".activity.MainActivity"
            android:theme="@style/FullScreenTheme">
        </activity>

        ...
    </application>

  </manifest>
  ```

说明：
  * 通过设置 Activity 的 Theme实现隐藏状态栏有如下优点：
    * 简单而且不易出错
    * UI 切换更流畅，因为系统在实例化 Activity 对象之前就已经获得了渲染 UI 界面的相关信息
  * 通过编写代码设置 `WindowManager` 的 flag 的方式更容易控制系统 UI 的显示和隐藏
  * 现在市场上 Android 4.0 之下的手机已经很少了，而且很多应用最低版本都在 Android 4.0 之上，所以这点可以依情况而定


#### 2.2.2 在 Android 4.0 之上

效果：
<div align=center>
    <img src="/app_screenshot/hidestatusbar.gif" width="300" height="400"/>
</div>

代码：
``` java
private fun hideStatusBar() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}
```

说明：
* 如果只使用 `SYSTEM_UI_FLAG_FULLSCREEN` 标志，在状态栏区域向下滑动出现状态栏的时候，内容区域会出现一个挤压的效果，如下图所示：
<div align=center>
    <img src="/app_screenshot/hidestatusbar1.gif" width="300" height="400"/>
</div>

* 可以使用 `SYSTEM_UI_FLAG_LAYOUT_FULLSCREE` 标志，让应用的内容区域显示在状态栏的后面，还可以配合 `SYSTEM_UI_FLAG_LAYOUT_STABLE` 标志使用，让布局保持稳定
* 一旦这些标志位被清除，则需要重新设置让状态栏隐藏，可以通过监听状态栏和导航栏的可见性，判断状态栏和导航栏是否可见
* 在不同的位置设置 UI flag 是有区别的。比如，如果在 `onCreate()` 方法中隐藏状态栏，那当用户按下 `Home` 键的时候，状态栏重新显示，再打开应用重新回到这个 Activity 的时候，用户可以看到状态栏，因为这时不会调用 `onCreate()` 方法。如果在 `onResume()` 或者 `onWindowFocusChanged()` 就可以避免上面这种情况
* 只有当调用 `setSystemUiVisibility()` 的 `View` 是可见的时候，`setSystemUiVisibility()` 方法才会起作用
* 界面的切换会导致 `setSystemUiVisibility()` 的设置失效
* 如果设置内容区域在状态栏的背后，那当状态栏显示的时候会遮挡住一部分内容区域，为防止这种情况发生，只需要在布局文件中添加 `android:fitsSystemWindows` 属性（值为true）,就可以解决这种问题，效果如下图所示：
<div align=center>
    <img src="/app_screenshot/fitsSystemWindows2.png" width="600" height="582"/>
</div>

  代码如下所示：
  ``` xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.lijiankun24.statusbarpractice.activity.FitsSystemWindowsActivity">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/holo_blue_light"
        android:fitsSystemWindows="true"
        android:text="@string/app_name"
        android:textColor="@android:color/white"
        android:textSize="28sp"/>
  </android.support.constraint.ConstraintLayout>
  ```

### 2.3 隐藏导航栏
效果：
<div align=center>
    <img src="/app_screenshot/hidenavigationbar.gif" width="300" height="400"/>
</div>

代码：
``` java
class HideNavigationbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavigationBar()
        setContentView(R.layout.activity_hide_navigationbar)
    }

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}
```

说明：
* 按照一个通用的规则，在隐藏导航栏的时候，一般也需要隐藏状态栏
* 通过这种方式隐藏导航栏和状态栏之后，触摸屏幕的任何区域，导航栏和状态栏都会重新出现且不会再消失，如果想让导航栏和状态栏消失，则需要手动重新设置 UI flag
* 如果想让内容区域出现在导航栏的后面，则需要配合使用 `SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION` 标志，并且最好配合使用 `SYSTEM_UI_FLAG_LAYOUT_STABLE` 使布局保持稳定
* `SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION` 和 `SYSTEM_UI_FLAG_HIDE_NAVIGATION` 可以在 Android 4.1 （API level 15）使用

### 2.4 沉浸式模式
效果：

`SYSTEM_UI_FLAG_IMMERSIVE` :
<div align=center>
    <img src="/app_screenshot/immersive.gif" width="300" height="400"/>
</div>

`SYSTEM_UI_FLAG_IMMERSIVE_STICKY` :
<div align=center>
    <img src="/app_screenshot/immersive1.gif" width="300" height="400"/>
</div>

代码：
``` java
class ImmersiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.activity_immersive)
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_IMMERSIVE
    }
}
```

说明：
* 真正的沉浸式模式如上面两个图所示，Activity 可以接收到所有的触摸事件
* 当使用 `SYSTEM_UI_FLAG_IMMERSIVE` 标志时，用户滑动状态栏和导航栏边缘的时候，状态栏和导航栏会出现，并且不会再消失，
* 当使用 `SYSTEM_UI_FLAG_IMMERSIVE_STICKY` 标志时，用户滑动状态栏和导航栏边缘的时候，状态栏和导航栏会出现，但是和 `SYSTEM_UI_FLAG_IMMERSIVE` 不同的是，过一会儿状态栏和导航栏会自动消失
* 如果设置了 `View.OnSystemUiVisibilityChangeListener` 监听器，`SYSTEM_UI_FLAG_IMMERSIVE` 会触发 `OnSystemUiVisibilityChangeListener` 监听器，但是 `SYSTEM_UI_FLAG_IMMERSIVE_STICKY` 不会触发 `OnSystemUiVisibilityChangeListener` 监听器

### 2.5 监听状态栏和导航栏可见性
可以通过 `View.OnSystemUiVisibilityChangeListener` 为该 `View` 设置状态栏和导航栏可见性的监听器，代码如下所示：
``` java
class RespondingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        respondingSystemUI()
        setContentView(R.layout.activity_responding)
        window.decorView.setOnSystemUiVisibilityChangeListener {
            L.i("visibility is " + it)
        }
    }

    private fun respondingSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE
    }
}
```

### 2.6 透明状态栏和导航栏
效果：
<div align=center>
    <img src="/app_screenshot/transparent.gif" width="300" height="400"/>
</div>

代码:
``` java
class TransparentNavigationbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentingNavigationbar()
        setContentView(R.layout.activity_transparent_navigationbar)
    }

    private fun transparentingNavigationbar() {
        var uiFlag = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or uiFlag
        }
        window.decorView.systemUiVisibility = uiFlag
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }
}
```

说明：
* 除了可以设置状态栏和导航栏为透明，还可以设置为其他颜色
* 只可以在 Android 5.0（API level 21）及以上的 Android 版本中设置状态栏和导航栏的颜色

## 3 总结
本文主要介绍了状态栏、导航栏、标题栏、沉浸式模式和透明状态栏的概念，以及实现隐藏状态栏、隐藏导航栏、沉浸式模式和透明状态栏的方式。

### 3.1 版本要求
实现的主要方式是通过设置 `window.decorView.systemUiVisibility` 的属性值实现的，但是不是所有的 Android 版本都可以实现上述那些效果的，实现上述效果的版本要求如下图所示：

|     效果    | 版本要求    |
| :---------:| :--------: |
| 淡化状态栏和导航栏 | Version >= 14 |
| 隐藏状态栏 | 全部版本 |
| 隐藏导航栏 | Version >= 16 |
| 沉浸式模式 | Version >= 19 |
| 透明状态栏 | Version >= 21 |
### 3.2 相关文章
本文中涉及到的代码在 Github 上面  [StatusBarPractice](https://github.com/lijiankun24/StatusBarPractice)

本文中只涉及到我在实现开屏广告全屏过程中遇到的一些问题，但是还有其他很多相关的问题没有涉及到，下面有一些相关文章，讲的都很详细：

[随手记Android沉浸式状态栏的踩坑之路](https://juejin.im/post/5a25f6146fb9a0452405ad5b)  ----  刘玲

[管理System UI (状态栏 + 导航栏)](https://www.jianshu.com/p/e27e7f09d1f7)  ----  [ShenJC](https://www.jianshu.com/u/5a578d8312b1)

[Android 系统状态栏沉浸式/透明化完整解决方案](https://www.jianshu.com/p/34a8b40b9308)  ----  [btman](https://www.jianshu.com/u/2656a3d4ac55)

[Android 沉浸式 (透明) 状态栏适配](https://www.jianshu.com/p/a44c119d6ef7)  ----  [xiaoyanger](https://www.jianshu.com/u/25c3b13f87ce)

[Android状态栏微技巧，带你真正理解沉浸式模式](http://blog.csdn.net/guolin_blog/article/details/51763825)  ---- 郭霖

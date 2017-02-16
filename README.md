# AndroidViews
Some views that are handy in an Android app.

# Proguard
Library includes a Proguard file to configure AppCompat and Android Design Support Library. No need to include any rules specifically to your project.

# For BaseToolbarActivity, you are told to override an abstract method to get a Toolbar layout. 

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/toolbar_foo"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:minHeight="?attr/actionBarSize"
    android:background="@color/color_of_toolbar_here"/>
```

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

# Install the modules:

Handy activities you can extend to work with fragments or toolbars super easy.

Warning: I hope to remove these classes eventually. I don't want to have Fragments or Activities you have to extend. Use extensions instead if you need to add functionality to fragments/activities.

```
compile 'com.curiosityio.androidviews:activities:commitOrTagHere'
```

Snackbar copy that you can put into your view hierarchy and have more control over when it appears. This is useful in the rare case that you have a view that refreshes a lot and your traditional Snackbar is attached to it.
```
compile 'com.curiosityio.androidviews:copycatsnackbar:commitOrTagHere'
```

GridView where it doesn't scroll. It's the full expanded height of all the cells inside of them. This is handy if you need to nest a GridView inside of something such as a RecyclerView row.
```
compile 'com.curiosityio.androidviews:expandedheightgridview:commitOrTagHere'
```

Views/ViewGroups used to show generic loading and empty views.
```
compile 'com.curiosityio.androidviews:loadingemptyviews:commitOrTagHere'
```
FloatingView [(Application Demo on Play Store)] (https://play.google.com/store/apps/details?id=fr.anthonyfernandez.floatingmenu)
====================

DEPRECATED SEE [FloatingView](https://github.com/recruit-lifestyle/FloatingView)

====================

Floating View for Android app - Facebook ChatHeads Notification system
This is a demo of how works Facebook ChatHeads. 

Basiclay all you need to do is to create a service (background running) with image View like this : 

```Android
windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    chatHead = new ImageView(this);
  	chatHead.setImageResource(R.drawable.floating2);

		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0;
		params.y = 100;
```

Then start your service : 
```Android
    startService(new Intent(MainActivity.this, ServiceFloating.class));
```

If you wanna have a floating window, you can use PopupWindow : 
```Android
      LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
  		View popupView = layoutInflater.inflate(R.layout.popup, null);  
			pwindo = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT);  
			if(_enable == true) {
			pwindo.showAsDropDown(chatHead, 50, -30);
```

![Floating1](http://185.14.185.122/github/float3.png)
![Floating2](http://185.14.185.122/github/float2.png)

```
This work is under the MIT License (MIT)

Copyright (c) 2013 Fernandez```

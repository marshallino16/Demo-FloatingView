FloatingNotification [(Application Demo on Play Store)] (https://play.google.com/store/apps/details?id=fr.anthonyfernandez.floatingmenu)
====================

Floating Notification for Android app - Facebook ChatHeads Notification system
This is an example of how works Facebook ChatHeads. 

What can I do with that : 

- Get list of all your installed packages
- Launch apps from the list
- Change icon of launcher
- Color and alpha picker to customise your icon

You have to create a service (background running) with image View like this : 

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

![Floating1](http://185.14.185.122/github/float1.png)
![Floating1](http://185.14.185.122/github/float3.png)
![Floating2](http://185.14.185.122/github/float2.png)

```
This work is under the MIT License (MIT)

Copyright (c) 2013 Fernandez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.```

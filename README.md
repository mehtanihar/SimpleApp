This repository contains code for both the application as well as the server for SimpleApp done as a part of the course CS 8803 Mobile Applications and Services (MAS) first programming assignment.

It contains:
1. Server
This is the code for the Django server which is hosted on Heroku.

2. Application code (SimpleApp)
This is the code for the Android app (SimpleApp) in Java. It uses MVP Framework for handling of user inputs and changes in views and REST API to make calls to the server.

A. Instructions for the application code:
To run the code, simply download the app-release.apk and install it in a device
Location of the apk: https://github.com/mehtanihar/SimpleApp/tree/master/SimpleApp/app-release.apk

To build the code, clone the repository Start Android Studio. Build the code and Test run it on a device. 

B. Instructions for server code:
To build and test the server code on Localhost, navigate to server directory and run the following commands:

```
python manage.py makemigrations
python manage.py migrate
python manage.py runserver
```

This will start a demo server on Localhost http://127.0.0.1/api/v1/users./login
The original server is running on https://simpleappdjango.herokuapp.com/api/v1/users/login

Here are the tutorial videos for the application:
https://drive.google.com/file/d/1005_Gh_lIeHj2TtDXT6sgLMSJBNWxX4E/view?usp=sharing
https://drive.google.com/file/d/105-ZjZjeAKVT2wAxuDxpojw21io0DYF0/view?usp=sharing

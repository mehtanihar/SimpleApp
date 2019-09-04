from django.urls import path

from . import views

urlpatterns = [
    path('register', views.register),
    path('login', views.trylogin),
    path('logout', views.dologout),
    path('<uid>/', views.UserProfileView.as_view()),
]
import json
from django.http import JsonResponse
from django.contrib.auth import authenticate, logout, login
from django.core.exceptions import PermissionDenied, ObjectDoesNotExist

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework import permissions
from rest_framework.decorators import api_view, permission_classes
from rest_framework import generics

from . import serializers
from .models import User
from common import responses

RESTRICTED_USERNAMES = ['login', 'register', 'logout', 'admin']


@api_view(['POST'])
def trylogin(request):
    try:
        data = json.loads(request.body.decode('utf-8'))
        email = data['email']
        password = data['password']
        user = authenticate(email=email, password=password)
        if user is not None:
            login(request, user)
            return responses.response_ok()
        logout(request)
        return JsonResponse({'success': False, 'message': 'Invalid username/password'}, status=401)
    except PermissionDenied:
        return JsonResponse({'success': False, 'message': 'Invalid username/password'}, status=401)


class UserProfileView(APIView):
    permission_classes = (permissions.IsAuthenticated,)

    def dispatch(self, request, *args, **kwargs):
        if not request.method == "GET":
            if not request.user.username == kwargs['username']:
                return responses.base_response(False, 'cannot edit or delete other users', 401)
        return super(UserProfileView, self).dispatch(request, *args, **kwargs)

    def get(self, request, uid):
        serializer = serializers.UserSerializer(request.user)
        return Response(serializer.data)

    def put(self, request, uid):
        serializer = serializers.UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, uid):
        pass

    def delete(self, request, uid):
        request.user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST'])
@permission_classes((permissions.IsAuthenticated,))
def dologout(request):
    logout(request)
    return responses.response_ok()


@api_view(['POST'])
def register(request):
    try:
        data = json.loads(request.body.decode('utf-8'))
        for name in RESTRICTED_USERNAMES:
            if data['email'].startswith(name):
                return responses.base_response(False, 'Invalid email', 401)
        if User.objects.filter(email=data['email']).exists():
            return responses.base_response(False, 'email already registered', 401)
        if User.objects.filter(username=data['username']).exists():
            return responses.base_response(False, 'username already taken', 401)
        if data['password1'] != data['password2']:
            return responses.base_response(False, "Passwords don't match", 401)
        user = User()
        user.email = data['email']
        user.username = data['username']
        user.set_password(data['password1'])
        user.save()
    except Exception:
        try:
            user.delete()
        except Exception:
            pass
        return responses.bad_request()
    return responses.response_ok("User Created")


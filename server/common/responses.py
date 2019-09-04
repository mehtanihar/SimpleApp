from django.http import JsonResponse, Http404


def login_required():
    return JsonResponse({
        'success': False,
        'message': 'Login Required'
    }, status=401)


def unauthorized(message='Unauthorized'):
    return JsonResponse({
        'success': False,
        'message': message
    }, status=401)


def bad_request(message='Bad request'):
    return JsonResponse({
        'success': False,
        'message': message
    }, status=400)


def response_ok(message='OK'):
    return JsonResponse({
        'success': True,
        'message': message
    }, status=200)


def not_found(what):
    return JsonResponse({
        'success': False,
        'message': what + ' not found'
    }, status=404)


def ok_data(data, message=""):
    return JsonResponse({
        'success': True,
        'message': message,
        'data'   : data
    }, status=200)


def base_response(sucess, message, status):
    return JsonResponse({
        'success': sucess,
        'message': message
    }, status=status)

from django.contrib import admin
from django.urls import path
from apis import views


urlpatterns = [
    path('admin/', admin.site.urls),
    path('get_question/', views.QuestionList.as_view(), name='QuestionList'),
    path('get_members/', views.MemberList.as_view(), name='MemberList'),
]

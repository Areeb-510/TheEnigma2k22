from django.shortcuts import render
from apis.models import Question, ExeMember
from apis.serializers import QuestionSerializer,ExeMemberSerializer
from django.http import Http404
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status


class QuestionList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request, format=None):
        questions = Question.objects.all()
        serializer = QuestionSerializer(questions, many=True)
        return Response(serializer.data)

    # def post(self, request, format=None):
    #     questions = QuestionSerializer(data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response(serializer.data, status=status.HTTP_201_CREATED)
    #     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class MemberList(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request, format=None):
        models = ExeMember.objects.all()
        serializer = ExeMemberSerializer(models, many=True)
        return Response(serializer.data)

   


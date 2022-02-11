from rest_framework import serializers
from apis.models import Question, ExeMember, ExeGallery


class QuestionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Question
        fields = ['question_name', 'question_number', 'description', 'gltf_model_name']


class ExeMemberSerializer(serializers.ModelSerializer):
    class Meta:
        model = ExeMember
        fields = ['name', 'position', 'image', 'githubUrl','linkedInUrl']

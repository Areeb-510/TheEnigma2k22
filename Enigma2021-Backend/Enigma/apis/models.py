from django.db import models

from django.core.validators import MinLengthValidator

# Create your models here.

class LeaderBoard(models.Model):

    ### LeaderBoards Here ###

    player_name = models.CharField(max_length=100)
    score = models.IntegerField()

    def __str__(self):
        return self.player_name

    class Meta:
        verbose_name_plural = "LeaderBoard"


class Question(models.Model):

    ### Question details here ###

    question_name = models.CharField(max_length=100,null=False, blank=False)
    question_number = models.IntegerField(null=False, blank=False)
    description = models.TextField(max_length=1000,null=False, blank=False)
    gltf_model_name = models.CharField(max_length=50,null=False, blank=False,default="None")

    def __str__(self):
        return self.question_name



class ExeMember(models.Model):

    ### Exe Members details here ###


    position_choices = (
        ('Developer', 'Developer'),
        ('Mentor', 'Mentor'),
        ('Alumni', 'Alumni'),
        ('Final year', 'Final year'),
        ('Pre-Final year', 'Pre-Final year'),
        ('Coordinator', 'Coordinator'),
        ('Executive', 'Executive'),
        ('Volunteer', 'Volunteer')
    )
    name = models.CharField(max_length=255, validators=[
        MinLengthValidator(limit_value=3, message="Name Length Should be greater than 3 characters.")])
    position = models.CharField(max_length=255, choices=position_choices)
    image = models.URLField(max_length=255, null=False, blank=False)
    githubUrl = models.URLField(max_length=255, null=True, blank=True)
    linkedInUrl = models.URLField(max_length=255, null=True, blank=True)

    def __str__(self):
        return self.name


class ExeGallery(models.Model):
    
    ### Model for To store links of Exe Images and Videos ###
    
    choices = (
        ('Video', 'Video'),
        ('Image', 'Image'),
    )
    url = models.CharField(max_length=256)
    type = models.CharField(max_length=256, choices=choices)


    def __str__(self):
        return self.type

    class Meta:
        verbose_name_plural = "Exe Gallery"
from django.contrib import admin
from apis.models import Question,ExeMember,ExeGallery,LeaderBoard

# Register your models here.

admin.site.register([
    Question,
    ExeMember,
    ExeGallery,
    LeaderBoard,
])

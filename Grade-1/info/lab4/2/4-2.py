import re
txt=""
list1=[]
with open("C:\\Users\\User\\Desktop\\信息\\lab4\\2\\2.txt","r",encoding="utf-8") as file1:
    f=file1.read()
    regex=re.compile("(((0|1)?[0-9])|(2[0-4]))(:[0-5][0-59]){1,2}")
    txt=re.sub(regex,"(TBD)",f)
print(txt)

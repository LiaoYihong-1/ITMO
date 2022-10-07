import re
txt=""
list1=[]
x=0
with open("C:\\Users\\User\\Desktop\\信息\\lab4\\5\\5.txt","r",encoding="utf-8") as file1:
    f=file1.read()
    list1=re.findall("[А-Я]{2,}",f)
    list1.sort(key=lambda ele:len(ele))
    for each in list1:
        print(each)

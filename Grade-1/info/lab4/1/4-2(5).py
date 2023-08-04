import re
with open("C:\\Users\\User\\Desktop\\信息\\lab4\\1\\1.txt","r",encoding="utf-8") as file1:
    f=file1.read()
    list1=re.split("\W",f)
    list2=[]
    #print(list1)
    for each in list1:
        if each=='офис'or each=='занятия' or each=="Основы":
            list2.append(each)
            list2.sort(key=lambda ele:len(ele))
    for i in list2:
        print(i)

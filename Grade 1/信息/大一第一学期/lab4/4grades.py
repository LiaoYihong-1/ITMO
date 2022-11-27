import re
list1=[]
list2=[]
list3=[]
list4=[]
list5=[]
str1=""
with open ("C:\\Users\\User\\Desktop\\信息\\lab4\\Macbeth.txt","r",encoding="utf-8") as g:
    f=g.readlines()
    for eachline in f:
        regex=re.compile('^\s*.*\?\s*$')
        if (re.match(regex,eachline)):
            list1.append(eachline)
    print(list1)
    for each in list1:
        list2.append(re.sub("[A-Z]{2,}.","",each))
    for each in list2:
        list3.append(re.sub("\\n","",each))
    #print(list2)
    for each in list3:
        list4.append(re.sub("\s\s+","",each))
    #print(list3)
    for each in list4:
        list5.append(re.split("\s",each))
    #print(list4)
    for each in list5:
        if len(each)<6:
            for eachword in each:
                str1=str1+eachword+" "
        print(str1)
        str1=""


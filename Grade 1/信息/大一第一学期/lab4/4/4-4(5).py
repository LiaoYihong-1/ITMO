import re
txt=""
list1=[]
with open("C:\\Users\\User\\Desktop\\信息\\lab4\\4\\4.txt","r",encoding="utf-8") as file1:
    f=file1.read()
    regex=r"(0|1)?[0-9]：\d{2}"
    #rege=re.compile("[\u4e00-\u9fa5]*")
    list1=re.findall("北京市",f)
    list2=re.findall("大",f)
    list3=re.findall("公庄大街",f)
    list4=list3+list2+list1
    list4.sort(key = lambda ele:len(ele))
    for each in list4:
        print(each)

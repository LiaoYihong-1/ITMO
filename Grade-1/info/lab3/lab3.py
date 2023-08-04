import re
with open ('C:\\Users\\User\\Desktop\\信息\\lab 3\\lab3.json','r',encoding='utf-8') as l:
    json=l.read()
    jlist=re.split("\n",json)
control=6
con=1
a=[]
regex="\d\d:\d\d-\d\d:\d\d"
for each in jlist:
    if each.find("\":\"")!=-1:
        str1=re.sub("\s{2,}","",each)
        str2=re.sub(",","",str1)
        a.append(re.sub("\"","",str2))

with open ('C:\\Users\\User\\Desktop\\信息\\lab 3\\lab3.xml','w',encoding=('utf-8')) as x:
    x.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
    str5 = "<P3111>\n"
    str6 = "</P3111>\n"
    indent="  "
    x.write("<Расписание>\n")
    for each in a:
        store=re.findall(regex,each)
        each=re.sub(regex,"00000",each)
        b=re.split(":",each)
        if (store!=[]):
            b[1]=re.sub("00000",store[0],b[1])
        if control%6==0:
            x.write(indent)
            x.write(str5)
        str3 = b[0]
        str4 = b[1]
        str7 = "<"+b[0]+">"+b[1]+"</"+b[0]+">\n"
        x.write(indent)
        x.write(indent)
        x.write(str7)
        if con%6==0:
            x.write(indent)
            x.write(str6)
        con=con+1
        control=control+1
    x.write("</Расписание>")

import re
with open("C:\\Users\\User\\Desktop\\信息\\lab4\\3\\3.txt","r",encoding="utf-8") as file1:
    f=file1.read()
    regex="(((0|1)[0-9])|(2[0-4]))(:[0-5][0-59]){1,2}"#(((0|1)[0-9])|(2[0-4]))для часов и (2[0-4]))(:[0-5][0-59]){1,2}для минуты и секунчки.
    ff=f.split(" ")
    string=""
    for i in ff:
        x=str(i)
        strfinal=""
        if re.match(regex,x):
            x="(TBD)"
        print(x)
        string=string+x+" "
    print(string)

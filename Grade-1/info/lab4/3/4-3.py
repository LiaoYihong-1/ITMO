import re
with open("C:\\Users\\User\\Desktop\\信息\\lab4\\3\\3.txt","r",encoding="utf-8") as file1:
    f=file1.read()
    print(f)
    regex="(0|1)[0-9]:\d{2}(:\d{2}){0,1}"
    ff=f.split(" ")
    string=""
    for i in ff:
        x=str(i)
        if re.match(regex,x):
            x="(TBD)"
        string=string+x+" "
    print(string)

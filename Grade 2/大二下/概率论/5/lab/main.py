import math

import matplotlib.pyplot as plt
import numpy as np
sum = 0
average = 0
all_num = [32,105,48,80,144,128,64,112,18,81,
           66,129,113,17,94,78,90,51,104,34,
           110,149,36,103,82,53,93,130,68,150,
           114,84,55,131,70,38,102,77,16,135,
           41,19,142,61,85,159,115,57,72,101,
           56,100,86,146,73,40,141,25,87,126,
           151,71,94,15,125,76,54,99,39,140,
           17,124,52,98,139,37,147,88,69,109,
           35,158,67,30,93,123,50,138,21,97,
           96,121,49,137,89,145,91,65,92,33]
N = len(all_num)
all_num.sort()
dictionary = {}
sorted_string = "[ "
for i in all_num:
    dictionary[i] = 0
for i in all_num:
    if i in dictionary:
        a = dictionary[i]
        a = a + 1
        dictionary[i] = a
    else:
        dictionary[i] = 1
for i in sorted(dictionary):
    sum = sum + i
    dictionary[i] = dictionary[i]/20
    sorted_string = sorted_string + str(i) + " "
average = sum/20
sorted_string = sorted_string + "]"
print("Вариационный ряд:")
print(sorted_string)
print("Максимальное значение:")
print(all_num[19])
print("Минимальное значение:")
print(all_num[0])
print("Размах выборки:")
gap = all_num[19] - all_num[0]
print(gap)
print("Таблица частот:")
for i in sorted(dictionary):
    print("|%.2f" % i, end="")
print("|")
frequences = []
for i in sorted(dictionary):
    frequence = dictionary[i]
    frequences.append(frequence)
    if i >= 0:
        print("|%.2f" % frequence, end="")
    else:
        print("|%.3f" % frequence, end="")
print("|")
print("Математическое ожидание:")
math_exp = 0
for i in dictionary:
    math_exp = math_exp + i*dictionary[i]
print(math_exp)
print("Среднеквадратическое отклонение:")
standard_deviation = 0
for i in dictionary:
    standard_deviation = standard_deviation + (i-average)**2*dictionary[i]
fixed = N*standard_deviation/(N-1)
fixed = fixed**0.5
standard_deviation = standard_deviation**0.5
print("%.4f" % standard_deviation)
print("Исправленныое среднеквадратическое отклонение:")
print("%.4f" % fixed)
print("Эмпирическая функция распределения:")
sum_frequence = 0
count = 0
while count < len(dictionary):
    if count == 0:
        print(" F(x)=%.2f, x<=%.2f"%(sum_frequence,all_num[count]))
        sum_frequence = sum_frequence + frequences[count]
        count = count + 1
    else:
        print(" F(x)=%.2f, %.2f<x<=%.2f"%(sum_frequence,all_num[count-1],all_num[count]))
        sum_frequence = sum_frequence + frequences[count]
        count=count+1
        if count == len(dictionary):
            print(" F(x)=%.2f, x>%.2f" % (sum_frequence, all_num[count-1]))
print("Какую графику в хотите?:")
print("1)Гистограмму и полигон приведенных частот группированной выборки")
print("2)Графику эмпирической функции распределения")
## dived into group
choice = input()
if choice == "1":
    n = max(all_num) - min(all_num)
    h = round(n/(1+math.log2(N)),2)
    print("значение h:%.2f"% h)
    x_min = min(all_num) - h/2
    start = x_min
    internals_x = []
    internals_y = []
    while start < max(all_num):
        temp = start
        start = start + h
        internals_x.append(round(temp, 3))
        internals_y.append(round(start, 3))
    dictionary_xy = {}
    for i in all_num:
        internals_amount = len(internals_x)
        while internals_amount > 0:
            internals_amount = internals_amount - 1
            if i < internals_y[internals_amount] and i >= internals_x[internals_amount]:
                s = "[" + str(round(internals_x[internals_amount], 2)) + "," + str(
                    round(internals_y[internals_amount], 2)) + ")"
                if s not in dictionary_xy:
                    dictionary_xy[s] = 0.05
                else:
                    dictionary_xy[s] = dictionary_xy[s] + 0.05
                break
    bar_x = []
    bar_y = []
    for i in dictionary_xy:
        bar_x.append(i)
        bar_y.append(dictionary_xy[i])
    plt.xticks(size=8)
    linar_x = []
    linar_y = []
    for a,b in zip(bar_x,bar_y):
        plt.text(a,b+0.005,'%.2f'%b,ha='center',va='bottom',fontsize=10)
        linar_x.append(a)
        linar_y.append(b)
    plt.plot(linar_x,linar_y,color="red")
    plt.bar(bar_x, bar_y)
    plt.ylim(0,max(bar_y)+0.1)
    plt.show()
elif choice=="2":
    dictionary_exp = dictionary.copy()
    for i in dictionary_exp:
        for j in dictionary:
            if i > j:
                dictionary_exp[i] = round(dictionary_exp[i] + dictionary[j],2)
    count = 0
    array_x = []
    array_y = []
    for i in dictionary_exp:
        array_x.append(i)
        array_y.append(dictionary_exp[i])
    axis_x = []
    axis_y = []
    while count < len(array_x):
        if count != len(array_x)-1:
            axis_x.append(array_x[count])
            axis_x.append(array_x[count+1])
            axis_y.append(array_y[count])
            axis_y.append(array_y[count])
            plt.plot(array_x[count+1],array_y[count],'o',color="black",markersize=2)
            plt.plot(axis_x,axis_y,color="blue")
            plt.text(array_x[count],array_y[count],str(array_y[count]),fontsize=10,verticalalignment="bottom")
        else:
            axis_x.append(array_x[count])
            axis_x.append(array_x[count]+0.2)
            axis_y.append(array_y[count])
            axis_y.append(array_y[count])
            plt.text(array_x[count],array_y[count],str(array_y[count]),fontsize=10,verticalalignment="bottom")
            plt.plot(axis_x,axis_y,color="blue")
        axis_x.clear()
        axis_y.clear()
        count = count+1
    plt.show()
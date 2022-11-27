import matplotlib.pyplot as plt
import numpy as np
fname = "./final.txt"
x = []
per_cpu = []
i = 0
start = True
average = 0
length = 0
with open(fname) as p:
    file = p.readlines()
    for lines in file:
        if not start:
            if i != length:
                line = lines[:-1].replace(",", ".").split(" ")
                print(line)
                x.append(i)
                per_cpu.append(float(line[7]))
                i = i + 1
            else:
                line = lines[:-1].replace(",", ".").split(" ")
                average = float(line[7])
        else:
            length = len(file) - 2
            print(lines[:-1].replace(",", ".").split(" "))
            start = False
draw_x = np.array(x)
draw_per_cpu = np.array(per_cpu)
draw_average = np.array([average]*length)
plt.plot(draw_x, draw_per_cpu, "blue", label="%cpu")
plt.plot(draw_x, draw_average, "red", label="%average")
plt.title("%CPU")
plt.legend(loc=2)
plt.show()
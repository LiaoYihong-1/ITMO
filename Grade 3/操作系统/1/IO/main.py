import matplotlib.pyplot as plt
import numpy as np
fname = "./out.txt"
start = True
i = 0
draw_rd = []
draw_wr = []
draw_ccwr = []
draw_x = []
with open(fname) as p:
    file = p.readlines()
    for lines in file:
        if not start:
            if i != length:
                line = lines[:-1].replace(",", ".").split(" ")
                print(line)
                draw_x.append(i)
                i = i + 1
                draw_rd.append(float(line[3]))
                draw_wr.append(float(line[4]))
                draw_ccwr.append(float(line[5]))
        else:
            length = len(file) - 2
            print(lines[:-1].replace(",", ".").split(" "))
            start = False
plt.plot(draw_x, np.array(draw_wr),"blue",label="kB_wr/s")
plt.plot(draw_x, np.array(draw_rd),"red",label="kB_rd/s")
plt.plot(draw_x, np.array(draw_ccwr),"orange",label="kB_ccwr/s")
plt.title("IO")
plt.legend()
plt.show()
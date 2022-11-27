import matplotlib.pyplot as plt
import numpy as np
fname = "./top.txt"
draw_x = []
x = 0
draw_total = []
draw_running = []
draw_sleep = []
draw_stop = []
draw_zombie = []
with open(fname) as p:
    lines = p.readlines()
    for line in lines:
        line = line[:-1].replace(",", " ")
        line = line[:-1].replace(":", " ")
        fragments = line.split("  ")
        if fragments[0] == "Threads":
            i = 1
            while i <= 5:
                eles = fragments[i]
                ele = eles.split(" ")
                if i == 1:
                    draw_total.append(int(ele[0]))
                if i == 2:
                    draw_running.append(int(ele[0]))
                if i == 3:
                    draw_sleep.append(int(ele[0]))
                if i == 4:
                    draw_stop.append(int(ele[0]))
                if i == 5:
                    draw_zombie.append(int(ele[0]))
                i = i + 1
            draw_x.append(x)
            x = x + 1
plt.plot(np.array(draw_x), np.array(draw_stop),"red",label="stop")
plt.plot(np.array(draw_x), np.array(draw_total), "black", label="total")
plt.plot(np.array(draw_x), np.array(draw_sleep), "blue", label="sleep")
plt.plot(np.array(draw_x), np.array(draw_zombie), "green", label="zombie")
plt.plot(np.array(draw_x), np.array(draw_running), "orange", label="running")
plt.title("Threads")
plt.legend()
plt.show()

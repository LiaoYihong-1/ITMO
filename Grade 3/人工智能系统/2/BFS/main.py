import codecs
import collections
import datetime

class Graph:
    def __init__(self, file):
        self.graph = dict()
        self.edge = 0
        file_op = codecs.open(file, encoding='utf-8')
        self.read_file(file_op)

    def read_file(self, file_op):
        for line in file_op.readlines():
            eles = line.split()
            self.add_edge(eles[0], eles[1], int(eles[2]))

    def add_edge(self, c1, c2, distance):
        if c1 not in self.graph:
            self.graph[c1] = {}
        if c2 not in self.graph:
            self.graph[c2] = {}
        if c2 not in self.graph[c1]:
            self.edge = self.edge + 1
            self.graph[c1][c2] = distance
            self.graph[c2][c1] = distance

class Graph_solver:
    def __init__(self, graph):
        self.graph = graph

    def print_path(self,method, path, start, end):
        print(method,end=":")
        position = end
        real_path = []
        while position != start:
            real_path.append(position)
            position = path[position]
        real_path.append(position)
        real_path.reverse()
        length = len(real_path)
        i = 0
        while i < length:
            if i != length - 1:
                print(real_path[i],end="->")
            else:
                print(real_path[i])
            i = i + 1

    ##广度
    def BFS(self, start, end):
        not_visited = collections.deque()
        visited = set()
        not_visited.append(start)
        found = False
        path = dict()
        while len(not_visited) > 0:
            city = not_visited.popleft()
            if city not in visited:
                visited.add(city)
            for neighbor in iter(self.graph.graph[city]):
                if neighbor not in visited:
                    path[neighbor] = city
                    not_visited.append(neighbor)
                    visited.add(neighbor)
                    if neighbor == end:
                        found = True
                        break
            if found:
                break
        self.print_path("BFS", path, start, end)

    ##深度
    def DFS(self, start, end):
        path = dict()
        visited = set()
        result = self.DFS_loop(start, end, path, visited)
        if result:
            self.print_path("DFS", path, start, end)
        else:
            print("Path not found with DLS")

    def DFS_loop(self, start, end, path, visited):
        if start == end:
            return True
        if start not in visited:
            visited.add(start)
            for city in iter(self.graph.graph[start]):
                if city not in visited:
                    path[city] = start
                    if self.DFS_loop(city, end, path, visited):
                        return True
            return False

    #深度限制
    def DLS(self, start ,end ,max_depth):
        path = dict()
        visited = set()
        result = self.DLS_loop(start, end, max_depth, path, visited)
        if result:
            self.print_path("DLS", path, start, end)
        else:
            print("DLS:Path not found with DLS")

    def DLS_loop(self, start, end, max_depth, path, visited):
        if start == end:
            return True
        if max_depth <= 0:
            return False
        if start not in visited:
            visited.add(start)
            for city in iter(self.graph.graph[start]):
                if city not in visited:
                    path[city] = start
                    if self.DLS_loop(city, end, max_depth-1, path, visited):
                        return True
            return False

        #递归优化
    def IDDFS(self, start, end, max_depth):
        for i in range(max_depth + 1):
            path = dict()
            visited = set()
            ## print(i)
            if self.DLS_loop(start, end, i, path, visited):
                self.print_path("IDDFS", path, start, end)
                print("Depth is %d for IDDFS"%i)
                return

    def BFS_Meet_Middle(self, start, end):
        not_visited = [collections.deque(), collections.deque()]
        not_visited[0].append(start)
        not_visited[1].append(end)
        visited_start = set()
        visited_end = set()
        path_start = dict()
        path_end = dict()
        stop = False
        meet_city = ""
        while not stop:
            city1 = not_visited[0].popleft()
            if city1 not in visited_start:
                visited_start.add(city1)
            for city in iter(self.graph.graph[city1]):
                if city not in visited_start and city not in visited_end:
                    visited_start.add(city)
                    path_start[city] = city1
                    not_visited[0].append(city)
                elif city not in visited_start and city in visited_end:
                    ## will meet
                    meet_city = city
                    path_start[city] = city1
                    not_visited[0].append(city)
                    stop = True
            if stop:
                break
            city2 = not_visited[1].popleft()
            if city2 not in visited_end:
                visited_end.add(city2)
            for city in iter(self.graph.graph[city2]):
                if city not in visited_start and city not in visited_end:
                    visited_end.add(city)
                    path_end[city] = city2
                    not_visited[1].append(city)
                elif city not in visited_end and city in visited_start:
                    ## will meet
                    meet_city = city
                    path_end[city] = city2
                    not_visited[1].append(city)
                    if len(path_end) == 0:
                        path_end[meet_city] = end
                    stop = True
        print("Двухнаправленный поиск", end=":")
        position = meet_city
        real_path = []
        while position != start:
            real_path.append(position)
            position = path_start[position]
        real_path.append(position)
        real_path.reverse()
        position = meet_city
        while position != end:
            position = path_end[position]
            real_path.append(position)
        length = len(real_path)
        i = 0
        while i < length:
            if i != length - 1:
                print(real_path[i], end="->")
            else:
                print(real_path[i])
            i = i + 1

##infor
    def get_distance(self, c1, c2):
        return self.graph.graph[c1][c2]

    def get_shortest(self, c1, close):
        min = ""
        over_large = 999999
        for i in iter(self.graph.graph[c1]):
            if self.graph.graph[c1][i] < over_large and i not in close:
                min = i
                over_large = self.graph.graph[c1][i]
        return min

    def GBSF(self, start, end):
        open = set()
        close = set()
        path = []
        node = start
        close.add(start)
        path.append(start)
        distance = 0
        while node != end:
            for i in iter(self.graph.graph[node]):
                if i not in close and i not in open:
                    open.add(i)
            min = self.get_shortest(node, close)
            if min == "":
                return False
            distance = distance + self.graph.graph[node][min]
            node = min
            path.append(node)
            open.remove(node)
            close.add(node)
            # print(open)
            # print(close)
            # print(node)
        i = 0
        print("GBSF", end=":")
        while i < len(path):
            if i != len(path) - 1:
                print(path[i], end="->")
            else:
                print(path[i])
            i = i + 1
        print("distance is %d" % distance)
        return True

    def get_smallest_A(self, open, list_value):
        over_large = 999999999
        min = ""
        for i in open:
            if list_value[i][1] < over_large:
                min = i
                over_large = list_value[i][1]
        return min

    def A(self, start, end):
        open = set()
        close = set()
        list_value = dict()
        open.add(start)
        list_value[start] = [start, 0]
        while True:
            min = self.get_smallest_A(open, list_value)
            open.remove(min)
            close.add(min)
            if end in close:
                break
            for i in self.graph.graph[min]:
                if i not in open and i not in close:
                    summ = list_value[min][1] + self.graph.graph[min][i]
                    path = list_value[min][0] + "->" + i
                    list_value[i] = ["", 0]
                    list_value[i][1] = summ
                    list_value[i][0] = path
                    open.add(i)
                elif i not in close and i in open:
                    summ = list_value[min][1] + self.graph.graph[min][i]
                    if summ < list_value[i][1]:
                        list_value[i][0] = list_value[min][0] + "->" + i
                        list_value[i][1] = summ
        print("A*:\npath: %s\ndistance is: %d"% (list_value[end][0],list_value[end][1]))

map = Graph("./cities.txt" )
solver = Graph_solver(map)
start = "Вильнюс"
end = "Одесса"
solver.DFS(start, end)
solver.BFS(start, end)
solver.DLS(start, end, 14)
solver.IDDFS(start, end, 7)
solver.BFS_Meet_Middle(start, end)
result = solver.GBSF(start, end)
if not result:
    print("GBSF can't find result cause target not on the best value path")
solver.A(start, end)
# map = Graph("./cities.txt" )
# solver = Graph_solver(map)
# print("Please choose method:")
# print("1)DFS")
# print("2)BFS")
# print("3)DLS")
# print("4)IDDFS")
# print("5)Двухнаправленный")
# print("6)DFSF")
# print("7)A*")
# method = input()
# start = "Калининград"
# end = "Киев"
# if method == "1":
#     solver.DFS(start, end)
# elif method == "2":
#     solver.BFS(start, end)
# elif method == "3":
#     depth = input("Please input max depth")
#     solver.DLS(start, end, int(depth))
# elif method == "4":
#     depth = input("Please input max depth")
#     solver.IDDFS(start, end, depth)
# elif method == "5":
#     solver.BFS_Meet_Middle(start, end)
# elif method == "6":
#     result = solver.GBSF(start, end)
#     if not result:
#         print("DFSF can't find result cause target not on the best value path")
# elif method == "7":
#     solver.A(start, end)

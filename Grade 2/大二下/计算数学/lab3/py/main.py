import sys
import math
from abc import ABC, abstractmethod

def is_digit(d: str):
    if d[0] == "-":
        d = d[1:]
    l = d.split(".")
    if len(l) != 2 and len(l) != 1:
        return False
    for i in l:
        if not i.isdigit():
            return False
    return True


class InputError(Exception):
    def __init__(self, info):
        super().__init__(self)
        self.errorInfo = info

    def __str__(self):
        return self.errorInfo


class AbstractFunction(ABC):
    a = 0
    b = 0
    c = 0
    d = 0
    type = "0"

    def __init__(self, a, b, c, d):
        self.a = a
        self.b = b
        self.c = c
        self.d = d

    @abstractmethod
    def getfirstInter(self, a: float, b: float):
        pass

    @abstractmethod
    def getvalue(self, x: float):
        pass


class FirstFunction(AbstractFunction):
    def __init__(self, a, b, c, d):
        super(FirstFunction, self).__init__(a, b, c, d)
        self.type = "1"

    def getfirstInter(self, a: float, b: float):
        return (0.25 * self.a * b ** 4 + 1/3 * self.b * b ** 3 + self.c/2 * b ** 2 + self.d*b) - (0.25 * self.a * a ** 4 + 1/3 * self.b * a ** 3 + self.c/2 * a ** 2 + self.d*a)

    def getvalue(self, x: float):
        return self.a * x ** 3 + self.b * x ** 2 + self.c * x + self.d


class SecondFunction(AbstractFunction):
    def __init__(self, a, b, c, d):
        super().__init__(a, b, c, d)
        self.type = "2"

    def getfirstInter(self, a: float, b:float):
        return ( self.a * math.exp(b) + 1/3 * self.b * b ** 3 + 0.5 * self.c * b **2 + self.d * b ) - ( self.a * math.exp(a) + 1/3 * self.b * a ** 3 + 0.5 * self.c * a **2 + self.d * a )

    def getvalue(self, x: float):
        return self.a * math.exp(x) + self.b * x ** 2 + self.c * x + self.d


class ThirdFunction(AbstractFunction):
    def __int__(self, a, b, c, d):
        super().__init__(a, b, c, d)
        self.type = "3"

    def getfirstInter(self, a: float, b:float):
        return ( self.a * math.log(b) + 1/3 * self.b * b ** 3 + 0.5 * self.c * b **2 + self.d * b ) - ( self.a * math.log(a) + 1/3 * self.b * a ** 3 + 0.5 * self.c * a **2 + self.d * a )

    def getvalue(self, x: float):
        return self.a / x + self.b * x ** 2 + self.c * x + self.d

class ForthFunction(AbstractFunction):
    def __int__(self, a, b, c, d):
        super().__init__(a, b, c, d)
        self.type = "4"

    def getfirstInter(self, a: float, b: float):
        return (2 * self.a * b**0.5 + 1/3 * self.b * b ** 3 + 0.5 * self.c * b **2 + self.d * b ) - ( 2 * self.a * (a-3)**0.5 + 1/3 * self.b * a ** 3 + 0.5 * self.c * a ** 2 + self.d * a)

    def getvalue(self, x: float):
        return self.a / x**0.5 + self.b * x ** 2 + self.c * x + self.d

class AbstractSolver(ABC):
    a: float
    b: float
    accuracy: float
    myfunction: AbstractFunction

    def __init__(self, myfunction: AbstractFunction, accuracy: float, a: float, b: float):
        self.myfunction = myfunction
        self.accuracy = accuracy
        self.a = a
        self.b = b

    @abstractmethod
    def solve(self):
        pass

    @abstractmethod
    def get_accuracy(self, a, b):
        pass


class SquareMethodSolver(AbstractSolver):
    def __init__(self, myfunction, accuracy, a, b):
        super().__init__(myfunction, accuracy, a, b)

    def get_accuracy(self, current, next):
        return (next - current) / 3

    def solve(self):
        current_accuracy = 100
        next_integral = 0
        print("1) Левые")
        print("2) Средние")
        print("3) Правые")
        choice = input("Choose which kind of method:\n")
        if choice != "1" and choice != "2" and choice != "3":
            raise InputError("Choose form 1, 2 and 3")
        n_string = input("Choose the start numbers of square(later will *2 every time):\n")
        if not is_digit(n_string):
            raise InputError("Enter a number for step")
        elif int(n_string) <= 0:
            raise InputError("Step should bigger 0")
        # how many square are there
        n = int(n_string)

        is_first = True
        while current_accuracy > self.accuracy:
            if choice == "1":
                current_integral = next_integral
                next_integral = 0
                k = 0
                position = self.a
                gap = (self.b - self.a) / n
                while k < n:
                    next_integral += self.myfunction.getvalue(position) * gap
                    position = position + gap
                    k = k + 1
                if not is_first:
                    current_accuracy = abs(self.get_accuracy(current_integral, next_integral))
                else:
                    is_first = False
                n = 2 * n
            elif choice == "2":
                current_integral = next_integral
                next_integral = 0
                gap = (self.b - self.a) / n
                position = self.a + gap/2
                k = 0
                while k < n:
                    next_integral += self.myfunction.getvalue(position) * gap
                    position = position + gap
                    k = k + 1
                if not is_first:
                    current_accuracy = abs(self.get_accuracy(current_integral, next_integral))
                else:
                    is_first = False
                n = 2 * n
            elif choice == "3":
                current_integral = next_integral
                gap = (self.b - self.a) / n
                position = self.a + gap
                k = 0
                next_integral = 0
                while k < n:
                    next_integral += self.myfunction.getvalue(position) * gap
                    position = position + gap
                    k = k + 1
                if not is_first:
                    current_accuracy = abs(self.get_accuracy(current_integral, next_integral))
                else:
                    is_first = False
                n = 2 * n
        print("Your range is dived to %d part, and the accuracy is %f"%(n/2, current_accuracy))
        print("The result integral is %.5f" % current_integral)


class TrapezoidMethodSolver(AbstractSolver):
    def __init__(self, myfunction, accuracy, a, b):
        super().__init__(myfunction, accuracy, a, b)

    def get_accuracy(self, current, next):
        return (next - current) / 3

    def solve(self):
        current_accuracy = 100
        next_integral = 0
        current_integral = 0
        n_string = input("Choose the start numbers of square(later will *2 every time):\n")
        if not is_digit(n_string):
            raise InputError("Enter a number for step")
        elif int(n_string) <= 0:
            raise InputError("Step should bigger 0")
        # how many Trapezoid are there
        n = int(n_string)
        is_first = True
        while current_accuracy > self.accuracy:
            current_integral = next_integral
            next_integral = 0
            gap = (self.b - self.a) / n
            position = self.a
            k = 0
            while k < n:
                next_integral += (self.myfunction.getvalue(position)+self.myfunction.getvalue(position+gap))/2 * gap
                position = position + gap
                k = k + 1
            if not is_first:
                current_accuracy = abs(self.get_accuracy(current_integral, next_integral))
            else:
                is_first = False
            n = 2 * n
        print("Your range is dived to %d part, and the accuracy is %f"%(n/2, current_accuracy))
        print("The result integral is %.5f"% current_integral)


class SimonMethodSolver(AbstractSolver):
    def __init__(self, myfunction, accuracy, a, b):
        super().__init__(myfunction, accuracy, a, b)

    def get_accuracy(self, current, next):
        return (next - current) / 15

    def solve(self):
        current_accuracy = 100
        next_integral = 0
        current_integral = 0
        n_string = input("Choose the start numbers of square(later will *2 every time):\n")
        if not is_digit(n_string):
            raise InputError("Enter a number for step")
        elif int(n_string) <= 0:
            raise InputError("Step should bigger 0")
        # how many Trapezoid are there
        n = int(n_string)
        is_first = True
        while current_accuracy > self.accuracy:
            current_integral = next_integral
            next_integral = 0
            sum_n = 0
            sum_2n = 0
            k = 1
            gap = (self.b - self.a)/n
            while k < n:
                if k % 2 != 0:
                    sum_n = sum_n + self.myfunction.getvalue(self.a + k * gap)
                else:
                    sum_2n = sum_2n + self.myfunction.getvalue(self.a + k * gap)
                k = k + 1
            next_integral = gap/3 * (self.myfunction.getvalue(self.a) + self.myfunction.getvalue(self.b) + 4 * sum_n + 2 * sum_2n)
            if not is_first:
                current_accuracy = abs(self.get_accuracy(current_integral, next_integral))
            else:
                is_first = False
            n = n * 2
        print("Your range is dived to %d part, and the accuracy is %f"%(n/2, current_accuracy))
        print("The result integral is %.5f"%current_integral)

while True:
    try:
        print("Please choose your equations:")
        print("1) ax^3 + bx^2 + cx + d")
        print("2) ae^x + bx^2 + cx + d")
        print("3) a/x + bx^2 + cx + d")
        print("4) a/x^0.5 + bx^2 + cx + d")
        equation = input()
        if equation != "1" and equation != "2" and equation != "3" and equation != "4":
            raise InputError("Choose form 1, 2, 3 and 4")
        string_coeffi = input("Please input the coefficients of your equation(a, b, c and d). Split by space:\n")
        for i in string_coeffi.split(" "):
            if not is_digit(i):
                raise InputError("The coefficients should be number")
        list_coeffi = string_coeffi.split(" ")
        if len(list_coeffi) != 4:
            raise InputError("Make sure that you input all 4 coefficient")

        range_string = input("Please input the range of calculation, split by spare:\n")
        list_range = range_string.split(" ")
        for i in list_range:
            if not is_digit(i):
                raise InputError("Input only number for range")
        if float(list_range[0]) >= float(list_range[1]):
            raise InputError("Right number should bigger than left")
        elif len(list_range) != 2:
            raise InputError("Make sure only two number were input for range")

        accuracy_string = input("Please input the accuracy of calculation:\n")
        if not is_digit(accuracy_string):
            raise InputError("Accuracy should be a number")
        elif float(accuracy_string) <= 0:
            raise InputError("Accuracy should be bigger than 0")

        print("Please choose the method:")
        print("1) Метод прямоугольников")
        print("2) Метод трапеций")
        print("3) Метод Симпсона")
        method = input()
        if method != "1" and method != "2" and method != "3":
            raise InputError("Choose form 1, 2 and 3")

        # create the function
        my_function: AbstractFunction
        if equation == "1":
            my_function = FirstFunction(float(list_coeffi[0]), float(list_coeffi[1]), float(list_coeffi[2]),
                                        float(list_coeffi[3]))
        elif equation == "2":
            my_function = SecondFunction(float(list_coeffi[0]), float(list_coeffi[1]), float(list_coeffi[2]),
                                         float(list_coeffi[3]))
        elif equation == "3":
            if float(list_range[0]) <= 0 or float(list_range[1]) >= 0:
                raise InputError("Интеграл не существует")
            my_function = ThirdFunction(float(list_coeffi[0]), float(list_coeffi[1]), float(list_coeffi[2]),
                                        float(list_coeffi[3]))
        elif equation == "4":
            if float(list_range[0]) < 0 or float(list_range[1]) <= 0:
                raise InputError("На этой отрезке функция не определена")
            if float(list_range[0]) == 0:
                list_range[0] = "0.0001"
            my_function = ForthFunction(float(list_coeffi[0]), float(list_coeffi[1]), float(list_coeffi[2]),
                                        float(list_coeffi[3]))

        # create the solver
        if method == "1":
            solver = SquareMethodSolver(my_function, float(accuracy_string), float(list_range[0]), float(list_range[1]))
            solver.solve()
        elif method == "2":
            solver = TrapezoidMethodSolver(my_function, float(accuracy_string), float(list_range[0]), float(list_range[1]))
            solver.solve()
        elif method == "3":
            solver = SimonMethodSolver(my_function, float(accuracy_string), float(list_range[0]), float(list_range[1]))
            solver.solve()
        go_on = input("Want to go on?\n")
        if not go_on.lower() == "yes":
            sys.exit(1)
        print("\n")
    except InputError as e:
        print(e)
        go_on = input("Want to go on?\n")
        if not go_on.lower() == "yes":
            sys.exit(1)
        print("\n")
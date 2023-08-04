import sys
import math
from abc import ABC, abstractmethod
import matplotlib.pyplot as plt
import numpy as np


def min(list_num):
    result = list_num[0]
    for i in list_num:
        if i < result:
            result = i
    return result


def min(list_num):
    result = list_num[0]
    for i in list_num:
        if i > result:
            result = i
    return result


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
    def getfirstDer(self, x: float):
        pass

    @abstractmethod
    def getvalue(self, x: float):
        pass

    # For method iterate，They are different ways to build x = φ(x) and φ'(x)
    @abstractmethod
    # φ'(x)
    def getpossibilityder1(self, x: float):
        pass

    @abstractmethod
    # φ(x)
    def getpossibilityder2(self, x: float):
        pass

    @abstractmethod
    def getpossibilityder3(self, x: float):
        pass

    @abstractmethod
    def getpossibilityv1(self, x: float):
        pass

    @abstractmethod
    def getpossibilityv2(self, x: float):
        pass

    @abstractmethod
    def getpossibilityv3(self, x: float):
        pass

    @abstractmethod
    def draw(self,a,b):
        pass


class FirstFunction(AbstractFunction):
    def __init__(self, a, b, c, d):
        super(FirstFunction, self).__init__(a, b, c, d)
        self.type = "1"

    def getfirstDer(self, x: float):
        return 3 * self.a * x ** 2 + self.b * 2 * x + self.c

    def getvalue(self, x: float):
        return self.a * x ** 3 + self.b * x ** 2 + self.c * x + self.d

    def getpossibilityder1(self, x: float):
        firstpart = math.pow(math.pow((-self.b * x ** 2 - self.c * x - self.d) / self.a, 2), -1 / 3) / 3
        secondpart = (-2 * self.b * x - self.c) / self.a
        return firstpart * secondpart

    def getpossibilityv1(self, x: float):
        return math.pow((-self.b * x ** 2 - self.c * x - self.d) / self.a, 1 / 3)

    def getpossibilityder2(self, x: float):
        firstpart = math.pow(-self.a * x ** 3 - self.c * x - self.d, -1 / 2) / 2
        secondpart = (-3 * self.a * x ** 2 - self.c) / self.b
        return firstpart * secondpart

    def getpossibilityv2(self, x: float):
        return math.pow((-self.a * x ** 3 - self.c * x - self.d) / self.b, 1 / 2)

    def getpossibilityder3(self, x: float):
        return (-3 * self.a * x ** 2 - 2 * self.b * x) / self.c

    def getpossibilityv3(self, x: float):
        return (-self.a * x ** 3 - self.b * x ** 2 - self.d) / self.c

    def draw(self, a, b):
        x = np.arange(a, b, 0.1)
        y = self.a*x**3 + self.b*x**2 + self.c*x + self.d
        y1 = 0*x
        plt.title("first function")
        plt.plot(x, y1)
        plt.plot(x, y)
        plt.show()


class SecondFunction(AbstractFunction):
    def __init__(self, a, b, c, d):
        super().__init__(a, b, c, d)
        self.type = "2"

    def getfirstDer(self, x: float):
        return self.a * math.exp(x) + 2 * self.b * x + self.c

    def getvalue(self, x: float):
        return self.a * math.exp(x) + self.b * x ** 2 + self.c * x + self.d

    def getpossibilityder1(self, x: float):
        if -self.b * x ** 2 - self.c * x - self.d == 0.0:
            # return 2, which >1, later this situation won't be chosen
            return 2
        else:
            return (self.a / (-self.b * x ** 2 - self.c * x - self.d)) * (-2 * self.b * x - self.c) / self.a

    def getpossibilityv1(self, x: float):
        return math.log((-self.b * x ** 2 - self.c * x - self.d) / self.a)

    def getpossibilityder2(self, x: float):
        if (-self.a * math.exp(x) - self.c * x - self.d) / self.b < 0:
            return 2
        firstpart = math.pow((-self.a * math.exp(x) - self.c * x - self.d) / self.b, -1 / 2) / 2
        secondpart = (-self.a * math.exp(x) - self.c) / self.d
        return firstpart * secondpart

    def getpossibilityv2(self, x: float):
        return math.pow((-self.a * math.exp(x) - self.c * x - self.d) / self.b, 1 / 2)

    def getpossibilityder3(self, x: float):
        return (-1 / self.c) * (self.a * math.exp(x) + 2 * self.b * x)

    def getpossibilityv3(self, x: float):
        return (-1 / self.c) * (self.a * math.exp(x) + self.b * x ** 2 + self.d)

    def draw(self, a, b):
        x = np.arange(a, b, 0.1)
        y = self.a*math.e**x + self.b*x**2 + self.c*x + self.d
        y1 = 0*x
        plt.title("Second function")
        plt.plot(x, y)
        plt.plot(x, y1)
        plt.show()


class ThirdFunction(AbstractFunction):
    def __int__(self, a, b, c, d):
        super().__init__(a, b, c, d)
        self.type = "3"

    def getfirstDer(self, x: float):
        return self.a * (-1) / (x ** 2) + self.b * 2 * x + self.c

    def getvalue(self, x: float):
        return self.a / x + self.b * x ** 2 + self.c * x + self.d

    def getpossibilityder1(self, x: float):
        if x == 0:
            raise InputError("'0' is not defined in an equation with 1/x")
        firstpart = 1 / 3 * math.pow(math.pow((-self.c * x ** 2 - self.d * x - self.a) / self.b, 2), -1 / 3)
        secondpart = (-1 / self.b) * (-2 * self.c * x - self.d)
        return firstpart * secondpart

    def getpossibilityv1(self, x: float):
        if x == 0:
            raise InputError("'0' is not defined in an equation with 1/x")
        return math.pow((1 / self.b) * (-self.c * x ** 2 - self.d * x - self.a), 1 / 3)

    def getpossibilityder2(self, x: float):
        if x == 0:
            raise InputError("'0' is not defined in an equation with 1/x")
        if (-1 / self.c) * (self.b * x ** 3 + self.d * x + self.a) < 0:
            return 2
        firstpart = 1 / 2 * math.pow((-1 / self.c) * (self.b * x ** 3 + self.d * x + self.a), -1 / 2)
        secondpart = (-1 / self.c) * (3 * self.b * x ** 2 + self.d)
        return firstpart * secondpart

    def getpossibilityv2(self, x: float):
        if x == 0:
            raise InputError("'0' is not defined in an equation with 1/x")
        return math.pow((-1 / self.c) * (self.b * x ** 3 + self.d * x + self.a), 1 / 2)

    def getpossibilityder3(self, x: float):
        if x == 0:
            raise InputError("'0' is not defined in an equation with 1/x")
        return (-1 / self.d) * (3 * self.b * x ** 2 + 2 * self.c * x)

    def getpossibilityv3(self, x: float):
        if x == 0:
            raise InputError("'0' is not defined in an equation with 1/x")
        return (-1 / self.d) * (self.b * x ** 3 + self.c * x ** 2 + self.a)

    def draw(self, a, b):
        x = np.arange(a, b, 0.1)
        y = self.a/x + self.b*x**2 + self.c*x + self.d
        y2 = 0*x
        plt.title("Third function")
        plt.plot(x, y)
        plt.plot(x, y2)
        plt.show()


class AbstractSolver(ABC):
    a: float
    b: float
    accuracy: float
    myfunction: AbstractFunction

    def __init__(self, myfunction: AbstractFunction):
        self.myfunction = myfunction

    @abstractmethod
    def solve(self,inputs):
        pass


class SimpleIterate(AbstractSolver):
    def __init__(self, myfunction: AbstractFunction):
        super().__init__(myfunction)
        self.a = 0
        self.b = 0

    def solve(self, inputs):
        input1 = inputs[0]
        input2 = inputs[1]
        self.accuracy = float(input2)
        times = 0
        ans: float
        caculateaccuracy = 10
        model = 0
        start = float(input1)
        if abs(self.myfunction.getpossibilityder1(start)) < 1:
            print("Possibility 1 is running")
            model = 1
        elif abs(self.myfunction.getpossibilityder2(start)) < 1:
            print("Possibility 2 is running")
            model = 2
        elif abs(self.myfunction.getpossibilityder3(start)) < 1:
            print("Possibility 3 is running")
            model = 3
        else:
            raise InputError("This point can't be a start point cause it doesn't meet the convergence requirements")
        print("-------------------Method Simple iterate-------------------")
        print("%-10s %-10s %-10s %-10s %-10s" % ("x(k)", "f(x(k))", "x(k+1)", "φ(x(k))", "|x(k)-x(k+1)|"))
        while caculateaccuracy >= self.accuracy:
            self.a = start
            times = times + 1
            f1 = self.myfunction.getvalue(self.a)
            if model == 1:
                start = self.myfunction.getpossibilityv1(start)
                if abs(self.myfunction.getpossibilityder1(start)) >= 1:
                    raise InputError("During the process we got a point doesnt meet requirement at %d iterate" % times)
            elif model == 2:
                start = self.myfunction.getpossibilityv2(start)
                if abs(self.myfunction.getpossibilityder2(start)) >= 1:
                    raise InputError("During the process we got a point doesnt meet requirement at %d iterate" % times)
            elif model == 3:
                start = self.myfunction.getpossibilityv3(start)
                if abs(self.myfunction.getpossibilityder3(start)) >= 1:
                    raise InputError("During the process we got a point doesnt meet requirement at %d iterate" % times)
            self.b = start
            caculateaccuracy = abs(self.a - self.b)
            print("%-10.4f %-10.4f %-10.4f %-10.4f %-10.4f" %
                  (self.a, f1, self.b, self.b, caculateaccuracy))
        print("-----------------------------------------------------------")
        print("Iterate times:%d" % times)
        print("Answer is %.4f" % self.b)
        print("Accuracy is %.4f" % caculateaccuracy)
        print("F(x) of answer is:%.4f" % self.myfunction.getvalue(self.b))
        self.myfunction.draw(int(self.a-1), int(self.b+1))


class SecantSolver(AbstractSolver):
    def __init__(self, myfunction: AbstractFunction):
        super().__init__(myfunction)
        self.a = 0
        self.b = 0

    def getnextpoint(self):
        fraction = (self.a - self.b) / (self.myfunction.getvalue(self.a) - self.myfunction.getvalue(self.b))
        return self.b - self.myfunction.getvalue(self.b) * fraction

    def solve(self, inputs):
        input1 = inputs[0]
        input2 = inputs[1]
        input3 = inputs[2]
        self.a = float(input1)
        self.b = float(input2)
        self.accuracy = float(input3)
        if self.myfunction.getvalue(self.a)*self.myfunction.getvalue(self.b) >= 0:
            raise InputError("In this range no solution")
        answer: float
        times = 0
        print("-------------------Method Secant Start-------------------")
        caculateaccuracy = 10
        print("%-10s %-10s %-10s %-10s %-10s %-10s %-10s" % ("x(k-1)", "f(x(k-1))", "x(k)",
                                                             "f(x(k))", "x(k+1)", "f(x(k+1))", "|x(k)-x(k+1)|"))
        while caculateaccuracy >= self.accuracy:
            times = times + 1
            printa = self.a
            printb = self.b
            f1 = self.myfunction.getvalue(self.a)
            f2 = self.myfunction.getvalue(self.b)
            nextpoint = self.getnextpoint()
            temp = self.b
            self.b = nextpoint
            self.a = temp
            caculateaccuracy = abs(self.a - self.b)
            printc = self.b
            f3 = self.myfunction.getvalue(self.b)
            answer = printc
            print("%-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f" %
                  (printa, f1, printb, f2, printc, f3, caculateaccuracy))
        print("-----------------------------------------------------------")
        print("The answer is:%.4f" % answer)
        print("The error is:%.4f" % caculateaccuracy)
        print("Iterated %d" % times)
        print("F(x) of answer:%.4f" % self.myfunction.getvalue(answer))
        self.myfunction.draw(int(self.a-1), int(self.b+1))


class SolverForSystem:
    # first point for iterate x1
    a: float
    # second point for iterate x2
    b: float
    accuracy: float
    '''
    0.1x^2+x+0.2y-0.3=0
    0.2x^2+y-0.1xy-0.7=0
    '''

    def __init__(self):
        pass

    def getvalue1(self):
        return 0.1*self.a**2+0.2*self.b**2+self.a-0.3

    def getvalue2(self):
        return 0.2*self.a**2+self.b-0.1*self.a*self.b-0.7

    # x1 = 0.3-0.1x1^2-0.2x2^2
    def firstiterator(self, x1, x2):
        return 0.3-0.1*x1**2-0.2*x2**2

    # -0.2x1
    def firstderonx(self, x1):
        return 0.2*x1

    # -0.4x2
    def firstderony(self, x2):
        return -0.4*x2

    # x2 = 0.7-0.2x1^2+0.1x1x2
    def seconditerator(self, x1, x2):
        return 0.7-0.2*x1**2+0.1*x1*x2

    # -0.4x1+0.1x2
    def secondderonx(self, x1, x2):
        return -0.4*x1+0.1*x2

    # 0.1x1
    def secondderony(self, x1):
        return 0.1*x1

    def draw(self, a: int, b: int):
        y = np.arange(-4, 4, 0.1)
        x = np.arange(a, b, 0.1)
        x, y = np.meshgrid(x, y)
        plt.contour(x, y, 0.1*x**2 + x + 0.2*y**2 - 0.3, [0])
        plt.contour(x, y, 0.2*x**2 + y - 0.1*x*y - 0.7, [0])
        plt.show()

    def solve(self, inputs):
        input1 = inputs[0]
        input2 = inputs[1]
        sumder1 = abs(self.firstderonx(float(input1))) + abs(self.firstderony(float(input2)))
        sumder2 = abs(self.secondderonx(float(input1), float(input2))) + abs(self.secondderony(float(input1)))
        if (sumder1 < 1) and (sumder2 < 1):
            self.a = float(input1)
            self.b = float(input2)
            input3 = inputs[2]
            self.accuracy = float(input3)
            caculateaccuracy = 10
            times = 0
            print("-------------------Method Simple iterate(for system)-------------------")
            print("%-10s %-10s %-10s %-10s %-10s" % ("x1", "x2", "φ(x1)", "φ(x2)", "max(|xi(k+1)-xi(k)|)"))
            while caculateaccuracy >= self.accuracy:
                times = times +1
                tempa = self.a
                tempb = self.b
                self.a = self.firstiterator(tempa, tempb)
                self.b = self.seconditerator(tempa, tempb)
                caculateaccuracy = max(abs(tempb - self.b), abs(tempa - self.a))
                print("%-10.4f %-10.4f %-10.4f %-10.4f %-10.4f" % (tempa, tempb, self.a, self.b, caculateaccuracy))
            print("------------------------------------------------------------------------")
            print("Iterated %d times" % times)
            print("Result for the first equation: %.4f" % self.getvalue1())
            print("Result for the second equation: %.4f" % self.getvalue2())
            print("Result x:%.4f" % self.a)
            print("Result y:%.4f" % self.b)
            self.draw(int(self.b-1), int(self.b+1))
        else:
            raise InputError("Near this point no solution")


def inputmodel():
    try:
        theFunction: AbstractFunction
        solver: AbstractSolver
        print("Please choose an equation from options:")
        print("1)3x^3+4.5x^2-2.3x-3=0")
        print("2)2.1e^x+x^2-4.2x-3.5=0")
        print("3)7/x-2.1x^2+3x+2=0")
        print("4)")
        print("0.1x^2+x+0.2y^2-0.3=0")
        print("0.2x^2+y-0.1xy-0.7=0")
        print("Attention! Option 4 is a system of equation. The only way to solve it is Simple iteration method")
        chooseEquation = input("Enter 1,2,3 or 4:\n")
        if (not chooseEquation.isdigit()) or (chooseEquation != "1" and chooseEquation != "2"
                                              and chooseEquation != "3" and chooseEquation != "4"):
            raise InputError("Please Enter 1-3 to choose the function you want")
        if chooseEquation == "1":
            theFunction = FirstFunction(3, 4.5, -2.3, -3)
        elif chooseEquation == "2":
            theFunction = SecondFunction(2.1, 1, -4.2, -8)
        elif chooseEquation == "3":
            theFunction = ThirdFunction(7, -2.1, 3, 2)
        else:
            input1 = input("Please input the start point of x:\n")
            if not is_digit(input1):
                raise InputError("Please input a number")
            input2 = input("Please input the start point of y:\n")
            if not is_digit(input2):
                raise InputError("Please input a number")
            input3 = input("Please input the accuracy:\n")
            if not is_digit(input3):
                raise InputError("Please input a number for accuracy")
            if float(input3) <=0:
                raise InputError("Accuracy should be bigger than 0")
            inputs = [input1, input2, input3]
            specialsolver = SolverForSystem()
            specialsolver.solve(inputs)
            return
        print("Please choose the method to solve equation:")
        print("1)Simple iteration method")
        print("2)The secant method")
        chooseMethod = input("Enter 1 or 2:\n")
        if not chooseMethod.isdigit() or (chooseMethod != "1" and chooseMethod != "2"):
            raise InputError("Please enter 1-3 to choose the method you want")
        if chooseMethod == "2":
            input1 = input("Please input a:\n")
            if not is_digit(input1):
                raise InputError("You should input a number for a")
            input2 = input("Please input b:\n")
            if not is_digit(input2):
                raise InputError("You should input a number for b")
            input3 = input("Please input accuracy:\n")
            if not is_digit(input3):
                raise InputError("You should input a number for accuracy")
            if chooseEquation == "3" and (input1 == "0" or input2 == "0"):
                raise InputError("'0' is not defined on equation with 1/x")
            if input3 == "0":
                raise InputError("Accuracy should be bigger than 0")
            inputs = [input1, input2, input3]
            solver = SecantSolver(theFunction)
            solver.solve(inputs)
        elif chooseMethod == "1":
            input1 = input("Please input the start point:\n")
            if not is_digit(input1):
                raise InputError("Please input a number for start point")
            if chooseEquation == "3" and input1 == "3":
                raise InputError("'0' is not defined on equation with 1/x")
            input2 = input("Please input accuracy:\n")
            if not is_digit(input2):
                raise InputError("You should input a number for accuracy")
            if input2 == "0":
                raise InputError("Accuracy should be bigger than 0")
            solver = SimpleIterate(theFunction)
            inputs = [input1, input2]
            solver.solve(inputs)
    except InputError as e:
        print(e)


def filefunctiondealer(theFunction: AbstractFunction, line):
    if line[1] == "1":
        solver = SimpleIterate(theFunction)
        if len(line[2:]) != 2:
            raise InputError("While you chose SimpleIterate, make sure "
                             "there only 2 elements(point and accuracy after choice function) in file")
        if float(line[3]) <= 0:
            raise InputError("Accuracy should bigger than 0")
        solver.solve(line[2:])
    elif line[1] == "2":
        solver = SecantSolver(theFunction)
        if len(line[2:]) != 3:
            raise InputError("While you chose SimpleIterate, make sure "
                             "there only 2 elements(point and accuracy after choice function) in file")
        if float(line[4]) <= 0:
            raise InputError("Accuracy should bigger than 0")
        solver.solve(line[2:])
    else:
        raise InputError("Choose the method 1 or 2")

def filemdoel():
    try:
        f = input("Please input file name:\n")
        fp = open(f, "r")
        for line in fp:
            choices = line.split(" ")
            for choice in choices:
                if not is_digit(choice):
                    raise InputError("Make sure that in file only have numbers and they are separated by ' '")
            if line[0] == "4":
                inputs = choices[1:]
                specialsolver = SolverForSystem()
                specialsolver.solve(inputs)
                fp.close()
                return
            elif choices[0] == "3":
                theFunction = ThirdFunction(7, -2.1, 3, 2)
                filefunctiondealer(theFunction, choices)
            elif choices[0] == "2":
                theFunction = SecondFunction(2.1, 1, -4.2, -8)
                filefunctiondealer(theFunction, choices)
            elif choices[0] == "1":
                theFunction = FirstFunction(3, 4.5, -2.3, -3)
                filefunctiondealer(theFunction, choices)
            else:
                raise InputError("First option should be 1-4")
    except FileNotFoundError as f:
        print("Sorry, such file not found")
    except InputError as e:
        print(e)

while True:
    choice = input("Please choose the input model 'file/input':\n")
    choice = choice.lower()
    if choice == "input":
        inputmodel()
        print("\n")
    elif choice == "file":
        filemdoel()
        print("\n")
    elif choice == "exit":
        sys.exit(1)

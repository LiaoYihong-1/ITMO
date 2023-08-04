from enum import Enum, unique

char = {
    ' ':0, 'a':1, 'b':2, 'c':3, 'd':4, 'e':5, 'f':6, 'g':7, 'h':8, 'i':9, 'j':10,
    'k':11, 'l':12, 'm':13, 'n':14, 'o':15, 'p':16, 'q':17, 'r':18, 's':19, 't':20, 'u':21,
    'v':22, 'w':23, 'x':24, 'y':25, 'z':26, 'A':27, 'B':28, 'C':29, 'D':30, 'E':31, 'F':32,
    'G':33, 'H':34, 'I':35, 'J':36, 'K':37, 'L':38, 'M':39, 'N':40, 'O':41, 'P':42, 'Q':43,
    'R':44, 'S':45, 'T':46, 'U':47, 'V':48, 'W':49, 'X':50, 'Y':51, 'Z':52, '':53, '0':54,
    '1':55, '2':56, '3':57, '4':58, '5':59, '6':60, '7':61, '8':62, '9':63, '!':64, ',':65,
    '.':66, '-':67, '*':68, '?':69, '+':70, '/':71, '@':72, '\0':73, '\n':74,
}
de_char = {
    0:' ', 1:'a', 2:'b', 3:'c', 4:'d', 5:'e', 6:'f', 7:'g', 8:'h', 9:'i', 10:'j',
    11:'k', 12:'l', 13:'m', 14:'n', 15:'o', 16:'p', 17:'q', 18:'r', 19:'s', 20:'t', 21:'u',
    22:'v', 23:'w', 24:'x', 25:'y', 26:'z', 27:'A', 28:'B', 29:'C', 30:'D', 31:'E', 32:'F',
    33:'G', 34:'H', 35:'I', 36:'J', 37:'K', 38:'L', 39:'M', 40:'N', 41:'O', 42:'P', 43:'Q',
    44:'R', 45:'S', 46:'T', 47:'U', 48:'V', 49:'W', 50:'X', 51:'Y', 52:'Z', 53:'', 54:'0',
    55:'1', 56:'2', 57:'3', 58:'4', 59:'5', 60:'6', 61:'7', 62:'8', 63:'9', 64:'!', 65:',',
    66:'.', 67:'-', 68:'*', 69:'?', 70:'+', 71:'/', 72:'@', 73:'\0', 74:'\n'
 }

def get_char(i:int):
    return char[i]

def char_var(i:str):
    assert len(i) == 1,'Function char_var() is used to translate only one char to it\'s value'
    return de_char[i]

@unique
class Register_Type(Enum):
    IP = "IP" ##计数
    BR = "BR" ##缓存，例如用于存储除法的余数等等
    AC = "AC" ##结果储存
    PS = "PS" ##nsvc
    SP = "SP" ##堆栈
    AR = "AR" ##地址
    IR = "IR"
    

@unique
class Instruction_Type(Enum):
    #math
    ADD = "ADD"
    SUB = "SUB"
    CMP = "CMP"
    DIV = "DIV"
    MUL = "MUL"
    INV = "INV"
    #data control
    LD = 'LD'
    ST = 'ST'
    ## stack
    PUSH = "PUSH"
    POP = "POP"
    ## skip to somewhere
    JMP = "JMP"
    JNZ = "JNZ"
    JZ = "JZ"
    JS = "JS"
    CALL = "CALL"
    RET = "RET"
    ## STOP
    HLT = "HLT"

MATH_INSTRUCTION = (Instruction_Type.ADD, Instruction_Type.SUB, Instruction_Type.INV, Instruction_Type.CMP,Instruction_Type.DIV,Instruction_Type.MUL)
DATA_INSTRUCTION = (Instruction_Type.LD, Instruction_Type.ST)
STACK_INSTRUCTION = (Instruction_Type.POP,Instruction_Type.PUSH)
JUMP_INSTRUCTION = (Instruction_Type.JZ, Instruction_Type.JMP, Instruction_Type.JNZ, Instruction_Type.CALL, Instruction_Type.JS, Instruction_Type.RET)
NO_ARGUMENT = (Instruction_Type.PUSH, Instruction_Type.POP, Instruction_Type.RET, Instruction_Type.INV, Instruction_Type.HLT)

class Instruction:
    def __init__(self, instruction: Instruction_Type, args: []):
        self.instruction = instruction
        self.args = args


    def to_string(self)->str:
        result = ""
        result = result + self.instruction.value
        k = 0
        for i in self.args:
            if k == 0:
                result = result + " " + i
                k += 1
            else:
                result = result + " " + i
        return result

@unique
class Position(Enum):
    INS = 1 ##instructions
    FUN = 2 ##Function
    LAB = 3 ##label
    VAR = 4 ##variable

def read_code(filename:str) -> {}:
    program = {'Instruction':[],'Variable':{},'Function':{}}
    position = Position.INS
    with open(filename,"r") as f:
        line = f.readline()
        assert line != "", "You open a file, whose format is not property"
        while line:
            line = line.replace("\n","")
            if line == "FUNCTION":
                position = Position.FUN
            elif line == "LABEL":
                position = Position.LAB
            elif line == "VARIABLE":
                position = Position.VAR
            else:
                ins: Instruction
                if position == Position.INS:
                    term = line.split(" ")
                    ins_type = Instruction_Type[term[1]]
                    while "" in term:
                        term.remove("")
                    if term[1] == 'HLT':
                        ins = Instruction(ins_type,[])
                    else:
                        ins = Instruction(ins_type,term[2:])
                    program['Instruction'].append(ins)
                elif position == Position.FUN:
                    term = line.split(":")
                    while "" in term:
                        term.remove("")
                    program['Function'][term[0]] = dict()
                    program['Function'][term[0]]['self'] = int(term[1])
                elif position == Position.LAB:
                    term = line.split(":")
                    while "" in term:
                        term.remove("")
                    program['Function'][term[0]][term[1]] = int(term[2])
                elif position == Position.VAR:
                    term = line.split(":", 1)
                    while "" in term:
                        term.remove("")
                    program['Variable'][term[0]] = term[1]
            line = f.readline()
    return program



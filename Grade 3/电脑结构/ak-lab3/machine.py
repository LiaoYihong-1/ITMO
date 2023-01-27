from ISA import Register_Type, read_code, Instruction, Instruction_Type, de_char, char, MATH_INSTRUCTION, \
    STACK_INSTRUCTION, DATA_INSTRUCTION
import re

N = 1
Z = 2
V = 4
C = 8
MAX = 2 ** 31 - 1  # 2^31-1
MIN = -2 ** 31  # -2^31


class Cell:
    def __init__(self):
        self.value = -1
        self.ins = Instruction(Instruction_Type.ADD, [])


def check_string(re_exp: str, target: str) -> bool:
    res = re.search(re_exp, target)
    if res:
        return True
    else:
        return False


class Register:
    def __init__(self, type: Register_Type):
        self.name = type
        if type == Register_Type.SP:
            self.value = 256
        else:
            self.value = 0

    # return an int or an instruction
    def get_value(self):
        return self.value

    # set an int or an instruction
    def set_value(self, value):
        self.value = value

    def to_string(self):
        if isinstance(self.value, Instruction):
            return self.value.to_string()
        else:
            return str(self.value)


class Buffer:
    def __init__(self, size):
        self.buf = [-1] * size
        self.read_pointer = 0
        self.write_pointer = 0
        self.size = size


class DataPath:
    def __init__(self, size: int, inputfile: str):
        self.memory = [Cell()] * size
        self.stack = [0] * 256
        self.size = size
        self.input_buffer = Buffer(256)
        self.output_buffer = Buffer(256)
        self.registers = {
            'BR': Register(Register_Type.BR),
            'AC': Register(Register_Type.AC),
            'SP': Register(Register_Type.SP),
            'PS': Register(Register_Type.PS),
            'IP': Register(Register_Type.IP),
            'AR': Register(Register_Type.AR),
            'IR': Register(Register_Type.IR)
        }
        self.input_index = int(size * 3 / 4 - 1)
        self.output_index = self.input_index
        self.io_part = int(size * 3 / 4 - 1)
        if inputfile != '':
            with open(inputfile) as f:
                c = f.read()
                for i in c:
                    assert self.input_index < self.size,'Input file too large!'
                    self.set_value_memory(self.input_index,char[i])
                    self.input_index += 1

    def set_value_memory(self, index: int, value: int):
        old_cell = self.memory[index]
        new_cell = Cell()
        new_cell.ins = old_cell.ins
        new_cell.value = value
        self.memory[index] = new_cell

    def get_value_register(self, choice: str):
        return self.registers[choice].get_value()

    def set_value_register(self, choice: str, value):
        self.registers[choice].set_value(value)

    def add_value_register(self, choice: str, value: int):
        self.registers[choice].add(value)

    def min_value_register(self, choice: str, value: int):
        self.registers[choice].min(value)

    def get_string_register(self, choice: str) -> str:
        return self.registers[choice].to_string()

    def get_value_memory(self, index: int) -> int:
        return self.memory[index].value

    def print_registers(self):
        print("BR:%s, AC:%s, SP:%s, PS:%s, IP:%s, AR:%s, IR:%s" %
              (self.get_string_register('BR'), self.get_string_register('AC'),
               self.get_string_register('SP'), self.get_string_register('PS'), self.get_string_register('IP'),
               self.get_string_register('IP'), self.registers['IR'].to_string()),end="")


class ALU:
    def __init__(self):
        self.left = 0
        self.right = 0
        self.nzvc = 0

    @staticmethod
    def add(left: int, right: int):
        result = left + right
        if left + right > MAX:
            result = result & (2 ** 31 - 1)
            result = -2 ** 31 + result
        elif left + right < MIN:
            result = -left - right
            result = result & (2 ** 31 - 1)
            result = result - 1
        return result

    @staticmethod
    def min(left: int, right: int):
        result = left - right
        if left - right > MAX:
            result = result & (2 ** 31 - 1)
            result = -2 ** 31 + result
        elif left - right < MIN:
            result = -left - right
            result = result & (2 ** 31 - 1)
        return result

    @staticmethod
    def div(left: int, right: int):
        return int(left / right)

    @staticmethod
    def mul(left: int, right: int):
        return left * right

    @staticmethod
    def or_operation(left: int, right: int):
        return left | right

    @staticmethod
    def inversion(left: int, right: int):
        return -(left + right)

    def min_one(self):
        self.right = self.get_right() - 1

    def add_one(self):
        self.right = self.get_right() + 1

    def act(self, f):
        if f == ALU.add:
            if self.left > 0 and self.right > 0 and f(self.left, self.right) < 0:
                self.nzvc = V + C + N
            elif self.left < 0 and self.right < 0 and f(self.left, self.right) >= 0:
                if f(self.left, self.right) == 0:
                    self.nzvc = Z + V + C
                else:
                    self.nzvc = V + C
            else:
                if f(self.left, self.right) == 0:
                    self.nzvc = Z
                if f(self.left, self.right) < 0:
                    self.nzvc = N
        elif f == ALU.min:
            if self.left > 0 and self.right < 0 and f(self.left, self.right) < 0:
                self.nzvc = V + C + N
            elif self.left < 0 and self.right > 0 and f(self.left, self.right) >= 0:
                if f(self.left, self.right) == 0:
                    self.nzvc = Z + V + C
                else:
                    self.nzvc = V + C
            else:
                if f(self.left, self.right) == 0:
                    self.nzvc = Z
                if f(self.left, self.right) < 0:
                    self.nzvc = N
        return f(self.get_left(), self.get_right())

    def put_left(self, value: int):
        self.left = value

    def put_right(self, value: int):
        self.right = value

    def get_left(self):
        v = self.left
        self.left = 0
        return v

    def get_right(self):
        v = self.right
        self.right = 0
        return v


class CPU:
    def __init__(self, datapath: DataPath, program: {}):
        self.program = program
        self.fun = {}
        self.var = {}
        self.position = []
        self.datapath = datapath
        self.tick = 0
        self.alu = ALU()

    def tick_tick(self):
        self.tick += 1

    def current_tick(self):
        return self.tick

    def decode(self):
        self.fun = self.program['Function']
        end = 0
        for i in self.program['Instruction']:
            new_cell = Cell()
            new_cell.ins = i
            self.datapath.memory[end] = new_cell
            end += 1
        end = len(self.program['Instruction'])
        for i in self.program['Variable']:
            self.var[i] = end
            if self.program['Variable'][i].isdigit():
                v = int(self.program['Variable'][i])
                assert MAX >= v >= MIN, "Input value of variable {} is out of range".format(i)
                new_cell = Cell()
                new_cell.value = v
                self.datapath.memory[end] = new_cell
                end += 1
            else:
                # else:
                string = self.program['Variable'][i].rsplit(',')
                length = int(string[1])
                v = string[0]
                v = v[1:len(v) - 1]
                i = 0
                while i < length:
                    new_cell = Cell()
                    if i < len(v):
                        new_cell.value = char[v[i]]
                        self.datapath.memory[end] = new_cell
                    end += 1
                    i += 1

    def read_ins(self):
        # IP->AR
        self.alu.put_right(self.datapath.get_value_register('IP'))
        r = self.alu.act(ALU.or_operation)
        self.datapath.set_value_register('AR', r)
        self.tick_tick()
        # IP + 1 -> IP, [AR] -> IR
        self.datapath.set_value_register("IP", self.datapath.get_value_register('IP') + 1)
        self.tick_tick()
        self.datapath.set_value_register('IR',self.datapath.memory[self.datapath.get_value_register("AR")].ins)

    def read_var(self, var: str):
        # VAR -> AR
        pos = self.var[var]
        self.alu.put_right(pos)
        self.datapath.set_value_register("AR", self.alu.get_right())
        self.tick_tick()
        return self.datapath.get_value_memory(self.datapath.get_value_register("AR"))

    # get proper value
    def addressing(self, ins: Instruction):
        arg = ins.args[0]
        if arg.isdigit():
            # Decoder -> AR
            self.datapath.set_value_register("AR", int(arg))
            self.tick_tick()
            return self.datapath.get_value_memory(int(arg))
        elif check_string("^#-?[1-9][0-9]*", arg) or check_string("^#0$", arg):
            return int(arg[1:])
        elif check_string("^\'.{1}\'$", arg) or arg == '\'\'':
            if arg == '\'\'':
                return char['']
            else:
                return char[arg[1]]
        else:
            assert arg in self.var.keys(), 'You use a variable {} which is not defined before'.format(arg)
            return self.read_var(arg)

    def math(self, ins: Instruction, opr):
        self.alu.put_right(self.addressing(ins))
        self.alu.put_left(self.datapath.get_value_register("AC"))
        self.tick_tick()
        if opr == ALU.div:
            # result ->　AC
            r= self.alu.act(opr)
            self.datapath.set_value_register("AC", r)
            self.tick_tick()
        else:
            # result ->　AC
            result = self.alu.act(opr)
            self.datapath.set_value_register("AC", result)
            self.tick_tick()

    def set_nzvc(self, var: int):
        self.alu.nzvc = var

    def get_nzvc(self):
        return self.alu.nzvc

    def ins_execute(self, ins: Instruction, position: str) -> int:
        if ins.instruction == Instruction_Type['HLT']:
            return 1
        elif ins.instruction in MATH_INSTRUCTION:
            if ins.instruction == Instruction_Type['ADD']:
                self.math(ins, ALU.add)
                self.datapath.set_value_register("PS", self.get_nzvc())
            elif ins.instruction == Instruction_Type['SUB']:
                self.math(ins, ALU.min)
                self.datapath.set_value_register("PS", self.get_nzvc())
            elif ins.instruction == Instruction_Type['MUL']:
                self.math(ins, ALU.mul)
            elif ins.instruction == Instruction_Type['DIV']:
                self.math(ins, ALU.div)
            elif ins.instruction == Instruction_Type['INV']:
                # -AC->AC, nzvc -> PS
                self.alu.put_left(self.datapath.get_value_register("AC"))
                self.tick_tick()
                self.datapath.set_value_register("AC", self.alu.act(ALU.inversion))
                self.set_nzvc(Z)
                self.datapath.set_value_register("PS", self.get_nzvc())
                self.tick_tick()
            # CMP
            else:
                self.alu.put_right(self.addressing(ins))
                # AC - arg to check nzcv -> PS
                self.alu.put_left(self.datapath.get_value_register("AC"))
                self.alu.act(ALU.min)
                self.datapath.set_value_register("PS", self.get_nzvc())
                self.tick_tick()
        elif ins.instruction in DATA_INSTRUCTION:
            arg = ins.args[0]
            if ins.instruction == Instruction_Type['LD']:
                assert arg != 'OUTPUT', 'Instruction LD can\'t call OUTPUT'
                if arg != 'INPUT':
                    self.datapath.set_value_register('AC', self.addressing(ins))
                    self.tick_tick()
                elif arg == 'INPUT':
                    # index -> AR， io->ac
                    assert self.datapath.output_index < self.datapath.size, 'Read input out of range!'
                    self.datapath.set_value_register("AR", self.datapath.output_index)
                    self.datapath.output_index += 1
                    self.tick_tick()
                    self.datapath.set_value_register("AC",self.datapath.memory[self.datapath.get_value_register('AR')].value)
                    self.tick_tick()
            # ST
            else:
                assert arg != 'INPUT', 'Instruction ST can\'t call input'
                if arg != 'OUTPUT':
                    assert arg in self.var.keys() or check_string("^[1-9][0-9]*",
                                                                  arg), 'You have to declare where you want to save the value by declaring a variable or address'
                    ## arg ->　ar
                    if arg in self.var.keys():
                        self.alu.put_right(self.var[arg])
                    else:
                        self.alu.put_right(int(arg))
                    self.datapath.set_value_register("AR", self.alu.act(ALU.or_operation))
                    ## AC -> [AR]
                    self.datapath.set_value_memory(self.datapath.get_value_register("AR"), self.datapath.get_value_register("AC"))
                    self.tick_tick()
                else:
                    # AC -> outputbuffer
                    self.datapath.output_buffer.buf[self.datapath.output_buffer.write_pointer] = self.datapath.get_value_register('AC')
                    self.datapath.output_buffer.write_pointer += 1
                    self.tick_tick()
        elif ins.instruction in STACK_INSTRUCTION:
            if ins.instruction == Instruction_Type.PUSH:
                # SP-1 -> SP
                self.datapath.set_value_register('SP',self.datapath.get_value_register('SP') - 1)
                self.tick_tick()
                # AC -> STACK[SP]
                self.datapath.stack[self.datapath.get_value_register("SP")] = self.datapath.get_value_register("AC")
                self.tick_tick()
            else:
                # [SP] -> AC
                self.datapath.set_value_register("AC", self.datapath.stack[self.datapath.get_value_register("SP")])
                self.tick_tick()
                # SP + 1 -> SP
                self.datapath.set_value_register('SP',self.datapath.get_value_register('SP') - 1)
                self.tick_tick()
        else:
            if ins.instruction == Instruction_Type.JMP:
                ## Decoder -> IP
                arg = ins.args[0]
                assert arg in self.fun[
                    position].keys(), "You are trying jump to a label which is not in his own function"
                self.datapath.set_value_register("IP", self.fun[position][arg])
                self.tick_tick()
            elif ins.instruction == Instruction_Type.CALL:
                # AC->BR save parameter
                self.alu.put_left(self.datapath.get_value_register('AC'))
                self.datapath.set_value_register("BR", self.alu.act(ALU.or_operation))
                self.tick_tick()
                # IP ->　AC
                arg = ins.args[0]
                assert arg in self.fun.keys(), "You are trying call a not existed function"
                self.alu.put_right(self.datapath.get_value_register('IP'))
                self.datapath.set_value_register("AC", self.alu.act(ALU.or_operation))
                self.tick_tick()
                # push
                new_ins = Instruction(Instruction_Type.PUSH, [])
                self.ins_execute(new_ins, position)
                # Decoder -> IP
                self.datapath.set_value_register("IP", self.fun[arg]['self'])
                self.position.append(arg)
                self.tick_tick()
                # BR->AC save parameter
                self.alu.put_left(self.datapath.get_value_register('BR'))
                self.datapath.set_value_register("AC", self.alu.act(ALU.or_operation))
                self.tick_tick()
            elif ins.instruction == Instruction_Type.RET:
                # AC->BR make sure that result of function is saved
                self.datapath.set_value_register("BR", self.datapath.get_value_register("AC"))
                self.tick_tick()
                # pop
                new_ins = Instruction(Instruction_Type.POP, [])
                self.ins_execute(new_ins, self.position[-1])
                self.position.pop()
                # AC -> IP
                self.alu.put_left(self.datapath.get_value_register("AC"))
                self.datapath.set_value_register("IP", self.alu.act(ALU.or_operation))
                self.tick_tick()
                # BR->AC
                self.datapath.set_value_register("AC", self.datapath.get_value_register("BR"))
                self.tick_tick()
            elif ins.instruction == Instruction_Type.JZ:
                if self.datapath.get_value_register("PS") | Z == Z and self.datapath.get_value_register("PS")!=0:
                    ins_2 = Instruction(Instruction_Type.JMP, args=ins.args)
                    self.ins_execute(ins_2, position)
            elif ins.instruction == Instruction_Type.JS:
                if self.datapath.get_value_register("PS") | N == N and self.datapath.get_value_register("PS")!=0:
                    ins_2 = Instruction(Instruction_Type.JMP, args=ins.args)
                    self.ins_execute(ins_2, position)
            # JNZ
            else:
                if self.datapath.get_value_register("PS") != Z:
                    ins_2 = Instruction(Instruction_Type.JMP, args=ins.args)
                    self.ins_execute(ins_2, position)
        return 0

    def run_ins(self, position: str) -> int:
        ins: Instruction
        self.read_ins()
        ins = self.datapath.get_value_register("IR")
        result = self.ins_execute(ins, position)
        print("DEBUG:root:{ ",end="")
        print("Tick:{}".format(self.tick),end=", ")
        self.datapath.print_registers()
        print(" }",end = "  ")
        print(ins.to_string())
        return result

    def run(self):
        start = '_START'
        self.position.append(start)
        while True:
            r = self.run_ins(position=self.position[-1])
            if r == 1:
                break
        print("Output:")
        first = True
        output_result = True
        result = ""
        for i in self.datapath.output_buffer.buf:
            if first:
                first = False
                if i == -1:
                    output_result = False
            if i != -1:
                print(de_char[i],end="")
                result = result + de_char[i]
            else:
                break
        print()
        if output_result:
            return result
        else:
            return str(self.datapath.get_value_register("AC"))


def start(sourcefile, inputfile):
    program = read_code(sourcefile)
    datapath = DataPath(256,inputfile)
    cpu = CPU(program=program, datapath=datapath)
    cpu.decode()
    out = cpu.run()
    return out


if __name__ == "__main__":
    import sys

    assert len(sys.argv) == 3, 'Please only input the name of one file after compiling'
    start(sys.argv[1], sys.argv[2])

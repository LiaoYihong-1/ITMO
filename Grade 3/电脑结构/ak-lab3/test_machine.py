import machine,translator
import unittest

class MyTest(unittest.TestCase):
    def setUp(self) -> None:
        print("Test beginning:")

    def tearDown(self) -> None:
        print("Test finished.")

    def test_cat(self):
        print("Testing cat")
        translator.translate('./asm/cat.asm','./asm/target')
        result = machine.start('./asm/target','./asm/test.txt')
        text = ''
        with open('./asm/test.txt') as f:
            text = f.read()
        print(text)
        print(result)
        self.assertEqual(result,text)


    def test_prob5(self):
        print("Testing prob5")
        translator.translate("./asm/prob5.asm","./asm/target")
        result = machine.start("./asm/target",'')
        self.assertEqual(int(result),232792560)

    def test_hello(self):
        print("Testing hello")
        translator.translate("./asm/hello.asm","./asm/target")
        result = machine.start("./asm/target",'')
        self.assertEqual(result,"Hello,world")

    def test_prob2(self):
        print("Testing prob2")
        translator.translate("./asm/prob2.asm","./asm/target")
        result = machine.start("./asm/target",'')
        self.assertEqual(int(result),4613732)

if __name__ == '__main__':
    unittest.main()

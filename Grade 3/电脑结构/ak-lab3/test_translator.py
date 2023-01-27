import translator
import unittest
import filecmp


class MyTest(unittest.TestCase):
    def setUp(self) -> None:
        print("Test of translator beginning:")

    def tearDown(self) -> None:
        print("Test of translator finished.")

    def test_prob5(self):
        print("Testing prob5")
        translator.translate("./asm/prob5.asm","result")
        status = filecmp.cmp('result','./target/prob5')
        self.assertEqual(status,True)

    def test_hello(self):
        print("Testing hello")
        translator.translate("./asm/hello.asm","result")
        status = filecmp.cmp('result','./target/hello')
        self.assertEqual(status,True)

    def test_prob2(self):
        print("Testing prob2")
        translator.translate("./asm/prob2.asm","result")
        status = filecmp.cmp('result','./target/prob2')
        self.assertEqual(status,True)

    def test_cat(self):
        print("Testing cat")
        translator.translate("./asm/cat.asm","result")
        status = filecmp.cmp('result','./target/cat')
        self.assertEqual(status,True)


if __name__ == '__main__':
    unittest.main()
import unittest
import jumble 

class TrieTest(unittest.TestCase):
    def setUp(self):
        self.dictionary = jumble.Node()
        self.dictionary.add("Chalupa")
        self.dictionary.add("Rutabaga")
        self.dictionary.add("Halitosis")
        self.dictionary.add("Cornucopia")
        
    def test_contains(self):
        self.assertTrue(self.dictionary.contains("Rutabaga"))
        self.assertFalse(self.dictionary.contains("Potato"))
        self.assertTrue(self.dictionary.contains(""))
        self.assertTrue(self.dictionary.contains("Hali"))
    def test_is_word(self):
        self.assertTrue(self.dictionary.isWord("Chalupa"))
        self.assertFalse(self.dictionary.isWord("Rutab"))
        self.assertFalse(self.dictionary.isWord("Amsterdam"))
        self.assertFalse(self.dictionary.isWord(""))
        
if __name__ == '__main__':
    unittest.main()

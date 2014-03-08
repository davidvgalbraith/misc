#A trie, to be used as a dictionary
class Node:
    def __init__(self):
        self.children = {}

    def contains(self, word):
        if word == "":
            return True
        if word[0] in self.children:
            return self.children[word[0]].contains(word[1:])
        return False
    
    def isWord(self, word):
        if word == "":
            return ":)" in self.children
        if word[0] in self.children:
            return self.children[word[0]].isWord(word[1:])
        return False

    def add(self, word):
        if word == "": 
            self.children[":)"] = ""
        else:
            if word[0] not in self.children:
                self.children[word[0]] = Node()
                self.children[word[0]].add(word[1:])
            else:
                self.children[word[0]].add(word[1:])
    def getChildren(self):
        return self.children

#get all the elements in dictionary whose length is length
def lenfilt(dictionary, length):
    ret = {}
    for x in dictionary:
        if len(x) == length:
            ret[x] = dictionary[x]
    return ret

#Print a set without set([ garble
def printify(s):
    for t in s:
        print(t + " "),
    print ""

if __name__ == '__main__':
    f = open("words.txt", 'r')
    dictionary = Node()
    print ("Loading dictionary, hold your horses...")
    for line in f:
        dictionary.add(line.rstrip())
    while True:
        word = raw_input("Please enter a word: ")
        words = set()
        prefixes = {}
        prefixes[""] = word
        for a in range (0, len(word)):
            aprefixes = lenfilt(prefixes.copy(), a)
            for w in aprefixes:
                for b in range(0, len(prefixes[w])):
                    newword = w + prefixes[w][b]
                    if dictionary.contains(newword):
                        #value of a prefix is the characters still available to complete it
                        prefixes[newword] = prefixes[w][:b] + prefixes[w][b+1:]
                    if dictionary.isWord(newword):
                        words.add(newword)
        print "The subwords of " + word + " are: "
        printify(words)

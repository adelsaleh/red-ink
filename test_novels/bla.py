f = open('aesop11.txt')
import string
words = f.read()
words = "".join([i for i in words if i not in string.punctuation]).split(" ")
wordnext = []
import enchant
us = enchant.Dict("en_US")
gb = enchant.Dict("en_GB")
for word in words:
    l = []
    if '\r\n' in word:
        parts = word.split('\r\n')
        parts = filter(lambda x: x!='', parts)
        if parts:
            if not (us.check(parts[0]) or gb.check(parts[0])) and not (us.check(parts[-1]) or gb.check(parts[-1])):
                l = [''.join(parts)]
            else:
                l = parts
    else:
        l = [word]
    wordnext.extend(l)
words = wordnext
words = filter(lambda x: x != '', words)
words = [word.lower() for word in words]
index = words.index('immediately')
for i in range(index-100, index+100):
    print 'assertTrue(words[{0}].getWord().equals("{1}"));'.format(str(i-441), words[i])

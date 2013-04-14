from HTMLParser import HTMLParser

class MyHTMLParser(HTMLParser):
   def handle_starttag(self, tag, attrs):
	  if tag in ['table']:
		print "Found transaction"
   
  # def handle_endtag(self, tag):
   #   print 'endtag: ', tag
  # def handle_data(self, data):
 #     print 'data: ', data

f = open('../transInfo.txt', 'r')
data = f.read()

parser = MyHTMLParser()
parser.feed(data)
linenum = parser.getpos()
print linenum
import sys
	
f = open("test.txt", "w")
f.write(str(sys.argv[1]))
f.write('\n')
f.close()

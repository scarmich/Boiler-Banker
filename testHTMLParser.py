import urllib
import urllib2
import cookielib

url = 'http://www.google.com'

values = {'name' : 'Shawn Carmichael',
      'location' : 'West Lafayette',
      'language' : 'Python',
      'password' : 'abcd1234'}

#create a cookie jar to keep cookies sent from HTTP get requests
jar = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(jar))

#encode credentials
data = urllib.urlencode(values)

#request and open url buffer

reqPage = urllib2.Request(url)
#when calling urllib2.Request(), override with (url, data) if giving the website credentials

pageInfo = opener.open(reqPage)
#pageInfo = urllib2.urlopen(reqPage)

#read url info into buffer
content = pageInfo.read()

print content

print ''
print 'Encoded data:'
print '----------------'
print data
print ''
print 'Cookies:'
print '----------------'
print jar

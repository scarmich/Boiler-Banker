import urllib
import urllib2
import cookielib

#Test server url information for testing scraper
url = 'http://web.ics.purdue.edu/~powerst/systfp/login.php'
destUrl = 'http://web.ics.purdue.edu/~powerst/systfp/home.php'

username = 'Shawn'
password = 'abc'

values = {'username' : username,
      'password' : password}

#create a cookie jar to keep cookies sent from HTTP get requests
jar = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(jar))

#encode credentials to ready for GET request
login_cred = urllib.urlencode(values)

#Send a POST request to destination URL
opener.open(url, login_cred)

#Send a GET request to destination URL
reqPage = opener.open(destUrl)

#store contents of destination URL
content = reqPage.read()

print content

print ''
print 'Encoded data:'
print '----------------'
print login_cred
print ''
print 'Cookies:'
print '----------------'
print jar

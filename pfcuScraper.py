import urllib
import urllib2
import cookielib

loginUrl =    'https://homebanking.purduefed.com/OnlineBanking/Login.aspx'
passwordUrl = 'https://homebanking.purduefed.com/OnlineBanking/AOP/Password.aspx'
acctUrl =     'https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx'

LoginName = 'abravoset'
Password = 'CS307project'

values = {'LoginName' : LoginName,
      'Password' : Password}

#Handles HTTP Redirection
class MyHTTPRedirectHandler(urllib2.HTTPRedirectHandler):
   def http_error_302(self, req, fp, code, msg, headers):
      print "Cookie Manip Right Here"
      return urllib2.HTTPRedirectHandler.http_error_302(self, req, fp, code, msg, headers)

   http_error_301 = http_error_303 = http_error_307 = http_error_302

password_mgr = urllib2.HTTPPasswordMgrWithDefaultRealm()
password_mgr.add_password(None, passwordUrl, LoginName, Password)

handler = urllib2.HTTPBasicAuthHandler(password_mgr)

#create a cookie jar to keep cookies sent from HTTP get requests
jar = cookielib.CookieJar()
cookieprocessor = urllib2.HTTPCookieProcessor(jar)

#Create build_opener with the redirect handler, cookie handler, and header info
opener = urllib2.build_opener(MyHTTPRedirectHandler, cookieprocessor, handler)
urllib2.install_opener(opener)
opener.addheaders = [('User-agent', 'Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5')]

#encode credentials
login_cred = urllib.urlencode(values)

opener.addheaders = [('Referer', loginUrl)]

#POST to loginURL with credentials
response = opener.open(loginUrl, login_cred)

#GET passwordURL HTML information
reqPage = opener.open(passwordUrl)

opener.addheaders = [('Referer', passwordUrl)]

#POST to passwordURL with credentials
response2 = opener.open(passwordUrl, login_cred)

#THIS DOESN'T WORK: Suspicion is session is reseting.
#acctUrl forwards to the Login screen if incorrect or no credientials
#are supplied, which is what is happening on the following GET request

#GET acctURL HTML information
reqPage2 = opener.open(acctUrl)

#Read the content of the final page
content = reqPage2.read()

print content

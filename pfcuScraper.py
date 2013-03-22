import urllib
import urllib2
import cookielib

loginUrl = 'https://homebanking.purduefed.com/OnlineBanking/Login.aspx'
passwordUrl = 'https://homebanking.purduefed.com/OnlineBanking/AOP/Password.aspx'
acctUrl = 'https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx'

LoginName = 'abravoset'
Password = 'CS307project'

values = {'LoginName' : LoginName}
values2 = {'Password' : Password}

#create a cookie jar to keep cookies sent from HTTP get requests
jar = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(jar))

#encode credentials
login_cred = urllib.urlencode(values)
pass_cred = urllib.urlencode(values2)

#log in to the guard url
response = opener.open(loginUrl, login_cred)

#Grabs the password entry page
reqPage = opener.open(passwordUrl)

#enter password into second guard url
response2 = opener.open(passwordUrl, pass_cred)

#retrieve user home page HTML code
reqPage2 = opener.open(acctUrl)

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

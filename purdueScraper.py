import requests

loginUrl = 'https://homebanking.purduefed.com/OnlineBanking/Login.aspx'
passwordUrl = 'https://homebanking.purduefed.com/OnlineBanking/AOP/Password.aspx'
accountUrl = 'https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx'

testUrl = 'http://web.ics.purdue.edu/~powerst/systfp/login.php'
destUrl = 'http://web.ics.purdue.edu/~powerst/systfp/home.php'

tN = 'Shawn'
tP = 'abc'

val = {'username' : tN, 'password' : tP}

loginName = 'abravoset'
password = 'CS307project'

values = {'LoginName' : loginName, 'Password' : password}
headers = {'User-agent' : 'Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5'}
r = requests.post(testUrl, data=val, headers=headers)
#r2 = requests.post(accountUrl, auth=HTTPDigestAuth(loginName, password), headers=headers)

print r.url


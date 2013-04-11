import requests

loginUrl = 'https://homebanking.purduefed.com/OnlineBanking/Login.aspx'
passwordUrl = 'https://homebanking.purduefed.com/OnlineBanking/AOP/Password.aspx'
acctUrl = 'https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx'

loginName = 'abravoset'
password = 'CS307project'

values = {'LoginName' : loginName, 'Password' : password}
headers = {'User-agent': 'Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5'}

r = requests.post(loginUrl, data=values, headers=headers)
r2 = requests.post(passwordUrl, data=values, headers=headers)
print r.url
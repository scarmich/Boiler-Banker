import requests
from bs4 import BeautifulSoup

loginUrl = 'https://homebanking.purduefed.com/OnlineBanking/Login.aspx'
passwordUrl = 'https://homebanking.purduefed.com/OnlineBanking/AOP/Password.aspx'
acctUrl = 'https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx'

loginName = 'abravoset'
password = 'CS307project'

values = {'LoginName' : loginName, 'Password' : password}
<<<<<<< HEAD
headers = {'User-agent': 'Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5'}

r = requests.post(loginUrl, data=values, headers=headers)
r2 = requests.post(passwordUrl, data=values, headers=headers)
print r.url
=======
headers = {'User-agent' : 'Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5'}

r = requests.get(loginUrl, headers=headers)

# Stores HTML of r to soup
soup = BeautifulSoup(r.text)

l = list()


# Set to loop through soup to find necessary input params
for item in soup.find_all('input'):
	if 'name' in item.attrs:
		if "LoginName" in item.attrs['name'] :
			item.attrs['value'] = loginName
		l.append({item.attrs['name'] : (item.attrs['value'] if 'value' in item.attrs else '')})	

print l

r2 = requests.post(loginUrl, header=headers, params=l)
>>>>>>> 4fe467bc3a90856a26edde1e29cc735e92f1cb59

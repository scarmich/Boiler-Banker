import requests
from bs4 import BeautifulSoup

loginUrl = 'https://homebanking.purduefed.com/OnlineBanking/Login.aspx'
passwordUrl = 'https://homebanking.purduefed.com/OnlineBanking/AOP/Password.aspx'
accountUrl = 'https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx'

loginName = 'abravoset'
password = 'CS307project'

values = {'LoginName' : loginName, 'Password' : password}
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

r2 = requests.post(loginUrl, params=l, headers=headers)

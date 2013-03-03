import mechanize
import cookielib

username = 'shawn'
password = 'abc'

br = mechanize.Brower()

cookies = cookielib.LWPCookieJar()
br.set_cookiejar(cookies)

br.set_handle_equiv(True)
br.set_handle_redirect(True)
br.set_handle_referer(True)
br.set_handle_robots(False)
br.set_debug_http(False)
br.set_debug_responses(False)
br.set_debug_redirects(False)
br.set_handle_refresh(mechanize._http.HTTPRefreshProcessor(), max_time = 1)
br.addheaders = [('User-agent', 'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.1) Gecko/2008071615 Fedora/3.0.1-1.fc9 Firefox/3.0.1')]

br.open('http://web.ics.purdue.edu/~powerst/systfp/index.php')

br.select_form(nr=1)

br['session[username]'] = username
br['session[password]'] = password
br.submit()
print(br.response().read())

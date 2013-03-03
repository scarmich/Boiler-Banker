import urllib2
import cookielib
import re
import ClientForm

class PFCUForm:
   def __init__(self, username, password):
      self.username = username
      self.password = password
      self.url = 'http://web.ics.purdue.edu/~powerst/systfp/index.php'

      cookiejar = cookielib.LWPCookieJar()
      cookiejar = urllib2.HTTPCookieProcessor(cookiejar)
      # debugger = urllib2.HTTPHandler(debuglevel=1)

      opener = urllib2.build_opener(cookiejar)
      urllib2.install_opener(opener)
      
   def login(self):

      response = urllib2.urlopen(self.url)
      forms = ClientForm.ParseResponse(response, backwards_compat=False)

      # forms[0] is 'GET', forms[1] is 'POST'
      form = forms[1]

      try:
         form['form[username]'] = self.username
         form['form[password]'] = self.password
      except Exception, e:
         print 'The following error occured: \n"%s"' % e
         print
         print 'A good idea is to open a browser and see if you can log in from there.'
         print 'URL:', self.url
         raw_input()
         exit()

      self.page = urllib2.urlopen(form.click('login')).read()

Scraper = PFCUForm('shawn', 'abc')

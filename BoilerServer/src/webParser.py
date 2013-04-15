# Given the user name...
User = 'abravoset'

# open a file in the UserTransaction folder with the <username>.txt as the filename
f = open('UserTransactions/' + User + '.txt', 'w')

with open('../transInfo.txt') as input_data:
	# Skips text before transaction data
	for line in input_data:
		if line.strip() == '<td align=\"center\"><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl01\',\'\')\">Date</a><img src=\"/OnlineBanking/images/ArrowUpBlack.gif\" /></td><td></td><td align=\"right\"><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl04\',\'\')\">Amount</a></td><td><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl05\',\'\')\">Int</a></td><td><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl06\',\'\')\">Principal</a></td><td align=\"right\"><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl08\',\'\')\">Balance</a></td>':
			break
	
	# Reads in the transaction data at the end of the block

	for line in input_data:
		if line.strip() == '<span id=\"M_content_PCDZ_MJ5DYII_ctl00_lblHtml\"><a href=\"/OnlineBanking/MCSClickService.aspx?ID=173&amp;ContentLocationID=14\"><img border=\"0\" src=\"/OnlineBanking/SqlImageViewerService.aspx?ID=267\" /></a></span>':
			break
		
		# This is the actual parsing code
		L = line.split()
		cutwhitespace = len(L)
		if cutwhitespace > 2:
			f.write(line)

f.close()
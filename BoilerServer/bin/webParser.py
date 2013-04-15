# Given the user name...
User = 'abravoset'

# open a file in the UserTransaction folder with the <username>.txt as the filename
f = open('UserTransactions/' + User + '.txt', 'w')
f.write('Date -> Vender and Location -> Transaction Amount -> Current Accrued Balance\n')
f.write('----------------------------------------------------------------------------\n')

with open('../transInfo.txt') as input_data:
	# Skips text before transaction data
	for line in input_data:
		if line.strip() == '<td align=\"center\"><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl01\',\'\')\">Date</a><img src=\"/OnlineBanking/images/ArrowUpBlack.gif\" /></td><td></td><td align=\"right\"><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl04\',\'\')\">Amount</a></td><td><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl05\',\'\')\">Int</a></td><td><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl06\',\'\')\">Principal</a></td><td align=\"right\"><a href=\"javascript:__doPostBack(\'M$content$PCDZ$M2SN6LC$ctl00$transactionsGrid$ctl01$ctl08\',\'\')\">Balance</a></td>':
			break
	
	# Reads in the transaction data at the end of the block

	for line in input_data:
		if line.strip() == '<span id=\"M_content_PCDZ_MJ5DYII_ctl00_lblHtml\"><a href=\"/OnlineBanking/MCSClickService.aspx?ID=173&amp;ContentLocationID=14\"><img border=\"0\" src=\"/OnlineBanking/SqlImageViewerService.aspx?ID=267\" /></a></span>':
			break
		
		# Gets rid of unecessary table indentation lines
		L = line.split()
		cutwhitespace = len(L)
		if cutwhitespace > 2:

			# ALL HAIL THE GLORY OF PYTHON STRINGS!
			transactionDate = L[1][15:25]
			accruedBalance = L[len(L)-1][14:-5]
			transactionAmount = L[len(L)-2][L[len(L)-2].find("(")+1:L[len(L)-2].find(")")]
			
			# the transaction vender will also contain the location
			# EG: McDonald's M6318(store number) West Lafayette IN

			transactionVender = L[1][34:]
			for x in range(2, len(L)-3):
				transactionVender = transactionVender + '_' + L[x]

			f.write(transactionDate + ' ' + transactionVender + ' ' + transactionAmount + ' ' + accruedBalance + '\n')

f.close()
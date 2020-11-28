## RightCard 
RightCard is an Android app developed to make it easier to keep track of all the benefits your credit cards offer. RightCard provides you with a search option where you can search for a shop and find out which credit card to use in which shop, to receive a discount or credits. 

	
## Technologies
The project is created with:
* Kotlin: 1.4.0
* Gradle: 4.0.1   
 
 
* Firebase
     * Firestore: 21.6.0
     * Firebase Authentication: 19.3.2
     * Firebase Storage: 19.2.0
* [Picasso: 2.71828](https://github.com/square/picasso)
* [Groupie: 2.1.0](https://github.com/lisawray/groupie)
	
## Setup
To run this project, download and install it locally using git clone:

```
$ git clone git@github.com:sveggen/RightCard.git
```

* In order to use the app it is required to create a user on the first screen  when launching the app for the first time. Anonymous users are currently not supported. 


## Current functionality

### Add a new credit card to your collection from the available credit cards:
- Tap on the three-dots in the upper right corner of the toolbar.
- Tap on "Add a Credit Card".
- Tap on the "Add Credit Card"-button on the left side of the credit card you wish to add.

--- 
### Delete a card:
1. Tap on the "Credit Card"-symbol in the toolbar.
2. Double-tap on the credit card and then click on the "Delete"-button on the left side of the credit card when prompted.

---
### Assign a new nickname to the card:
1. Tap on the "Credit Card"-symbol in the toolbar.
2. Tap on the 'nickname'-text underneath the card. 
3. Type in the new nickname.
4. Tap on the "Done"-button in the lower left corner on the keyboard when finished.

---
### Search for a shop:
1. Tap on the "Shop"-symbol on the toolbar.
2. Type in the name of the shop you wish to check if your credit cards provide any benefits/discounts in.
3. The search bar uses autocomplete, so if the name of the shop you type in matches any of the shops in the database, you are able to tap on the autocompleted match. 
4. The page will then load all the credit cards you own that provide any benefits/discounts in this shop.

**NOTE:** The shops that currently are saved in the database are as follows: Rema 1000, Kiwi, Intersport, Lidl, Spar.  
